package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.LifeStatus;
import uet.oop.bomberman.entities.objects.IObstacle;
import uet.oop.bomberman.entities.MovingEntity;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Enemy extends MovingEntity implements IObstacle {

    public int REAL_WIDTH = Sprite.SCALED_SIZE - 1;
    public int REAL_HEIGHT = Sprite.SCALED_SIZE - 1;

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

}
