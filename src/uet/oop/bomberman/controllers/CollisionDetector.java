package uet.oop.bomberman.controllers;

import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.entities.items.ItemBombs;
import uet.oop.bomberman.entities.items.ItemFlames;
import uet.oop.bomberman.entities.items.ItemSpeed;
import uet.oop.bomberman.entities.items.Portal;
import uet.oop.bomberman.entities.objects.Wall;
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

    public boolean checkCollisionWithBomb(int x, int y, List<Entity> bombList, int REAL_WIDTH, int REAL_HEIGHT) {
        final int FIX_POSITION = 12;
        Rectangle rectEnemy = new Rectangle(x, y, REAL_WIDTH, REAL_HEIGHT);
        for (Entity element : bombList) {
            if (rectEnemy.intersects(element.getX() + FIX_POSITION,
                    element.getY() + FIX_POSITION,
                    Sprite.SCALED_SIZE - FIX_POSITION * 2,
                    Sprite.SCALED_SIZE - FIX_POSITION * 2)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCollisionWithFlame(int x, int y, int REAL_WIDTH, int REAL_HEIGHT) {
        final int FIX_POSITION = 6;
        Rectangle rectBlock = new Rectangle(x, y, REAL_WIDTH, REAL_HEIGHT);
        for (Entity element : gameMap.getBombList()) {
            Bomb bomb = (Bomb) element;
            if (bomb.getBombStatus().equals(Bomb.BombStatus.WENTOFF)) {
                if (rectBlock.intersects(bomb.getX(), bomb.getY(),
                        Sprite.SCALED_SIZE, Sprite.SCALED_SIZE)) {
                    return true;
                }
                for (Entity flame : bomb.getUpFlameList()) {
                    if (rectBlock.intersects(flame.getX() + FIX_POSITION, flame.getY() + FIX_POSITION,
                            Sprite.SCALED_SIZE - 2 * FIX_POSITION,
                            Sprite.SCALED_SIZE - 2 * FIX_POSITION)) {
                        return true;
                    }
                }
                for (Entity flame : bomb.getDownFlameList()) {
                    if (rectBlock.intersects(flame.getX() + FIX_POSITION, flame.getY() + FIX_POSITION,
                            Sprite.SCALED_SIZE - 2 * FIX_POSITION,
                            Sprite.SCALED_SIZE - 2 * FIX_POSITION)) {
                        return true;
                    }
                }
                for (Entity flame : bomb.getLeftFlameList()) {
                    if (rectBlock.intersects(flame.getX() + FIX_POSITION, flame.getY() + FIX_POSITION,
                            Sprite.SCALED_SIZE - 2 * FIX_POSITION,
                            Sprite.SCALED_SIZE - 2 * FIX_POSITION)) {
                        return true;
                    }
                }
                for (Entity flame : bomb.getRightFlameList()) {
                    if (rectBlock.intersects(flame.getX() + FIX_POSITION, flame.getY() + FIX_POSITION,
                            Sprite.SCALED_SIZE - 2 * FIX_POSITION,
                            Sprite.SCALED_SIZE - 2 * FIX_POSITION)) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public boolean checkCollisionWithEnemy(int x, int y, List<Entity> enemyList, int REAL_WIDTH, int REAL_HEIGHT) {
        final int FIX_POSITION = 6;
        Rectangle rectEnemy = new Rectangle(x, y, REAL_WIDTH, REAL_HEIGHT);
        for (Entity element : enemyList) {
            Enemy enemy = (Enemy) element;
            if (enemy.getLifeStatus().equals(LifeStatus.ALIVE)) {
                if (rectEnemy.intersects(element.getX() + FIX_POSITION, element.getY() + FIX_POSITION,
                        Sprite.SCALED_SIZE - FIX_POSITION * 2, Sprite.SCALED_SIZE - FIX_POSITION * 2)) {
                    return true;
                }
            }

        }
        return false;
    }

    public void checkCollisionWithItem(int x, int y, Bomber bomber) {
        Rectangle rectBomber = new Rectangle(x, y, Bomber.REAL_WIDTH, Bomber.REAL_HEIGHT);
        int delPos = -1;
        for (int i = 0; i < gameMap.getItems().size(); i++) {
            if (rectBomber.intersects(gameMap.getItems().get(i).getX(),
                    gameMap.getItems().get(i).getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE)
                    && gameMap.getBrickAtPosition(gameMap.getItems().get(i).getX(), gameMap.getItems().get(i).getY()) == null) {
                Game gameInstance = Game.getInstance();
                if (gameMap.getItems().get(i) instanceof ItemSpeed) {
                    if (!Game.getInstance().getAudioController().isPlaying(AudioController.AudioType.ITEM_COLLECTED)) {
                        Game.getInstance().getAudioController().playSoundEffect(AudioController.AudioType.ITEM_COLLECTED);
                    }
                    bomber.setSpeedRun(bomber.getSpeedRun() + 1);
                    gameInstance.getItemInfo().increaseItemSpeedCount();
                    delPos = i;
                }
                if (gameMap.getItems().get(i) instanceof ItemBombs) {
                    if (!Game.getInstance().getAudioController().isPlaying(AudioController.AudioType.ITEM_COLLECTED)) {
                        Game.getInstance().getAudioController().playSoundEffect(AudioController.AudioType.ITEM_COLLECTED);
                    }
                    bomber.increaseBombListMaxSize();
                    gameInstance.getItemInfo().increaseItemBombsCount();
                    delPos = i;
                }
                if (gameMap.getItems().get(i) instanceof ItemFlames) {
                    if (!Game.getInstance().getAudioController().isPlaying(AudioController.AudioType.ITEM_COLLECTED)) {
                        Game.getInstance().getAudioController().playSoundEffect(AudioController.AudioType.ITEM_COLLECTED);
                    }
                    bomber.increaseBombLevel();
                    gameInstance.getItemInfo().increaseItemFlamesCount();
                    delPos = i;
                }
                if (gameMap.getItems().get(i) instanceof Portal
                        && gameMap.getEnemies().size() == 0
                        && Game.getInstance().getGameStatus().equals(GameStatus.PLAYING)) {
                    Game.getInstance().setGameStatus(GameStatus.NEXT_LEVEL_BRIDGE);
                }
            }
        }
        if (delPos != -1) {
            gameMap.getItems().remove(delPos);
        }
    }

    public boolean checkCollisionWithMap(int x, int y, int REAL_WIDTH, int REAL_HEIGHT, boolean isWallPass) {
        //Wall and grass entity
        Entity topLeftWallAndGrass = gameMap.getWallsAndGrassAtPosition(x, y);
        Entity topRightWallAndGrass = gameMap.getWallsAndGrassAtPosition(x + REAL_WIDTH, y);
        Entity downLeftWallAndGrass = gameMap.getWallsAndGrassAtPosition(x, y + REAL_HEIGHT);
        Entity downRightWallAndGrass = gameMap.getWallsAndGrassAtPosition(x + REAL_WIDTH,
                y + REAL_HEIGHT);
        Entity topLeftBrick = gameMap.getBrickAtPosition(x, y);
        Entity topRightBrick = gameMap.getBrickAtPosition(x + REAL_WIDTH, y);
        Entity downLeftBrick = gameMap.getBrickAtPosition(x, y + REAL_HEIGHT);
        Entity downRightBrick = gameMap.getBrickAtPosition(x + REAL_WIDTH,
                y + REAL_HEIGHT);
        if (isWallPass) {
            return topLeftWallAndGrass instanceof Wall || topRightWallAndGrass instanceof Wall
                    || downLeftWallAndGrass instanceof Wall || downRightWallAndGrass instanceof Wall;
        }
        return topLeftWallAndGrass instanceof Wall || topRightWallAndGrass instanceof Wall
                || downLeftWallAndGrass instanceof Wall || downRightWallAndGrass instanceof Wall
                || topLeftBrick != null || topRightBrick != null || downLeftBrick != null || downRightBrick != null;
    }
}
