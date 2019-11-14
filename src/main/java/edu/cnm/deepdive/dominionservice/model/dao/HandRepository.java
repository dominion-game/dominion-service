package edu.cnm.deepdive.dominionservice.model.dao;

import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.DiscardPile;
import edu.cnm.deepdive.dominionservice.model.entity.DrawPile;
import edu.cnm.deepdive.dominionservice.model.entity.Game;

import edu.cnm.deepdive.dominionservice.model.entity.Hand;
import edu.cnm.deepdive.dominionservice.model.entity.Player;
import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HandRepository extends CrudRepository <Card, Integer> {

  ArrayList<Hand> getAllByGameOrderByPlayer(Game game);
  //TODO make this work- I dont know the exact query language for this
  Hand getLastByPlayer(Player player);


}
