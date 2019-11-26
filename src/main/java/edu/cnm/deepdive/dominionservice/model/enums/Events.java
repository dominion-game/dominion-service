package edu.cnm.deepdive.dominionservice.model.enums;

public enum Events {
  GAME_INTIALIZED,
  GAME_STARTS,
  GAME_FINISHES,
  RETURN_TO_LOBBY,
  END_ACTIONS,
  END_BUYS,
  END_TURN,
  BEGIN_TURN,
 BEGIN_GAME,
  PLAYER_1_START,
  PLAYER_1_END,
  PLAYER_2_START,
  PLAYER_2_END;
  public static Events getByName(String name) {
    for (Events event : Events.values()) {
      if (event.name().equals(name)) {
        return event;
      }
    }
    return null;
  }
}
