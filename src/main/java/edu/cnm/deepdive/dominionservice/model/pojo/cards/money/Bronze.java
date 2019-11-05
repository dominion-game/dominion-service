package edu.cnm.deepdive.dominionservice.model.pojo.cards.money;

import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Location;

public class Bronze extends Card {

  public Bronze(String cardName, Location location,
      CardType cardType, CardCategory cardCategory, int cost) {
    super(cardName, location, cardType, cardCategory, cost);
  }
}
