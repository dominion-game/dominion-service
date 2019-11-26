package edu.cnm.deepdive.dominionservice.model.dao;

import edu.cnm.deepdive.dominionservice.model.entity.DiscardPile;
import edu.cnm.deepdive.dominionservice.model.entity.DrawPile;
import edu.cnm.deepdive.dominionservice.model.entity.Game;
import edu.cnm.deepdive.dominionservice.model.entity.Player;
import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

public interface DiscardPileRepository extends CrudRepository<DiscardPile, Integer> {


  DiscardPile getLastByPlayer(Player player);

}
