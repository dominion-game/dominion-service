package edu.cnm.deepdive.dominionservice.model.entity;
/**
 *
 * May not be necessary. Mainly meant to provide a joint table including both Stack and Player
 * locations for the id in Card that says "where is"
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.lang.NonNull;

@Entity
@Table
public class Location {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "location_id", updatable = false, nullable = false)
  private Long id;

  @OneToMany(mappedBy="card",cascade = CascadeType.ALL)
  List<Card> cards = new LinkedList<>();

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name="player_id", nullable = true, updatable = false)
  private Player player;

  /**
   * Location type is an enum of Strings that identifies what type of stack or player location the
   * card resides in. Values are "Stack", "Deck", "Discard", and "Hand", and these correspond to
   * playerId and stackId to allow for locating the card.
   */
  @Column(name="location_type")
  private LocationType locationType;

  @NonNull
  @ManyToOne
  @JoinColumn(nullable = true, updatable = false)
  private int stackId;
  private boolean hasCards;

  public boolean hasCards(Location fromWhere){
    //this will get the length of the arraylist in a locations object (cards). Probably won't work
    //until we put this method in the location class.
    if(cards.size()==0){
      hasCards= false;
    }else{
      hasCards = true;
    }
  return hasCards;
  }
  public Card getTopCard(Location fromWhere){
    //gets the top card from a deck, discard, or stack. Will always return the same card for stacks, but
    //decks and discards will be different
    LocationType type = fromWhere.getLocationType();
    if (fromWhere.hasCards) {
      switch (type) {
        case STACK:
          break;
        case DECK:
          break;
        case DISCARD:
          break;
        default:
          //return invalid action error
          break;
      }
    }

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Card> getCards() {
    return cards;
  }

  public void setCards(List<Card> cards) {
    this.cards = cards;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public LocationType getLocationType() {
    return locationType;
  }

  public void setLocationType(
      LocationType locationType) {
    this.locationType = locationType;
  }

  public int getStackId() {
    return stackId;
  }

  public void setStackId(int stackId) {
    this.stackId = stackId;
  }

  public enum LocationType{
    STACK,
    HAND,
    DISCARD,
    DECK,
    INPLAY;
  }
}
