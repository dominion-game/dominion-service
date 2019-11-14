package edu.cnm.deepdive.dominionservice.model.entity;

import edu.cnm.deepdive.dominionservice.model.entity.Card;
import java.util.ArrayList;
import java.util.Collections;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Entity
public class DiscardPile {

  private ArrayList<Card> discardCards;

  public DiscardPile(ArrayList<Card> cards) {
    this.discardCards = cards;
  }


  //takes the discard pile, shuffles it, and returns
  public ArrayList<Card> makeNewDrawPile() {
    Collections.shuffle(discardCards);
    return discardCards;
  }
  @Id
  @GeneratedValue
  private int id;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name="player_id", nullable = false, updatable = false)
  private Player player;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name="game_id", nullable = false, updatable = false)
  private Game game;

  public void addToDiscard(Card card){
    discardCards.add(card);
  }

  public void addToDiscard(ArrayList<Card> cards){
    discardCards.addAll(cards);
  }

  public void discardCard(Card c) {
    discardCards.add(c);
  }



  public ArrayList<Card> getDiscardCards() {
    return discardCards;
  }

  public void setDiscardCards(
      ArrayList<Card> discardCards) {
    this.discardCards = discardCards;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @NonNull
  public Player getPlayer() {
    return player;
  }

  public void setPlayer(@NonNull Player player) {
    this.player = player;
  }

  @NonNull
  public Game getGame() {
    return game;
  }

  public void setGame(@NonNull Game game) {
    this.game = game;
  }
}