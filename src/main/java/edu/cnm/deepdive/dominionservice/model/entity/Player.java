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

  public void setWhoseTurn(long whoseTurn) {
    this.whoseTurn = (int) whoseTurn;
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

}
