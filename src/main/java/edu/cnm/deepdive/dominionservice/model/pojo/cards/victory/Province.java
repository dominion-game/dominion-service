package edu.cnm.deepdive.dominionservice.model.pojo.cards.victory;

import edu.cnm.deepdive.dominionservice.model.entity.Card;

public class Province extends Card {

  public Province(long cardId) {
    //TODO: get fields by cardId

    if (location.getPlayer() != null){
      player = location.getPlayer();
    }
  }
}
