package edu.cnm.deepdive.dominionservice.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.lang.NonNull;

/**
 * Keeps track of all the various stacks (money, action, and victory point) used in the game.
 */
@Entity
@Table(
    indexes = {@Index(columnList = "stackType"),
    @Index(columnList = "stackCount")}
)
public class Stack {

  /**
   * Creates the primary key for Stack.
   */
  @Id
  @GeneratedValue
  @Column(name = "stack_id", updatable = false, nullable = false)
  private Long id;
  /**
   * Associates the stacks with a given game.
   */
  @NonNull
  @ManyToOne
  @JoinColumn(nullable = false, updatable = false)
  private Game game;
  /**
   * Given certain action cards, a player may trash cards from their hand.
   */
  @Column(name="is_trash")
  private boolean isTrash;
  /**
   * Stacks are sorted by type: Money, Action, Victory Point.
   */
  @Column(name="stack_type")
  private String stackType;
  /**
   * Keeps track of the number of cards per stack. This helps with player strategy and also game
   * terminating requirements.
   */
  @Column(name="stack_count")
  private int stackCount;

  public Long getId() {
    return id;
  }

  public Game getGame() {
    return game;
  }

  public boolean isTrash() {
    return isTrash;
  }

  public void setTrash(boolean trash) {
    isTrash = trash;
  }

  public String getStackType() {
    return stackType;
  }

  public void setStackType(String stackType) {
    this.stackType = stackType;
  }

  public int getStackCount() {
    return stackCount;
  }

  public void setStackCount(int stackCount) {
    this.stackCount = stackCount;
  }
}
