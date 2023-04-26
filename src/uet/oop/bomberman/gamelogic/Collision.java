package uet.oop.bomberman.gamelogic;

import javafx.scene.shape.Shape;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.*;

import java.util.List;

public class Collision {
    private static Shape intersect1;

    public static void checkCollision1(Bomber bomber, List<Entity> entities) {
        for (Entity entity : entities) {
            intersect1 = Shape.intersect(bomber.getBoundingBox(), entity.getBoundingBox());
            if (intersect1.getBoundsInLocal().getWidth() != -1 && !(entity instanceof Bomber)) {
                System.out.println("Collide " + entity.getClass());
            }
        }
    }
}
