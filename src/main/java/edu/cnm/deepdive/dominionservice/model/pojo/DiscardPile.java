package edu.cnm.deepdive.dominionservice.model.pojo;

import edu.cnm.deepdive.dominionservice.model.entity.Card;
import java.util.ArrayList;
import java.util.Collections;
import org.springframework.stereotype.Component;

//@Component
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



  public void addToDiscard(ArrayList<Card> cards){
    discardCards.addAll(cards);
  }

  public void discardCard(Card c) {
    discardCards.add(c);
  }



  public ArrayList<Card> getDiscardCards() {
    return discardCards;
  }
}
