package edu.cnm.deepdive.dominionservice.model.pojo.cards.victory;

import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Location;

public class Duchy extends Card {

  public Duchy(long cardId) {
    //TODO: get fields by cardId

    if (location.getPlayer() != null){
      player = location.getPlayer();
    }
  }
}
