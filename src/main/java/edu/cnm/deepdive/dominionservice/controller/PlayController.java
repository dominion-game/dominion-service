package edu.cnm.deepdive.dominionservice.controller;

import edu.cnm.deepdive.dominionservice.model.dao.CardRepository;
import edu.cnm.deepdive.dominionservice.model.dao.PlayRepository;
import edu.cnm.deepdive.dominionservice.model.dao.TurnRepository;
import edu.cnm.deepdive.dominionservice.model.dto.GameStateInfo;
import edu.cnm.deepdive.dominionservice.model.entity.Card.CardType;
import edu.cnm.deepdive.dominionservice.model.entity.Game;
import edu.cnm.deepdive.dominionservice.model.entity.Play;
import edu.cnm.deepdive.dominionservice.model.entity.Player;
import edu.cnm.deepdive.dominionservice.model.entity.Turn;
import edu.cnm.deepdive.dominionservice.service.GameLogic;
import edu.cnm.deepdive.dominionservice.service.GameService;
import edu.cnm.deepdive.dominionservice.service.TurnService;
import java.util.NoSuchElementException;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games/{gameId}/play")
@ExposesResourceFor(Play.class)
public class PlayController {

  @Autowired
      GameLogic gameLogic;

  Logger logger = LoggerFactory.getLogger(PlayController.class);

   @PostMapping("/card/play/{id}")
  public GameStateInfo playCard(@PathVariable CardType cardType){
     return gameLogic.playCard(cardType);
  }

  @PostMapping("/buy/{cardtype}")
  public GameStateInfo playerBuysTarget(@PathVariable CardType cardType, int playerId, int gameId){
    return gameLogic.buyTarget(cardType, playerId, gameId);
  }

  @PostMapping("/endphase/{phaseId}")
  public GameStateInfo playerEndsPhase(@PathVariable String phaseState){
     return gameLogic.playerEndsPhase(phaseState);
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
