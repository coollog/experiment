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

package test.experiment;

public class ConsoleGame {

  //  public static void main(String[] args) {
  //    ConsoleGame consoleGame = new ConsoleGame();
  //    consoleGame.display();
  //
  //    while (true) {
  //      while (!consoleGame.getInput()) ;
  //      consoleGame.processInput();
  //      consoleGame.display();
  //    }
  //  }
  //
  //  private final Controller controller = new Controller();
  //
  //  @Nullable private Direction directionInput;
  //
  //  private ConsoleGame() {
  //    controller.addPlayer("me", new Position(0, 0));
  //  }
  //
  //  private boolean getInput() {
  //    System.out.println("Press up/down/left/right and then enter: ");
  //
  //    Direction direction;
  //    Scanner scanner = new Scanner(System.in, "US-ASCII");
  //    String nextLine = scanner.nextLine();
  //    switch (nextLine.charAt(0)) {
  //      case KeyEvent.VK_ESCAPE:
  //        switch (nextLine.charAt(1)) {
  //          case '[':
  //            switch (nextLine.charAt(2)) {
  //              case 'A':
  //                direction = Direction.UP;
  //                break;
  //              case 'B':
  //                direction = Direction.DOWN;
  //                break;
  //              case 'D':
  //                direction = Direction.LEFT;
  //                break;
  //              case 'C':
  //                direction = Direction.RIGHT;
  //                break;
  //              default:
  //                System.out.println("Invalid input");
  //                return false;
  //            }
  //            break;
  //          default:
  //            System.out.println("Invalid input");
  //            return false;
  //        }
  //        break;
  //      default:
  //        System.out.println("Invalid input");
  //        return false;
  //    }
  //
  //    directionInput = direction;
  //    return true;
  //  }
  //
  //  private void processInput() {
  //    if (directionInput == null) {
  //      return;
  //    }
  //
  //    Player player = controller.getPlayerRegistry().getByUsername("me").get();
  //    Moveable.Result result = controller.move(player, directionInput);
  //    if (result != Moveable.Result.SUCCESS) {
  //      System.out.println(result);
  //    }
  //  }
  //
  //  private void display() {
  //    if (directionInput != null) {
  //      System.out.println(directionInput.name());
  //    }
  //
  //    WorldMap worldMap = controller.getMap();
  //    for (int row = 0; row < worldMap.getRowCount(); row++) {
  //      for (int column = 0; column < worldMap.getColumnCount(); column++) {
  //        Optional<Entity> optionalEntity = worldMap.get(new Position(row, column));
  //        if (optionalEntity.isPresent()) {
  //          switch (optionalEntity.get().getType()) {
  //            case PLAYER:
  //              System.out.print('P');
  //              break;
  //            case COIN:
  //              System.out.print('C');
  //              break;
  //            default:
  //              throw new IllegalStateException("unknown entity type");
  //          }
  //        } else {
  //          System.out.print('_');
  //        }
  //      }
  //      System.out.println();
  //    }
  //  }
}
