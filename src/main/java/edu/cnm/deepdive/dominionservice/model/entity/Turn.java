package edu.cnm.deepdive.dominionservice.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.lang.NonNull;

@Entity
public class Turn {
  @Id
  @GeneratedValue
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

  public boolean isHasDrawn() {
    return hasDrawn;
  }

  public void setHasDrawn(boolean hasDrawn) {
    this.hasDrawn = hasDrawn;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
