package edu.cnm.deepdive.dominionservice.controller;

import edu.cnm.deepdive.dominionservice.model.dao.GameRepository;
import edu.cnm.deepdive.dominionservice.model.dto.GameStateInfo;
import edu.cnm.deepdive.dominionservice.model.entity.Game;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
@ExposesResourceFor(Game.class)
public class GameController {

  private final GameRepository gameRepository;

  @Autowired
  public GameController(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }




  //TODO: Consider replacing with Firebase
  @PostMapping(value = "/create")
  public GameStateInfo createNewGame(@RequestBody Game newGame) {
   //TODO
    GameStateInfo gameStateInfo = new GameStateInfo(game);
    return gameStateInfo;
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
