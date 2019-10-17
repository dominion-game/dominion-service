package edu.cnm.deepdive.dominionservice.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.lang.NonNull;

@Entity
public class Card {
  @Id
  @GeneratedValue
  @Column(name = "card_id", updatable = false, nullable = false)
  private Long id;
  //why object?

  @NonNull
  @ManyToOne
  @JoinColumn(nullable = false, updatable = false)
  private Player player;

  @NonNull
  @ManyToOne
  @JoinColumn(nullable = false, updatable = false)
  private Stack stack;


  @NonNull
  private String state;
  //in player (deck, hand, discard), trash (isTrash stack), stack

  @NonNull
  private String type;
  //money, action, victory point---possibly unnecessary

  @NonNull
  private String name;
  //name of card




}
