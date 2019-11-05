package edu.cnm.deepdive.dominionservice.controller;

import edu.cnm.deepdive.dominionservice.model.states.States;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

@WithStateMachine
static class GameTransitions{

  @OnTransition(target = States.NOT_PLAYING)
  void toState1() {
  }

  @OnTransition(target = "STATE2")
  void toState2() {
  }
}