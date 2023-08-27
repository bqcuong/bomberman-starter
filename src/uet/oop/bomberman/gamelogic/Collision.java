package uet.oop.bomberman.gamelogic;

import java.util.List;
import javafx.scene.shape.Shape;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Enemy.*;
import uet.oop.bomberman.entities.Map.*;
import uet.oop.bomberman.entities.Player.*;
import uet.oop.bomberman.entities.Powerup.*;
import uet.oop.bomberman.graphics.Sprite;

/**
 * Kiểm tra va chạm
 */
public class Collision {
  //Kiểm tra va chạm giữa Bomber và các vật thể khác
  public static void checkCollisionBomber1(Bomber bomber, List<Entity> entities) {
    for (Entity entity : entities) {
      Shape intersect1 = Shape.intersect(bomber.getBoundingBox(), entity.getBoundingBox());
      if (intersect1.getBoundsInLocal().getWidth() != -1
          && !(entity instanceof Bomber || entity instanceof Grass || entity instanceof Wall)) {
        System.out.println("Collide " + entity.getClass());
      }
    }
  }

  //Kiểm tra va chạm giữa 2 vật thể
  public static boolean checkCollision2(Entity e1, Entity e2) {
    Shape intersect1 = Shape.intersect(e1.getBoundingBox(), e2.getBoundingBox());
    return intersect1.getBoundsInLocal().getWidth() != -1;
  }

  //Kiểm tra 1 ô có phải là Wall
  public static boolean checkWall(int x, int y, List<Entity> entityList) {
    return entityList.contains(new Wall(x, y, Sprite.wall.getFxImage()));
  }

  //Kiểm tra 1 ô có phải là Brick
  public static boolean checkBrick(int x, int y, List<Entity> entityList) {
    return entityList.contains(new Brick(x, y, Sprite.brick.getFxImage()));
  }

  //Kiểm tra di chuyển có hợp lệ
  public static boolean checkLegalMove(Bomber bomber, String direction, List<Entity> stillObject) {
    switch (direction) {
      case "right":
        return !checkWall((bomber.getX() + 24 + 1) / 32, bomber.getY() / 32, stillObject)
            && !checkWall((bomber.getX() + 24 + 1) / 32, (bomber.getY() + 31) / 32, stillObject)
            && !checkBrick((bomber.getX() + 24 + 1) / 32, bomber.getY() / 32, stillObject)
            && !checkBrick((bomber.getX() + 24 + 1) / 32, (bomber.getY() + 31) / 32, stillObject);
      case "left":
        return !checkWall((bomber.getX() - 1) / 32, bomber.getY() / 32, stillObject)
            && !checkWall((bomber.getX() - 1) / 32, (bomber.getY() + 31) / 32, stillObject)
            && !checkBrick((bomber.getX() - 1) / 32, bomber.getY() / 32, stillObject)
            && !checkBrick((bomber.getX() - 1) / 32, (bomber.getY() + 31) / 32, stillObject);
      case "up":
        return !checkWall(bomber.getX() / 32, (bomber.getY() - 1) / 32, stillObject)
            && !checkWall((bomber.getX() + 22) / 32, (bomber.getY() - 1) / 32, stillObject)
            && !checkBrick(bomber.getX() / 32, (bomber.getY() - 1) / 32, stillObject)
            && !checkBrick((bomber.getX() + 22) / 32, (bomber.getY() - 1) / 32, stillObject);
      case "down":
        return !checkWall(bomber.getX() / 32, (bomber.getY() + 32) / 32, stillObject)
            && !checkWall((bomber.getX() + 22) / 32, (bomber.getY() + 32) / 32, stillObject)
            && !checkBrick(bomber.getX() / 32, (bomber.getY() + 32) / 32, stillObject)
            && !checkBrick((bomber.getX() + 22) / 32, (bomber.getY() + 32) / 32, stillObject);

      default:
        throw new IllegalStateException("Unexpected direction: " + direction);
    }
  }
}
