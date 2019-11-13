package edu.cnm.deepdive.dominionservice.model.dto;

import edu.cnm.deepdive.dominionservice.model.dao.CardRepository;
import edu.cnm.deepdive.dominionservice.model.dao.LocationRepository;
import edu.cnm.deepdive.dominionservice.model.dao.PlayerRepository;
import edu.cnm.deepdive.dominionservice.model.dao.TurnRepository;
import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Game;
import edu.cnm.deepdive.dominionservice.model.entity.Location.LocationType;
import edu.cnm.deepdive.dominionservice.model.entity.Player;
import edu.cnm.deepdive.dominionservice.model.entity.Turn;
import edu.cnm.deepdive.dominionservice.model.enums.PhaseStates;
import edu.cnm.deepdive.dominionservice.model.pojo.DiscardPile;
import edu.cnm.deepdive.dominionservice.model.pojo.DrawPile;
import edu.cnm.deepdive.dominionservice.model.pojo.Hand;
import java.io.Serializable;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerStateInfo implements Serializable {

  private TurnRepository turnRepository;


      private CardRepository cardRepository;


      private PlayerRepository playerRepository;
  private Turn turn;
  private Player player;
  private Game game;
  private DrawPile drawPile;
  private Hand hand;
  private DiscardPile discardPile;
  private PhaseState phaseState;
  private LocationRepository locationRepository;

  PlayerStateInfo(Game game, Player player) {
    this.game=game;
    this.player=player;
    this.turn = turnRepository.getCurrentTurn();
    //TODO change location ID search to location type enum
    if (player.getId() == 1) {
      this.hand = new Hand(new ArrayList<>(cardRepository.getAllByLocationType(
          LocationType.PLAYER_1_HAND)));
      this.discardPile = new DiscardPile(new ArrayList<>(cardRepository.getAllByLocationType(
          LocationType.PLAYER_1_DISCARD)));
      this.drawPile = new DrawPile(new ArrayList<>(cardRepository.getAllByLocationTypeOrderByLocationIndex(
          LocationType.PLAYER_1_DRAW_PILE)));
    }else if (player.getId() == 2){
      this.hand = new Hand(new ArrayList<>(cardRepository.getAllByLocationType(
          LocationType.PLAYER_2_HAND)));
      this.discardPile = new DiscardPile(new ArrayList<>(cardRepository.getAllByLocationType(
          LocationType.PLAYER_2_DISCARD)));
      this.drawPile = new DrawPile(new ArrayList<>(cardRepository.getAllByLocationTypeOrderByLocationIndex(
          LocationType.PLAYER_2_DRAW_PILE)));
    }



    //get phase state
  }
  public void saveAll(){
    this.turnRepository.save(turn);
    for (Card card: this.hand.getCardsInHand()){
      this.cardRepository.save(card);
    }
    for (Card card: this.drawPile.getDrawPileCards()){
      this.cardRepository.save(card);
    }
    for (Card card: this.discardPile.getDiscardCards()){
      this.cardRepository.save(card);
    }
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

  public int calculateBuyingPower() {
    int buyingPower = 0;

    return buyingPower;
  }

  public enum PhaseState {
    DISCARDING,
    TAKING_ACTION,
    DOING_BUYS,
    PASSIVE;
  }



}
