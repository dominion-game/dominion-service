package edu.cnm.deepdive.dominionservice.model.pojo;

import edu.cnm.deepdive.dominionservice.model.entity.Card;
import java.util.ArrayList;

public class Deck {

  private ArrayList<Card> deckCards;

  public Deck(ArrayList<Card> cards) {
    this.deckCards = cards;
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
}
