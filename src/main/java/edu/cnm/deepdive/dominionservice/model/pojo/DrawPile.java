package edu.cnm.deepdive.dominionservice.model.pojo;

import edu.cnm.deepdive.dominionservice.model.dto.GameStateInfo;
import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Location;
import edu.cnm.deepdive.dominionservice.model.entity.Location.LocationType;
import edu.cnm.deepdive.dominionservice.model.entity.Player;
import java.util.ArrayList;
import java.util.Collections;
import org.springframework.stereotype.Component;


public class DrawPile {
  private Location location;
  private ArrayList<Card> drawPileCards;


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

  /**
   public static DrawPile newDeck(ArrayList<Card> cards ) {
   return new DrawPile(cards);
   }
   */
  public ArrayList<Card> getDrawPileCards() {
    return drawPileCards;
  }

  public long getLocationId() {
    return location.getId();
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public void setDeckCards(
      ArrayList<Card> drawPileCards) {
    this.drawPileCards = drawPileCards;
  }
}