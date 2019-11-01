package edu.cnm.deepdive.dominionservice.model.dao;

import edu.cnm.deepdive.dominionservice.model.entity.Location;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location,Long> {

  List<Location> save(Location location);

  Location findLocationByStackId(int stackId);

  @Override
  void delete(Location location);

  @Override
  void deleteAll();
}
