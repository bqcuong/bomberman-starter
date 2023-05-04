package uet.oop.bomberman.gamelogic;


import static uet.oop.bomberman.BombermanGame.SPEED;

import java.util.List;
import javafx.scene.shape.Shape;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

public class Collision {

  public static void checkCollision1(Bomber bomber, List<Entity> entities) {
    for (Entity entity : entities) {
      Shape intersect1 = Shape.intersect(bomber.getBoundingBox(), entity.getBoundingBox());
      if (intersect1.getBoundsInLocal().getWidth() != -1 && !(entity instanceof Bomber)) {
        System.out.println("Collide " + entity.getClass());
      }
    }
  }

  public static boolean checkWall(int x, int y, List<Entity> entityList) {
    return entityList.contains(new Wall(x, y, Sprite.wall.getFxImage()));
  }

  public static boolean checkBrick(int x, int y, List<Entity> entityList) {
    return entityList.contains(new Brick(x, y, Sprite.brick.getFxImage()));
  }

  public static boolean checkLegalMove(Bomber bomber, String direction, List<Entity> stillObject) {
    switch (direction) {
      case "right":
        return !checkWall((bomber.getX() + 22 + SPEED )/ 32, bomber.getY() / 32, stillObject) &&
                !checkWall((bomber.getX() + 22 + SPEED )/ 32, (bomber.getY() + 31 )/ 32, stillObject)&&
                !checkBrick((bomber.getX() + 22 + SPEED )/ 32, bomber.getY() / 32, stillObject) &&
                !checkBrick((bomber.getX() + 22 + SPEED )/ 32, (bomber.getY() + 31 )/ 32, stillObject);


        
      case "left":
        return true;
    }
    return true;
  }
}
