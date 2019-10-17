package edu.cnm.deepdive.dominionservice.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.lang.NonNull;

@Entity
@Table(
    indexes = {@Index(columnList = "stackType"),
    @Index(columnList = "stackCount")}
)
public class Stack {

  @Id
  @GeneratedValue
  @Column(name = "stack_id", updatable = false, nullable = false)
  private Long id;

  @NonNull
  @ManyToOne
  @JoinColumn(nullable = false, updatable = false)
  private Game game;

  @Column(name="is_trash")
  private boolean isTrash;

  @Column(name="stack_type")
  private String stackType;

  @Column(name="stack_count")
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
