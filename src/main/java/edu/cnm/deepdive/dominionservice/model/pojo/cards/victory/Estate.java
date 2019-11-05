package edu.cnm.deepdive.dominionservice.model.pojo.cards.victory;

import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Location;

public class Estate extends Card {

  public Estate(String cardName, Location location,
      CardType cardType, CardCategory cardCategory, int cost) {
    super(cardName, location, cardType, cardCategory, cost);
  }
}
