package edu.cnm.deepdive.dominionservice.model.dao;


import edu.cnm.deepdive.dominionservice.model.entity.Player;
import edu.cnm.deepdive.dominionservice.model.entity.Turn;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {


  Optional<Player> findPlayerById(Long id);

  @Override
  void deleteAll();

  Optional<Player> getPlayerById(int i);

}