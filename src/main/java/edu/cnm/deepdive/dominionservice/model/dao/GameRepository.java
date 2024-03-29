package edu.cnm.deepdive.dominionservice.model.dao;


import edu.cnm.deepdive.dominionservice.model.entity.Game;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

  Game getGameById(long id);

}