package edu.cnm.deepdive.dominionservice.model.dao;

import edu.cnm.deepdive.dominionservice.model.entity.Turn;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface TurnRepository extends CrudRepository<Turn,Long> {

  List<Turn> save(Turn turn);

  Turn findTurnById(Long id);

  @Override
  void deleteAll();
}
