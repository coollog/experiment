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

import java.util.Set;

public class Controller {

  private static final int WORLD_ROW_COUNT = 8;
  private static final int WORLD_COLUMN_COUNT = 12;

  private final MutableWorldMap worldMap = new MutableWorldMap(WORLD_COLUMN_COUNT, WORLD_ROW_COUNT);
  private final MutablePlayerRegistry playerRegistry = new MutablePlayerRegistry();

  public WorldMap getMap() {
    return worldMap;
  }

  public Set<Entity> getAllEntities() {
    return worldMap.getAll();
  }

  public PlayerRegistry<? extends Player> getPlayerRegistry() {
    return playerRegistry;
  }

  public Player addPlayer(String username, Position position) {
    if (playerRegistry.getByUsername(username).isPresent()) {
      throw new IllegalStateException("username taken");
    }

    MutablePlayer player = new MutablePlayer(username, position);
    if (!worldMap.add(player)) {
      throw new IllegalStateException("position invalid");
    }
    playerRegistry.register(player);
    return player;
  }

  public void removePlayer(String username) {}

  public void addCoin() {
    int coinValue = 10 + ((int) (Math.random() * 10)) * 10;
    Position coinPosition =
        new Position(
            (int) (Math.random() * WORLD_ROW_COUNT), (int) (Math.random() * WORLD_COLUMN_COUNT));
    Coin coin = new Coin(coinValue, coinPosition);
    if (!worldMap.add(coin)) {
      throw new IllegalStateException("position invalid");
    }
    coin.setDestroyCallback(this::addCoin);
  }

  public Moveable.Result move(Player player, Direction direction) {
    MutablePlayer mutablePlayer = playerRegistry.getMutable(player);
    Position newPosition = Positions.moveTowards(mutablePlayer.getPosition(), direction);
    return mutablePlayer.moveTo(newPosition);
  }
}
