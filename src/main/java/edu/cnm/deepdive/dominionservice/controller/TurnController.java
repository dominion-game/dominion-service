package edu.cnm.deepdive.dominionservice.controller;

import edu.cnm.deepdive.dominionservice.model.dao.TurnRepository;
import edu.cnm.deepdive.dominionservice.model.entity.Game;
import edu.cnm.deepdive.dominionservice.model.entity.Turn;
import edu.cnm.deepdive.dominionservice.model.entity.Turn.TurnState;
import edu.cnm.deepdive.dominionservice.service.GameService;
import edu.cnm.deepdive.dominionservice.service.PlayerService;
import edu.cnm.deepdive.dominionservice.service.TurnService;
import java.util.NoSuchElementException;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/turn")
@ExposesResourceFor(Turn.class)
public class TurnController {
  @Autowired
  GameService gameService;

  @Autowired
  PlayerService playerService;

  @Autowired
  HttpSession httpSession;

  @Autowired
  private final TurnRepository turnRepository;

  @Autowired
  TurnService turnService;

  Logger logger = LoggerFactory.getLogger(TurnController.class);


  @Autowired
  public TurnController(TurnRepository turnRepository) {
    this.turnRepository = turnRepository;
  }

  @PutMapping("/turn/endphase/{key}")
  public TurnState endTurnPhase(@PathVariable TurnState endingState){
    //TODO end the current turn state, send message to state machine, and move to next state
  }

  @PutMapping("/turn/endturn")
  public void endTurn(){
    //TODO end the current turn state, send message to state machine, and move to next state
  }


  @GetMapping("/turn/{id}")
  public Turn getCurrentTurn(@PathVariable long turnId) {
    Turn currentTurn = turnRepository.findTurnById(turnId);
    return currentTurn;
  }

  @GetMapping("/turn/{id}/state")
  public TurnState getCurrentTurnState(@PathVariable long turnId) {
    Turn currentTurn = turnRepository.findTurnById(turnId);
    return currentTurn.getTurnState();
  }


  @PostMapping("/turn/new")
  public ResponseEntity<Turn> startTurn(@RequestBody Turn newTurn) {
    Long gameId = (Long) httpSession.getAttribute("gameId");
    logger.info("move to insert:" + createMoveDTO.getBoardColumn() + createMoveDTO.getBoardRow());
    Turn turn = turnService.createTurn(gameService.getGame(gameId), playerService.getLoggedUser(), createMoveDTO);
    Game game = gameService.getGameById(gameId);
    gameService.updateGameStatus(gameService.getGame(gameId), moveService.checkCurrentGameStatus(game));
    return move;
    //TODO: creates a new turn when player starts
    //TODO: notify state machine to change state
    turnRepository.save(newTurn);
    turn.setTurnState(TurnState.ACTING);
  }

  @GetMapping(//TODO ADD PARAMETERS)
      public boolean isMyTurn(Player player, long gameId, )

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

