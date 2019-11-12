package edu.cnm.deepdive.dominionservice.model.pojo;

import edu.cnm.deepdive.dominionservice.model.entity.Card;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class DiscardPile {

  private ArrayList<Card> discardCards;

  public DiscardPile(ArrayList<Card> cards) {
    this.discardCards = cards;
  }

  //this is an overload of the constructor, to use to create a new deck when we are combining the discards
  //into it. Used in shuffle method.
  public DiscardPile(ArrayList<Card> deckCards, ArrayList<Card> otherCards) {

  }
  public void addToDiscard(ArrayList<Card> cards){
    discardCards.addAll(cards);
  }

  public void discardCard(Card c) {
    discard.add(c);
  }



  public ArrayList<Card> getDiscardCards() {
    return discardCards;
  }
}
