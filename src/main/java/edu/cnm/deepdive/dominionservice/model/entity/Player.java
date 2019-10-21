package edu.cnm.deepdive.dominionservice.model.entity;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
  @ManyToOne
  @JoinColumn(nullable = false, updatable = false)
  private Game game;

  @Column
  private int playerScore;

  @Column
  private long whoseTurn;

  @OneToMany(mappedBy= "player", cascade = CascadeType.ALL)
  private List<Card> deck = new LinkedList<>();

  @OneToMany(mappedBy= "player", cascade = CascadeType.ALL)
  private List<Card> discard = new LinkedList<>();

  public void setPlayerScore(int playerScore) {
    this.playerScore = playerScore;
  }

  public void setWhoseTurn(long whoseTurn) {
    this.whoseTurn = whoseTurn;
  }

  public void setDeck(List<Card> deck) {
    this.deck = deck;
  }

  public void setDiscard(List<Card> discard) {
    this.discard = discard;
  }

  public void setHand(List<Card> hand) {
    this.hand = hand;
  }

  public Long getId() {
    return id;
  }

  public Game getGame() {
    return game;
  }

  public int getPlayerScore() {
    return playerScore;
  }

  public long getWhoseTurn() {
    return whoseTurn;
  }

  public List<Card> getDeck() {
    return deck;
  }

  public List<Card> getDiscard() {
    return discard;
  }

  public List<Card> getHand() {
    return hand;
  }

  @OneToMany(mappedBy= "player", cascade = CascadeType.ALL)
  private List<Card> hand = new LinkedList<>();
}
