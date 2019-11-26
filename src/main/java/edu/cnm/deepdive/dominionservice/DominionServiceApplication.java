package edu.cnm.deepdive.dominionservice;

import edu.cnm.deepdive.dominionservice.model.enums.Events;
import edu.cnm.deepdive.dominionservice.model.enums.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;


@SpringBootApplication
@EnableWebSecurity
public class DominionServiceApplication extends ResourceServerConfigurerAdapter
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
    stateMachine.sendEvent(Events.BEGIN_GAME);
    stateMachine.sendEvent(Events.PLAYER_1_START);
    stateMachine.sendEvent(Events.PLAYER_1_END);
    stateMachine.sendEvent(Events.PLAYER_2_START);
    stateMachine.sendEvent(Events.PLAYER_2_END);
    stateMachine.sendEvent(Events.RETURN_TO_LOBBY);
  }

  //@Value("${oauth.clientId}")
 // private String clientId;

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    // http.authorizeRequests().anyRequest().anonymous();
    http.authorizeRequests().anyRequest().hasRole("USER");
  }

}
