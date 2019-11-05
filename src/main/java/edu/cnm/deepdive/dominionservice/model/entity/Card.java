package edu.cnm.deepdive.dominionservice.model.entity;

import edu.cnm.deepdive.dominionservice.model.CardType;
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

  private Card(String cardName, Location location, CardType cardType, int cost) {
    this.cardName = cardName;
    this.cardType = cardType;
    this.cost = cost;
    this.id = id;
    this.location = location;
  }

  //Static factory method to make card
  public Card newCard(String cardName, Location location, CardType cardType, int cost) {
    return new Card(cardName, location, cardType, cost);
  }

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

  public void play(Player player) {
    switch (this.cardType) {
      //only care about action cards. ignore money and victory
      case cellar:
        player.drawCard();
        break;
      case market:
        player.drawCard();
        player.addAction();
        player.addBuy();
        player.addGold();
        break;
      case merchant:
        player.drawCard();
        player.addAction();
        //TODO add gold when playing silver
        break;
      case militia:
        player.addGold();
        player.addGold();
        //TODO each other player discards down to 3 cards
        break;
      case mine:
        break;
      case moat:
        player.drawCard();
        player.drawCard();
        break;
      case remodel:
        break;
      case smithy:
        //player draws 3 cards
        player.drawCard();
        player.drawCard();
        player.drawCard();
        break;
      case village:
        player.drawCard();
        player.addAction();
        player.addAction();
        break;
      case workshop:
        break;





      default:
        //do nothing (money or victory)
    }

  }

  @NonNull
  @Column(updatable = false)

  public void setLocation(Location location) {
    this.location = location;
  }

  public int getId() {
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

}


