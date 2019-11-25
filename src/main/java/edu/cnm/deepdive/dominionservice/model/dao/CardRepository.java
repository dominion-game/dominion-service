package edu.cnm.deepdive.dominionservice.model.dao;


import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Card.CardType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {


 // Card findCardByCardCategory(CardCategory cardCategory);

  @Override
  void deleteAll();
  Optional<Card> getCardById(int cardId);

  Optional<Card> getCardByCardType(CardType cardType);



  Optional<CardType> getCardTypeById(int cardId);


}