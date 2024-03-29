package edu.cnm.deepdive.dominionservice.model.entity;


import java.io.Serializable;
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
    indexes = {@Index(columnList = "stack_type"),
        @Index(columnList = "stack_count")}
)
public class Stack implements Serializable {

  /**
   * Generates primary key id.
   */
  @Id
  @GeneratedValue
  @Column(name = "stack_id", updatable = false, nullable = false)
  private Long id;


  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "game_id", nullable = false, updatable = false)
  private Game game;

  /**
   * The variable that holds the stack type. Allowed types are: Bronze, Silver, Gold, Estate, Duchy,
   * Province, Cellar, Moat, Village, Workshop, Smithy, Remodel, Militia, Market, Mine, Merchant,
   * Trash.
   */
  @Column(name = "stack_type")
  private StackType stackType;

  /**
   * The number of stacks with one or more cards.
   */
  @Column(name = "stack_count")
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
    Bronze {
      @Override
      public int getIndex() {
        return 0;
      }

      @Override
     public int getInitialCardAmounts() {
        return 0;
      }
    },
    Silver{
      @Override
      public int getIndex() {
        return 1;
      }

      @Override
      public int getInitialCardAmounts() {
        return 0;
      }
    },
    Gold{
      @Override
      public int getIndex() {
        return 2;
      }

      @Override
      public int getInitialCardAmounts() {
        return 0;
      }
    },
    Estate{
      @Override
      public int getIndex() {
        return 3;
      }

      @Override
      public int getInitialCardAmounts() {
        return 0;
      }
    },
    Duchy{
      @Override
      public int getIndex() {
        return 4;
      }

      @Override
      public int getInitialCardAmounts() {
        return 0;
      }
    },
    Province{
      @Override
     public int getIndex() {
        return 5;
      }

      @Override
      public int getInitialCardAmounts() {
        return 0;
      }
    },
    Cellar{
      @Override
      public int getIndex() {
        return 6;
      }

      @Override
      public int getInitialCardAmounts() {
        return 0;
      }
    },
    Moat{
      @Override
      public int getIndex() {
        return 7;
      }

      @Override
      public int getInitialCardAmounts() {
        return 0;
      }
    },
    Village{
      @Override
     public int getIndex() {
        return 8;
      }

      @Override
      public int getInitialCardAmounts() {
        return 0;
      }
    },
    Workshop{
      @Override
      public int getIndex() {
        return 9;
      }

      @Override
     public int getInitialCardAmounts() {
        return 0;
      }
    },
    Smithy{
      @Override
      public int getIndex() {
        return 10;
      }

      @Override
      public int getInitialCardAmounts() {
        return 0;
      }
    },
    Remodel{
      @Override
      public int getIndex() {
        return 11;
      }

      @Override
     public int getInitialCardAmounts() {
        return 0;
      }
    },
    Militia{
      @Override
      public int getIndex() {
        return 12;
      }

      @Override
      public int getInitialCardAmounts() {
        return 0;
      }
    },
    Market{
      @Override
      public int getIndex() {
        return 13;
      }

      @Override
     public int getInitialCardAmounts() {
        return 0;
      }
    },
    Mine{
      @Override
     public int getIndex() {
        return 14;
      }

      @Override
     public int getInitialCardAmounts() {
        return 0;
      }
    },
    Merchant{
      @Override
     public int getIndex() {
        return 15;
      }

      @Override
      public int getInitialCardAmounts() {
        return 0;
      }
    },
    Trash{
      @Override
      public int getIndex() {
        return 16;
      }

      @Override
     public int getInitialCardAmounts() {
        return 0;
      }
    };

    String[] symbols = {"Bronze", "Silver", "Gold", "Estate", "Duchy", "Province", "Cellar", "Moat",
        "Village",
        "Workshop", "Smithy", "Remodel", "Militia", "Market", "Mine", "Merchant", "Trash"};

    public String toString(StackType stackType) {
      return stackType.getSymbol();
    }

    public String getSymbol() {
      return symbols[ordinal()];
    }

     public abstract int getIndex();
   public abstract int getInitialCardAmounts();
    }
  }

  /**public static void main(String[] args) {
   System.out.println(StackType.Merchant.toString());
   }
   */


