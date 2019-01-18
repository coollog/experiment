/*
 * Copyright 2019 Google LLC. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package test.experiment.server;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import test.experiment.core.Controller;
import test.experiment.core.Direction;
import test.experiment.core.Moveable;
import test.experiment.core.Player;
import test.experiment.core.Position;

class GameLoop implements Runnable {

  private final ConcurrentHashMap<String, Input> playerInputMap = new ConcurrentHashMap<>();

  private final Controller controller = new Controller();

  private final ConcurrentLinkedQueue<Runnable> actions = new ConcurrentLinkedQueue<>();

  private final View view =
      new View(controller.getMap().getRowCount(), controller.getMap().getColumnCount());

  GameLoop() {}

  @Override
  public void run() {
    //    display();

    while (true) {
      doActions();

      processInput();
      updateView();
      //      display();
      resetInput();

      try {
        Thread.sleep(200);

      } catch (InterruptedException ex) {
        throw new RuntimeException(ex);
      }
    }
  }

  void addPlayer(String username, Position position) {
    System.out.println("awgew");
    actions.add(
        () -> {
          controller.addPlayer(username, position);
          System.out.println("hmmmmm");
        });
  }

  void setPlayerDirection(String username, Direction direction) {
    actions.add(() -> playerInputMap.put(username, new Input(direction)));
  }

  View.Serialized getView() {
    return view.serialize();
  }

  private void doActions() {
    actions.forEach(Runnable::run);
    actions.clear();
  }

  private void processInput() {
    for (Map.Entry<String, Input> playerInput : playerInputMap.entrySet()) {
      String username = playerInput.getKey();
      Input input = playerInput.getValue();

      if (!input.getNextDirection().isPresent()) {
        continue;
      }

      Optional<? extends Player> optionalPlayer =
          controller.getPlayerRegistry().getByUsername(username);
      if (!optionalPlayer.isPresent()) {
        throw new IllegalStateException("processing input for unregistered player");
      }

      Player player = optionalPlayer.get();
      Moveable.Result result = controller.move(player, input.getNextDirection().get());
      if (result != Moveable.Result.SUCCESS) {
        System.out.println(result);
      }
    }
  }

  private void resetInput() {
    playerInputMap.clear();
  }

  private void updateView() {
    view.reset();
    controller.getAllEntities().forEach(view::addEntity);
  }
}
