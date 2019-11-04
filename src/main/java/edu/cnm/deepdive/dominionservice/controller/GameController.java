package edu.cnm.deepdive.dominionservice.controller;

import edu.cnm.deepdive.dominionservice.model.entity.Game;
import edu.cnm.deepdive.dominionservice.model.entity.Turn;
import edu.cnm.deepdive.dominionservice.service.GameGenerator;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
@ExposesResourceFor(Game.class)
public class GameController {

  private final GameGenerator generator;
  private final GameRepository gameRepository;

  @Autowired
  public GameController(GameGenerator generator,
      GameRepository gameRepository) {
    this.generator = generator;
    this.gameRepository = gameRepository;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Game newGame() {
    return gameRespository.getGame();
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Game getWinner() {
    //Return finalized game. Calculate winner. Return victory data.
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Turn> getAll() {
    return turnRepository.getAllByOrderByKeyAsc();
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
