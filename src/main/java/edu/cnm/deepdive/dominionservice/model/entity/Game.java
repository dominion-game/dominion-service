package edu.cnm.deepdive.dominionservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.cnm.deepdive.dominionservice.model.enums.Events;
import edu.cnm.deepdive.dominionservice.model.enums.States;
import edu.cnm.deepdive.dominionservice.service.state.ContextEntity;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.statemachine.StateMachineContext;


/**
 * The game is responsible for the following: creating a new game keeping track of players keep
 * track of stacks determining end of game
 */
@Entity
@Data
@AllArgsConstructor
@DynamicUpdate
public class Game extends AbstractPersistable<Long> implements Serializable, ContextEntity<States, Events, Long> {

  public Game(List<Stack> stacks,
      List<Player> players) {
    this.stacks = stacks;
    this.players = players;
  }

  public Game() {
  }

  /**
   * Creates the primary Game Id.
   */
  @Id
  @GeneratedValue
  @Column(name = "game_id", updatable = false, nullable = false)
  private Long id;

  @JsonIgnore
  @Override
  public boolean isNew() {
    return super.isNew();
  }

  /**
   * Returns a list of stacks. This list documents all of the stacks available to the players. It
   * will be updated throughout the game as players pull cards for the stacks.
   */
  @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
  private List<Stack> stacks = new LinkedList<>();

  /**
   * Returns a list of Players. This allows for keeping track of players, turns, and points
   * throughout the game.
   */
  @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
  private List<Player> players = new LinkedList<>();

  /**
   * Gets players.
   *
   * @return the players
   */
  public List<Player> getPlayers() {
    return players;
  }

  /**
   * Sets players.
   *
   * @param players the players
   */
  public void setPlayers(List<Player> players) {
    this.players = players;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Gets stacks.
   *
   * @return the stacks
   */
  public List<Stack> getStacks() {
    return stacks;
  }

  /**
   * Sets stacks.
   *
   * @param stacks the stacks
   */
  public void setStacks(List<Stack> stacks) {
    this.stacks = stacks;
  }

  @Override
  public StateMachineContext<States, Events> getStateMachineContext() {
    return null;
  }
  @Getter
  @Enumerated(EnumType.STRING)
  States currentState;

  @Getter
  @JsonIgnore
  StateMachineContext<States, Events> stateMachineContext;

  @Override
  public void setStateMachineContext(StateMachineContext context) {
    this.currentState = stateMachineContext.getState();
    this.stateMachineContext = stateMachineContext;
  }
}
