package edu.cnm.deepdive.dominionservice.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.lang.NonNull;


/**
 * Stacks are made up of cards that are used be the player to progress through a game. Stacks are
 * arranged by types. The number of cards in each stack will be tracked throughout the game.
 */
@Entity
@Table(
    indexes = {@Index(columnList = "stackType"),
    @Index(columnList = "stackCount")}
)
public class Stack {

  /**
   * Generates primary key id.
   */
  @Id
  @GeneratedValue
  @Column(name = "stack_id", updatable = false, nullable = false)
  private Long id;


  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name="game_id", nullable = false, updatable = false)
  private Game game;

  /**
   *  The variable that holds the stack type. Allowed types are: Bronze, Silver, Gold, Estate, Duchy,
   *  Province, Cellar, Moat, Village, Workshop, Smithy, Remodel, Militia, Market, Mine, Merchant,
   *  Trash.
   *
   */
  @Column(name="stack_type")
  private StackType stackType;

  /**
   * The number of stacks with one or more cards.
   */
  @Column(name="stack_count")
  private int stackCount;


 // private List<Stack> stacks;

  /**
   * Gets id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Gets game.
   *
   * @return the game
   */
  public Game getGame() {
    return game;
  }

  /**
   * Gets stack type.
   *
   * @return the stack type
   */
  public StackType getStackType() {
    return stackType;
  }

  /**
   * Sets stack type.
   *
   * @param stackType the stack type
   */
  public void setStackType(StackType stackType) {
    this.stackType = stackType;
  }

  /**
   * Gets stack count.
   *
   * @return the stack count
   */
  public int getStackCount() {
    return stackCount;
  }

  /**
   * Sets stack count.
   *
   * @param stackCount the stack count
   */
  public void setStackCount(int stackCount) {
    this.stackCount = stackCount;
  }

  /**
   * The enum Stack type.
   */
  public enum StackType {
    Bronze,
    Silver,
    Gold,
    Estate,
    Duchy,
    Province,
    Cellar,
    Moat,
    Village,
    Workshop,
    Smithy,
    Remodel,
    Militia,
    Market,
    Mine,
    Merchant,
    Trash;

    String [] symbols = {"Bronze", "Silver", "Gold", "Estate", "Duchy", "Province", "Cellar", "Moat", "Village",
    "Workshop", "Smithy", "Remodel", "Militia", "Market", "Mine", "Merchant", "Trash"};

    public String toString(StackType stackType) {
      return stackType.getSymbol();
   }
   private String getSymbol(){
      return symbols[ordinal()];
   }
  }

  /**public static void main(String[] args) {
    System.out.println(StackType.Merchant.toString());
  }
*/
}

