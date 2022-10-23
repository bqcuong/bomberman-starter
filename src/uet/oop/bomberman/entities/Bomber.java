package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.controllers.CollisionDetector;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.objects.Brick;
import uet.oop.bomberman.entities.objects.Grass;
import uet.oop.bomberman.entities.objects.Wall;
import uet.oop.bomberman.events.DirectionStatus;
import uet.oop.bomberman.events.KeyboardEvent;
import uet.oop.bomberman.graphics.GameMap;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends MovingEntity {
    private LifeStatus lifeStatus = LifeStatus.ALIVE;
    private KeyboardEvent keyboardEvent;

    //Main game map
    private GameMap gameMap;

    private int left = 2;

    //Real Width of bomber
    public static int REAL_WIDTH = 30;

    //Real Height of bomber
    public static int REAL_HEIGHT = 44;

    private static int MAX_SPEED = 4;
    private CollisionDetector collisionDetector;

    //Bomb list
    private List<Entity> bombList = new ArrayList<>();

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
        if (lifeStatus.equals(LifeStatus.ALIVE)) {
            boolean isPressed = false;
            //unit coordinates of x and y
            int xUnit = this.x / Sprite.SCALED_SIZE;
            int yUnit = this.y / Sprite.SCALED_SIZE;

            //horizontal support
            int horizontalMovingSupport2 = (this.y + Bomber.REAL_HEIGHT)
                    - (yUnit * Sprite.SCALED_SIZE + Sprite.SCALED_SIZE);
            int horizontalMovingSupport1 = Bomber.REAL_HEIGHT - horizontalMovingSupport2;

            //vertical support
            int verticalMovingSupport2 = (this.x + Bomber.REAL_WIDTH)
                    - (xUnit * Sprite.SCALED_SIZE + Sprite.SCALED_SIZE);
            int verticalMovingSupport1 = Bomber.REAL_HEIGHT - verticalMovingSupport2;

            //collision check with item
            collisionDetector.checkCollisionWithItem(this.x, this.y, this);

            //collision check with flame and enemy
            if (collisionDetector.checkCollisionWithEnemyAndFlame(this.x, this.y, bombList)) {
                lifeStatus = LifeStatus.DEAD;
            }

            //handle event
            //UP
            if (keyboardEvent.isPressed(KeyCode.W)) {
                isPressed = true;
                boolean upBombCheck = collisionDetector.checkCollisionWithBombWhenMove(this.x, this.y - speedRun, bombList);
                boolean upMapCheck = collisionDetector.checkCollisionWithMap(this.x, this.y - speedRun);
                if (upMapCheck || upBombCheck) {
                    if (!upBombCheck) {
                        if (verticalMovingSupport1 >= 15
                                && gameMap.getWallsAndGrassAtPosition(xUnit * Sprite.SCALED_SIZE,
                                (yUnit - 1) * Sprite.SCALED_SIZE) instanceof Grass
                                && gameMap.getBrickAtPosition(xUnit * Sprite.SCALED_SIZE,
                                (yUnit - 1) * Sprite.SCALED_SIZE) == null) {
                            super.updateDirection(directionStatus.LEFT, true, speedRun);
                        }

                        if (verticalMovingSupport2 >= 15
                                && (gameMap.getWallsAndGrassAtPosition(xUnit * Sprite.SCALED_SIZE,
                                (yUnit - 1) * Sprite.SCALED_SIZE) instanceof Wall
                                || gameMap.getBrickAtPosition(xUnit * Sprite.SCALED_SIZE,
                                (yUnit - 1) * Sprite.SCALED_SIZE) instanceof Brick)

                                && gameMap.getWallsAndGrassAtPosition((xUnit + 1) * Sprite.SCALED_SIZE,
                                (yUnit - 1) * Sprite.SCALED_SIZE) instanceof Grass
                                && gameMap.getBrickAtPosition((xUnit + 1) * Sprite.SCALED_SIZE,
                                (yUnit - 1) * Sprite.SCALED_SIZE) == null) {
                            super.updateDirection(directionStatus.RIGHT, true, speedRun);

                        }
                    }
                    super.updateDirection(directionStatus.UP, false, speedRun);
                    indexBomberSprite = 0;
                } else {
                    super.updateDirection(directionStatus.UP, true, speedRun);
                }
                setImg(Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1,
                        Sprite.player_up_2, indexBomberSprite, 15).getImage());
            }

            //DOWN
            if (keyboardEvent.isPressed(KeyCode.S)) {
                isPressed = true;
                boolean downBombCheck = collisionDetector.checkCollisionWithBombWhenMove(this.x, this.y + speedRun, bombList);
                boolean downMapCheck = collisionDetector.checkCollisionWithMap(this.x, this.y + speedRun);
                if (downMapCheck || downBombCheck) {
                    if (!downBombCheck) {
                        if (verticalMovingSupport1 >= 15
                                && gameMap.getWallsAndGrassAtPosition(xUnit * Sprite.SCALED_SIZE,
                                (yUnit + 1) * Sprite.SCALED_SIZE) instanceof Grass
                                && gameMap.getBrickAtPosition(xUnit * Sprite.SCALED_SIZE,
                                (yUnit + 1) * Sprite.SCALED_SIZE) == null) {
                            super.updateDirection(directionStatus.LEFT, true, speedRun);
                        }

                        if (verticalMovingSupport2 >= 15
                                && (gameMap.getWallsAndGrassAtPosition(xUnit * Sprite.SCALED_SIZE,
                                (yUnit + 1) * Sprite.SCALED_SIZE) instanceof Wall
                                || gameMap.getBrickAtPosition(xUnit * Sprite.SCALED_SIZE,
                                (yUnit + 1) * Sprite.SCALED_SIZE) instanceof Brick)

                                && gameMap.getWallsAndGrassAtPosition((xUnit + 1) * Sprite.SCALED_SIZE,
                                (yUnit + 1) * Sprite.SCALED_SIZE) instanceof Grass
                                && gameMap.getBrickAtPosition((xUnit + 1) * Sprite.SCALED_SIZE,
                                (yUnit + 1) * Sprite.SCALED_SIZE) == null) {
                            super.updateDirection(directionStatus.RIGHT, true, speedRun);
                        }
                    }
                    indexBomberSprite = 0;
                    super.updateDirection(directionStatus.DOWN, false, speedRun);
                } else {
                    super.updateDirection(directionStatus.DOWN, true, speedRun);
                }
                setImg(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1,
                        Sprite.player_down_2, indexBomberSprite, 15).getImage());
            }

            //LEFT
            if (keyboardEvent.isPressed(KeyCode.A)) {
                isPressed = true;
                boolean leftBombCheck = collisionDetector.checkCollisionWithBombWhenMove(this.x - speedRun, this.y, bombList);
                boolean leftMapCheck = collisionDetector.checkCollisionWithMap(this.x - speedRun, this.y);
                if (leftMapCheck || leftBombCheck) {
                    if (!leftBombCheck) {
                        if (horizontalMovingSupport1 >= 18
                                && gameMap.getWallsAndGrassAtPosition((xUnit - 1) * Sprite.SCALED_SIZE,
                                yUnit * Sprite.SCALED_SIZE) instanceof Grass
                                && gameMap.getBrickAtPosition((xUnit - 1) * Sprite.SCALED_SIZE,
                                yUnit * Sprite.SCALED_SIZE) == null) {
                            super.updateDirection(directionStatus.UP, true, speedRun);
                        }
                        if (horizontalMovingSupport2 >= 18
                                && (gameMap.getWallsAndGrassAtPosition((xUnit - 1) * Sprite.SCALED_SIZE,
                                yUnit * Sprite.SCALED_SIZE) instanceof Wall
                                || gameMap.getBrickAtPosition((xUnit - 1) * Sprite.SCALED_SIZE,
                                yUnit * Sprite.SCALED_SIZE) instanceof Brick)

                                && (gameMap.getWallsAndGrassAtPosition((xUnit - 1) * Sprite.SCALED_SIZE,
                                (yUnit + 1) * Sprite.SCALED_SIZE) instanceof Grass
                                && gameMap.getBrickAtPosition((xUnit - 1) * Sprite.SCALED_SIZE,
                                (yUnit + 1) * Sprite.SCALED_SIZE) == null)) {
                            super.updateDirection(directionStatus.DOWN, true, speedRun);
                        }
                    }
                    super.updateDirection(directionStatus.LEFT, false, speedRun);
                    indexBomberSprite = 0;
                } else {
                    super.updateDirection(directionStatus.LEFT, true, speedRun);
                }

                setImg(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1,
                        Sprite.player_left_2, indexBomberSprite, 15).getImage());
            }

            //RIGHT
            if (keyboardEvent.isPressed(KeyCode.D)) {
                isPressed = true;
                boolean rightBombCheck = collisionDetector.checkCollisionWithBombWhenMove(this.x + speedRun, this.y, bombList);
                boolean rightMapCheck = collisionDetector.checkCollisionWithMap(this.x + speedRun, this.y);
                if (rightMapCheck || rightBombCheck) {
                    if (!rightBombCheck) {
                        if (horizontalMovingSupport1 >= 18
                                && gameMap.getWallsAndGrassAtPosition((xUnit + 1) * Sprite.SCALED_SIZE,
                                yUnit * Sprite.SCALED_SIZE) instanceof Grass
                                && gameMap.getBrickAtPosition((xUnit + 1) * Sprite.SCALED_SIZE,
                                yUnit * Sprite.SCALED_SIZE) == null) {
                            super.updateDirection(directionStatus.UP, true, speedRun);
                        }
                        if (horizontalMovingSupport2 >= 18
                                && (gameMap.getWallsAndGrassAtPosition((xUnit + 1) * Sprite.SCALED_SIZE,
                                yUnit * Sprite.SCALED_SIZE) instanceof Wall
                                || gameMap.getBrickAtPosition((xUnit + 1) * Sprite.SCALED_SIZE,
                                yUnit * Sprite.SCALED_SIZE) instanceof Brick)
                                && (gameMap.getWallsAndGrassAtPosition((xUnit + 1) * Sprite.SCALED_SIZE,
                                (yUnit + 1) * Sprite.SCALED_SIZE) instanceof Grass
                                && gameMap.getBrickAtPosition((xUnit + 1) * Sprite.SCALED_SIZE,
                                (yUnit + 1) * Sprite.SCALED_SIZE) == null)) {
                            super.updateDirection(directionStatus.DOWN, true, speedRun);
                        }
                    }
                    super.updateDirection(directionStatus.RIGHT, false, speedRun);
                    indexBomberSprite = 0;
                } else {
                    super.updateDirection(directionStatus.RIGHT, true, speedRun);
                }
                setImg(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1,
                        Sprite.player_right_2, indexBomberSprite, 15).getImage());
            }

            //SPACE
            if (keyboardEvent.isPressed(KeyCode.SPACE)) {
                isPlantBomb = true;
                isPressed = true;
            } else {
                isPlantBomb = false;
            }

            if (!isPressed) {
                indexBomberSprite = 0;
                isPlantBomb = false;
            } else {
                ++indexBomberSprite;
            }
        }
        if (lifeStatus.equals(LifeStatus.DEAD)) {
            setImg(Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2,
                    Sprite.player_dead3, indexBomberSprite, 40).getImage());
            if (indexBomberSprite < 40) {
                ++indexBomberSprite;
            } else {
                if (left > 0) {
                    --left;
                    lifeStatus = LifeStatus.ALIVE;
                    setX(Sprite.SCALED_SIZE);
                    setY(Sprite.SCALED_SIZE);
                    setImg(Sprite.player_right.getImage());
                } else {
                    setImg(null);
                    //TO DO:
                    //set stage start menu
                }
            }
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
        int xUnit = (getX() + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
        int yUnit = (getY() + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;

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
            if (bomb.getBombStatus() == Bomb.BombStatus.DISAPEAR) {
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

    public Entity getBombAtPosition(int x, int y) {
        int xUnit = x / Sprite.SCALED_SIZE;
        int yUnit = y / Sprite.SCALED_SIZE;
        for (int i = 0; i < bombList.size(); i++) {
            if ((bombList.get(i).getX() / Sprite.SCALED_SIZE) == xUnit
                    && (bombList.get(i).getY() / Sprite.SCALED_SIZE) == yUnit) {
                return bombList.get(i);
            }
        }
        return null;
    }

    public List<Entity> getBombList() {
        return bombList;
    }
}
