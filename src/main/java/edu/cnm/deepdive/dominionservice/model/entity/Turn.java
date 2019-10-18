package edu.cnm.deepdive.dominionservice.model.entity;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.lang.NonNull;

/**
 * Keeps a table consisting of every turn taken by both players,
 * Organized by a Turn ID and a Player ID. For example, Turn 1-Player 1
 * Is player 1's first turn, while Turn 1- Player 2 is Player 2's First Turn.
 * When HasFinished updates to True, the game's state resets for the next turn.
 */
@Entity
@Table
public class Turn {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "turn_id", updatable = false, nullable = false)
  private Long id;

  /**
   * Foreign Key playerId, refers to the player who took this turn.
   */
  @NonNull
  @ManyToOne
  @JoinColumn(nullable = false, updatable = false)
  private int playerId;

  /**
   * Buys Remaining- a counter that iterates down to zero. When it returns zero, a method is triggered
   * to move to the next phase (discard).
   */
  @NonNull
  @Column(name="buys")
  private int buysRemaining;

  /**
   * Actions Remaining- a counter that iterates down to zero. When it returns zero, a method is triggered
   * to move to the next phase (buy).
   */
  @NonNull
  @Column(name="actions")
  private int actionsRemaining;

  /**
   * Boolean has_discarded indicates whether or not the player has discarded. Can correspond
   * to a LiveData object in the Room implementation? Triggers the next phase.
   */
  @NonNull
  @Column(name="has_discarded")
  private boolean hasDiscarded = false;

  /**
   * Boolean has_discarded indicates whether or not the player has drawn, a necessary
   * step to end one's turn. Can correspon to a LiveData object in the Room implementation? Triggers the next phase.
   */
  @NonNull
  @Column(name="has_drawn")
  private boolean hasDrawn = false;

  /**
   * Includes a list of plays per turn.
   */
  @OneToMany(mappedBy = "turn_id", cascade = CascadeType.ALL,orphanRemoval = true)
  @OrderBy("play_id ASC")
  private List<Play> plays = new LinkedList<>();

  public List<Play> getPlays() {
    return plays;
  }

  public boolean isHasDrawn() {
    return hasDrawn;
  }

  public void setHasDrawn(boolean hasDrawn) {
    this.hasDrawn = hasDrawn;
  }

  public Long getId() {
    return id;
  }


  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public int getBuysRemaining() {
    return buysRemaining;
  }

  public void setBuysRemaining(int buysRemaining) {
    this.buysRemaining = buysRemaining;
  }

  public int getActionsRemaining() {
    return actionsRemaining;
  }

  public void setActionsRemaining(int actionsRemaining) {
    this.actionsRemaining = actionsRemaining;
  }

  public boolean isHasFinished() {
    return hasFinished;
  }

  public void setHasFinished(boolean hasFinished) {
    this.hasFinished = hasFinished;
  }

}
