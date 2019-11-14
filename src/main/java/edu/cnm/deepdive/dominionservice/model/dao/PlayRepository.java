package edu.cnm.deepdive.dominionservice.model.dao;

import edu.cnm.deepdive.dominionservice.model.entity.Play;
import edu.cnm.deepdive.dominionservice.model.entity.Turn;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayRepository extends CrudRepository<Play,Long> {

  List<Play> save(Play play);

  Play findPlayByTurn(Turn turn);

  List<Play> getAllByTurn(Turn turn);

  @Override
  void deleteAll();

  @Override
  <S extends Play> Iterable<S> saveAll(Iterable<S> iterable);

  @Override
  Optional<Play> findById(Long aLong);

  @Override
  boolean existsById(Long aLong);

  @Override
  Iterable<Play> findAll();

  @Override
  Iterable<Play> findAllById(Iterable<Long> iterable);

  @Override
  long count();

  @Override
  void deleteById(Long aLong);

  @Override
  void delete(Play play);

  @Override
  void deleteAll(Iterable<? extends Play> iterable);
}