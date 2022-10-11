package uet.oop.bomberman.controllers;

import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.IObstacle;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;

public class CollisionDetector {
    private Map map;

    public CollisionDetector(Map map) {
        this.map = map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public boolean checkCollision(int x, int y, CheckCollisionObject checkCollisionObject) {
        switch (checkCollisionObject) {
            case OBSTACLE:
                return checkCollisionWithMap(x, y);
            case ITEM_SPEED:
                Rectangle rectBomber = new Rectangle(x, y, Bomber.REAL_WIDTH, Bomber.REAL_HEIGHT);
                for (Entity at : map.getItem()) {
                    if (rectBomber.intersects(at.getX(), at.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE)) {
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    public boolean checkCollisionWithMap(int x, int y) {
        Entity topLeft = map.getEntityAtPosition(x, y);
        Entity topRight = map.getEntityAtPosition(x + Bomber.REAL_WIDTH, y);
        Entity downLeft = map.getEntityAtPosition(x, y + Bomber.REAL_HEIGHT);
        Entity downRight = map.getEntityAtPosition(x + Bomber.REAL_WIDTH, y + Bomber.REAL_HEIGHT);
        return topLeft instanceof IObstacle || topRight instanceof IObstacle
                || downLeft instanceof IObstacle || downRight instanceof IObstacle;
    }
}
