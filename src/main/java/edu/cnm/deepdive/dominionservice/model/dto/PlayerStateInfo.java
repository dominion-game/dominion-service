package edu.cnm.deepdive.dominionservice.model.dto;

import edu.cnm.deepdive.dominionservice.model.dao.CardRepository;
import edu.cnm.deepdive.dominionservice.model.dao.TurnRepository;
import edu.cnm.deepdive.dominionservice.model.entity.Game;
import edu.cnm.deepdive.dominionservice.model.entity.Player;
import edu.cnm.deepdive.dominionservice.model.entity.Turn;
import edu.cnm.deepdive.dominionservice.model.enums.PhaseStates;
import edu.cnm.deepdive.dominionservice.model.pojo.DiscardPile;
import edu.cnm.deepdive.dominionservice.model.pojo.DrawPile;
import edu.cnm.deepdive.dominionservice.model.pojo.Hand;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerStateInfo {

  @Autowired
  private TurnRepository turnRepository;

  @Autowired
  private CardRepository cardRepository;

  Turn turn;
  Player player;
  Game game;
  DrawPile drawPile;
  Hand hand;
  DiscardPile discardPile;
  PhaseState phaseState;

  public PlayerStateInfo(Game game, Player player) {
    this.game = game;
    this.player = player;
    this.turn = turnRepository.getCurrentTurn();
    //TODO change location ID search to location type enum
    this.hand = new Hand(new ArrayList<>(cardRepository.getAllByLocationId());
    this.discardPile = new DiscardPile(new ArrayList<>(cardRepository.getAllByLocationId()));
    this.drawPile = new DrawPile(new ArrayList<>(cardRepository.getAllByLocationId())));
    //get phase state
  }

  public PhaseState getPhaseState() {
    return phaseState;
  }

  public void setPhaseState(
      PhaseState phaseState) {
    this.phaseState = phaseState;
  }

  public Turn getTurn() {
    return turn;
  }

  public void setTurn(Turn turn) {
    this.turn = turn;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public DrawPile getDrawPile() {
    return drawPile;
  }

  public void setDrawPile(DrawPile drawPile) {
    this.drawPile = drawPile;
  }

  public Hand getHand() {
    return hand;
  }

  public void setHand(Hand hand) {
    this.hand = hand;
  }

  public DiscardPile getDiscardPile() {
    return discardPile;
  }

  public void setDiscardPile(DiscardPile discardPile) {
    this.discardPile = discardPile;
  }

  public enum PhaseState {
    DISCARDING,
    TAKING_ACTION,
    DOING_BUYS,
    PASSIVE;
  }

}
