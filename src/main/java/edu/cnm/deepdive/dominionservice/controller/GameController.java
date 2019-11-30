package edu.cnm.deepdive.dominionservice.controller;

import edu.cnm.deepdive.dominionservice.model.dao.GameRepository;
import edu.cnm.deepdive.dominionservice.model.dto.GameStateInfo;
import edu.cnm.deepdive.dominionservice.model.entity.Game;
import edu.cnm.deepdive.dominionservice.model.enums.States;
import edu.cnm.deepdive.dominionservice.service.GameLogic.Events;
import edu.cnm.deepdive.dominionservice.service.state.DefaultStateMachineAdapter;
import java.util.NoSuchElementException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.security.core.Authentication;
import org.springframework.statemachine.StateMachine;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
@ExposesResourceFor(Game.class)
public class GameController {

  private final GameRepository gameRepository;
 // DefaultStateMachineAdapter<States, Events, ContextEntity<States, Events, ? extends Serializable>> gameStateMachineAdapter;
  private StateMachine<States, Events> stateMachine;

  @Autowired
  public GameController(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }


  DefaultStateMachineAdapter<States, Events, Game> gameStateMachineAdapter;

  @RequestMapping(path = "/orders/{id}/receive/{event}", method = RequestMethod.POST)
  @SneakyThrows
  @Transactional
  public HttpEntity<Void> receiveEvent(@PathVariable("id") Game game, @PathVariable("event") Events event) {
    StateMachine<States, Events> stateMachine = gameStateMachineAdapter.restore(game);
    if (stateMachine.sendEvent(event)) {
      gameStateMachineAdapter.persist(stateMachine, game);
      return ResponseEntity.accepted().build();
    } else {
      return ResponseEntity.unprocessableEntity().build();
    }
  }

  //TODO: Consider replacing with Firebase
  @PostMapping(value = "/create")
  public String createNewGame() {
   // StateMachine<States, Events> stateMachine = gameStateMachineAdapter.restore(order);
    if (States.INITIAL.equals(stateMachine.getState())) {
      //TODO create new game
      signalMachine(Events.ONE_PLAYER_JOINS);
    }else if (States.WAITING.equals(stateMachine.getState())){
      signalMachine(Events.PLAYER_TWO_JOINS);
    }
   // GameStateInfo gameStateInfo = new GameStateInfo(newGame);
    return stateMachine.getState().toString();
  }
  @GetMapping(value="/getstate")
  public String getState() {

    return stateMachine.getState().toString();
  }
  @PostMapping(value="/onejoins")
  public String onePlayerJoins() {
    signalMachine(Events.ONE_PLAYER_JOINS);
    return stateMachine.getState().toString();
  }
  @PostMapping(value="/twojoins")
  public String twoPlayerJoins() {
    signalMachine(Events.PLAYER_TWO_JOINS);
    return stateMachine.getState().toString();
  }

  void signalMachine(Events event) {
    Message<Events> message = MessageBuilder
        .withPayload(event)
        .setHeader("Event Transition", event.toString())
        .build();
    stateMachine.sendEvent(message);
  }

  @GetMapping(value = "/{id")
  public Game getGameinfo(@PathVariable("id") long id){
    return gameRepository.getGameById(id);
  }

  @GetMapping("{gameid}/state")
  public GameStateInfo getCurrentTurnState(@PathVariable("gameid") long gameId) {
    //TODO add filter so GameStateInfo does not return all the info on the contents of other persons hand, either deck
    GameStateInfo gameStateInfo = new GameStateInfo(gameRepository.getGameById(gameId));
    return gameStateInfo;
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(Exception.class)
  public void badRequest() {
  }

}
