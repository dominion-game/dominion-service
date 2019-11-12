package edu.cnm.deepdive.dominionservice.model.entity;

import edu.cnm.deepdive.dominionservice.model.dto.GameStateInfo;
import edu.cnm.deepdive.dominionservice.model.pojo.DrawPile;
import java.util.List;
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
      public void play(PlayerInfo playerInfo,
          List<Card> additionalCards) {
        playerInfo.addBuyingPower();
      }
    },
    SILVER {
      @Override
      public void play(PlayerInfo playerInfo,
          List<Card> additionalCards) {
        playerInfo.addBuyingPower(3);
      }
    },
    GOLD {
      @Override
      public void play(PlayerInfo playerInfo,
          List<Card> additionalCards) {
        playerInfo.addBuyingPower(6);
      }
    },
    ESTATE {
      @Override
      public void play(PlayerInfo playerInfo,
          List<Card> additionalCards) {
        //Not an action or money card.Counts as points at end of game
      }
    },
    DUCHY {
      @Override
      public void play(PlayerInfo playerInfo,
          List<Card> additionalCards) {
        //Not an action or money card.Counts as points at end of game

      }
    },
    PROVINCE {
      @Override
      public void play(PlayerInfo playerInfo,
          List<Card> additionalCards) {
        //Not an action or money card.Counts as points at end of game
      }
    },

    CELLAR {
      @Override
      public void play(PlayerInfo playerInfo, List<Card> additionalCards) {
        //discard any number of cards from hand, redraw that many cards
        int numDiscarded = additionalCards.size();
        for (int i = 0; i < numDiscarded; i++) {
          playerInfo.discardCard(additionalCards.get(i));
        }
        for (int i = 0; i < numDiscarded ; i++) {
          playerInfo.drawCard();
        }
        playerInfo.decrementActionsRemaining();
      }
    },
    MOAT {
      @Override
      public void play(PlayerInfo playerInfo, List<Card> additionalCards) {
        playerInfo.drawCard();
        playerInfo.drawCard();
        playerInfo.decrementActionsRemaining();
        //TODO Add functionality to respond to militia
      }
    },

    MARKET {
      @Override
      public void play(PlayerInfo playerInfo, List<Card> additionalCards) {
        playerInfo.drawCard();
        playerInfo.addAction();
        playerInfo.addBuy();
        playerInfo.addBuyingPower();
        playerInfo.decrementActionsRemaining();
      }
    },

    MERCHANT {
      @Override
      public void play(PlayerInfo playerInfo, List<Card> additionalCards) {
        playerInfo.drawCard();
        playerInfo.addAction();
        playerInfo.addGoldIfSilver();
        playerInfo.decrementActionsRemaining();
        //TODO add gold when playing silver

      }
    },
    MILITIA {
      @Override
      public void play(PlayerInfo playerInfo, List<Card> additionalCards) {
        playerInfo.addBuyingPower();
        playerInfo.addBuyingPower();
        playerInfo.decrementActionsRemaining();
        //TODO implement Militia method

      }
    },
    MINE {
      @Override
      public void play(PlayerInfo playerInfo, List<Card> additionalCards) {
        //TODO make sure additional cards has a card in it (how to hand error state)
        playerInfo.trashCard(additionalCards.get(0));
        playerInfo.getTreasure();
        playerInfo.decrementActionsRemaining();
        //TODO Gain a Treasure to your hand costing up to 3 more than it
      }
    },

    REMODEL {
      @Override
      public void play(PlayerInfo playerInfo, List<Card> additionalCards) {
        //TODO make sure additional cards has a card in it (how to hand error state)
        playerInfo.trashCard(additionalCards.get(0));
        playerInfo.decrementActionsRemaining();
        //TODO Gain a card costing up to 2 more gold than the one you trashed.

      }
    },

    SMITHY {
      @Override
      public void play(PlayerInfo playerInfo, List<Card> additionalCards) {
        playerInfo.drawCard();
        playerInfo.drawCard();
        playerInfo.drawCard();
        playerInfo.decrementActionsRemaining();

      }
    },

    VILLAGE {
      @Override
      public void play(GameStateInfo gameStateInfo, List<Card> additionalCards) {
        DrawPile drawPile = gameStateInfo.getCurrentPlayerStateInfo().getDrawPile();
        gameStateInfo.getCurrentPlayerStateInfo().getHand().draw(drawpile);
        int actionsRemaining = gameStateInfo.getCurrentPlayerStateInfo().getTurn().getActionsRemaining();
        gameStateInfo.getCurrentPlayerStateInfo().getTurn().setActionsRemaining(actionsRemaining+2);
        playerInfo.decrementActionsRemaining();

      }
    },
    WORKSHOP {
      @Override
      public void play(PlayerInfo playerInfo, List<Card> additionalCards) {
        playerInfo.decrementActionsRemaining();
        //TODO Gain card costing up to 4 gold
      }
    };

    public abstract void play(GameStateInfo gameStateInfo, List<Card> additionalCards);
  }
}
