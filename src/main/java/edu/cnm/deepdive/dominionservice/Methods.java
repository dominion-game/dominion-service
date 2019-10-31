package edu.cnm.deepdive.dominionservice;

import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Game;
import edu.cnm.deepdive.dominionservice.model.entity.Location;
import edu.cnm.deepdive.dominionservice.model.entity.Player;
import edu.cnm.deepdive.dominionservice.model.entity.Turn;
import edu.cnm.deepdive.dominionservice.model.pojo.Deck;
import edu.cnm.deepdive.dominionservice.model.pojo.DiscardPile;
import java.util.ArrayList;
import java.util.Collections;

public class Methods {

  /** Game Methods*/
  public void deal(Game game);

  /** DECK METHODS*/
  public Deck shuffle (Deck shuffleDeck){
    Collections.shuffle(shuffleDeck.getDeckCards());
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
  public Turn doAction(Card card, Turn turn, Player player, Action A){
    Turn updatedTurn = new Turn(turn.getId(),turn.getPlayer(),turn.getBuysRemaining(),
        turn.getActionsRemaining());
    //TODO call cost method
    switch(card.getCardCategory()){
      case Mine:

        break;
      case Market:
        //TODO make this draw work
        player.draw();
        addAction(updatedTurn, 1);
        addBuys(updatedTurn,1);
        break;
      case Merchant:
        break;
      case Moat:
        break;
      case Cellar:
        addAction(updatedTurn, 1);
        //TODO: Set player state to "DISCARDING"
        break;
      case Village:
        //TODO make this draw work
        player.draw();
        addAction(updatedTurn, 2);
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

  private void addBuys(Turn updatedTurn, int howMany) {
    updatedTurn.setBuysRemaining(howMany + updatedTurn.getBuysRemaining());
  }

  private void addAction(Turn updatedTurn, int howMany) {
    updatedTurn.setActionsRemaining(howMany + updatedTurn.getActionsRemaining());
  }


  /** Player methods */
  public Card draw(Location fromWhere, Player player, Location toWhere){
    //Return a new card from a stack, deck, or other
    card.changeLocation(toWhere);
    return card;
  }

  /** Location methods */



}
