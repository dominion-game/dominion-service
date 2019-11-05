/**
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

  public void deal(Game game);

  public Deck shuffle(Deck shuffleDeck) {
    Collections.shuffle(shuffleDeck.getDeckCards());
    return shuffleDeck;
  }

  //shuffling when you have to put your discards back into the deck
  public Deck shuffle(Deck shuffleDeck, DiscardPile discardPile) {
    ArrayList<Card> combinedDeckList = new ArrayList<>();
    combinedDeckList.addAll(shuffleDeck.getDeckCards());
    for (Card card : discardPile.getDiscardCards()) {
      //DAO CARD UPDATE LOCATION IDs TO SHOW THEY ARE IN DECK NOW
    }
    combinedDeckList.addAll((discardPile.getDiscardCards()));
    Collections.shuffle(combinedDeckList);
    return new Deck(combinedDeckList);
  }

  public Card changeLocation(Card card, Location newLocation) {
    //DAO UPDATE LOCATION METHOD
    card.setLocation(newLocation);
    return card;
  }

  public Turn doAction(Card card, Turn turn, Player player, Action A) {
    Turn updatedTurn = new Turn(turn.getId(), turn.getPlayer(), turn.getBuysRemaining(),
        turn.getActionsRemaining());
    //TODO call cost method
    switch (card.getCardCategory()) {
      case Mine:

        break;
      case Market:
        //TODO make this draw work
        player.draw();
        addAction(updatedTurn, 1);
        addBuys(updatedTurn, 1);
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
        player.drawFromStack(trashCost + 2);
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


*/

/** Card Methods: */
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

public Card getTopCard() {
    if(deck.isEmpty()) {
    deck.addAll(discard);
    discard.clear();
    Collections.shuffle(deck);
    //notify all players that you had to shuffle
    sendShuffled();
    }
    if(!deck.isEmpty()) { //i.e. there was something in discard
    return deck.pop();
    }
    return null;
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

public void cleanup() {
    discard.addAll(nextTurn.inPlay);
    discard.addAll(nextTurn.inHand);
//			nextTurn.inHand.clear();
    sendEndTurn();
    nextTurn = new ServerTurn(this);
    nextTurn.drawCards(5);
    Game.this.nextPlayer();
    //TODO: Outpost?
    }

private void sendCardToHand(Card c) {
    RemoteMessage rm = new RemoteMessage(Action.addCardToHand, playerNum, c, null);
    System.out.println("Server: sending card to player " + rm);
    //TODO send to everyone that you got a card
    streams.sendMessage(rm);
    }

    /** CardTypeAction method */





    /** Game Methods: */


    }



