package edu.cnm.deepdive.dominionservice.model.dao;


import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Card.CardCategory;
import edu.cnm.deepdive.dominionservice.model.entity.Card.CardType;
import edu.cnm.deepdive.dominionservice.model.entity.Location;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

//  List<Card> save(Card card);

//  Card findCardById(Long id);
//  Card findCardByCardName(String cardName);
//  Card findCardByLocation(Location location);
//  Card findCardByCardType(CardType cardType);

//  @Override
//  void deleteAll();

//  Optional<Card> getFirstByCardType(CardType cardType);
//  Optional<Card> getCardByCardType(CardType cardType);


  Card getCardByName(String cardName);
  Card getCardByCardType(CardType cardType);
  List<Card> getAllByCost(int cost);

//  List<Card> getAllByLocationId(Location location);
//
//  CardType getCardTypeById(int cardId);
}