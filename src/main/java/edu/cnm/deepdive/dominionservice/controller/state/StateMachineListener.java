package edu.cnm.deepdive.dominionservice.controller.state;

import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.logging.Logger;

public final class StateMachineListener <States, Events> extends StateMachineListenerAdapter {

  private static final Logger LOGGER = Logger.getLogger(StateMachineListener.class.getName());

  @Override
  public static void stateChanged(State from, State to) {
    LOGGER.info(() -> String.format("Transitioned from %s to %s%n", from == null ? "none" : from.getId(), to.getId()));
  }
}