package edu.cnm.deepdive.dominionservice.controller.state;

import edu.cnm.deepdive.dominionservice.model.enums.Events;
import edu.cnm.deepdive.dominionservice.model.enums.States;
import edu.cnm.deepdive.dominionservice.service.GameLogic;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

//TODO we will have to change autostartup when we build the lobby/waiting room but it works for now
  @Override
  public void configure(StateMachineConfigurationConfigurer<States, Events> config)
      throws Exception {
    config.withConfiguration().autoStartup(true).listener(listener());
  }

  @Override
  public void configure(StateMachineStateConfigurer<States, Events> states)
      throws Exception {

    states
        .withStates()
        .initial(States.NOT_PLAYING)
        .state(States.NOT_PLAYING)
        .and()
        .withStates()
        .parent(States.NOT_PLAYING)
        .initial(States.IDLE)
          .state(States.GAME_SETUP)
          .and()
        .withStates()
          .state(States.GAME_PLAYING)
        .and()
        .withStates()
         .parent(States.GAME_PLAYING)
          .initial(States.GAME_START)
          .state(States.GAME_START)
          .and()
        .withStates()
        .initial(States.GAME_START)
        .state(States.GAME_START)
        .and()
        .withStates()
        .parent(States.GAME_START)
        .initial(States.GAME_START)
        .state(States.GAME_PLAYING)
        .and()
        .withStates()
        .parent(States.GAME_PLAYING)
        .initial(States.PLAYER_1_TURN)
        .state(States.PLAYER_1_TURN)
        .state(States.PLAYER_2_TURN);

  }

  @Override
  public void configure(
      StateMachineTransitionConfigurer<States, Events> transitions)
      throws Exception {

    transitions
        .withExternal()
        .source(States.IDLE).target(States.GAME_SETUP).event(Events.GAME_INTIALIZED)
        .and()
        .withExternal()
        .source(States.GAME_SETUP).target(States.GAME_START).event(Events.GAME_STARTS)
        .and()
        .withExternal()
        .source(States.GAME_START).target(States.GAME_PLAYING).event(Events.BEGIN_TURN)
        .and()
        .withInternal()
        .source(States.GAME_PLAYING).event(Events.END_ACTIONS)
        .and()
        .withInternal()
        .source(States.GAME_PLAYING).event(Events.)

  }
  //TODO see if we can set this up on initialization of game
  public enum Variables{
    PLAYER_1,
    PLAYER_2
  }

  //LOGGING FOR THE TIME BEING, WILL PROBABLY BE UNNCESSARY IN PRODUCTION
  @Bean
  public StateMachineListener<States, Events> listener(){
    return new StateMachineListenerAdapter<States, Events>(){
      @Override
      public void stateChanged(States<States, Events> from, States<States, Events> to){
        System.out.println("State change to "+to.toString());
      }
    };
  }

  //TURN START AND END METHODS, GAME START AND CLEANUP METHODS INCLUDING VICTORY,
  //ENTRY AND EXIT METHODS
  @Bean
  public NewTurnAction newTurnAction(){
    return new NewTurnAction();
  }
  //... more to come

  public static class NewTurnAction implements Action<States, Events>{

    @Override
    public void execute(StateContext<States, Events> stateContext) {
    //TODO override execute method
    }
  }
  @Bean
  public GameLogic gameLogic(){
    return new GameLogic();
  }

  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.RUNTIME)
  @OnTransition
  public interface StatesOnTransition{
    States[] source() default {};
    States[] target() default {};
  }


}
