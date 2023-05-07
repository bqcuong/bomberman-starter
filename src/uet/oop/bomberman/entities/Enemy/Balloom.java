package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

/**
 * Kẻ địch Balloom
 */
public class Balloom extends Entity {
    private boolean isDead = false;
    private boolean isDisappear = false;
    private int disappearTime = 0;
    public final int DISAPPEAR = 15;
    public Balloom(int x, int y, Image img) {
        super(x, y, img);
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean isDisappear() {
        return isDisappear;
    }

    public void setDisappear(boolean disappear) {
        isDisappear = disappear;
    }

    @Override
    public void update() {
        if (isDead) disappearTime++;
        if (disappearTime == DISAPPEAR) isDisappear = true;
        if (disappearTime > 0) this.img = Sprite.balloom_dead.getFxImage();
    }
}
