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

  @OneToMany(mappedBy="turn", cascade=CascadeType.ALL)
  private List<Turn> turns = new LinkedList<>();

  @OneToMany(mappedBy="location", cascade=CascadeType.ALL)
  private List<Location> locations = new LinkedList<>();

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

  public List<Location> getLocations() {
    return locations;
  }

  public void setLocations(
      List<Location> locations) {
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

  public Game getGame() {
    return game;
  }

  public int getPlayerScore() {
    return playerScore;
  }

  public int getWhoseTurn() {
    return whoseTurn;
  }

 /** public List<Card> getDeck() {
    return deck;
  }

  public List<Card> getDiscard() {
    return discard;
  }

  public List<Card> getHand() {
    return hand;
  }
*/

 public enum PlayerState{
   MY_TURN,
   WATCHING,
   MILITIA_RESPONSE,
   ACTION;
 }

  public static class Hand {

  }
}
