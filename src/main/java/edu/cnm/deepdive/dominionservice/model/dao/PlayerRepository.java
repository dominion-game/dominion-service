package edu.cnm.deepdive.dominionservice.model.dao;

import edu.cnm.deepdive.dominionservice.model.entity.Player;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player,Long> {

  List<Player> save(Player player);

  Player findPlayerById(Long id);

  @Override
  void deleteAll();
}
