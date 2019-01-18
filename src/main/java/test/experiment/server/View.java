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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import test.experiment.core.Coin;
import test.experiment.core.Entity;
import test.experiment.core.Player;
import test.experiment.core.Position;

public class View {

  public static class Serialized {

    public final int rowCount;
    public final int columnCount;
    public final List<SerializedEntity> entities;

    private Serialized(int rowCount, int columnCount, List<SerializedEntity> entities) {
      this.rowCount = rowCount;
      this.columnCount = columnCount;
      this.entities = entities;
    }
  }

  public static class SerializedPosition {

    public final int row;
    public final int column;

    private SerializedPosition(Position position) {
      this.row = position.getRow();
      this.column = position.getColumn();
    }
  }

  public static class SerializedEntity {

    public final SerializedPosition position;

    private SerializedEntity(Entity entity) {
      this.position = new SerializedPosition(entity.getPosition());
    }
  }

  public static class SerializedPlayer extends SerializedEntity {

    public final String type = "player";
    public final String username;
    public final int score;

    private SerializedPlayer(Player player) {
      super(player);

      this.username = player.getUsername();
      this.score = player.getScore();
    }
  }

  public static class SerializedCoin extends SerializedEntity {

    public final String type = "coin";
    public final int value;

    private SerializedCoin(Coin coin) {
      super(coin);

      this.value = coin.getValue();
    }
  }

  private final int rowCount;
  private final int columnCount;
  private final ConcurrentLinkedQueue<SerializedEntity> entities = new ConcurrentLinkedQueue<>();

  View(int rowCount, int columnCount) {
    this.rowCount = rowCount;
    this.columnCount = columnCount;
  }

  View addEntity(Entity entity) {
    SerializedEntity serializedEntity;

    switch (entity.getType()) {
      case PLAYER:
        serializedEntity = new SerializedPlayer((Player) entity);
        break;
      case COIN:
        serializedEntity = new SerializedCoin((Coin) entity);
        break;
      default:
        throw new IllegalArgumentException("unknown entity type: " + entity.getType());
    }

    entities.add(serializedEntity);

    return this;
  }

  View reset() {
    entities.clear();
    return this;
  }

  Serialized serialize() {
    return new Serialized(rowCount, columnCount, new ArrayList<>(entities));
  }
}
