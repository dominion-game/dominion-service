package edu.cnm.deepdive.dominionservice.model.dao;


import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Card.CardType;
import edu.cnm.deepdive.dominionservice.model.entity.DiscardPile;
import edu.cnm.deepdive.dominionservice.model.entity.DrawPile;
import edu.cnm.deepdive.dominionservice.model.entity.Hand;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card,Long> {

  List<Card> save(Card card);

  Card findCardById(int id);
  Card findCardByCardName(String cardName);
 // Card findCardByCardCategory(CardCategory cardCategory);

  @Override
  void deleteAll();

  Card getCardById(int cardId);

  Card getCardByName(String cardName);
  Card getCardByCardType(CardType cardType);
  List<Card> getAllByCost(int cost);

  List<Card> getAllByDrawPileOrderByIndex(DrawPile drawPile);
  List<Card> getAllByDiscardPile(DiscardPile discardPile);
  List<Card> getAllByHand(Hand hand);

  CardType getCardTypeById(int cardId);


}