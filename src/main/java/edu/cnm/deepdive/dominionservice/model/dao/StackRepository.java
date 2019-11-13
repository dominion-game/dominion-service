package edu.cnm.deepdive.dominionservice.model.dao;
import edu.cnm.deepdive.dominionservice.model.entity.Stack;
import edu.cnm.deepdive.dominionservice.model.entity.Stack.StackType;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface StackRepository extends CrudRepository<Stack,Long> {

  List<Stack> save(Stack stack);

  List<Stack> getAllByGame_Id();

  List<Stack> findStackByStackType(StackType stackType);

  List<StackType> getAllByStackType();

  List<Integer> getAllByStackCount();

  @Override
  void delete(Stack stack);

  @Override
  void deleteAll();

  int getCountByStackType();

  int findAllByGameAndStackCountIsZero();

  List<Stack> getAllByGameId(long gameId);
}