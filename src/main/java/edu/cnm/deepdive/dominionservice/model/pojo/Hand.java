package edu.cnm.deepdive.dominionservice.model.pojo;

import edu.cnm.deepdive.dominionservice.model.dao.CardRepository;
import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Location;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class Hand {

  private static CardRepository cardRepository;
  @Autowired
  private CardRepository cardRepository;

  private List<Card> cardsInHand;

  private Hand(List<Card> cardsInHand) {
    this.cardsInHand = cardsInHand;
  }


  public static Hand newHand(Location location) {
    return new Hand(cardRepository.getAllByLocationId(location));
  }
}