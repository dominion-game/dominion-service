package edu.cnm.deepdive.dominionservice.service;

import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Player;
import edu.cnm.deepdive.dominionservice.model.pojo.Deck;
import edu.cnm.deepdive.dominionservice.model.pojo.DiscardPile;
import java.util.ArrayList;
import java.util.Collections;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeckService {

  public Deck shuffle(Deck shuffleDeck) {
    Collections.shuffle(shuffleDeck.getDeckCards());
    return shuffleDeck;
  }

  public Deck shuffle(Deck shuffleDeck, DiscardPile discardPile, Player player) {
    ArrayList<Card> combinedDeckList = new ArrayList<>();
    combinedDeckList.addAll(shuffleDeck.getDeckCards());
    for (Card card : discardPile.getDiscardCards()) {
      card.setLocation(shuffleDeck.getLocation());
    }
    combinedDeckList.addAll((discardPile.getDiscardCards()));
    Collections.shuffle(combinedDeckList);
    return new Deck(combinedDeckList, shuffleDeck.getLocation());
  }

}
