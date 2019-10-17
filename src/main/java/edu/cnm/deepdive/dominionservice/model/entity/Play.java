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

/** Keeps a log of the significant player actions taken during each turn, sorted
 * by player and turn.
 */
@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnName = "play_id", "turn_id", "player_id")
)
public class Play {
  /**Each action taken by a player generates a new play. Typically, most of the fields
   * will be null or zero in the interest of separating out each action individually.
   */
  @Id
  @GeneratedValue
  @Column(name = "play_id", updatable = false, nullable = false)
  private Long id;


  /**
   * Stores the id of the Player that took the action.
   */
  @NonNull
  @JoinColumn(name= "player_id", nullable = false, updatable = false)
  @ManyToOne(fetch=FetchType.EAGER, optional = false)
  private int playerId;

  /**Each action taken by a player generates a new play. Typically, most of the fields
   * will be null or zero in the interest of separating out each action individually.
   */
  @NonNull
  @ManyToOne(fetch= FetchType.EAGER, optional = false)
  @JoinColumn(name = "turn_id", nullable = false, updatable = false)
  private Turn turn;


  @NonNull
  @Column(name="card_played", nullable = true, updatable = false)
  private int cardPlayed;

  @NonNull
  @Column(name="gold_spent", nullable = true, updatable=false)
  private int goldSpent;

  @NonNull
  @Column(name="card_bought", nullable = true, updatable = false)
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

  public int getPlayerId() {
    return playerId;
  }

  public int getCardBoughtId() {
    return cardBoughtId;
  }

  public void setCardBoughtId(int cardBoughtId) {
    this.cardBoughtId = cardBoughtId;
  }
}
