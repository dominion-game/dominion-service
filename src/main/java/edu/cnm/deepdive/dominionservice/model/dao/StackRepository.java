package edu.cnm.deepdive.dominionservice.model.dao;
import edu.cnm.deepdive.dominionservice.model.entity.Stack;
import edu.cnm.deepdive.dominionservice.model.entity.Stack.StackType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StackRepository extends CrudRepository<Stack,Long> {

  List<Stack> save(Stack stack);

  List<Stack> getAllByGame_Id();

  List<Stack> findStackByStackType(StackType stackType);

  List<StackType> getAllByStackType();

  List<Integer> getAllByStackCount();

  void delete(Stack stack);

  void deleteAll();

  int getCountByStackType();

  int findAllByGameAndStackCountIsZero();

  List<Stack> getAllByGameId(long gameId);

  <S extends Stack> Iterable<S> saveAll(Iterable<S> iterable);

  Optional<Stack> findById(Long aLong);

  boolean existsById(Long aLong);

  Iterable<Stack> findAll();

  Iterable<Stack> findAllById(Iterable<Long> iterable);

  long count();

  void deleteById(Long aLong);

  void deleteAll(Iterable<? extends Stack> iterable);
}