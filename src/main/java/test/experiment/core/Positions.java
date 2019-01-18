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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/** Static helpers for {@link Position}. */
public class Positions {

  /**
   * Iterates over the positions in a grid row-first.
   *
   * @param width grid width
   * @param height grid height
   * @param positionFunction function to process location into {@code <V>}
   * @param <V> type of mapped result
   * @return list of mapped results
   */
  public static <V> List<V> map(int width, int height, Function<Position, V> positionFunction) {
    List<V> results = new ArrayList<>();

    for (int row = 0; row < height; row++) {
      for (int column = 0; column < width; column++) {
        results.add(positionFunction.apply(new Position(row, column)));
      }
    }

    return results;
  }

  public static Position moveTowards(Position position, Direction direction) {
    switch (direction) {
      case UP:
        return new Position(position.getRow() - 1, position.getColumn());
      case DOWN:
        return new Position(position.getRow() + 1, position.getColumn());
      case LEFT:
        return new Position(position.getRow(), position.getColumn() - 1);
      case RIGHT:
        return new Position(position.getRow(), position.getColumn() + 1);
      default:
        throw new IllegalStateException("unknown direction: " + direction);
    }
  }
}
