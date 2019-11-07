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
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
@Component
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
    Message message = MessageBuilder.withPayload(Events.END_TURN).build();
    stateMachine
        .sendEvent(Mono.just(message))
        .subscribe();
  }
  public void militiaAttack(){
    Message message = MessageBuilder.withPayload(Events.MILITIA_ATTACK).build();
    stateMachine
        .sendEvent(Mono.just(message))
        .subscribe();
  }
  public void endMilitiaTurn(){
    Message message = MessageBuilder.withPayload(Events.END_MILITIA).build();
    stateMachine
        .sendEvent(Mono.just(message))
        .subscribe();
  }
  public void endGame(){
    Message message = MessageBuilder.withPayload(Events.BEGIN_TURN).build();
    stateMachine
        .sendEvent(Mono.just(message))
        .subscribe();
  }


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
