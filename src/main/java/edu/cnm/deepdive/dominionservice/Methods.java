package edu.cnm.deepdive.dominionservice;

import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Location;
import edu.cnm.deepdive.dominionservice.model.entity.Location.LocationType;
import edu.cnm.deepdive.dominionservice.model.entity.Player;

public class Methods {


  /**CARD METHODS*/
  public Card changeLocation(Card card, Location newLocation){
    //DAO UPDATE LOCATION METHOD
    card.setLocation(newLocation);
    return card;
  }

  /** Player methods */
  public Card draw(Location fromWhere, Player player, Location toWhere){
    //Return a new card from a stack, deck, or other
    card.changeLocation(toWhere);
    return card;
  }

  /** Location methods */
  public Card getTopCard(Location fromWhere){
    //gets the top card from a deck, discard, or stack. Will always return the same card for stacks, but
    //decks and discards will be different
    LocationType type = fromWhere.getLocationType();
    if (fromWhere.hasCards) {
      switch (type) {
        case STACK:
          break;
        case DECK:
          break;
        case DISCARD:
          break;
        default:
          //return invalid action error
          break;
      }
    }

  }

  public boolean hasCards(Location fromWhere){
    //this will get the length of the arraylist in a locations object (cards). Probably won't work
    //until we put this method in the location class.
    if(cards.size==0){
      return false;
    }else{
      return true;
    }

  }
}
