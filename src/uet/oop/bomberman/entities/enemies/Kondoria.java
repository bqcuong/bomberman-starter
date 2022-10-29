package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.controllers.AudioController;
import uet.oop.bomberman.controllers.CollisionDetector;
import uet.oop.bomberman.controllers.Game;
import uet.oop.bomberman.entities.LifeStatus;
import uet.oop.bomberman.events.DirectionStatus;
import uet.oop.bomberman.graphics.GameMap;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Kondoria extends Enemy {

    //step left before change direction
    private int stepLeft;
    private int prevX;
    private int prevY;

    private final int score = 1000;

    public Kondoria(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Kondoria(int xUnit, int yUnit, Image img, GameMap gameMap, CollisionDetector collisionDetector) {
        super(xUnit, yUnit, img);
        this.gameMap = gameMap;
        directionStatus = getRandomDirectionStatus();
        this.collisionDetector = collisionDetector;
        stepLeft = getRandomStepLeft();
        prevX = x / Sprite.SCALED_SIZE;
        prevY = y / Sprite.SCALED_SIZE;
        setLifeStatus(LifeStatus.ALIVE);
        setSpeedRun(1);
    }


    @Override
    public void update() {
        super.update();
    }

    public void updatePosition() {
        if (lifeStatus.equals(LifeStatus.ALIVE)) {
            if (collisionDetector.checkCollisionWithFlame(x, y, REAL_WIDTH, REAL_HEIGHT)) {
                if (!Game.getInstance().getAudioController().isPlaying(AudioController.AudioType.ENEMY_DIE)) {
                    Game.getInstance().getAudioController().playSoundEffect(AudioController.AudioType.ENEMY_DIE);
                }
                setLifeStatus(LifeStatus.DEAD);
                setDeadPhaseStatus(DeadPhaseStatus.PHASE_FIRST);
                indexEnemySprite = 0;
            }
            if (directionStatus.equals(DirectionStatus.RIGHT)) {
                boolean rightMapCheck = collisionDetector.checkCollisionWithMap(this.x + speedRun, this.y,
                        REAL_WIDTH, REAL_HEIGHT, true);
                boolean rightBombCheck = collisionDetector.checkCollisionWithBomb(this.x + speedRun, this.y,
                        gameMap.getPlayer().getBombList(), REAL_WIDTH, REAL_HEIGHT);
                if (rightMapCheck || rightBombCheck) {
                    updateDirection(directionStatus, false, speedRun);
                    directionStatus = getRandomDirectionStatus();
                    indexEnemySprite = 0;
                } else {
                    updateDirection(directionStatus, true, speedRun);
                    if (prevX != this.x / Sprite.SCALED_SIZE && stepLeft > 0) {
                        stepLeft--;
                        prevX = this.x / Sprite.SCALED_SIZE;
                    }
                }
            }
            if (directionStatus.equals(DirectionStatus.LEFT)) {
                boolean leftMapCheck = collisionDetector.checkCollisionWithMap(this.x - speedRun, this.y,
                        REAL_WIDTH, REAL_HEIGHT, true);
                boolean leftBombCheck = collisionDetector.checkCollisionWithBomb(this.x - speedRun, this.y,
                        gameMap.getPlayer().getBombList(), REAL_WIDTH, REAL_HEIGHT);
                if (leftMapCheck || leftBombCheck) {
                    updateDirection(directionStatus, false, speedRun);
                    directionStatus = getRandomDirectionStatus();
                    indexEnemySprite = 0;
                } else {
                    updateDirection(directionStatus, true, speedRun);
                    if (prevX != this.x / Sprite.SCALED_SIZE && stepLeft > 0) {
                        stepLeft--;
                        prevX = this.x / Sprite.SCALED_SIZE;
                    }
                }
            }
            if (directionStatus.equals(DirectionStatus.UP)) {
                boolean upMapCheck = collisionDetector.checkCollisionWithMap(this.x, this.y - speedRun,
                        REAL_WIDTH, REAL_HEIGHT, true);
                boolean upBombCheck = collisionDetector.checkCollisionWithBomb(this.x, this.y - speedRun,
                        gameMap.getPlayer().getBombList(), REAL_WIDTH, REAL_HEIGHT);
                if (upMapCheck || upBombCheck) {
                    updateDirection(directionStatus, false, speedRun);
                    directionStatus = getRandomDirectionStatus();
                    indexEnemySprite = 0;
                } else {
                    updateDirection(directionStatus, true, speedRun);
                    if (prevY != this.y / Sprite.SCALED_SIZE && stepLeft > 0) {
                        stepLeft--;
                        prevY = this.y / Sprite.SCALED_SIZE;
                    }
                }
            }
            if (directionStatus.equals(DirectionStatus.DOWN)) {
                boolean downMapCheck = collisionDetector.checkCollisionWithMap(this.x, this.y + speedRun,
                        REAL_WIDTH, REAL_HEIGHT, true);
                boolean downBombCheck = collisionDetector.checkCollisionWithBomb(this.x, this.y + speedRun,
                        gameMap.getPlayer().getBombList(), REAL_WIDTH, REAL_HEIGHT);
                if (downMapCheck || downBombCheck) {
                    updateDirection(directionStatus, false, speedRun);
                    directionStatus = getRandomDirectionStatus();
                    indexEnemySprite = 0;
                } else {
                    updateDirection(directionStatus, true, speedRun);
                    if (prevY != this.y / Sprite.SCALED_SIZE && stepLeft > 0) {
                        stepLeft--;
                        prevY = this.y / Sprite.SCALED_SIZE;
                    }
                }
            }
            if (stepLeft == 0) {
                directionStatus = getRandomDirectionStatus();
                stepLeft = getRandomStepLeft();
            }
        }

    }

    @Override
    public int getScore() {
        return score;
    }

    public DirectionStatus getRandomDirectionStatus() {
        List<DirectionStatus> list = Arrays.asList(DirectionStatus.UP,
                DirectionStatus.RIGHT, DirectionStatus.LEFT, DirectionStatus.DOWN);
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public int getRandomStepLeft() {
        List<Integer> list
                = Arrays.asList(2, 3, 4, 4, 2, 3, 4, 2, 3, 2, 3, 4, 4, 2, 3, 4, 2, 3, 9, 10);
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

}
