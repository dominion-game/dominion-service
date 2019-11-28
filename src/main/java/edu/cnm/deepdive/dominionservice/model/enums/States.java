package edu.cnm.deepdive.dominionservice.model.enums;

public enum States {
  //super state not-playing
  INITIAL,
  WAITING,
  GAME_PLAYING,
  GAME_START,
  GAME_END,
  PLAYER_1_TURN,
  PLAYER_2_TURN;

  public static States getByName(String name) {
    for (States state : States.values()) {
      if (state.name().equals(name)) {
        return state;
      }
    }
    return null;
  }
}
