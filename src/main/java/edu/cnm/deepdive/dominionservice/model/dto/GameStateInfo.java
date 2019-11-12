package edu.cnm.deepdive.dominionservice.model.dto;

import edu.cnm.deepdive.dominionservice.controller.state.StateMachineConfig;
import edu.cnm.deepdive.dominionservice.model.dao.PlayerRepository;
import edu.cnm.deepdive.dominionservice.model.dao.StackRepository;
import edu.cnm.deepdive.dominionservice.model.dao.TurnRepository;
import edu.cnm.deepdive.dominionservice.model.entity.Game;
import edu.cnm.deepdive.dominionservice.model.entity.Player;
import edu.cnm.deepdive.dominionservice.model.entity.Stack;
import edu.cnm.deepdive.dominionservice.model.entity.Stack.StackType;
import edu.cnm.deepdive.dominionservice.model.entity.Turn;
import edu.cnm.deepdive.dominionservice.model.enums.StackTypes;
import edu.cnm.deepdive.dominionservice.model.enums.States;
import edu.cnm.deepdive.dominionservice.model.pojo.Hand;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;

public class GameStateInfo implements Serializable {

  private PlayerStateInfo playerStateInfoPlayer1;
  private PlayerStateInfo playerStateInfoPlayer2;
  private StackRepository stackRepository;
  private TurnRepository turnRepository;
  private PlayerRepository playerRepository;
  private EnumMap<StackType, Integer> stackMap;
  private Game game;
  private List<Turn> previousTurns;
  private long currentPlayerId;
  private StateMachine stateMachine;



  public GameStateInfo(Game game) {
    this.game=game;
    Player player1 = game.getPlayers().get(1);
    Player player2= game.getPlayers().get(2);
    //TODO fix this
    stackMap = new EnumMap<StackType, Integer>(StackTypes);
    playerStateInfoPlayer1 = new PlayerStateInfo(game, player1);
    playerStateInfoPlayer2 = new PlayerStateInfo(game, player2);
    previousTurns = (List<Turn>) turnRepository.getAllByOrderByKeyAsc();
    //go out to state machine and get who is playing
    State state = stateMachine.getState();
    if (States
        .PLAYER_1_TURN.equals(state)) {
      currentPlayerId = 1;
    } else if (States.PLAYER_2_TURN.equals(state)) {
      currentPlayerId = 2;
    }
  }
  public PlayerStateInfo getCurrentPlayerStateInfo(){
    switch((int) currentPlayerId) {
      case 1:
        return playerStateInfoPlayer1;
      case 2:
        return playerStateInfoPlayer2;
    }
    return null;
  }
  public PlayerStateInfo getPlayerStateInfoPlayer1() {
    return playerStateInfoPlayer1;
  }

  public void setPlayerStateInfoPlayer1(
      PlayerStateInfo playerStateInfoPlayer1) {
    this.playerStateInfoPlayer1 = playerStateInfoPlayer1;
  }

  public PlayerStateInfo getPlayerStateInfoPlayer2() {
    return playerStateInfoPlayer2;
  }

  public void setPlayerStateInfoPlayer2(
      PlayerStateInfo playerStateInfoPlayer2) {
    this.playerStateInfoPlayer2 = playerStateInfoPlayer2;
  }

  public EnumMap<StackType, Integer> getStackMap() {
    return stackMap;
  }

  public void setStackMap(
      EnumMap<StackType, Integer> stackMap) {
    this.stackMap = stackMap;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public List<Turn> getPreviousTurns() {
    return previousTurns;
  }

  public void setPreviousTurns(
      List<Turn> previousTurns) {
    this.previousTurns = previousTurns;
  }
  public Player getCurrentPlayer() {
    return playerRepository.findPlayerById(currentPlayerId);
  }

  public void setCurrentPlayer(long playerId) {
    this.currentPlayerId = playerId;
  }

  public void saveAll() {
    //call repository save methods
  }
   public void currentPlayerDraws(){
     getCurrentPlayer().drawCard();
  }

}
