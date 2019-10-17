package edu.cnm.deepdive.dominionservice.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.lang.NonNull;

@Entity
public class Stack {

  @Id
  @GeneratedValue
  @Column(name = "stack_id", updatable = false, nullable = false)
  private Long id;

  @NonNull
  @ManyToOne
  @JoinColumn(nullable = false, updatable = false)
  private Game game;

  @Column
  private boolean isTrash;

  @Column
  private String stackType;

  @Column
  private int stackCount;

  public Long getId() {
    return id;
  }

  public Game getGame() {
    return game;
  }

  public boolean isTrash() {
    return isTrash;
  }

  public void setTrash(boolean trash) {
    isTrash = trash;
  }

  public String getStackType() {
    return stackType;
  }

  public void setStackType(String stackType) {
    this.stackType = stackType;
  }

  public int getStackCount() {
    return stackCount;
  }

  public void setStackCount(int stackCount) {
    this.stackCount = stackCount;
  }
}
