package edu.cnm.deepdive.dominionservice.model.dao;


import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Card.CardType;
import edu.cnm.deepdive.dominionservice.model.entity.Location;
import edu.cnm.deepdive.dominionservice.model.entity.Location.LocationType;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card,Long> {

  List<Card> save(Card card);

  Card findCardById(Long id);
  Card findCardByCardName(String cardName);
 // Card findCardByCardCategory(CardCategory cardCategory);
  List<Card> getAllByLocationType(LocationType locationType);
  List<Card> getAllByLocationTypeOrderByLocationIndex(LocationType locationType);
  @Override
  void deleteAll();

  Card getCardById(long cardId);

  Card getCardByName(String cardName);
  Card getCardByCardType(CardType cardType);
  List<Card> getAllByCost(int cost);

  List<Card> getAllByLocationId(Location location);

  CardType getCardTypeById(int cardId);
}