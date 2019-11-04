package edu.cnm.deepdive.dominionservice.controller;


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
public class TurnStateMachine extends StateMachineConfigurerAdapter<TurnStates, TurnEvents> {

  @Autowired
  private StateMachine<String, String> stateMachine;

  @Override
  public void configure(StateMachineStateConfigurer<TurnStates, TurnEvents> states)
      throws Exception {

    states
        .withStates()
        .initial(TurnStates.PASSIVE)
        .end(TurnStates.PASSIVE)
        .states(
            new HashSet<TurnStates>(Arrays.asList(TurnStates.ACTING, TurnStates.BUYING,
                TurnStates.DISCARDING, TurnStates.DRAWING)));

  }

  @Override
  public void configure(
      StateMachineTransitionConfigurer<TurnStates, TurnEvents> transitions)
      throws Exception {

    transitions.withExternal()
        .source(TurnStates.PASSIVE).target(TurnStates.ACTING).event(TurnEvents.BEGIN_TURN).and()
        .withExternal()
        .source(TurnStates.ACTING).target(TurnStates.BUYING).event(TurnEvents.END_ACTIONS).and()
        .withExternal()
        .source(TurnStates.BUYING).target(TurnStates.DISCARDING).event(TurnEvents.END_BUYS).and()
        .withExternal()
        .source(TurnStates.DISCARDING).target(TurnStates.DRAWING).event(TurnEvents.END_DISCARD)
        .and()
        .withExternal()
        .source(TurnStates.DRAWING).target(TurnStates.PASSIVE).event(TurnEvents.END_DRAW);
  }

}