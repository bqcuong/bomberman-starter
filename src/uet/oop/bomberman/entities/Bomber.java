package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.controllers.CheckCollisionObject;
import uet.oop.bomberman.controllers.CollisionDetector;
import uet.oop.bomberman.events.DirectionStatus;
import uet.oop.bomberman.events.KeyboardEvent;
import uet.oop.bomberman.graphics.GameMap;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends MovingEntity {
    private KeyboardEvent keyboardEvent;

    //Main game map
    private GameMap gameMap;

    //Real Width of bomber
    public static int REAL_WIDTH = 20;

    //Real Height of bomber
    public static int REAL_HEIGHT = 29;

    private static int MAX_SPEED = 4;
    private CollisionDetector collisionDetector;

    //Bomb list
    private List<Entity> bombList = new ArrayList<>();
    private List<Integer> bombListTest = new ArrayList<>();

    //Max number of bomb can set at the same time
    private int bombListMaxSize = 1;

    //Check if current bomb is place or not
    private boolean isPlantBomb = false;

    private int bombLevel = 1;

    private int indexBomberSprite = 0;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    public Bomber(int x, int y, Image img, KeyboardEvent keyboardEvent, CollisionDetector collisionDetector, GameMap gameMap) {
        super(x, y, img);
        this.keyboardEvent = keyboardEvent;
        this.collisionDetector = collisionDetector;
        this.gameMap = gameMap;
    }

    private void updateKeyHandle() {
        boolean isPressed = false;
        collisionDetector.checkCollisionWithItem(this.x, this.y, this);
        if (keyboardEvent.isPressed(KeyCode.W)) {
            isPressed = true;
            if (collisionDetector.checkCollision(this.x, this.y - speedRun, CheckCollisionObject.OBSTACLE)
                    || collisionDetector.checkCollisionWithBombWhenMove(this.x, this.y - speedRun, bombList)) {
                super.updateDirection(directionStatus.UP, false, speedRun);
                indexBomberSprite = 0;
            } else {
                super.updateDirection(directionStatus.UP, true, speedRun);
            }
            setImg(Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1,
                    Sprite.player_up_2, indexBomberSprite, 15).getImage());
            ++indexBomberSprite;
        }
        if (keyboardEvent.isPressed(KeyCode.A)) {
            isPressed = true;
            if (collisionDetector.checkCollision(this.x - speedRun, this.y, CheckCollisionObject.OBSTACLE)
                    || collisionDetector.checkCollisionWithBombWhenMove(this.x - speedRun, this.y, bombList)) {
                super.updateDirection(directionStatus.LEFT, false, speedRun);
                indexBomberSprite = 0;
            } else {
                super.updateDirection(directionStatus.LEFT, true, speedRun);
            }

            setImg(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1,
                    Sprite.player_left_2, indexBomberSprite, 15).getImage());
            ++indexBomberSprite;
        }
        if (keyboardEvent.isPressed(KeyCode.S)) {
            isPressed = true;
            if (collisionDetector.checkCollision(this.x, this.y + speedRun, CheckCollisionObject.OBSTACLE)
                    || collisionDetector.checkCollisionWithBombWhenMove(this.x, this.y + speedRun, bombList)) {
                super.updateDirection(directionStatus.DOWN, false, speedRun);
                indexBomberSprite = 0;
            } else {
                super.updateDirection(directionStatus.DOWN, true, speedRun);
            }
            setImg(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1,
                    Sprite.player_down_2, indexBomberSprite, 15).getImage());
            ++indexBomberSprite;
        }
        if (keyboardEvent.isPressed(KeyCode.D)) {
            isPressed = true;
            if (collisionDetector.checkCollision(this.x + speedRun, this.y, CheckCollisionObject.OBSTACLE)
                    || collisionDetector.checkCollisionWithBombWhenMove(this.x + speedRun, this.y, bombList)) {
                super.updateDirection(directionStatus.RIGHT, false, speedRun);
                indexBomberSprite = 0;
            } else {
                super.updateDirection(directionStatus.RIGHT, true, speedRun);
            }
            setImg(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1,
                    Sprite.player_right_2, indexBomberSprite, 15).getImage());
            ++indexBomberSprite;
        }
        if (keyboardEvent.isPressed(KeyCode.SPACE)) {
            isPlantBomb = true;
            isPressed = true;
        } else {
            isPlantBomb = false;
        }

        if (!isPressed) {
            indexBomberSprite = 0;
            isPlantBomb = false;
        }
    }

    @Override
    public void update() {
        updateKeyHandle();
        updatePlantBomb();
        updateBombList();
    }

    @Override
    public void render(GraphicsContext gc) {
        for (Entity element : bombList) {
            element.render(gc);
        }
        super.render(gc);
    }

    public void updatePlantBomb() {
        int xUnit = (getX() + Sprite.DEFAULT_SIZE) / Sprite.SCALED_SIZE;
        int yUnit = (getY() + Sprite.DEFAULT_SIZE) / Sprite.SCALED_SIZE;
//        if (bombListMaxSize == 1) {
//            if (bombList.isEmpty() && isPlantBomb) {
//                bombList.add(new Bomb(xUnit, yUnit, Sprite.bomb.getImage(), gameMap));
//            }
//        }
        if (bombList.size() < bombListMaxSize) {
            if (!bombList.isEmpty() && isPlantBomb) {
                //check duplicate bomb has already in the list or not
                boolean isDuplicateBomb = false;
                for (Entity element : bombList) {
                    if ((element.getX() / Sprite.SCALED_SIZE == xUnit
                            && element.getY() / Sprite.SCALED_SIZE == yUnit)) {
                        isDuplicateBomb = true;
                    }
                }
                if (!isDuplicateBomb) {
                    bombList.add(new Bomb(xUnit, yUnit, Sprite.bomb.getImage(), bombLevel, gameMap));
                }

            }
            if (bombList.isEmpty() && isPlantBomb) {
                bombList.add(new Bomb(xUnit, yUnit, Sprite.bomb.getImage(), bombLevel, gameMap));
            }
        }
        isPlantBomb = false;
    }

    public void updateBombList() {
        for (Entity element : bombList) {
            element.update();
        }
        if (bombList.size() > 0) {
            Bomb bomb = (Bomb) bombList.get(0);
            if (bomb.getBombStatus() == BombStatus.DISAPEAR) {
                bombList.remove(0);
            }
        }
    }

    @Override
    public void updateDirection(DirectionStatus directionStatus, boolean isAllowedToMove, int speedRun) {
        super.updateDirection(directionStatus, isAllowedToMove, speedRun);
    }

    //Bomb Item
    public void increaseBombListMaxSize() {
        ++bombListMaxSize;
    }

    public void setBombListMaxSize(int bombListMaxSize) {
        this.bombListMaxSize = bombListMaxSize;
    }


    public int getBombListMaxSize() {
        return bombListMaxSize;
    }

    @Override
    public int getSpeedRun() {
        return speedRun;
    }

    @Override
    public void setSpeedRun(int speedRun) {
        if (this.speedRun == MAX_SPEED) {
            return;
        }
        this.speedRun = speedRun;
    }

    public void increaseBombLevel() {
        ++bombLevel;
    }

    public int getBombLevel() {
        return bombLevel;
    }
}
