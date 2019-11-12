package edu.cnm.deepdive.dominionservice.model.pojo;

import edu.cnm.deepdive.dominionservice.model.dto.GameStateInfo;
import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Location;
import edu.cnm.deepdive.dominionservice.model.entity.Location.LocationType;
import edu.cnm.deepdive.dominionservice.model.entity.Player;
import java.util.ArrayList;
import java.util.Collections;
import org.springframework.stereotype.Component;

@Component
public class DrawPile {
  private Location location;
  private ArrayList<Card> drawPileCards;


  public DrawPile(ArrayList<Card> drawPileCards, Location location) {
    this.drawPileCards = drawPileCards;
    this.location = location;
  }

  //this is an overload of the constructor, to use to create a new deck when we are combining the discards
  //into it. Used in shuffle method.
  public DrawPile(ArrayList<Card> drawPileCards, ArrayList<Card> discardPile) {
    this.drawPileCards = shuffleDeckAndDiscard(discardPile, drawPileCards);
  }

  private ArrayList<Card> shuffleDeckAndDiscard(ArrayList<Card> discardPile, ArrayList<Card> drawPileCards) {
    ArrayList<Card> combinedDeckList = new ArrayList<>();
    combinedDeckList.addAll(drawPileCards);
    combinedDeckList.addAll(discardPile);
    Collections.shuffle(combinedDeckList);
    return combinedDeckList;
  }

  public Card getTopCard(GameStateInfo gameStateInfo) {
    if (drawPileCards.isEmpty()) {
      ArrayList<Card> discardPile = gameStateInfo.getPlayerStateInfoPlayer1().getDiscardPile().getDiscardCards();
      this.drawPileCards = shuffleDeckAndDiscard(discardPile, drawPileCards);
    }
    return drawPileCards.get(0);
  }

  /**

  public static DrawPile newDeck(ArrayList<Card> cards ) {
    return new DrawPile(cards);
  }
*/
  public ArrayList<Card> getDeckCards() {
    return deckCards;
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
      ArrayList<Card> deckCards) {
    this.deckCards = deckCards;
  }
}
