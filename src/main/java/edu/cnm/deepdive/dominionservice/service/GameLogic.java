package edu.cnm.deepdive.dominionservice.service;

import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Game;
import edu.cnm.deepdive.dominionservice.model.entity.Player;
import edu.cnm.deepdive.dominionservice.model.states.Events;
import edu.cnm.deepdive.dominionservice.model.states.States;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.WithStateMachine;
import reactor.core.publisher.Mono;

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
  public void startTurn(Player player){
    Message message = MessageBuilder.withPayload(Events.BEGIN_TURN).build();
    stateMachine
        .sendEvent(Mono.just(message))
        .subscribe();
  }
  public void endActions(){}
  public void endBuys(){}
  public void endTurn(){}
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

  public void trashCard(Card c) {
    trash.add(c);
  }
  //Implement state machine
}
