package edu.cnm.deepdive.dominionservice.model.entity;

import edu.cnm.deepdive.dominionservice.model.dao.CardRepository;
import edu.cnm.deepdive.dominionservice.model.dto.GameStateInfo;
import edu.cnm.deepdive.dominionservice.model.entity.Location;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

@Entity
public class Hand {


  @Autowired
  private CardRepository cardRepository;

  private List<Card> cardsInHand;


  public Hand(List<Card> cardsInHand) {
    this.cardsInHand = cardsInHand;
  }

  @Id
  @GeneratedValue
  private int id;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name="player_id", nullable = false, updatable = false)
  private Player player;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name="game_id", nullable = false, updatable = false)
  private Game game;




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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @NonNull
  public Player getPlayer() {
    return player;
  }

  public void setPlayer(@NonNull Player player) {
    this.player = player;
  }

  @NonNull
  public Game getGame() {
    return game;
  }

  public void setGame(@NonNull Game game) {
    this.game = game;
  }
}