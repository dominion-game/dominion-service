package edu.cnm.deepdive.dominionservice.service;

import edu.cnm.deepdive.dominionservice.model.entity.Game;
import edu.cnm.deepdive.dominionservice.model.entity.Player;
import edu.cnm.deepdive.dominionservice.model.states.Events;
import edu.cnm.deepdive.dominionservice.model.states.States;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.WithStateMachine;

@WithStateMachine
public class GameLogic {
  private Game game;
  @Autowired
  private StateMachine<States, Events> stateMachine;

  private String gameStatus = "Not Currently Playing";


  //METHODS

  public void startGame(){
    stateMachine
        .sendEvent().subscribe();
  }
  public void startTurn(){}
  public void endActions(){}
  public void endBuys(){}
  public void endDiscard(){}
  public void endDraw(){}
  public void militiaAttack(){}
  public void endMilitiaTurn(){}
  public void endGame(){}


  public static Player declareWinner(Game thisGame){
    List<Player> players = thisGame.getPlayers();
    Player winner;
    for(Player player:players){
      //TODO implement test of all players in Game table to see who has the most victory points
    }
    return winner;
  }
  //Implement state machine
}
