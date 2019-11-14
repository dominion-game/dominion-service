package edu.cnm.deepdive.dominionservice.model.entity;

import edu.cnm.deepdive.dominionservice.model.dto.GameStateInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.lang.NonNull;

@Entity
public class DrawPile implements Iterable{

  private ArrayList<Card> drawPileCards;

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

  public DrawPile(ArrayList<Card> drawPileCards) {
    this.drawPileCards = drawPileCards;
  }


  public Card getTopCard(GameStateInfo gameStateInfo) {
    if (drawPileCards.isEmpty()) {
      //if drawPile exhausted, get shuffled discardPile and make new drawPile
      this.drawPileCards = gameStateInfo.getCurrentPlayerStateInfo().getDiscardPile().makeNewDrawPile();
    }
    Card topCard = drawPileCards.get(0);
    drawPileCards.remove(0);
    return topCard;
  }
  public void setDrawPileCards(
      ArrayList<Card> drawPileCards) {
    this.drawPileCards = drawPileCards;
  }

  /**
   public static DrawPile newDeck(ArrayList<Card> cards ) {
   return new DrawPile(cards);
   }
   */
  public ArrayList<Card> getDrawPileCards() {
    return drawPileCards;
  }



  public void setDeckCards(
      ArrayList<Card> drawPileCards) {
    this.drawPileCards = drawPileCards;
  }

  @Override
  public Iterator iterator() {
    return null;
  }

  @Override
  public void forEach(Consumer action) {

  }

  @Override
  public Spliterator spliterator() {
    return null;
  }
}