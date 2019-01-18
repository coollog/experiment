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

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/** The entire game world map that can be changed. */
class MutableWorldMap implements WorldMap, EntityCollection {

  private final int width;
  private final int height;

  private final Map<Entity, Position> entityToPositionMap = new HashMap<>();

  MutableWorldMap(int width, int height) {
    this.width = width;
    this.height = height;
  }

  @Override
  public boolean add(Entity entity) {
    if (!isWithinBounds(entity.getPosition())) {
      return false;
    }

    entityToPositionMap.put(entity, entity.getPosition());
    entity.setMoveToReceiver(moveEntityFunction(entity));
    return true;
  }

  @Override
  public void remove(Entity entity) {
    Preconditions.checkArgument(entityToPositionMap.containsKey(entity));

    entityToPositionMap.remove(entity);
  }

  @Override
  public Set<Entity> getAll() {
    return entityToPositionMap.keySet();
  }

  @Override
  public int getColumnCount() {
    return width;
  }

  @Override
  public int getRowCount() {
    return height;
  }

  private Function<Position, Moveable.Result> moveEntityFunction(Entity entity) {
    return position -> {
      if (!isWithinBounds(position)) {
        return Moveable.Result.OUT_OF_BOUNDS;
      }

      entityToPositionMap.put(entity, position);

      return Moveable.Result.SUCCESS;
    };
  }

  private boolean isWithinBounds(Position position) {
    return position.getRow() >= 0
        && position.getRow() < height
        && position.getColumn() >= 0
        && position.getColumn() < width;
  }
}
