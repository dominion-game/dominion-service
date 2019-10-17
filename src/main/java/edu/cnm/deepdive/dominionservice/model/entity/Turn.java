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
@Table(
    indexes = @Index(columnList = "turn_id")
)
public class Turn {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "turn_id", updatable = false, nullable = false)
  private Long id;

  @NonNull
  @ManyToOne
  @JoinColumn(name = "player_id", nullable = false, updatable = false)
  private Player player;


  @NonNull
  @Column(name="buys")
  private int buysRemaining;

  @NonNull
  @Column(name="actions")
  private int actionsRemaining;

  @NonNull
  @Column(name="has_discarded")
  private boolean hasFinished = false;

  @NonNull
  @Column(name="has_drawn")
  private boolean hasDrawn = false;

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
