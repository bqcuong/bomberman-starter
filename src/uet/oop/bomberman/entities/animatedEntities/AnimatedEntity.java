package uet.oop.bomberman.entities.animatedEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;

public abstract class AnimatedEntity extends Entity {
    protected long animatedTime = 0;
    private final long MAX_ANIMATE = 20_000_000_000l; // 20 secs

    public AnimatedEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void animate() {
        if (animatedTime < MAX_ANIMATE) {
            animatedTime += BombermanGame.TIME_UNIT;
        } else {
            animatedTime = 0;
        }
    }
}
