package uet.oop.bomberman.controllers;

import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.entities.*;
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
            } else {
                bomb.setAllowedToGoThrough(false);
            }
        }
        return false;
    }

    public boolean checkCollisionWithItem(int x, int y, Bomber bomber) {
        Rectangle rectBomber = new Rectangle(x, y, Bomber.REAL_WIDTH, Bomber.REAL_HEIGHT);
        int delPos = -1;
        for (int i = 0; i < gameMap.getItems().size(); i++) {
            if (rectBomber.intersects(gameMap.getItems().get(i).getX(),
                    gameMap.getItems().get(i).getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE)) {
                delPos = i;
                if (gameMap.getItems().get(i) instanceof ItemSpeed) {
                    bomber.setSpeedRun(bomber.getSpeedRun() + 1);
                }
                if (gameMap.getItems().get(i) instanceof ItemBombs) {
                    bomber.increaseBombListMaxSize();
                }
                if (gameMap.getItems().get(i) instanceof ItemFlames) {
                    bomber.increaseBombLevel();
                }
            }
        }
        if (delPos != -1) {
            gameMap.getItems().remove(delPos);
        }
        return false;
    }

    public boolean checkCollisionWithBrick(int x, int y) {
        Rectangle rectBomber = new Rectangle(x, y, Bomber.REAL_WIDTH, Bomber.REAL_HEIGHT);
        for (Entity element : gameMap.getBricks()) {
            if (rectBomber.intersects(element.getX(),
                    element.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCollisionWithMap(int x, int y) {
        Entity topLeft = gameMap.getWallsAndGrassAtPosition(x, y);
        Entity topRight = gameMap.getWallsAndGrassAtPosition(x + Bomber.REAL_WIDTH, y);
        Entity downLeft = gameMap.getWallsAndGrassAtPosition(x, y + Bomber.REAL_HEIGHT);
        Entity downRight = gameMap.getWallsAndGrassAtPosition(x + Bomber.REAL_WIDTH, y + Bomber.REAL_HEIGHT);
        return checkCollisionWithBrick(x, y) || topLeft instanceof IObstacle || topRight instanceof IObstacle
                || downLeft instanceof IObstacle || downRight instanceof IObstacle;
    }
}
