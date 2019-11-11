package edu.cnm.deepdive.dominionservice.model.entity;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.lang.NonNull;

/**
 * The type Player info.
 */
@Entity
public class PlayerInfo {

  @Id
  @GeneratedValue
  @Column(name = "player_id", updatable = false, nullable = false)
  private Long id;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "game_id", nullable = false, updatable = false)
  private Game game;


  @Column
  private int playerScore;

  @Column
  private int whoseTurn;

  private int actionsRemaining;

  private int buysRemaining;

  private int buyingPower;
  //TODO how to handle this

  private int extraGoldIfSilver;

  //@OneToMany(mappedBy = "turn", cascade = CascadeType.ALL)
  //private List<Turn> turns = new LinkedList<>();

//  //@OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
//  private List<Location> locations = new LinkedList<>();

  /**
   * Sets player score.
   *
   * @param playerScore the player score
   * @OneToMany(mappedBy= "deck", cascade = CascadeType.ALL)private List<Card> deck = new
   * LinkedList<>();
   * @OneToMany(mappedBy= "player", cascade = CascadeType.ALL)private List<Card> discard = new
   * LinkedList<>();
   * @OneToMany(mappedBy= "player", cascade = CascadeType.ALL)private List<Card> hand = new
   * LinkedList<>();
   */
  @Column
  private PlayerState playerState;

//  @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL)
//  private List<Card> deck = new LinkedList<>();

  @OneToMany(mappedBy = "drawPile", cascade = CascadeType.ALL)
  private List<Card> drawPile = new LinkedList<>();

  @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
  private List<Card> discard = new LinkedList<>();

  @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
  private List<Card> hand = new LinkedList<>();

//  //may not need to know about trash (it would probably be easier)
  @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
  private List<Card> trash = new LinkedList<>();

  /**
   * Sets player score.
   *
   * @param playerScore the player score
   */
  public void setPlayerScore(int playerScore) {
    this.playerScore = playerScore;
  }

  /**
   * Sets whose turn.
   *
   * @param whoseTurn the whose turn
   */
  public void setWhoseTurn(long whoseTurn) {
    this.whoseTurn = (int) whoseTurn;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Sets game.
   *
   * @param game the game
   */
  public void setGame(Game game) {
    this.game = game;
  }

  /**
   * Sets whose turn.
   *
   * @param whoseTurn the whose turn
   */
  public void setWhoseTurn(int whoseTurn) {
    this.whoseTurn = whoseTurn;
  }

//  public List<Turn> getTurns() {
//    return turns;
//  }
//
//  public void setTurns(List<Turn> turns) {
//    this.turns = turns;
//  }

  /**
   * Gets player state.
   *
   * @return the player state
   */
  public PlayerState getPlayerState() {
    return playerState;
  }

  /**
   * Sets player state.
   *
   * @param playerState the player state
   */
  public void setPlayerState(PlayerState playerState) {
    this.playerState = playerState;
  }

  /**
   * public void setDiscard(List<Card> discard) { this.discard = discard; }
   * <p>
   * public void setHand(List<Card> hand) { this.hand = hand; }
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
   * Gets player score.
   *
   * @return the player score
   */
  public int getPlayerScore() {
    return playerScore;
  }

  /**
   * Gets whose turn.
   *
   * @return the whose turn
   */
  public int getWhoseTurn() {
    return whoseTurn;
  }

  /**
   * gets the number of actions remaining
   *
   * @return the number of actions remaining
   */
  public int getActionsRemaining() {
    return actionsRemaining;
  }

  /**
   * Sets num action.
   *
   * @param actionsRemaining sets number of actions remaining
   */
  public void setActionsRemaining(int actionsRemaining) {
    this.actionsRemaining = actionsRemaining;
  }

  /**
   * Decrement actions remaining.
   */
  public void decrementActionsRemaining() {
    actionsRemaining--;
  }

  /**
   * Gets player's number of buys.
   *
   * @return the player's number of buys.
   */
  public int getBuysRemaining() {
    return buysRemaining;
  }

  /**
   * Sets number of buys remaining.
   *
   * @param buysRemaining the number of buys remaining
   */
  public void setBuysRemaining(int buysRemaining) {
    this.buysRemaining = buysRemaining;
  }

  /**
   * Decrement buys remaining.
   */
  public void decrementBuysRemaining() {
    buysRemaining--;
  }

  /**
   * Gets hand.
   *
   * @return the hand
   */
  public List<Card> getHand() {
    return hand;
  }

  /**
   * Sets hand.
   *
   * @param hand the hand
   */
  public void setHand(List<Card> hand) {
    this.hand = hand;
  }

  /**
   * The enum Player state.
   */
  public enum PlayerState {
    /**
     * My turn player state.
     */
    MY_TURN,
    /**
     * Watching player state.
     */
    WATCHING,
    /**
     * Militia response player state.
     */
    MILITIA_RESPONSE,
    /**
     * Action player state.
     */
    ACTION;
  }

  //  public static class Hand {
//
//  }
  private void shuffleDrawPile() {

    //TODO
  }

  private void checkDrawPile() {
    if (drawPile.size() == 0) {
      //add discard to draw (remove cards from discard, add to drawPile)
      while (discard.size() > 0) {
        drawPile.add(discard.remove(0));
      }
      shuffleDrawPile();
    }
  }

  /**
   * Draw card.
   */
  public void drawCard() {
    //takes a card from the players drawPile and adds to hand
    //first remove a card from the drawPile
    //first make sure we have something to draw
    checkDrawPile();
    Card newCard = drawPile.remove(0);
    hand.add(newCard);
  }

  /**
   * Add action.
   */
  public void addAction() {
    actionsRemaining++;
  }

  /**
   * Add buy.
   */
  public void addBuy() {
    buysRemaining++;
  }

  /**
   * Add buying power.
   */
  public void addBuyingPower() {
    buyingPower++;
  }

  /**
   * Add buying power.
   *
   * @param amountToAdd the amount to add
   */
  public void addBuyingPower(int amountToAdd) {
    buyingPower += amountToAdd;
  }

  /**
   * Add gold if silver.
   */
  public void addGoldIfSilver() {
    extraGoldIfSilver++;
  }


  /**
   * Play militia.
   */
  public void playMilitia() {
    //TODO set flag for attacked player to discard at beginning of next turn
  }

  /**
   * React to militia.
   */
  public void reactToMilitia() {
    //TODO other players discard down to 3 or play Moat
  }

//  public void trashCard(Card c) {
//    trash.add(c);
//  }

  public void trashCard(Card cardToTrash) {
    int indexToRemove = -1;
    for (int i = 0; i < hand.size(); i++) {
      if (hand.get(i).getCardType() == cardToTrash.getCardType()) {
        indexToRemove = i;
        break;
      }
    }
      hand.remove(indexToRemove);
      trash.add(cardToTrash);
  }

  /**
   * Gets treasure.
   */
  public void getTreasure() {
    //TODO implement this method (might need game info state)
  }

  /**
   * Discards one card. This method allows the client side to pick a card to discard. The server
   * verifies that it is a card in the player's hand. If it is, it removes it from the player's hand
   * and adds it to the player's discard pile.
   *
   * @param cardToDiscard the card to discard
   */
  public void discardCard(Card cardToDiscard) {
    int indexToRemove = -1;
    for (int i = 0; i < hand.size(); i++) {
      if (hand.get(i).getCardType() == cardToDiscard.getCardType()) {
        indexToRemove = i;
        break;
      }
    }
    if (indexToRemove < 0) {
      throw new IllegalArgumentException("Card not found in player's hand: " + cardToDiscard);
    }
    hand.remove(indexToRemove);
    discard.add(cardToDiscard);
  }

  /**
   * Discards multiple cards. Calls discardCard method for each card in this list.
   *
   * @param cardsToDiscard
   */
  public void discardCards(List<Card> cardsToDiscard) {
    for (int i = 0; i < cardsToDiscard.size(); i++) {
      discardCard(cardsToDiscard.get(i));
    }
  }
}

