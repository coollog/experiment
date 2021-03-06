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

import java.util.function.Function;
import javax.annotation.Nullable;

public class Coin implements Entity {

  private final int value;
  private final Position position;
  private boolean isDestroyed = false;

  @Nullable private Runnable destroyCallback;

  Coin(int value, Position position) {
    this.value = value;
    this.position = position;
  }

  public int getValue() {
    return value;
  }

  @Override
  public Type getType() {
    return Type.COIN;
  }

  @Override
  public Position getPosition() {
    return position;
  }

  @Override
  public void setMoveToReceiver(Function<Position, Moveable.Result> moveToHandler) {}

  @Override
  public void collide(Entity entity) {}

  @Override
  public void destroy() {
    if (destroyCallback != null) {
      destroyCallback.run();
    }
    isDestroyed = true;
  }

  @Override
  public boolean isDestroyed() {
    return isDestroyed;
  }

  @Override
  public void setDestroyCallback(Runnable destroyCallback) {
    Runnable previousDestroyCallback = this.destroyCallback;
    this.destroyCallback =
        () -> {
          if (previousDestroyCallback != null) {
            previousDestroyCallback.run();
          }
          destroyCallback.run();
        };
  }
}
