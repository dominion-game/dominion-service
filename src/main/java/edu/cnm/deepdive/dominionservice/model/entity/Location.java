package edu.cnm.deepdive.dominionservice.model.entity;
/**
 *
 * May not be necessary. Mainly meant to provide a joint table including both Stack and Player
 * locations for the id in Card that says "where is"
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.lang.NonNull;

@Entity
@Table
public class Location {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "location_id", updatable = false, nullable = false)
  private Long id;

  @NonNull
  @ManyToOne
  @JoinColumn(nullable = true, updatable = false)
  private int playerId;

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

  public enum LocationType{
    STACK,
    HAND,
    DISCARD,
    DECK,
    INPLAY;
  }
}
