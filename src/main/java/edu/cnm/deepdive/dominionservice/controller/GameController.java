package edu.cnm.deepdive.dominionservice.controller;

import edu.cnm.deepdive.dominionservice.model.dao.GameRepository;
import edu.cnm.deepdive.dominionservice.model.entity.Game;
import edu.cnm.deepdive.dominionservice.model.entity.Turn;
import edu.cnm.deepdive.dominionservice.service.GameService;
import edu.cnm.deepdive.dominionservice.service.PlayerService;
import java.util.NoSuchElementException;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
@ExposesResourceFor(Game.class)
public class GameController {

  private final GameRepository gameRepository;

  @Autowired
  public GameController(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }
  @Autowired
  GameService gameService;

  @Autowired
  PlayerService playerService;

  @Autowired
  HttpSession httpSession;

  Logger logger = LoggerFactory.getLogger(GameController.class);

  //TODO: Consider replacing with Firebase
  @PostMapping(value = "/create")
  public Game createNewGame(@RequestBody Game newGame) {
    Game game = gameService.createNewGame(playerService.getUser(), gameRepository);
    httpSession.setAttribute("gameId", game.getId());
    logger.info("new game id# " + httpSession.getAttribute("gameId")+" stored in session");
    return game;
  }


  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Game getWinner() {
    //Return finalized game. Calculate winner. Return victory data.
  }

  @GetMapping(value = "/{id")
  public Game getGameinfo(@PathVariable long id){
    return gameRepository.getGameById(id);
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
