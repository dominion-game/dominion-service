package edu.cnm.deepdive.dominionservice.controller;

import edu.cnm.deepdive.dominionservice.model.states.GameEvents;
import edu.cnm.deepdive.dominionservice.model.states.GameStates;
import edu.cnm.deepdive.dominionservice.model.states.TurnEvents;
import edu.cnm.deepdive.dominionservice.model.states.TurnStates;
import java.util.Arrays;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachine
public class GameStateMachine extends StateMachineConfigurerAdapter<GameStates, GameEvents> {

  @Autowired
  private StateMachine<String, String> stateMachine;

  @Override
  public void configure(StateMachineStateConfigurer<GameStates, GameEvents> states)
      throws Exception {

    states
        .withStates()
        .initial(GameStates.NOT_PLAYING)
        .end(GameStates.NOT_PLAYING)
        .states(
            new HashSet<GameStates>(Arrays.asList(GameStates.NOT_PLAYING, GameStates.GAME_START,
                GameStates.IN_PROGRESS, GameStates.GAME_END, GameStates.NOT_PLAYING)));

  }

  @Override
  public void configure(
      StateMachineTransitionConfigurer<GameStates, GameEvents> transitions)
      throws Exception {


    transitions.withExternal()
        .source(GameStates.NOT_PLAYING).target(GameStates.GAME_START).event(GameEvents.GAME_INTIALIZED).and()
        .withExternal()
        .source(GameStates.GAME_START).target(GameStates.IN_PROGRESS).event(GameEvents.GAME_STARTS).and()
        .withExternal()
        .source(GameStates.IN_PROGRESS).target(GameStates.GAME_END).event(GameEvents.GAME_FINISHES).and()
        .withExternal()
        .source(GameStates.GAME_END).target(GameStates.NOT_PLAYING).event(GameEvents.RETURN_TO_LOBBY);
  }
}
