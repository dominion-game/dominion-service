package edu.cnm.deepdive.dominionservice.model.dto;

import edu.cnm.deepdive.dominionservice.model.entity.Player;
import java.util.List;

public class GameDTO {

  private long id;
  private List<Player> playerList;

  public GameDTO() {
    this.id = 0;
    this.playerList = null;
  }

  public GameDTO(int id,
      List<Player> playerList) {
    this.id = id;
    this.playerList = playerList;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public List<Player> getPlayerList() {
    return playerList;
  }

  public void setPlayerList(
      List<Player> playerList) {
    this.playerList = playerList;
  }
}
