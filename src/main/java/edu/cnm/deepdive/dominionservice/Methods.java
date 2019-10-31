package edu.cnm.deepdive.dominionservice;

import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Game;
import edu.cnm.deepdive.dominionservice.model.entity.Location;
import edu.cnm.deepdive.dominionservice.model.entity.Location.LocationType;
import edu.cnm.deepdive.dominionservice.model.entity.Player;
import edu.cnm.deepdive.dominionservice.model.entity.Turn;
import edu.cnm.deepdive.dominionservice.model.pojo.Deck;
import edu.cnm.deepdive.dominionservice.model.pojo.DiscardPile;
import edu.cnm.deepdive.dominionservice.service.TurnState;
import java.util.ArrayList;
import java.util.Collections;

public class Methods {

  /** Game Methods*/
  public void deal(Game game);

  /** DECK METHODS*/
  public Deck shuffle (Deck shuffleDeck){
    return shuffleDeck;
  }
  //shuffling when you have to put your discards back into the deck
  public Deck shuffle(Deck shuffleDeck, DiscardPile discardPile){
    ArrayList<Card> combinedDeckList = new ArrayList<>();
    combinedDeckList.addAll(shuffleDeck.getDeckCards());
    for (Card card: discardPile.getDiscardCards()){
      //DAO CARD UPDATE LOCATION IDs TO SHOW THEY ARE IN DECK NOW
    }
    combinedDeckList.addAll((discardPile.getDiscardCards()));
    Collections.shuffle(combinedDeckList);
    return new Deck(combinedDeckList);
  }


  /**CARD METHODS*/
  public Card changeLocation(Card card, Location newLocation){
    //DAO UPDATE LOCATION METHOD
    card.setLocation(newLocation);
    return card;
  }
  public Turn doAction(Card card, Turn turn, Player player){
    Turn updatedTurn = new Turn(turn.getId(),turn.getPlayer(),turn.getBuysRemaining(),
        turn.getActionsRemaining());
    //TODO call cost method
    switch(card.getCardCategory()){
      case Mine:

        break;
      case Market:
        //TODO make this draw work
        player.draw();
        updatedTurn.setActionsRemaining(1+ updatedTurn.getActionsRemaining());
        updatedTurn.setBuysRemaining(1+ updatedTurn.getBuysRemaining());
        break;
      case Merchant:
        break;
      case Moat:
        break;
      case Cellar:
        updatedTurn.setActionsRemaining(1+ updatedTurn.getActionsRemaining());
        //TODO: Set player state to "DISCARDING"
        break;
      case Village:
        //TODO make this draw work
        player.draw();
        updatedTurn.setActionsRemaining(2+ updatedTurn.getActionsRemaining());
        break;
      case Workshop:
        player.drawFromStack(4);
        break;
      case Smithy:
        break;
      case Remodel:
        //TODO: set player state to "TRASHING"
        //Get cost from trashed card and add 2
        player.drawFromStack(trashCost+2);
        break;
      case Militia:
        break;
      default:
        //return invalid operation
        break;
    }
    //TODO make this discard method work
    card.discard();
    return updatedTurn;
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
