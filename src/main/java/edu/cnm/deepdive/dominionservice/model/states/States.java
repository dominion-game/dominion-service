package edu.cnm.deepdive.dominionservice.model.states;

public enum States {
  //super state not-playing
  NOT_PLAYING,
  IDLE,
  GAME_SETUP,

  //super state game-playing
  GAME_PLAYING,
  GAME_START,
  TURN,
  GAME_END
}
