/**

package edu.cnm.deepdive.dominionservice;

import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Game;
import edu.cnm.deepdive.dominionservice.model.entity.Location;
import edu.cnm.deepdive.dominionservice.model.entity.Location.LocationType;
import edu.cnm.deepdive.dominionservice.model.entity.Player;
import edu.cnm.deepdive.dominionservice.model.entity.Turn;
import edu.cnm.deepdive.dominionservice.model.pojo.DrawPile;
import edu.cnm.deepdive.dominionservice.model.pojo.DiscardPile;
import edu.cnm.deepdive.dominionservice.service.TurnState;
import java.util.ArrayList;
import java.util.Collections;

public class Methods {

  /** Game Methods*/
  /** DECK METHODS*



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


  public Card draw(Location fromWhere, Player player, Location toWhere){
    //Return a new card from a stack, deck, or other
    card.changeLocation(toWhere);
    return card;
  }

  /** Location methods


  public boolean hasCards(Location fromWhere){
    //this will get the length of the arraylist in a locations object (cards). Probably won't work
    //until we put this method in the location class.
    if(cards.size==0){
      return false;
    }else{
      return true;
    }

  }

  "ERICA"

 /** Card Methods:
  Card type;
  int numLeft;

  public CardStack(Card type, int numLeft) {
    this.type = type;
    this.numLeft = numLeft;
  }
  public boolean isEmpty() {
    return numLeft == 0;
  }
  @Override
  public String toString() {
    return "There are " + numLeft + " " + type + "s left";
  }
  @Override
  public int compareTo(CardStack other) {
    return type.compareTo(other.type);
  }
  public void setupStartingDeck() {
    for(int i = 0; i < Card.startingHand.length; i++) {
      deck.push(Card.startingHand[i]);
    }
    Collections.shuffle(deck);
  }

  public void drawCards(int numCards) {
    for(int i = 0; i < numCards; i++) drawCard();
  }


  }
  public void drawCard() {
    Card c = getTopCard();
    if(c != null) {
      nextTurn.inHand.add(c);
      sendCardToHand(c);
    }
  }

  public Card getCardFromStack(Card card)
  {
    CardStack cardStack = getCardStack(card);
    if(!cardStack.isEmpty()) {
      cardStack.numLeft--;
      return card;
    }
    return null;
  }

  public CardStack getCardStack(Card card) {
//			System.out.println("Server: Searching supply for: " + card + " with upperLimit " + upperLimit);
    for(CardStack cardStack: Game.this.stacks) {
//				System.out.println("Server: Looking at stack " + cardStack);
      if(cardStack.type.equals(card)) {
//					System.out.println("Server: found match in stack " + cardStack);
        return cardStack;
      }
    }
    return null;
  }

  public void trashCard(Card c) {
    trash.add(c);
  }

  //assumes caller has already removed it from appropriate place
  public void discardCard(Card c) {
    discard.add(c);
  }

  public void discardCards(List<Card> l) {
    discard.addAll(l);
  }
  public void discardDeck() {
    discard.addAll(deck);
    deck.clear();
  }




  /** CardTypeAction method */





