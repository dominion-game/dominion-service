package edu.cnm.deepdive.dominionservice.model.entity;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Game {
  @Id
  @GeneratedValue
  @Column(name = "game_id", updatable = false, nullable = false)
  private Long id;

  @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
  private List<Stack> stacks= new LinkedList<>();

  @OneToMany(mappedBy= "game", cascade = CascadeType.ALL)
  private List<Player> players = new LinkedList<>();

  public List<Player> getPlayers() {
    return players;
  }

  public void setPlayers(List<Player> players) {
    this.players = players;
  }

  public Long getId() {
    return id;
  }

  public List<Stack> getStacks() {
    return stacks;
  }

  public void setStacks(List<Stack> stacks) {
    this.stacks = stacks;
  }
}
