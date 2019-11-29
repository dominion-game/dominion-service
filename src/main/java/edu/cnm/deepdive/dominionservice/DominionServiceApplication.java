package edu.cnm.deepdive.dominionservice;

import edu.cnm.deepdive.dominionservice.model.enums.Events;
import edu.cnm.deepdive.dominionservice.model.enums.States;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import edu.cnm.deepdive.dominionservice.service.state.ContextEntity;
import edu.cnm.deepdive.dominionservice.service.state.ContextObjectResourceProcessor;
import edu.cnm.deepdive.dominionservice.service.state.DefaultStateMachineAdapter;
import java.io.Serializable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.EntityLinks;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
//import org.springframework.statemachine.config.EnableStateMachineFactory;
//extends ResourceServerConfigurerAdapter

@SpringBootApplication
@EntityScan

public class DominionServiceApplication
    implements CommandLineRunner{

  private StateMachine<States, Events> stateMachine;
  //private static Logger logger = LoggingUtils.LOGGER;


  public DominionServiceApplication(
      StateMachine<States, Events> stateMachine) {
    this.stateMachine = stateMachine;
  }

  public static void main(String[] args) {
    SpringApplication.run(DominionServiceApplication.class, args);
  }



  @Override
  public void run(String... args) throws Exception {
    stateMachine.start();
    stateMachine.sendEvent(Events.ONE_PLAYER_JOINS);
    stateMachine.sendEvent(Events.PLAYER_TWO_JOINS);

  }

  //@Value("${oauth.clientId}")
 // private String clientId;
/**
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    // http.authorizeRequests().anyRequest().anonymous();
    http.authorizeRequests().anyRequest().hasRole("USER");
  }
*/
@Bean
public DefaultStateMachineAdapter<States, Events, ContextEntity<States, Events, Serializable>> orderStateMachineAdapter(
    StateMachineFactory<States, Events> stateMachineFactory,
    StateMachinePersister<States, Events, ContextEntity<States, Events, Serializable>> persister) {
  return new DefaultStateMachineAdapter<>(stateMachineFactory, persister);
}

  @Bean
  public ContextObjectResourceProcessor<States, Events, ContextEntity<States, Events, Serializable>> orderResourceProcessor(
      EntityLinks entityLinks,
      DefaultStateMachineAdapter<States, Events, ContextEntity<States, Events, ? extends Serializable>> orderStateMachineAdapter) {
    return new ContextObjectResourceProcessor<>(entityLinks, orderStateMachineAdapter);
  }

  @Bean
  public StateMachinePersister<States, Events, ContextEntity<States, Events, Serializable>> persister(
      StateMachinePersist<States, Events, ContextEntity<States, Events, Serializable>> persist) {
    return new DefaultStateMachinePersister<>(persist);
  }

  @Bean
  public StateMachinePersist<States, Events, ContextEntity<States, Events, Serializable>> persist() {
    return new StateMachinePersist<States, Events, Serializable>() {

      @Override
      public void write(StateMachineContext stateMachineContext,
          ContextEntity<States, Events, Serializable> order) throws Exception {
       o.setStateMachineContext(stateMachineContext);
      }

      @Override
      public StateMachineContext read(ContextEntity<States, Events, Serializable> game) throws Exception {
        return game.getStateMachineContext();
      }


    };
  }
}
