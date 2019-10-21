package edu.cnm.deepdive.dominionservice.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.lang.NonNull;

@Entity
public class Card {
  @Id
  @GeneratedValue
  @Column(name = "card_id", updatable = false, nullable = false)
  private int id;

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
  @ManyToOne
  private int location;

  /***
   * name of card
   */
  @NonNull
  @Column(updatable = false)
  private String cardName;

  public void setLocation(long location) {
    this.location = location;
  }

  public int getId() {
    return id;
  }

  public int getCost() {
    return cost;
  }

  public int getLocation() {
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

  public enum CardType{
    MONEY,
  ACTION,
  VICTORY;

  }





}
