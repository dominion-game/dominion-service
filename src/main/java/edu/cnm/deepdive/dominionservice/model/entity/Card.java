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

  @NonNull
  @ManyToOne
  @JoinColumn(nullable = false, updatable = false)
  private Player player;

  @NonNull
  @ManyToOne
  @JoinColumn(nullable = false, updatable = false)
  private Stack stack;


}
