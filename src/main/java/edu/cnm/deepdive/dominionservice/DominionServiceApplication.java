package edu.cnm.deepdive.dominionservice;

import edu.cnm.deepdive.dominionservice.model.enums.Events;
import edu.cnm.deepdive.dominionservice.model.enums.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;

@EnableStateMachineFactory
@EnableStateMachine
@SpringBootApplication
public class DominionServiceApplication implements CommandLineRunner {
  private final StateMachine<States, Events> stateMachine;
  //private static Logger logger = LoggingUtils.LOGGER;

@Autowired
  public DominionServiceApplication(
      StateMachine<States, Events> stateMachine) {
    this.stateMachine = stateMachine;
  }

  public static void main(String[] args) {
    SpringApplication.run(DominionServiceApplication.class, args);
  }


  @Override
  public void run(String... args) throws Exception {

  }
}
