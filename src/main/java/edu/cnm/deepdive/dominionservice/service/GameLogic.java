package edu.cnm.deepdive.dominionservice.service;

import edu.cnm.deepdive.dominionservice.model.dao.CardRepository;
import edu.cnm.deepdive.dominionservice.model.dao.GameRepository;
import edu.cnm.deepdive.dominionservice.model.dao.LocationRepository;
import edu.cnm.deepdive.dominionservice.model.dao.PlayRepository;
import edu.cnm.deepdive.dominionservice.model.dao.PlayerRepository;
import edu.cnm.deepdive.dominionservice.model.dao.StackRepository;
import edu.cnm.deepdive.dominionservice.model.dao.TurnRepository;
import edu.cnm.deepdive.dominionservice.model.dto.GameStateInfo;
import edu.cnm.deepdive.dominionservice.model.dto.PlayerStateInfo.PhaseState;
import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Card.CardType;
import edu.cnm.deepdive.dominionservice.model.entity.Game;
import edu.cnm.deepdive.dominionservice.model.entity.Location;
import edu.cnm.deepdive.dominionservice.model.entity.Location.LocationType;
import edu.cnm.deepdive.dominionservice.model.entity.Play;
import edu.cnm.deepdive.dominionservice.model.entity.Player;
import edu.cnm.deepdive.dominionservice.model.entity.Player.PlayerState;
import edu.cnm.deepdive.dominionservice.model.entity.Stack;
import edu.cnm.deepdive.dominionservice.model.entity.Turn;
import edu.cnm.deepdive.dominionservice.model.enums.States;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
@Transactional
@WithStateMachine
public class GameLogic {

  private Game game;

  private StateMachine<States, Events> stateMachine;

  @Autowired
  GameRepository gameRepository;

  @Autowired
  CardRepository cardRepository;

  @Autowired
  TurnRepository turnRepository;

  @Autowired
  PlayRepository playRepository;

  @Autowired
  PlayerRepository playerRepository;

  @Autowired
  LocationRepository locationRepository;

  @Autowired
  StackRepository stackRepository;



  public GameStateInfo playCard(int cardId, long gameId, int playerId) {
    GameStateInfo gameStateInfo = new GameStateInfo(gameRepository.getGameById(gameId));
    Card playingCard = new Card(cardRepository.getCardTypeById(cardId));
    playingCard.getCardType().play(gameStateInfo);
    if(gameStateInfo.getCurrentPlayerStateInfo().getTurn().getActionsRemaining()==0){
      endActions(gameStateInfo);
      gameStateInfo.getCurrentPlayerStateInfo().setPhaseState(PhaseState.DOING_BUYS);
    }
    gameStateInfo.saveAll();
    return gameStateInfo;
  }
  public GameStateInfo playCardWithCards(int cardId, long gameId, int playerId, ArrayList<Card> cards) {
    GameStateInfo gameStateInfo = new GameStateInfo(gameRepository.getGameById(gameId));
    Card playingCard = new Card(cardRepository.getCardTypeById(cardId));
    playingCard.getCardType().play(gameStateInfo, cards);
    if(gameStateInfo.getCurrentPlayerStateInfo().getTurn().getActionsRemaining()==0){
      endActions(gameStateInfo);
      gameStateInfo.getCurrentPlayerStateInfo().setPhaseState(PhaseState.DOING_BUYS);
    }
    gameStateInfo.saveAll();
    return gameStateInfo;
  }

  public GameStateInfo playerEndsPhase(int gameId, int playerId, String phaseState) {
    GameStateInfo currentGameState = new GameStateInfo(gameRepository.getGameById(gameId));
    switch(currentGameState.getCurrentPlayerStateInfo().getPhaseState()){
      case DISCARDING:
        endDiscarding(currentGameState);
        currentGameState.getCurrentPlayerStateInfo().setPhaseState(PhaseState.TAKING_ACTION);
        break;
      case TAKING_ACTION:
        endActions(currentGameState);
        currentGameState.getCurrentPlayerStateInfo().setPhaseState(PhaseState.DOING_BUYS);
        break;
      case DOING_BUYS:
        endTurn(gameRepository.getGameById(gameId), currentGameState.getCurrentPlayer());
        currentGameState.getCurrentPlayerStateInfo().setPhaseState(PhaseState.PASSIVE);
        break;
    }
    currentGameState.saveAll();
    return currentGameState;
  }
  void signalMachine(Events event) {
    Message<Events> message = MessageBuilder
        .withPayload(event)
        .setHeader("Event Transition", event.toString())
        .build();
    stateMachine.sendEvent(message);
  }

  public void initGame(){
    this.game= new Game();
    gameRepository.save(game);
    //TODO modify to bring in lobby, Oauth credentials, etc
    playerRepository.save(new Player());
    signalMachine(Events.BEGIN_GAME);
    //get all location types and initialize database
    //for (LocationType locationType : LocationType){
    ArrayList<Stack> allStacks = new ArrayList(stackRepository.getAllByStackType());
    for (Stack stack : allStacks){
      stack.setStackCount(stack.getStackType().getInitialCardAmounts());
      stackRepository.save(stack);
    }

  }

  public GameStateInfo buyTarget(CardType cardType, int playerId, int gameId){
    GameStateInfo gameStateInfo = new GameStateInfo(gameRepository.getGameById(gameId));
    Card buyCard = new Card(cardType);
    int buyingPower = gameStateInfo.getCurrentPlayerStateInfo().getTurn().getBuyingPower() - buyCard.getCost();
    if (buyingPower < 0){
      endTurn(gameRepository.getGameById(gameId), playerRepository.getPlayerById(playerId));
    }else{
      gameStateInfo.getCurrentPlayerStateInfo().getDiscardPile().addToDiscard(buyCard);
      switch(cardType){
        case PROVINCE:
        case DUCHY:
        case ESTATE:
          testForVictory(gameStateInfo);
          break;
        default:
          break;
      }
      int buysRemaining = gameStateInfo.getCurrentPlayerStateInfo().getTurn().getBuysRemaining()-1;
      if(buysRemaining <=0){
        gameStateInfo.getCurrentPlayerStateInfo().getTurn().setBuysRemaining(buysRemaining);
        gameStateInfo.saveAll();
        endTurn(this.game, gameStateInfo.getCurrentPlayer());
        return gameStateInfo;
      }else {
        gameStateInfo.getCurrentPlayerStateInfo().getTurn().setBuysRemaining(buysRemaining);
        gameStateInfo.saveAll();
        return gameStateInfo;
      }
    }
    return gameStateInfo;
  }
  public void testForVictory(){
    GameStateInfo currentGameState = new GameStateInfo(this.game);
    testForVictory(currentGameState);
  }

  private void testForVictory(GameStateInfo gameStateInfo) {
    List<Stack> currentStacks = gameStateInfo.getStacks();
   if (currentStacks.get(5).getStackCount()==0){
     endGame();
   }
    int zeroCounter = 0;
    for (Stack currentStack : currentStacks) {
      if (currentStack.getStackCount() == 0) {
        zeroCounter++;
      }
    }
    if (zeroCounter >=3){
      endGame();
    }
  }

  public GameStateInfo discard(GameStateInfo gameStateInfo, Card... cards) {
    ArrayList discardCards = new ArrayList(Arrays.asList(cards.clone()));
    gameStateInfo.getCurrentPlayerStateInfo().getDiscardPile().addToDiscard(discardCards);
    gameStateInfo.saveAll();
    return gameStateInfo;
  }


  public void startTurn(Player player){
    if (player.getId() == 1) {
      initTurn(player);
      signalMachine(Events.PLAYER_1_START);
    }else{
      initTurn(player);
      signalMachine(Events.PLAYER_2_START);
    }
  }

  public void initTurn(Player player){
    Turn thisTurn = new Turn(this.game, player);
    turnRepository.save(thisTurn);
    GameStateInfo gameStateInfo = new GameStateInfo(game);
    if(gameStateInfo.getPreviousTurns().get(thisTurn.getId()-1).isDidAttack()){
      gameStateInfo.getCurrentPlayer().setPlayerState(PlayerState.MILITIA_RESPONSE);
    }else {
      gameStateInfo.getCurrentPlayer().setPlayerState(PlayerState.ACTION);
    }
    gameStateInfo.saveAll();
  }
  public void endDiscarding(GameStateInfo gameStateInfo){
    gameStateInfo.getCurrentPlayer().setPlayerState(PlayerState.ACTION);
    gameStateInfo.saveAll();
  }
  public void endActions(GameStateInfo gameStateInfo){
    gameStateInfo.getCurrentPlayer().setPlayerState(PlayerState.BUYING);
    gameStateInfo.getCurrentPlayerStateInfo().calculateBuyingPower();
    gameStateInfo.saveAll();
  }

  public void endTurn(Game game, Player player){
    GameStateInfo gameStateInfo = new GameStateInfo(game);
    gameStateInfo.getCurrentPlayer().setPlayerState(PlayerState.WATCHING);
    if (gameStateInfo.getCurrentPlayer().getId() == 1) {
      signalMachine(Events.PLAYER_1_END);
    }else{
      signalMachine(Events.PLAYER_2_END);
    }
    gameStateInfo.saveAll();
    //TODO update other player
  }
  public List<Play> updateOtherPlayer(){
    return playRepository.getAllByTurn(turnRepository.getCurrentTurn());
  }

  public void endGame(){
    signalMachine(Events.GAME_FINISHES);
  }


  public enum Events {
    GAME_INTIALIZED,
    GAME_STARTS,
    GAME_FINISHES,
    RETURN_TO_LOBBY,
    END_ACTIONS,
    END_BUYS,
    END_TURN,
    BEGIN_TURN,
    BEGIN_GAME,
    PLAYER_1_START,
    PLAYER_1_END,
    PLAYER_2_START,
    PLAYER_2_END;
  }
}
