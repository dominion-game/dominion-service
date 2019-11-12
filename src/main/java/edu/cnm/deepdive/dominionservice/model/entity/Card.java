package edu.cnm.deepdive.dominionservice.model.entity;

import edu.cnm.deepdive.dominionservice.model.dto.GameStateInfo;
import edu.cnm.deepdive.dominionservice.model.pojo.DrawPile;
import edu.cnm.deepdive.dominionservice.model.pojo.Hand;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.springframework.lang.NonNull;

@Entity
public class Card {

  public Card(String cardName, CardType cardType, int cost) {
    this.cardName = cardName;
    this.cardType = cardType;
    this.cost = cost;
  }

  public Card(CardType cardType) {
    this.cardType = cardType;
    //TODO implement constructor using card type
  }

  //Static factory method to make card
  public Card newCard(String cardName, CardType cardType, int cost) {
    return new Card(cardName, cardType, cost);
  }

  @Id
  @GeneratedValue
  @Column(name = "card_id", updatable = false, nullable = false)
  private int id;

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

  public void doAction(Card card) {
    //do nothing- overridden by enum below
  }

  public int getCost() {
    return cost;
  }

  public String getCardName() {
    return cardName;
  }

  public CardType getCardType() {
    return cardType;
  }


  @NonNull
  private CardType cardType;

  public enum CardType {
    COPPER {
      @Override
      public void play(GameStateInfo gameStateInfo) {
        GameStateInfo.addBuyingPower();
      }
    },
    SILVER {
      @Override
      public void play(GameStateInfo gameStateInfo) {
        GameStateInfo.addBuyingPower(3);
      }
    },
    GOLD {
      @Override
      public void play(GameStateInfo gameStateInfo) {
        gameStateInfo.addBuyingPower(6);
      }
    },
    ESTATE {
      @Override
      public void play(GameStateInfo gameStateInfo) {
        //Not an action or money card.Counts as points at end of game
      }
    },
    DUCHY {
      @Override
      public void play(GameStateInfo gameStateInfo) {
        //Not an action or money card.Counts as points at end of game

      }
    },
    PROVINCE {
      @Override
      public void play(GameStateInfo gameStateInfo) {
        //Not an action or money card.Counts as points at end of game
      }
    },

    CELLAR {
      @Override
      public void play(GameStateInfo gameStateInfo) {
        //discard any number of cards from hand, redraw that many cards
        int numDiscarded = additionalCards.size();
        for (int i = 0; i < numDiscarded; i++) {
          gameStateInfo.discardCard(additionalCards.get(i));
        }
        for (int i = 0; i < numDiscarded; i++) {
          DrawPile drawPile = gameStateInfo.getCurrentPlayerStateInfo().getDrawPile();
          Hand newHand = gameStateInfo.getCurrentPlayerStateInfo().getHand().draw(drawPile);
          gameStateInfo.getCurrentPlayerStateInfo().setHand(newHand);
        }
        int actionsRemaining = gameStateInfo.getCurrentPlayerStateInfo().getTurn().getActionsRemaining() - 1;
        gameStateInfo.getCurrentPlayerStateInfo().getTurn().setActionsRemaining(actionsRemaining + 1);

      }
    },
    MOAT {
      @Override
      public void play(GameStateInfo gameStateInfo) {
        DrawPile drawPile = gameStateInfo.getCurrentPlayerStateInfo().getDrawPile();
        Hand newHand = gameStateInfo.getCurrentPlayerStateInfo().getHand().draw(drawPile);
        gameStateInfo.getCurrentPlayerStateInfo().setHand(newHand);

        DrawPile drawPile = gameStateInfo.getCurrentPlayerStateInfo().getDrawPile();
        Hand newHand = gameStateInfo.getCurrentPlayerStateInfo().getHand().draw(drawPile);
        gameStateInfo.getCurrentPlayerStateInfo().setHand(newHand);

        int actionsRemaining = gameStateInfo.getCurrentPlayerStateInfo().getTurn().getActionsRemaining() - 1;
        gameStateInfo.getCurrentPlayerStateInfo().getTurn().setActionsRemaining(actionsRemaining);
      }
    },

    MARKET {
      @Override
      public void play(GameStateInfo gameStateInfo) {
        DrawPile drawPile = gameStateInfo.getCurrentPlayerStateInfo().getDrawPile();
        Hand newHand = gameStateInfo.getCurrentPlayerStateInfo().getHand().draw(drawPile);
        gameStateInfo.getCurrentPlayerStateInfo().setHand(newHand);

        int actionsRemaining = gameStateInfo.getCurrentPlayerStateInfo().getTurn().getActionsRemaining() - 1;
        gameStateInfo.getCurrentPlayerStateInfo().getTurn().setActionsRemaining(actionsRemaining + 1);

        //  playerInfo.addBuy();
        int buysRemaining = gameStateInfo.getCurrentPlayerStateInfo().getTurn().getBuysRemaining();
        gameStateInfo.getCurrentPlayerStateInfo().getTurn().setBuysRemaining(buysRemaining + 1);

        //  playerInfo.addBuyingPower();\
        int buyingPower = gameStateInfo.getCurrentPlayerStateInfo().getTurn().getBuyingPower();
        gameStateInfo.getCurrentPlayerStateInfo().getTurn().setBuyingPower(buyingPower + 1);
      }
    },

    MERCHANT {
      @Override
      public void play(GameStateInfo gameStateInfo) {
        DrawPile drawPile = gameStateInfo.getCurrentPlayerStateInfo().getDrawPile();
        Hand newHand = gameStateInfo.getCurrentPlayerStateInfo().getHand().draw(drawPile);
        gameStateInfo.getCurrentPlayerStateInfo().setHand(newHand);

        int actionsRemaining = gameStateInfo.getCurrentPlayerStateInfo().getTurn().getActionsRemaining() - 1;
        gameStateInfo.getCurrentPlayerStateInfo().getTurn().setActionsRemaining(actionsRemaining + 1);

        gameStateInfo.getCurrentPlayerStateInfo().getTurn().addGoldIfSilver();
        //TODO add gold when playing silver

      }
    },
    MILITIA {
      @Override
      public void play(GameStateInfo gameStateInfo) {
        int actionsRemaining = gameStateInfo.getCurrentPlayerStateInfo().getTurn().getActionsRemaining() - 1;
        gameStateInfo.getCurrentPlayerStateInfo().getTurn().setActionsRemaining(actionsRemaining);

        int buyingPower = gameStateInfo.getCurrentPlayerStateInfo().getTurn().getBuyingPower();
        gameStateInfo.getCurrentPlayerStateInfo().getTurn().setBuyingPower(buyingPower + 2);
      }
    },

    MINE {
      @Override
      public void play(GameStateInfo gameStateInfo) {
        //TODO make sure additional cards has a card in it (how to hand error state)
        gameStateInfo.trashCard(additionalCards.get(0));
        gameStateInfo.getTreasure();

        int actionsRemaining = gameStateInfo.getCurrentPlayerStateInfo().getTurn().getActionsRemaining() - 1;
        gameStateInfo.getCurrentPlayerStateInfo().getTurn().setActionsRemaining(actionsRemaining);

        //TODO Gain a Treasure to your hand costing up to 3 more than it
      }
    },

    REMODEL {
      @Override
      public void play(GameStateInfo gameStateInfo) {
        //TODO make sure additional cards has a card in it (how to hand error state)
        gameStateInfo.trashCard(additionalCards.get(0));

        int actionsRemaining = gameStateInfo.getCurrentPlayerStateInfo().getTurn().getActionsRemaining() - 1;
        gameStateInfo.getCurrentPlayerStateInfo().getTurn().setActionsRemaining(actionsRemaining);

        //TODO Gain a card costing up to 2 more gold than the one you trashed.

      }
    },

    SMITHY {
      @Override
      public void play(GameStateInfo gameStateInfo) {
        DrawPile drawPile = gameStateInfo.getCurrentPlayerStateInfo().getDrawPile();
        Hand newHand = gameStateInfo.getCurrentPlayerStateInfo().getHand().draw(drawPile);
        gameStateInfo.getCurrentPlayerStateInfo().setHand(newHand);

        drawPile = gameStateInfo.getCurrentPlayerStateInfo().getDrawPile();
        newHand = gameStateInfo.getCurrentPlayerStateInfo().getHand().draw(drawPile);
        gameStateInfo.getCurrentPlayerStateInfo().setHand(newHand);

        drawPile = gameStateInfo.getCurrentPlayerStateInfo().getDrawPile();
        newHand = gameStateInfo.getCurrentPlayerStateInfo().getHand().draw(drawPile);
        gameStateInfo.getCurrentPlayerStateInfo().setHand(newHand);

        int actionsRemaining = gameStateInfo.getCurrentPlayerStateInfo().getTurn().getActionsRemaining() - 1;
        gameStateInfo.getCurrentPlayerStateInfo().getTurn().setActionsRemaining(actionsRemaining);
      }
    },

    VILLAGE {
      @Override
      public void play(GameStateInfo gameStateInfo) {
        DrawPile drawPile = gameStateInfo.getCurrentPlayerStateInfo().getDrawPile();
        Hand newHand = gameStateInfo.getCurrentPlayerStateInfo().getHand().draw(drawPile);
        gameStateInfo.getCurrentPlayerStateInfo().setHand(newHand);

        int actionsRemaining = gameStateInfo.getCurrentPlayerStateInfo().getTurn().getActionsRemaining() - 1;
        gameStateInfo.getCurrentPlayerStateInfo().getTurn().setActionsRemaining(actionsRemaining + 2);


      }
    },
    WORKSHOP {
      @Override
      public void play(GameStateInfo gameStateInfo) {
        int actionsRemaining = gameStateInfo.getCurrentPlayerStateInfo().getTurn().getActionsRemaining() - 1;
        gameStateInfo.getCurrentPlayerStateInfo().getTurn().setActionsRemaining(actionsRemaining);

        //TODO Gain card costing up to 4 gold
      }
    };

    public abstract void play(GameStateInfo gameStateInfo);
  }
}

