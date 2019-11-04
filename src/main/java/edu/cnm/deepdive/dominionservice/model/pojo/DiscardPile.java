package edu.cnm.deepdive.dominionservice.model.pojo;

import edu.cnm.deepdive.dominionservice.model.entity.Card;
import java.util.ArrayList;

public class DiscardPile {

  private ArrayList<Card> discardCards;

  public DiscardPile(ArrayList<Card> cards) {
    this.discardCards = cards;
  }

  //this is an overload of the constructor, to use to create a new deck when we are combining the discards
  //into it. Used in shuffle method.
  public DiscardPile(ArrayList<Card> deckCards, ArrayList<Card> otherCards) {

  }


  public ArrayList<Card> getDiscardCards() {
    return discardCards;
  }
}
