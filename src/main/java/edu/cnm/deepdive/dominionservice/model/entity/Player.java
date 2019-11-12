package edu.cnm.deepdive.dominionservice.model.entity;

import edu.cnm.deepdive.dominionservice.model.pojo.DiscardPile;
import java.util.ArrayList;
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
 * The type Player.
 */
@Entity
public class Player {
  @Id
  @GeneratedValue
  @Column(name = "player_id", updatable = false, nullable = false)
  private Long id;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name="game_id", nullable = false, updatable = false)
  private Game game;

  @Column
  private int playerScore;

  @Column
  private int whoseTurn;

  private int numAction;

  private int numBuy;

  private int extraGold;

  private List<Card> playerHand;

  @OneToMany(mappedBy="turn", cascade=CascadeType.ALL)
  private ArrayList<Turn> turns = new ArrayList<>();

  @OneToMany(mappedBy="location", cascade=CascadeType.ALL)
  private ArrayList<Location> locations = new ArrayList<>();

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


  /**@OneToMany(mappedBy= "deck", cascade = CascadeType.ALL)
  private List<Card> deck = new LinkedList<>();

  @OneToMany(mappedBy= "player", cascade = CascadeType.ALL)
  private List<Card> discard = new LinkedList<>();

  @OneToMany(mappedBy= "player", cascade = CascadeType.ALL)
  private List<Card> hand = new LinkedList<>();
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
    this.whoseTurn = (int) whoseTurn;}

  public void setId(Long id) {
    this.id = id;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public void setWhoseTurn(int whoseTurn) {
    this.whoseTurn = whoseTurn;
  }

  public List<Turn> getTurns() {
    return turns;
  }

  public void setTurns(List<Turn> turns) {
    this.turns = turns;
  }

  public ArrayList<Location> getLocations() {
    return locations;
  }

  public void setLocations(
      ArrayList<Location> locations) {
    this.locations = locations;
  }

  public PlayerState getPlayerState() {
    return playerState;
  }

  public void setPlayerState(PlayerState playerState) {
    this.playerState = playerState;
  }

  /** public void setDeck(List<Card> deck) {
    this.deck = deck;
  }

  public void setDiscard(List<Card> discard) {
    this.discard = discard;
  }

  public void setHand(List<Card> hand) {
    this.hand = hand;
  }
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
   * public List<Card> getDeck() { return deck; } public List<Card> getDiscard() { return discard; }
   * public List<Card> getHand() { return hand; }
   *
   * @return the num action
   */
  public int getNumAction() {
    return numAction;
  }

  /**
   * Sets num action.
   *
   * @param numAction the num action
   */
  public void setNumAction(int numAction) {
    this.numAction = numAction;
  }

  /**
   * Gets player's number of buys.
   *
   * @return the player's number of buys.
   */
  public int getNumBuy() {
    return numBuy;
  }

  /**
   * Sets num buy.
   *
   * @param numBuy the num buy
   */
  public void setNumBuy(int numBuy) {
    this.numBuy = numBuy;
  }

  public List<Card> getPlayerHand() {
    return playerHand;
  }

  public void setPlayerHand(List<Card> playerHand) {
    this.playerHand = playerHand;
  }
 public enum PlayerState{
   MY_TURN,
   WATCHING,
   MILITIA_RESPONSE,
   ACTION;

   public DiscardPile getDiscardPile() {
   }
 }

//  public static class Hand {
//
//  }
  private void shuffleDrawPile(){
    //TODO
  }
  private void checkDrawPile(){
    if (drawPile.size() ==0){
      //add discard to draw (remove cards from discard, add to drawPile)
      while (discard.size() > 0){
        drawPile.add(discard.remove(0));
      }
      shuffleDrawPile();
    }
  }

  public void drawCard(){
    //takes a card from the players drawPile and adds to hand
    //first remove a card from the drawPile
    //first make sure we have something to draw
    checkDrawPile();
    Card newCard = drawPile.remove(0);
    hand.add(newCard);
  }

  public void addAction(){
    numAction++;
  }

  public void addBuy(){
    numBuy++;
  }

  public void addGold(){
    extraGold++;
  }

  public void playMilitia(){
    //TODO tell game to switch states, to all engage all players.
    // other players discard down to 3 or play Moat
  }

  public void reactToMilitia(){
    //TODO other players discard down to 3 or play Moat
  }

}