package edu.cnm.deepdive.dominionservice.model.dao;
import edu.cnm.deepdive.dominionservice.model.entity.Play;
import edu.cnm.deepdive.dominionservice.model.entity.Turn;
import edu.cnm.deepdive.dominionservice.model.entity.Turn.TurnState;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnRepository extends JpaRepository<Turn,Long> {



  Turn getTurnById(Long id);
  


  Iterable<Turn> getAllByOrderByIdAsc();
  ArrayList<Turn> getAllByOrderByIdDesc();





  boolean existsById(int turnId);



  long count();

  void deleteById(int id);

  void delete(Turn turn);

  void deleteAll(Iterable<? extends Turn> iterable);
}