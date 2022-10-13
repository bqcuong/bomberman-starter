package uet.oop.bomberman.controllers;

import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.IObstacle;
import uet.oop.bomberman.graphics.GameMap;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class CollisionDetector {
    private GameMap gameMap;

    public CollisionDetector(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void setMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public boolean checkCollision(int x, int y, CheckCollisionObject checkCollisionObject) {
        Rectangle rectBomber = new Rectangle(x, y, Bomber.REAL_WIDTH, Bomber.REAL_HEIGHT);
        switch (checkCollisionObject) {
            case OBSTACLE:
                return checkCollisionWithMap(x, y);
//                for (List<Entity> List : map.getMap()) {
//                    for (Entity entity : List) {
//                        if (entity instanceof IObstacle) {
//                            if (rectBomber.intersects(entity.getX(), entity.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE)) {
//                                return true;
//                            }
//                        }
//                    }
//                }
            case ITEM_SPEED:
                for (Entity at : gameMap.getItem()) {
                    if (rectBomber.intersects(at.getX(), at.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE)) {
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    public boolean checkCollisionWithBombWhenMove(int x, int y, List<Entity> bombList) {
        Rectangle rectBomber = new Rectangle(x, y, Bomber.REAL_WIDTH, Bomber.REAL_HEIGHT);
        for (Entity element : bombList) {
            Bomb bomb = (Bomb) element;
            if (rectBomber.intersects(element.getX(), element.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE)) {
                if (bomb.isAllowedToGoThrough()) {
                    return false;
                } else {
                    return true;
                }
            }else{
                bomb.setAllowedToGoThrough(false);
            }
        }
        return false;
    }

    public boolean checkCollisionWithMap(int x, int y) {
        Entity topLeft = gameMap.getEntityAtPosition(x, y);
        Entity topRight = gameMap.getEntityAtPosition(x + Bomber.REAL_WIDTH, y);
        Entity downLeft = gameMap.getEntityAtPosition(x, y + Bomber.REAL_HEIGHT);
        Entity downRight = gameMap.getEntityAtPosition(x + Bomber.REAL_WIDTH, y + Bomber.REAL_HEIGHT);
        return topLeft instanceof IObstacle || topRight instanceof IObstacle
                || downLeft instanceof IObstacle || downRight instanceof IObstacle;
    }
}
