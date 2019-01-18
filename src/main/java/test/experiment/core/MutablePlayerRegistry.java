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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class MutablePlayerRegistry implements PlayerRegistry<MutablePlayer> {

  private final Map<String, MutablePlayer> usernameToPlayerMap = new HashMap<>();
  private final Map<Player, MutablePlayer> players = new HashMap<>();

  void register(MutablePlayer player) {
    usernameToPlayerMap.put(player.getUsername(), player);
    players.put(player, player);
  }

  void deregister(MutablePlayer player) {
    usernameToPlayerMap.remove(player.getUsername());
    players.remove(player);
  }

  MutablePlayer getMutable(Player player) {
    return players.get(player);
  }

  @Override
  public List<MutablePlayer> getAll() {
    return null;
  }

  @Override
  public Optional<MutablePlayer> getByUsername(String username) {
    if (usernameToPlayerMap.containsKey(username)) {
      return Optional.of(usernameToPlayerMap.get(username));
    }
    return Optional.empty();
  }
}
