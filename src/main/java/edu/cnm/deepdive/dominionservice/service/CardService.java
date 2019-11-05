package edu.cnm.deepdive.dominionservice.service;

import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Card.CardCategory;
import edu.cnm.deepdive.dominionservice.model.entity.Location;
import edu.cnm.deepdive.dominionservice.model.entity.Location.LocationType;
import edu.cnm.deepdive.dominionservice.model.entity.Player;
import edu.cnm.deepdive.dominionservice.model.entity.Stack;
import edu.cnm.deepdive.dominionservice.model.pojo.Deck;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CardService {

 /** public Card draw(Location fromWhere, Player player, Location toWhere) {
    if (fromWhere.getLocationType()== LocationType.STACK){
      //TODO fix this
    } else if (fromWhere.getLocationType() == LocationType.DECK){
      //TODO implement draw from deck
    }

    card.changeLocation(toWhere);
    return card;
  }**/
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

  private void sendCardToHand(Card c) {
    RemoteMessage rm = new RemoteMessage(Action.addCardToHand, playerNum, c, null);
    System.out.println("Server: sending card to player " + rm);
    //TODO send to everyone that you got a card
    streams.sendMessage(rm);
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


}
