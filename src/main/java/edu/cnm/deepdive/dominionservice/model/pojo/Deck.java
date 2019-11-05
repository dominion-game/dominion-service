package edu.cnm.deepdive.dominionservice.model.pojo;

import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Location;
import edu.cnm.deepdive.dominionservice.model.entity.Player;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class Deck {
  private Location location;
  private ArrayList<Card> deckCards;

  public Deck(ArrayList<Card> cards, Location location) {
    this.deckCards = cards;
    this.location = location;
  }

  //this is an overload of the constructor, to use to create a new deck when we are combining the discards
  //into it. Used in shuffle method.
  public Deck(ArrayList<Card> deckCards, ArrayList<Card> otherCards) {

  }

  public static Deck newDeck(ArrayList<Card> cards) {
    return new Deck(cards);
  }

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
