package edu.cnm.deepdive.dominionservice.model.pojo;

import edu.cnm.deepdive.dominionservice.model.dao.CardRepository;
import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Location;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Hand {


  @Autowired
  private CardRepository cardRepository;

  private List<Card> cardsInHand;

  private Hand(List<Card> cardsInHand) {
    this.cardsInHand = cardsInHand;
  }


  public Hand newHand(Location location) {
    return new Hand(cardRepository.getAllByLocationId(location));
  }
  public Hand draw(fromwhere){

  }

}