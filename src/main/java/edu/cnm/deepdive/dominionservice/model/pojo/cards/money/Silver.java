package edu.cnm.deepdive.dominionservice.model.pojo.cards.money;

import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Location;

public class Silver extends Card {

  public Silver(String cardName, Location location,
      CardType cardType, CardCategory cardCategory, int cost) {
    super(cardName, location, cardType, cardCategory, cost);
  }
}
