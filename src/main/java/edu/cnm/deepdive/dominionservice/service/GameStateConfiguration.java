package edu.cnm.deepdive.dominionservice.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfig;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigBuilder;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineModelConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.config.common.annotation.AnnotationBuilder;

@Configuration
@EnableStateMachine
public class GameStateConfiguration extends StateMachineConfigurerAdapter<String,String> {

  @Override
  public void configure(StateMachineConfigBuilder<String, String> config) throws Exception {
    super.configure(config);
  }

  @Override
  public void configure(StateMachineModelConfigurer<String, String> model) throws Exception {
    super.configure(model);
  }

  @Override
  public void configure(StateMachineConfigurationConfigurer<String, String> config)
      throws Exception {
    super.configure(config);
  }

  @Override
  public void configure(StateMachineStateConfigurer<String, String> states) throws Exception {
    super.configure(states);
  }

  @Override
  public void configure(StateMachineTransitionConfigurer<String, String> transitions)
      throws Exception {
    super.configure(transitions);
  }

  @Override
  public boolean isAssignable(AnnotationBuilder<StateMachineConfig<String, String>> builder) {
    return super.isAssignable(builder);
  }
}
