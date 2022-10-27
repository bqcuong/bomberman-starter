package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.controllers.CollisionDetector;
import uet.oop.bomberman.entities.LifeStatus;
import uet.oop.bomberman.entities.objects.IObstacle;
import uet.oop.bomberman.entities.MovingEntity;
import uet.oop.bomberman.events.DirectionStatus;
import uet.oop.bomberman.graphics.GameMap;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Enemy extends MovingEntity implements IObstacle {

    public int REAL_WIDTH = Sprite.SCALED_SIZE - 1;
    public int REAL_HEIGHT = Sprite.SCALED_SIZE - 1;

    protected CollisionDetector collisionDetector;

    protected DirectionStatus directionStatus;

    protected GameMap gameMap;

    protected LifeStatus lifeStatus = LifeStatus.ALIVE;

    public enum DeadPhaseStatus {
        PHASE_NONE, PHASE_FIRST, PHASE_SECOND, PHASE_DISAPPEAR
    }

    protected int indexEnemySprite = 0;

    public LifeStatus getLifeStatus() {
        return lifeStatus;
    }

    public void setLifeStatus(LifeStatus lifeStatus) {
        this.lifeStatus = lifeStatus;
    }

    protected DeadPhaseStatus deadPhaseStatus;

    public void setDeadPhaseStatus(DeadPhaseStatus deadPhaseStatus) {
        this.deadPhaseStatus = deadPhaseStatus;
    }

    public DeadPhaseStatus getDeadPhaseStatus() {
        return deadPhaseStatus;
    }

    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public abstract void updatePosition();

    public abstract int getScore();

    @Override
    public void update() {
        if (lifeStatus.equals(LifeStatus.ALIVE)) {
            if (this instanceof Ballom) {
                if (directionStatus.equals(DirectionStatus.LEFT) || directionStatus.equals(DirectionStatus.DOWN)) {
                    setImg(Sprite.movingSprite(Sprite.balloom_left1,
                            Sprite.balloom_left2, Sprite.balloom_left3, indexEnemySprite, 30).getImage());
                    ++indexEnemySprite;
                } else if (directionStatus.equals(DirectionStatus.RIGHT) || directionStatus.equals(DirectionStatus.UP)) {
                    setImg(Sprite.movingSprite(Sprite.balloom_right1,
                            Sprite.balloom_right2, Sprite.balloom_right2, indexEnemySprite, 30).getImage());
                    ++indexEnemySprite;
                }
            }
            if (this instanceof Oneal) {
                if (directionStatus.equals(DirectionStatus.LEFT) || directionStatus.equals(DirectionStatus.DOWN)) {
                    setImg(Sprite.movingSprite(Sprite.oneal_left1,
                            Sprite.oneal_left2, Sprite.oneal_left3, indexEnemySprite, 30).getImage());
                    ++indexEnemySprite;
                } else if (directionStatus.equals(DirectionStatus.RIGHT) || directionStatus.equals(DirectionStatus.UP)) {
                    setImg(Sprite.movingSprite(Sprite.oneal_right1,
                            Sprite.oneal_right2, Sprite.oneal_right2, indexEnemySprite, 30).getImage());
                    ++indexEnemySprite;
                }
            }
            if (this instanceof Minvo) {
                if (directionStatus.equals(DirectionStatus.LEFT) || directionStatus.equals(DirectionStatus.DOWN)) {
                    setImg(Sprite.movingSprite(Sprite.minvo_left1,
                            Sprite.minvo_left2, Sprite.minvo_left3, indexEnemySprite, 30).getImage());
                    ++indexEnemySprite;
                } else if (directionStatus.equals(DirectionStatus.RIGHT) || directionStatus.equals(DirectionStatus.UP)) {
                    setImg(Sprite.movingSprite(Sprite.minvo_right1,
                            Sprite.minvo_right2, Sprite.minvo_right2, indexEnemySprite, 30).getImage());
                    ++indexEnemySprite;
                }
            }
            if (this instanceof Doll) {
                if (directionStatus.equals(DirectionStatus.LEFT) || directionStatus.equals(DirectionStatus.DOWN)) {
                    setImg(Sprite.movingSprite(Sprite.doll_left1,
                            Sprite.doll_left2, Sprite.doll_left3, indexEnemySprite, 30).getImage());
                    ++indexEnemySprite;
                } else if (directionStatus.equals(DirectionStatus.RIGHT) || directionStatus.equals(DirectionStatus.UP)) {
                    setImg(Sprite.movingSprite(Sprite.doll_right1,
                            Sprite.doll_right2, Sprite.doll_right2, indexEnemySprite, 30).getImage());
                    ++indexEnemySprite;
                }
            }
            updatePosition();
        }
        if (lifeStatus.equals(LifeStatus.DEAD)) {
            if (this instanceof Ballom) {
                if (deadPhaseStatus.equals(DeadPhaseStatus.PHASE_FIRST)) {
                    setImg(Sprite.movingSprite(Sprite.balloom_dead, Sprite.balloom_dead, Sprite.balloom_dead,
                            indexEnemySprite, 30).getImage());
                    ++indexEnemySprite;
                    if (indexEnemySprite == 30) {
                        setDeadPhaseStatus(DeadPhaseStatus.PHASE_SECOND);
                        indexEnemySprite = 0;
                    }
                }
            }
            if (this instanceof Oneal) {
                if (deadPhaseStatus.equals(DeadPhaseStatus.PHASE_FIRST)) {
                    setImg(Sprite.movingSprite(Sprite.oneal_dead, Sprite.oneal_dead, Sprite.oneal_dead,
                            indexEnemySprite, 30).getImage());
                    ++indexEnemySprite;
                    if (indexEnemySprite == 30) {
                        setDeadPhaseStatus(DeadPhaseStatus.PHASE_SECOND);
                        indexEnemySprite = 0;
                    }
                }
            }
            if (this instanceof Minvo) {
                if (deadPhaseStatus.equals(DeadPhaseStatus.PHASE_FIRST)) {
                    setImg(Sprite.movingSprite(Sprite.minvo_dead, Sprite.minvo_dead, Sprite.minvo_dead,
                            indexEnemySprite, 30).getImage());
                    ++indexEnemySprite;
                    if (indexEnemySprite == 30) {
                        setDeadPhaseStatus(DeadPhaseStatus.PHASE_SECOND);
                        indexEnemySprite = 0;
                    }
                }
            }
            if (this instanceof Doll) {
                if (deadPhaseStatus.equals(DeadPhaseStatus.PHASE_FIRST)) {
                    setImg(Sprite.movingSprite(Sprite.doll_dead, Sprite.doll_dead, Sprite.doll_dead,
                            indexEnemySprite, 30).getImage());
                    ++indexEnemySprite;
                    if (indexEnemySprite == 30) {
                        setDeadPhaseStatus(DeadPhaseStatus.PHASE_SECOND);
                        indexEnemySprite = 0;
                    }
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
}
