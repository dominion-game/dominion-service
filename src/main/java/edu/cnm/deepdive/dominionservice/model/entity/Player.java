package edu.cnm.deepdive.dominionservice.model.entity;

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
  @JoinColumn(name = "game_id", nullable = false, updatable = false)
  private Game game;
}
