package edu.cnm.deepdive.dominionservice.service;

import edu.cnm.deepdive.dominionservice.model.dao.CardRepository;
import edu.cnm.deepdive.dominionservice.model.dao.GameRepository;
import edu.cnm.deepdive.dominionservice.model.dto.GameStateInfo;
import edu.cnm.deepdive.dominionservice.model.dto.PlayerStateInfo.PhaseState;
import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Card.CardType;
import edu.cnm.deepdive.dominionservice.model.entity.Game;
import edu.cnm.deepdive.dominionservice.model.entity.Player;
import edu.cnm.deepdive.dominionservice.model.entity.Turn;
import edu.cnm.deepdive.dominionservice.model.enums.States;
import java.util.ArrayList;
import java.util.Arrays;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

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


  public GameStateInfo playCard(int cardId, long gameId, int playerId) {
    GameStateInfo gameStateInfo = new GameStateInfo(gameRepository.getGameById(gameId));
    Card playingCard = new Card(cardRepository.getCardTypeById(cardId));
    playingCard.getCardType().play(, gameStateInfo, playerId);
    if(gameStateInfo.getCurrentPlayerStateInfo().getTurn().getActionsRemaining()==0){
      endActions();
      gameStateInfo.getCurrentPlayerStateInfo().setPhaseState(PhaseState.DOING_BUYS);
    }
    gameStateInfo.saveAll();
    return gameStateInfo;
  }

  public GameStateInfo playerEndsPhase(GameStateInfo currentGameState) {
    switch(currentGameState.getCurrentPlayerStateInfo().getPhaseState()){
      case DISCARDING:
        endDiscarding();
        currentGameState.getCurrentPlayerStateInfo().setPhaseState(PhaseState.TAKING_ACTION);
        break;
      case TAKING_ACTION:
        endActions();
        currentGameState.getCurrentPlayerStateInfo().setPhaseState(PhaseState.DOING_BUYS);
        break;
      case DOING_BUYS:
        endTurn();
        currentGameState.getCurrentPlayerStateInfo().setPhaseState(PhaseState.PASSIVE);
        break;
    }
    gameStateInfo.saveAll();
    return currentGameState;
  }
  void signalMachine(Events event) {
    Message<Events> message = MessageBuilder
        .withPayload(event)
        .setHeader("Event Transition", event.toString())
        .build();
    stateMachine.sendEvent(message);
  }

  public GameStateInfo initGame(Game game){
    GameStateInfo gameStateInfo = new GameStateInfo(game);
    startGame();
    return gameStateInfo;
  }

  public GameStateInfo buyTarget(CardType cardType, int playerId, int gameId){
    GameStateInfo gameStateInfo = new GameStateInfo(gameRepository.getGameById(gameId));
    gameStateInfo.saveAll();
    return gameStateInfo;
  }

  public GameStateInfo discard(Card... cards, GameStateInfo gameStateInfo){
    ArrayList<Card> discardCards = new ArrayList<Card>(Arrays.asList(cards.clone()));
    gameStateInfo.getCurrentPlayerStateInfo().getDiscardPile().addToDiscard(discardCards);
    gameStateInfo.saveAll();
    return gameStateInfo;
  }
  //METHODS

  public void startGame(){
    signalMachine(Events.BEGIN_GAME);
  }

  public void startTurn(Player player){
    if (player.getId() == 1) {
      signalMachine(Events.PLAYER_1_START);
    }else{
      signalMachine(Events.PLAYER_2_START);
    }
  }

  public void endDiscarding(Player player){

  }
  public void endActions(){
    Message message = MessageBuilder.withPayload(Events.END_ACTIONS).build();
    stateMachine
        .sendEvent(Mono.just(message))
        .subscribe();
  }
  public void endBuys(){
    Message message = MessageBuilder.withPayload(Events.END_BUYS).build();
    stateMachine
        .sendEvent(Mono.just(message))
        .subscribe();
  }
  public void endTurn(){
    if (player.getId() == 1) {
      signalMachine(Events.PLAYER_1_END);
    }else{
      signalMachine(Events.PLAYER_2_END);
    }
  }

  public void endGame(){
    signalMachine(Events.GAME_FINISHES);
  }




  public void trashCard(Card c) {
    trash.add(c);
  }
  //Implement state machine

  public Turn createTurn(Game game, Player player, Turn turn) {

  }

  public Turn addBuys(Turn turn, int howMany) {
    turn.setBuysRemaining(howMany + turn.getBuysRemaining());
    return turn;
  }

  public Turn addAction(Turn turn, int howMany) {
    turn.setActionsRemaining(howMany + turn.getActionsRemaining());
    return turn;
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
