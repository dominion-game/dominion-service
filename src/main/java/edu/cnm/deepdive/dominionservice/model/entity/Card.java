package edu.cnm.deepdive.dominionservice.model.entity;

import edu.cnm.deepdive.dominionservice.model.dto.GameStateInfo;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.lang.NonNull;

@Entity
public class Card implements Serializable {



  public Card(String cardName, CardType cardType, int cost) {
    this.cardName = cardName;
    this.cardType = cardType;
    this.cost = cost;
  }

  public Card(CardType cardType) {
    this.cardType = cardType;

    //TODO implement constructor using card type
    //need to get info from database do not understand this
  }

  //Static factory method to make card
  public Card newCard(String cardName, CardType cardType, int cost) {
    return new Card(cardName, cardType, cost);
  }

    @Id
    @GeneratedValue
     @Column(name = "id", updatable = false, nullable = false)
     private int id;

  @NonNull
  @Column(name = "card_type", updatable = false, nullable = false)
  private CardType cardType;


  @Column
  private int index;


  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name="draw_pile_id", nullable = false, updatable = false)
  private DrawPile drawPile;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name="discard_pile_id", nullable = false, updatable = false)
  private DiscardPile discardPile;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name="hand_id", nullable = false, updatable = false)
  private Hand hand;

  /***
   * cost of card
   */
  @NonNull
  @Column(updatable = false)
  private int cost;

  /***
   * name of card
   */
  @NonNull
  @Column(updatable = false)
  private String cardName;

  public int getCost() {
    return cost;
  }

  public String getCardName() {
    return cardName;
  }

  public CardType getCardType() {
    return cardType;
  }

  public void setCardType(@NonNull CardType cardType) {
    this.cardType = cardType;
  }

  public DrawPile getDrawPile() {
    return drawPile;
  }

  public void setDrawPile(DrawPile drawPile) {
    this.drawPile = drawPile;
  }

  public DiscardPile getDiscardPile() {
    return discardPile;
  }

  public void setDiscardPile(DiscardPile discardPile) {
    this.discardPile = discardPile;
  }

  public Hand getHand() {
    return hand;
  }

  public void setHand(Hand hand) {
    this.hand = hand;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }

  public void setCardName(@NonNull String cardName) {
    this.cardName = cardName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }


  public enum CardType {
    COPPER {
      @Override
      public void play(GameStateInfo gameStateInfo,
          List<Card> additionalCards) {}

      @Override
      public String toString() {
        return "Copper";
      }
    },
    SILVER {
      @Override
      public void play(GameStateInfo gameStateInfo,
          List<Card> additionalCards) {}
      @Override
      public String toString() {
        return "Silver";
      }
    },
    GOLD {
      @Override
      public void play(GameStateInfo gameStateInfo,
          List<Card> additionalCards) {}
      @Override
      public String toString() {
        return "Gold";
      }
    },
    ESTATE {
      @Override
      public void play(GameStateInfo gameStateInfo,
          List<Card> additionalCards) {}
      @Override
      public String toString() {
        return "Estate";
      }
    },
    DUCHY {
      @Override
      public void play(GameStateInfo gameStateInfo,
          List<Card> additionalCards) {}
      @Override
      public String toString() {
        return "Duchy";
      }
    },
    PROVINCE {
      @Override
      public void play(GameStateInfo gameStateInfo,
          List<Card> additionalCards) {}
      @Override
      public String toString() {
        return "Province";
      }
    },

    CELLAR {
      @Override
      public void play(GameStateInfo gameStateInfo,
          List<Card> additionalCards) {
        Hand currentHand = gameStateInfo.getCurrentPlayerStateInfo().getHand();
        currentHand.discardFromHand(additionalCards);
         gameStateInfo.getCurrentPlayerStateInfo().getDiscardPile().addToDiscard(additionalCards);
        gameStateInfo.getCurrentPlayerStateInfo().setHand(currentHand);
        //discard any number of cards from hand, redraw that many cards
        // need to select which cards to be deleted
        int numDiscarded = additionalCards.size();
        //for (int i = 0; i < numDiscarded; i++) {
         // gameStateInfo.getCurrentPlayerStateInfo(.additionalCards.get(i));
        //}
        for (int i = 0; i < numDiscarded; i++) {
          DrawPile drawPile = gameStateInfo.getCurrentPlayerStateInfo().getDrawPile();
          Hand hand = gameStateInfo.getCurrentPlayerStateInfo().getHand();
          hand.draw(drawPile,gameStateInfo, 1);
        }
        int actionsRemaining = gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().getActionsRemaining() - 1;
        gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().setActionsRemaining(actionsRemaining + 1);

      }
      @Override
      public String toString() {
        return "Cellar";
      }
    },
    MOAT {
      @Override
      public void play(GameStateInfo gameStateInfo,
          List<Card> additionalCards) {
        DrawPile drawPile = gameStateInfo.getCurrentPlayerStateInfo().getDrawPile();
        Hand hand = gameStateInfo.getCurrentPlayerStateInfo().getHand();
        hand.draw(drawPile,gameStateInfo, 2);

        int actionsRemaining = gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().getActionsRemaining() - 1;
        gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().setActionsRemaining(actionsRemaining);
      }
      @Override
      public String toString() {
        return "Moat";
      }
    },

    MARKET {
      @Override
      public void play(GameStateInfo gameStateInfo,
          List<Card> additionalCards) {
        DrawPile drawPile = gameStateInfo.getCurrentPlayerStateInfo().getDrawPile();
        Hand hand = gameStateInfo.getCurrentPlayerStateInfo().getHand();
        hand.draw(drawPile,gameStateInfo, 1);

        int actionsRemaining = gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().getActionsRemaining() - 1;
        gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().setActionsRemaining(actionsRemaining + 1);

        //  playerInfo.addBuy();
        int buysRemaining = gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().getBuysRemaining();
        gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().setBuysRemaining(buysRemaining + 1);

        //  playerInfo.addBuyingPower();\
        int buyingPower = gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().getBuyingPower();
        gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().setBuyingPower(buyingPower + 1);
      }
      @Override
      public String toString() {
        return "Market";
      }
    },

    MERCHANT {
      @Override
      public void play(GameStateInfo gameStateInfo,
          List<Card> additionalCards) {
        DrawPile drawPile = gameStateInfo.getCurrentPlayerStateInfo().getDrawPile();
        Hand hand = gameStateInfo.getCurrentPlayerStateInfo().getHand();
        hand.draw(drawPile,gameStateInfo, 1);

        int actionsRemaining = gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().getActionsRemaining() - 1;
        gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().setActionsRemaining(actionsRemaining + 1);

        gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().addGoldIfSilver();
        //TODO add gold when playing silver

      }
      @Override
      public String toString() {
        return "Merchant";
      }
    },
    MILITIA {
      @Override
      public void play(GameStateInfo gameStateInfo,
          List<Card> additionalCards) {
        int actionsRemaining = gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().getActionsRemaining() - 1;
        gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().setActionsRemaining(actionsRemaining);

        int buyingPower = gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().getBuyingPower();
        gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().setBuyingPower(buyingPower + 2);
      }
      @Override
      public String toString() {
        return "Militia";
      }
    },

    MINE {
      @Override
      public void play(GameStateInfo gameStateInfo,
          List<Card> additionalCards) {
        //TODO make sure additional cards has a card in it (how to hand error state)
        //gameStateInfo.trashCard(additionalCards.get(0));
       // gameStateInfo.getTreasure();

        int actionsRemaining = gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().getActionsRemaining() - 1;
        gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().setActionsRemaining(actionsRemaining);

        //TODO Gain a Treasure to your hand costing up to 3 more than it
      }
      @Override
      public String toString() {
        return "Mine";
      }
    },

    REMODEL {
      @Override
      public void play(GameStateInfo gameStateInfo,
          List<Card> additionalCards) {
        //TODO make sure additional cards has a card in it (how to hand error state)
        //gameStateInfo.trashCard(additionalCards.get(0));

        int actionsRemaining = gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().getActionsRemaining() - 1;
        gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().setActionsRemaining(actionsRemaining);

        //TODO Gain a card costing up to 2 more gold than the one you trashed.

      }
      @Override
      public String toString() {
        return "Remodel";
      }
    },

    SMITHY {
      @Override
      public void play(GameStateInfo gameStateInfo,
          List<Card> additionalCards) {
        DrawPile drawPile = gameStateInfo.getCurrentPlayerStateInfo().getDrawPile();
        Hand hand = gameStateInfo.getCurrentPlayerStateInfo().getHand();
        hand.draw(drawPile,gameStateInfo, 3); // How many cards????
        gameStateInfo.getCurrentPlayerStateInfo().setHand(hand);

        int actionsRemaining = gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().getActionsRemaining() - 1;
        gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().setActionsRemaining(actionsRemaining);
      }
      @Override
      public String toString() {
        return "Smithy";
      }
    },

    VILLAGE {
      @Override
      public void play(GameStateInfo gameStateInfo,
          List<Card> additionalCards) {
        DrawPile drawPile = gameStateInfo.getCurrentPlayerStateInfo().getDrawPile();
        Hand hand = gameStateInfo.getCurrentPlayerStateInfo().getHand();
        hand.draw(drawPile,gameStateInfo, 1);

        int actionsRemaining = gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().getActionsRemaining() - 1;
        gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().setActionsRemaining(actionsRemaining + 2);


      }
      @Override
      public String toString() {
        return "Village";
      }
    },
    WORKSHOP {
      @Override
      public void play(GameStateInfo gameStateInfo, List<Card> additionalCards) {


        int actionsRemaining = gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().getActionsRemaining() - 1;
        gameStateInfo.getCurrentPlayerStateInfo().getThisTurn().setActionsRemaining(actionsRemaining);

        //TODO Gain card costing up to 4 gold
      }
      @Override
      public String toString() {
        return "Workshop";
      }
    };


    public abstract void play(GameStateInfo gameStateInfo,
        List<Card> additionalCards);

  }

}
