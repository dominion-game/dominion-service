package edu.cnm.deepdive.dominionservice.service.state;


import edu.cnm.deepdive.dominionservice.model.entity.Game;
import edu.cnm.deepdive.dominionservice.model.enums.Events;
import edu.cnm.deepdive.dominionservice.model.enums.States;
import java.io.Serializable;


import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Component;



import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Component
@RequiredArgsConstructor
class GameCreatedEventListener extends AbstractRepositoryEventListener<Game> {

  DefaultStateMachineAdapter<States, Events, ContextEntity<States, Events,
      ? extends Game>> gameStateMachineAdapter;

  @Override
  @SneakyThrows
  protected void onBeforeCreate(Game game) {
    try {
      gameStateMachineAdapter.persist(gameStateMachineAdapter.create(), game);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}