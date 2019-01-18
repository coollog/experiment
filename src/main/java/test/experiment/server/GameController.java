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

import javax.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.experiment.core.Direction;
import test.experiment.core.Position;

@RestController
public class GameController {

  private final GameLoop gameLoop = new GameLoop();

  @PostConstruct
  public void init() {
    new Thread(gameLoop).start();

    //    gameLoop.addPlayer("me", new Position(0, 0));
  }

  @RequestMapping("/join/{username}")
  public ResponseEntity<String> join(@PathVariable("username") String username) {
    gameLoop.addPlayer(username, new Position(0, 0));
    return new ResponseEntity<>("joined", HttpStatus.OK);
  }

  @RequestMapping("/move/{username}/{direction}")
  public ResponseEntity<String> move(
      @PathVariable("username") String username,
      @PathVariable("direction") String directionString) {
    Direction direction;
    if ("up".equals(directionString)) {
      direction = Direction.UP;
    } else if ("down".equals(directionString)) {
      direction = Direction.DOWN;
    } else if ("left".equals(directionString)) {
      direction = Direction.LEFT;
    } else if ("right".equals(directionString)) {
      direction = Direction.RIGHT;
    } else {
      return new ResponseEntity<>("unknown direction: " + directionString, HttpStatus.NOT_FOUND);
    }

    gameLoop.setPlayerDirection(username, direction);
    return new ResponseEntity<>("direction updated", HttpStatus.OK);
  }

  @RequestMapping("/view")
  public ResponseEntity<View.Serialized> view() {
    return new ResponseEntity<>(gameLoop.getView(), HttpStatus.OK);
  }
}
