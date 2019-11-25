package edu.cnm.deepdive.dominionservice.model.dao;

import edu.cnm.deepdive.dominionservice.model.entity.Play;
import edu.cnm.deepdive.dominionservice.model.entity.Turn;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayRepository extends JpaRepository<Play,Long> {

  public <S extends Play> Iterable<S> save(Play... plays);

  <S extends Play> Iterable<S> findPlayByTurn(Turn turn);

  Iterable<Play> getAllByTurn(Turn turn);

  @Override
  void deleteAll();


  @Override
  Optional<Play> findById(Long aLong);

  @Override
  boolean existsById(Long aLong);


  @Override
  long count();

  @Override
  void deleteById(Long aLong);

  @Override
  void delete(Play play);

  @Override
  void deleteAll(Iterable<? extends Play> iterable);
}