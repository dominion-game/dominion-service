package edu.cnm.deepdive.dominionservice.model.pojo;

import edu.cnm.deepdive.dominionservice.model.dao.CardRepository;
import edu.cnm.deepdive.dominionservice.model.dto.GameStateInfo;
import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Location;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class Hand {


  @Autowired
  private CardRepository cardRepository;

  private List<Card> cardsInHand;


  public Hand(List<Card> cardsInHand) {
    this.cardsInHand = cardsInHand;
  }


  public Hand newHand(Location location) {
    return new Hand(cardRepository.getAllByLocationId(location));
  }


  public List<Card> draw(DrawPile drawPile, GameStateInfo gameStateInfo){
    cardsInHand.add(drawPile.getTopCard(gameStateInfo));
    return cardsInHand;
  }

  public List<Card> draw(DrawPile drawPile, GameStateInfo gameStateInfo, int numOfCards){
    for (int i = 0; i < numOfCards; i++) {
      draw(drawPile, gameStateInfo);
    }
    return cardsInHand;
  }

  public CardRepository getCardRepository() {
    return cardRepository;
  }

  public void setCardRepository(CardRepository cardRepository) {
    this.cardRepository = cardRepository;
  }

  public List<Card> getCardsInHand() {
    return cardsInHand;
  }

  public void setCardsInHand(
      List<Card> cardsInHand) {
    this.cardsInHand = cardsInHand;
  }
}