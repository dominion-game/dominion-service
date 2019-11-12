package edu.cnm.deepdive.dominionservice.service;

import edu.cnm.deepdive.dominionservice.model.dao.GameRepository;
import edu.cnm.deepdive.dominionservice.model.entity.Game;
import edu.cnm.deepdive.dominionservice.model.entity.Player;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GameService {

  private final GameRepository gameRepository;

  @Autowired
  public GameService(GameRepository gameRepository){
    this.gameRepository = gameRepository;
  }

  public Game createNewGame(Player player, GameRepository gameRepository) {
    Game game = new Game();
    /**TODO:
     * for (Player : PlayerQueue){
     * game.setFirstPlayer(player);
     *     game.setSecondPlayer();
     *     */

    return game;
  }

  public Game finishGame(){
    return game;
  }


  public Game getGameById(Long gameId) {
    return gameRepository.getGameById(gameId);
  }
}
