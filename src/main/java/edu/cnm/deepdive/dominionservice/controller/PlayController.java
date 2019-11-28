package edu.cnm.deepdive.dominionservice.controller;

import edu.cnm.deepdive.dominionservice.model.dto.GameStateInfo;
import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Card.CardType;
import edu.cnm.deepdive.dominionservice.model.entity.Play;
import edu.cnm.deepdive.dominionservice.service.GameLogic;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games/{gameId}/plays")
@ExposesResourceFor(Play.class)
public class PlayController {

  private GameLogic gameLogic;

  Logger logger = LoggerFactory.getLogger(PlayController.class);

  @PostMapping("/{cardid}/action")
  public GameStateInfo playCard(@PathVariable long gameId, int playerId, int cardId,
      @RequestBody ArrayList<Card> cards, Authentication authentication){
    if (cards == null){
      return gameLogic.playCardWithCards(cardId, gameId, playerId, null);
    }else {
      return gameLogic.playCardWithCards(cardId, gameId, playerId, cards);
    }
  }

  @PostMapping("{cardid}/buy")
  public GameStateInfo playerBuysTarget(@PathVariable int gameId, int playerId,CardType cardType, Authentication authentication){
    return gameLogic.buyTarget(cardType, playerId, gameId);
  }

  @PostMapping("/endphase")
  public GameStateInfo playerEndsPhase(@PathVariable int gameId, int playerId, String phaseState, Authentication authentication){
     return gameLogic.playerEndsPhase(gameId, playerId, phaseState);
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
