package edu.cnm.deepdive.dominionservice.service;

import edu.cnm.deepdive.dominionservice.model.entity.Card;
import edu.cnm.deepdive.dominionservice.model.entity.Card.CardCategory;
import edu.cnm.deepdive.dominionservice.model.entity.Location;
import edu.cnm.deepdive.dominionservice.model.entity.Location.LocationType;
import edu.cnm.deepdive.dominionservice.model.entity.Player;
import edu.cnm.deepdive.dominionservice.model.entity.Stack;
import edu.cnm.deepdive.dominionservice.model.pojo.Deck;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CardService {

  public Card draw(Location fromWhere, Player player, Location toWhere) {
    if (fromWhere.getLocationType()== LocationType.STACK){
      //TODO fix this
    } else if (fromWhere.getLocationType() == LocationType.DECK){
      //TODO implement draw from deck
    }

    card.changeLocation(toWhere);
    return card;
  }
}
