package edu.cnm.deepdive.dominionservice.model.dao;
import edu.cnm.deepdive.dominionservice.model.entity.Turn;
import edu.cnm.deepdive.dominionservice.model.entity.Turn.TurnState;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnRepository extends CrudRepository<Turn,Long> {

  List<Turn> save(Turn turn);

  Turn getTurnById(Long id);
  

  @Override
  void deleteAll();

  TurnState getTurnState(Long id);

  Iterable<Turn> getAllByOrderByKeyAsc();

  TurnState getCurrentTurnState();

  Turn getCurrentTurn();

  <S extends Turn> Iterable<S> saveAll(Turn turn);

  Optional<Turn> findById(int turnId);

  boolean existsById(int turnId);

  Iterable<Turn> findAll();


  long count();

  void deleteById(int id);

  void delete(Turn turn);

  void deleteAll(Iterable<? extends Turn> iterable);
}