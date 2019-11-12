package edu.cnm.deepdive.dominionservice.model.dao;


import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Card.CardCategory;
import edu.cnm.deepdive.dominionservice.model.entity.Card.CardType;
import edu.cnm.deepdive.dominionservice.model.entity.Location;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card,Long> {

  List<Card> save(Card card);

  Card findCardById(Long id);
  Card findCardByCardName(String cardName);
  Card findCardByLocation(Location location);
  Card findCardByCardCategory(CardCategory cardCategory);

  @Override
  void deleteAll();

  Card getCardById(long cardId);

  List<Card> getAllByLocationId(Location location);

  CardType getCardTypeById(int cardId);
}