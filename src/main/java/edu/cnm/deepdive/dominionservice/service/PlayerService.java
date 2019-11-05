package edu.cnm.deepdive.dominionservice.service;

import edu.cnm.deepdive.dominionservice.model.dao.PlayerRepository;
import edu.cnm.deepdive.dominionservice.model.entity.Player;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PlayerService {

  public Player createNewPlayer(PlayerRepository newPlayerRepository) {
  }

  public void listPlayers() {
  }

  public Player getUser() {
  }
}
