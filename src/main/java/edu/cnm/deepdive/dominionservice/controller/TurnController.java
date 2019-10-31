package edu.cnm.deepdive.dominionservice.controller;

import edu.cnm.deepdive.dominionservice.model.entity.Turn;
import edu.cnm.deepdive.dominionservice.service.TurnGenerator;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

