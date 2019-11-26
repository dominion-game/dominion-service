package edu.cnm.deepdive.dominionservice.service.state;

import edu.cnm.deepdive.dominionservice.model.dao.PlayerRepository;
import edu.cnm.deepdive.dominionservice.model.entity.Player;
import edu.cnm.deepdive.dominionservice.model.enums.Events;
import edu.cnm.deepdive.dominionservice.model.enums.States;
import edu.cnm.deepdive.dominionservice.service.GameLogic;
import java.util.EnumSet;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.StaticListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.state.State;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private GameLogic gameLogic;

  @Autowired
  private PlayerRepository playerRepository;

  @Bean
  public StateMachine<States, Events> stateMachine() throws Exception {
    StateMachineBuilder.Builder<States, Events> builder = StateMachineBuilder.builder();

    builder.configureStates()
        .withStates()
        .initial(States.INITIAL)
        .end(States.GAME_END)
        .states(EnumSet.allOf(States.class));

    builder.configureTransitions()
        .withExternal()
        .source(States.INITIAL).target(States.GAME_PLAYING).event(Events.BEGIN_GAME)
        .and()
        .withExternal()
        .source(States.GAME_PLAYING).target(States.PLAYER_1_TURN).event(Events.PLAYER_1_START)
        .action(newTurnActionPlayer1())
        .and()
        .withExternal()
        .source(States.PLAYER_1_TURN).target(States.GAME_PLAYING).event(Events.PLAYER_1_END)
        .and()
        .withExternal()
        .source(States.GAME_PLAYING).target(States.PLAYER_2_TURN).event(Events.PLAYER_2_START)
        .action(newTurnActionPlayer2())
        .and()
        .withExternal()
        .source(States.PLAYER_2_TURN).target(States.GAME_PLAYING).event(Events.PLAYER_2_END)
        .and()
        .withExternal()
        .source(States.GAME_PLAYING).target(States.GAME_END).event(Events.GAME_FINISHES)
        .and()
        .withExternal()
        .source(States.GAME_END).target(States.INITIAL).event(Events.RETURN_TO_LOBBY);


    StateMachine<States, Events> stateMachine = builder.build();
   // stateMachine.addStateListener(listener);
    return stateMachine;
  }

  @Override
  public void configure(StateMachineConfigurationConfigurer<States, Events> config)
      throws Exception {
    config.withConfiguration().autoStartup(true)
        .beanFactory(new StaticListableBeanFactory())
        .taskExecutor(new SyncTaskExecutor())
        .taskScheduler(new ConcurrentTaskScheduler())
        .listener(new StateMachineListener<States, Events>());
  }

  @Override
  public void configure(StateMachineStateConfigurer<States, Events> states)
      throws Exception {

    states
       /** .withStates()
        .state(States.INITIAL)
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
*/

            .withStates()
        .initial(States.INITIAL)
        .end(States.GAME_END)
        .states(EnumSet.allOf(States.class));
  }

  @Override
  public void configure(
      StateMachineTransitionConfigurer<States, Events> transitions)
      throws Exception {

    transitions
        .withExternal()
        .source(States.INITIAL).target(States.GAME_PLAYING).event(Events.BEGIN_GAME)
        .and()
        .withExternal()
        .source(States.GAME_PLAYING).target(States.PLAYER_1_TURN).event(Events.PLAYER_1_START)
        .action(newTurnActionPlayer1())
        .and()
        .withExternal()
        .source(States.PLAYER_1_TURN).target(States.GAME_PLAYING).event(Events.PLAYER_1_END)
        .and()
        .withExternal()
        .source(States.GAME_PLAYING).target(States.PLAYER_2_TURN).event(Events.PLAYER_2_START)
        .action(newTurnActionPlayer2())
        .and()
        .withExternal()
        .source(States.PLAYER_2_TURN).target(States.GAME_PLAYING).event(Events.PLAYER_2_END)
        .and()
        .withExternal()
        .source(States.GAME_PLAYING).target(States.GAME_END).event(Events.GAME_FINISHES)
        .and()
        .withExternal()
        .source(States.GAME_END).target(States.INITIAL).event(Events.RETURN_TO_LOBBY);

  }

  @Bean
  GameSetupAction gameSetupAction() {
    return new GameSetupAction() {
      @Override
      public void execute(StateContext<States, Events> stateContext) {
        gameLogic.initGame();
        super.execute(stateContext);
      }
    };
  }


  //LOGGING FOR THE TIME BEING, WILL PROBABLY BE UNNCESSARY IN PRODUCTION



  //TURN START AND END METHODS, GAME START AND CLEANUP METHODS INCLUDING VICTORY,
//ENTRY AND EXIT METHODS
  @Bean
  public NewTurnAction newTurnActionPlayer1() {
    return new NewTurnAction() {
      @Override
      public void execute(StateContext<States, Events> stateContext) {
        Optional<Player> player = playerRepository.findPlayerById((long) 1);
        gameLogic.startTurn(player);
        super.execute(stateContext);

        //turn entry actions
      }
    };
  }
  @Bean
  public StateMachineListener<States, Events> listener() {
    return new StateMachineListener<States, Events>() {
      @Override
      public void stateChanged(State from, State to) {
        logger.info("State change to " + to.getId());
      }
    };
  }

  @Bean
  public NewTurnAction newTurnActionPlayer2() {
    return new NewTurnAction() {
      @Override
      public void execute(StateContext<States, Events> stateContext) {
        Optional<Player> player = playerRepository.findPlayerById((long) 2);
        gameLogic.startTurn(player);
        super.execute(stateContext);

        //turn entry actions
      }
    };
  }

  @Bean
  public EndTurnAction endTurnAction() {
    return new EndTurnAction() {
      @Override
      public void execute(StateContext<States, Events> stateContext) {
        gameLogic.testForVictory();
        super.execute(stateContext);
        //turn exit actions
      }
    };
  }

  @Bean
  public EndGameAction endGame() {
    return new EndGameAction() {
      @Override
      public void execute(StateContext<States, Events> stateContext) {
        gameLogic.endGame();
        super.execute(stateContext);
      }
    };
  }

  public static class EndGameAction implements Action<States, Events> {

    @Override
    public void execute(StateContext<States, Events> stateContext) {

    }
  }

  public static class NewTurnAction implements Action<States, Events> {

    @Override
    public void execute(StateContext<States, Events> stateContext) {

    }
  }

  public static class EndTurnAction implements Action<States, Events> {

    @Override
    public void execute(StateContext<States, Events> stateContext) {

    }
  }

  public static class GameSetupAction implements Action<States, Events> {

    @Override
    public void execute(StateContext<States, Events> stateContext) {

    }
  }


}