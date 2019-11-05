package edu.cnm.deepdive.dominionservice.model.dao;


import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Card.CardCategory;
import edu.cnm.deepdive.dominionservice.model.entity.Location;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card,Long> {

  List<Card> save(Card card);

  Card findCardById(Long id);
  Card findCardByCardName(String cardName);
  Card findCardByLocation(Location location);
  Card findCardByCardCategory(CardCategory cardCategory);

  @Override
  void deleteAll();
}