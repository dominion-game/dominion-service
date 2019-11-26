package edu.cnm.deepdive.dominionservice.model.dao;
import edu.cnm.deepdive.dominionservice.model.entity.Stack;
import edu.cnm.deepdive.dominionservice.model.entity.Stack.StackType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StackRepository extends JpaRepository<Stack,Long> {

  Stack save(Stack stack);





  void delete(Stack stack);

  void deleteAll();


  List<Stack> getAllByGameId(long gameId);


  Optional<Stack> findById(Long aLong);

  boolean existsById(Long aLong);

  long count();

  void deleteById(Long aLong);

  void deleteAll(Iterable<? extends Stack> iterable);
}