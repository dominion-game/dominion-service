package edu.cnm.deepdive.dominionservice.controller;

import edu.cnm.deepdive.dominionservice.model.dao.PlayRepository;
import edu.cnm.deepdive.dominionservice.model.dao.TurnRepository;
import edu.cnm.deepdive.dominionservice.model.entity.Play;
import edu.cnm.deepdive.dominionservice.model.entity.Turn;
import edu.cnm.deepdive.dominionservice.service.GameService;
import edu.cnm.deepdive.dominionservice.service.PlayerService;
import edu.cnm.deepdive.dominionservice.service.TurnService;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/play")
@ExposesResourceFor(Play.class)
public class PlayController {
  @Autowired
  GameService gameService;

  @Autowired
  PlayerService playerService;

  @Autowired
  HttpSession httpSession;

  @Autowired
  private final PlayRepository playRepository;

  @Autowired
  private final TurnRepository turnRepository;

  @Autowired
  TurnService turnService;

  Logger logger = LoggerFactory.getLogger(PlayController.class);

  //TODO implement play class
  @PostMapping("/play/card/{id}")
  public Turn playCard(){

  }
  @PostMapping()
  public Turn
}
