package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.controllers.CollisionDetector;
import uet.oop.bomberman.entities.LifeStatus;
import uet.oop.bomberman.events.DirectionStatus;
import uet.oop.bomberman.graphics.GameMap;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Ballom extends Enemy {
    private CollisionDetector collisionDetector;

    private DirectionStatus directionStatus;

    //step left before change direction
    private int stepLeft;
    private int prevX;
    private int prevY;


    private GameMap gameMap;

    public Ballom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Ballom(int xUnit, int yUnit, Image img, GameMap gameMap, CollisionDetector collisionDetector) {
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
        if (lifeStatus.equals(LifeStatus.ALIVE)) {
            if (directionStatus.equals(DirectionStatus.LEFT) || directionStatus.equals(DirectionStatus.DOWN)) {
                setImg(Sprite.movingSprite(Sprite.balloom_left1,
                        Sprite.balloom_left2, Sprite.balloom_left3, indexEnemySprite, 30).getImage());
                ++indexEnemySprite;
            } else if (directionStatus.equals(DirectionStatus.RIGHT) || directionStatus.equals(DirectionStatus.UP)) {
                setImg(Sprite.movingSprite(Sprite.balloom_right1,
                        Sprite.balloom_right2, Sprite.balloom_right2, indexEnemySprite, 30).getImage());
                ++indexEnemySprite;
            }
            updatePosition();
        }
        if (lifeStatus.equals(LifeStatus.DEAD)) {
            if (deadPhaseStatus.equals(DeadPhaseStatus.PHASE_FIRST)) {
                setImg(Sprite.movingSprite(Sprite.balloom_dead, Sprite.balloom_dead, Sprite.balloom_dead,
                        indexEnemySprite, 30).getImage());
                ++indexEnemySprite;
                if (indexEnemySprite == 30) {
                    setDeadPhaseStatus(DeadPhaseStatus.PHASE_SECOND);
                    indexEnemySprite = 0;
                }
            }
            if (deadPhaseStatus.equals(DeadPhaseStatus.PHASE_SECOND)) {
                setImg(Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3,
                        indexEnemySprite, 30).getImage());
                ++indexEnemySprite;
                if (indexEnemySprite == 30) {
                    indexEnemySprite = 0;
                    deadPhaseStatus = DeadPhaseStatus.PHASE_DISAPPEAR;
                }
            }

        }

    }

    public void updatePosition() {
        if (lifeStatus.equals(LifeStatus.ALIVE)) {
            if (collisionDetector.checkCollisionWithFlame(x, y, REAL_WIDTH, REAL_HEIGHT)) {
                setLifeStatus(LifeStatus.DEAD);
                setDeadPhaseStatus(DeadPhaseStatus.PHASE_FIRST);
                indexEnemySprite=0;
            }
            if (directionStatus.equals(DirectionStatus.RIGHT)) {
                boolean rightMapCheck = collisionDetector.checkCollisionWithMap(this.x + speedRun, this.y,
                        REAL_WIDTH, REAL_HEIGHT);
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
                        REAL_WIDTH, REAL_HEIGHT);
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
                        REAL_WIDTH, REAL_HEIGHT);
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
                        REAL_WIDTH, REAL_HEIGHT);
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
