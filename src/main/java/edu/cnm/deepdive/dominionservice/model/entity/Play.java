package edu.cnm.deepdive.dominionservice.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Fetch;
import org.springframework.lang.NonNull;

/**
 * Keeps a log of the significant player actions taken during each turn, sorted by player and turn.
 */
@Entity
@Table
public class Play {

  /**
   * Each action taken by a player generates a new play. Typically, most of the fields will be null
   * or zero in the interest of separating out each action individually. Example: play: 1, Player:1,
   *  Turn: 1, cardPlayed: 45, goldSpent: 0, cardBought: 0. Note that cards are referred to by
   * integer indices.
   */
  @Id
  @GeneratedValue
  @Column(name = "play_id", updatable = false, nullable = false)
  private Long id;


  /**
   * Each action taken by a player generates a new play. Typically, most of the fields will be null
   * or zero in the interest of separating out each action individually.
   */
  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "turn_id", nullable = false, updatable = false)
  private Turn turn;

  /**
   * Each play is a associated with zero or more cards played. This records a log of which card was
   * played and when.
   */
  @NonNull
  @Column(name = "card_played", nullable = true, updatable = false)
  private int cardPlayed;

  /**
   * Each play is associated with zero or more gold spent, typically to buy new cards.
   */
  @NonNull
  @Column(name = "gold_spent", nullable = true, updatable = false)
  private int goldSpent;

  /**
   * Each play is associated with zero or more cards bought.
   */
  @NonNull
  @Column(name = "card_bought", nullable = true, updatable = false)
  private int cardBoughtId;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Turn getTurn() {
    return turn;
  }

  public void setTurn(Turn turn) {
    this.turn = turn;
  }

  public int getCardPlayed() {
    return cardPlayed;
  }

  public void setCardPlayed(int cardPlayed) {
    this.cardPlayed = cardPlayed;
  }

  public int getGoldSpent() {
    return goldSpent;
  }

  public void setGoldSpent(int goldSpent) {
    this.goldSpent = goldSpent;
  }


  public int getCardBoughtId() {
    return cardBoughtId;
  }

  public void setCardBoughtId(int cardBoughtId) {
    this.cardBoughtId = cardBoughtId;
  }
}
