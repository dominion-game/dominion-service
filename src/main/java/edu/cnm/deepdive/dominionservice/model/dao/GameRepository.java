package edu.cnm.deepdive.dominionservice.model.dao;

import edu.cnm.deepdive.dominionservice.model.entity.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository {

  Game save(Game game);

  void delete(Game game);
}
