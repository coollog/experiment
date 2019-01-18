/*
 * Copyright 2018 Google LLC. All rights reserved.
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

package test.experiment.core;

import java.time.Duration;
import java.util.function.Function;
import javax.annotation.Nullable;

class MutablePlayer implements Player, MutableEntity, Moveable {

  private final String username;

  private Position position;
  @Nullable private Function<Position, Result> moveToHandler;

  MutablePlayer(String username, Position position) {
    this.username = username;
    this.position = position;
  }

  @Override
  public void delete() {}

  @Override
  public Result moveTo(Position position) {
    Result result = moveToHandler == null ? Result.SUCCESS : moveToHandler.apply(position);
    if (result == Result.SUCCESS) {
      this.position = position;
    }
    return result;
  }

  @Override
  public Duration getDelayTillReady() {
    return Duration.ZERO;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public int getScore() {
    return 0;
  }

  @Override
  public Type getType() {
    return Type.PLAYER;
  }

  @Override
  public Position getPosition() {
    return position;
  }

  @Override
  public void setMoveToReceiver(Function<Position, Result> moveToHandler) {
    this.moveToHandler = moveToHandler;
  }
}
