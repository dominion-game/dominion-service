package edu.cnm.deepdive.dominionservice.model.dto;

import edu.cnm.deepdive.dominionservice.model.entity.Player;
import edu.cnm.deepdive.dominionservice.model.entity.Turn.TurnState;

public class TurnDTO {
  private TurnState turnState;
  private long id;
  private int buysRemaining;
  private int actionsRemaining;
  private int buyingPower;
  private Player player;

  public TurnState getTurnState() {
    return turnState;
  }

  public void setTurnState(TurnState turnState) {
    this.turnState = turnState;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public int getBuyingPower() {
    return buyingPower;
  }

  public void setBuyingPower(int buyingPower) {
    this.buyingPower = buyingPower;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }
}
