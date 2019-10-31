package edu.cnm.deepdive.dominionservice.controller;

import edu.cnm.deepdive.dicewareservice.model.dao.PassphraseRepository;
import edu.cnm.deepdive.dicewareservice.model.entity.Passphrase;
import edu.cnm.deepdive.dicewareservice.model.entity.Word;
import edu.cnm.deepdive.dicewareservice.service.PassphraseGenerator;
import edu.cnm.deepdive.dominionservice.model.entity.Turn;
import edu.cnm.deepdive.dominionservice.service.TurnGenerator;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfig;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigBuilder;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineModelConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.config.common.annotation.AnnotationBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/turn")
@ExposesResourceFor(Turn.class)
public class TurnController {

  private final TurnGenerator generator;
  private final TurnRepository turnRepository;

  @Autowired
  public TurnController(TurnGenerator generator,
      TurnRepository turnRepository) {
    this.generator = generator;
    this.turnRepository = turnRepository;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Turn getTurnStatus(){
    return turnRepository.getCurrentTurnStatus();
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces =MediaType.APPLICATION_JSON_VALUE)
  public Turn takeAction(){
    //TODO update current turn with action taken.
    return turnRepository.getCurrentTurnStatus();
  }

  @PostMapping()
  public Turn startTurn(){
    //TODO: creates a new turn when player starts
    return turnRepository.getCurrentTurnStatus();
  }


  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Turn> getAll() {
    return turnRepository.getAllByOrderByKeyAsc();
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {}

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(Exception.class)
  public void badRequest() {}

}

