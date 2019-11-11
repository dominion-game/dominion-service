package edu.cnm.deepdive.dominionservice.model.pojo.cards.actioncards;

import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Location;
import edu.cnm.deepdive.dominionservice.model.entity.Player;

public class Village extends Card {
  Player player = null;


  public Village(long cardId) {
    //TODO: get fields by cardId

    if (location.getPlayer() != null){
      player = location.getPlayer();
    }
  }

  @Override
  public void doAction(Card card) {
    player.drawCard();
    player.addAction();
    player.addAction();
  }

  //THE GETTER AND SETTER HERE ARE PROBABLY NOT NECESSARY
  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }
}
