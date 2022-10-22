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
                return !bomb.isAllowedToGoThrough();
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
                    gameMap.getItems().get(i).getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE)
                    && gameMap.getBrickAtPosition(gameMap.getItems().get(i).getX(), gameMap.getItems().get(i).getY()) == null) {
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

    public boolean checkCollisionWithMap(int x, int y) {
        //Wall and grass entity
        Entity topLeftWallAndGrass = gameMap.getWallsAndGrassAtPosition(x, y);
        Entity topRightWallAndGrass = gameMap.getWallsAndGrassAtPosition(x + Bomber.REAL_WIDTH, y);
        Entity downLeftWallAndGrass = gameMap.getWallsAndGrassAtPosition(x, y + Bomber.REAL_HEIGHT);
        Entity downRightWallAndGrass = gameMap.getWallsAndGrassAtPosition(x + Bomber.REAL_WIDTH,
                y + Bomber.REAL_HEIGHT);
        Entity topLeftBrick = gameMap.getBrickAtPosition(x, y);
        Entity topRightBrick = gameMap.getBrickAtPosition(x + Bomber.REAL_WIDTH, y);
        Entity downLeftBrick = gameMap.getBrickAtPosition(x, y + Bomber.REAL_HEIGHT);
        Entity downRightBrick = gameMap.getBrickAtPosition(x + Bomber.REAL_WIDTH,
                y + Bomber.REAL_HEIGHT);
        return topLeftWallAndGrass instanceof Wall || topRightWallAndGrass instanceof Wall
                || downLeftWallAndGrass instanceof Wall || downRightWallAndGrass instanceof Wall
                || topLeftBrick != null || topRightBrick != null || downLeftBrick != null || downRightBrick != null;
    }
}
