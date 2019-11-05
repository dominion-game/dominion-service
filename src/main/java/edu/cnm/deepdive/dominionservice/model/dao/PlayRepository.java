package edu.cnm.deepdive.dominionservice.model.dao;

import edu.cnm.deepdive.dominionservice.model.entity.Play;
import edu.cnm.deepdive.dominionservice.model.entity.Turn;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PlayRepository extends CrudRepository<Play,Long> {

  List<Play> save(Play play);

  Play findPlayByTurn(Turn turn);

  @Override
  void deleteAll();
}