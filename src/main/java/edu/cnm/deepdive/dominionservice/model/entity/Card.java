package edu.cnm.deepdive.dominionservice.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.lang.NonNull;

@Entity
public class Card {

  private Card(String cardName, Location location, CardType cardType,
      CardCategory cardCategory, int cost) {
    this.cardName = cardName;
    this.cardType = cardType;
    this.cost = cost;
    this.id = id;
    this.location = location;
  }

  //Static factory method to make card
  public Card newCard(String cardName, Location location, CardType cardType,
      CardCategory cardCategory, int cost) {
    return new Card(cardName, location, cardType, cardCategory, cost);
  }

  @Id
  @GeneratedValue
  @Column(name = "card_id", updatable = false, nullable = false)
  private Long id;

  /***
   * cost of card
   */
  @NonNull
  @Column(updatable = false)
  private int cost;

  /***
   * where card located
   */
  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "location_id", nullable = true, updatable = false)
  private Location location;

  /***
   * name of card
   */
  @NonNull
  @Column(updatable = false)
  private String cardName;

  public void doAction(Card card) {
    //do nothing- overridden by enum below
  }

  @NonNull
  @Column(updatable=false)
  private CardCategory cardCategory;

  public CardCategory getCardCategory() {
    return cardCategory;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public Long getId() {
    return id;
  }

  public int getCost() {
    return cost;
  }

  public Location getLocation() {
    return location;
  }

  public String getCardName() {
    return cardName;
  }

  public CardType getCardType() {
    return cardType;
  }


  @NonNull
  private CardType cardType;

  public enum CardType {
    MONEY,
    ACTION,
    VICTORY;

  }

  public enum CardCategory {
    Bronze,
    Silver,
    Gold,
    Estate,
    Duchy,
    Province,
    Cellar,
    Moat,
    Village,
    Workshop,
    Smithy,
    Remodel,
    Militia,
    Market,
    Mine,
    Merchant;
  }
}
