package edu.cnm.deepdive.dominionservice.model.entity;

import java.util.LinkedList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

  @Column()
  private int playerScore;

  @Column
  private String whoseTurn;

  private LinkedList<Card> deck;

  private LinkedList<Card> discard;

  private LinkedList<Card> hand;
}
