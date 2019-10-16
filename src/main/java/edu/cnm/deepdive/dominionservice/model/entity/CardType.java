package edu.cnm.deepdive.dominionservice.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.springframework.lang.NonNull;

@Entity
public class CardType {
  @Id
  @GeneratedValue
  @Column(name = "card_type_id", updatable = false, nullable = false)
  private Long id;

  @NonNull
  @OneToOne
  @JoinColumn(name= "card_id", updatable = false, nullable = false)
  private Card card;
}
