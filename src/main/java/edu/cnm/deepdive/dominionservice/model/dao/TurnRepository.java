package edu.cnm.deepdive.dominionservice.model.dao;
import edu.cnm.deepdive.dominionservice.model.entity.Turn;
import edu.cnm.deepdive.dominionservice.model.entity.Turn.TurnState;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface TurnRepository extends CrudRepository<Turn,Long> {

  List<Turn> save(Turn turn);

  Turn getTurnById(Long id);
  

  @Override
  void deleteAll();

  TurnState getTurnState(Long id);

  Iterable<Turn> getAllByOrderByKeyAsc();
}