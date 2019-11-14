package edu.cnm.deepdive.dominionservice.model.dao;

import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.DiscardPile;
import edu.cnm.deepdive.dominionservice.model.entity.DrawPile;
import edu.cnm.deepdive.dominionservice.model.entity.Game;
import edu.cnm.deepdive.dominionservice.model.entity.Player;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrawPileRepository extends CrudRepository {

  ArrayList<DrawPile> getAllByGameOrderByPlayer(Game game);


  //TODO make this work- I dont know the exact query language for this
  DrawPile getLastByPlayer(Player player);


}
