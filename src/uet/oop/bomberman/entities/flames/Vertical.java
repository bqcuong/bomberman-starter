package uet.oop.bomberman.entities.flames;

import javafx.animation.AnimationTimer;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Explosion;
import uet.oop.bomberman.graphics.Sprite;

public class Vertical extends Explosion {
    private AnimationTimer explosionAnimation = new AnimationTimer() {
        int imgIndex = 0; // == Sprite.explosion_vertical.getFxImage()
        private long lastUpdate = 0;

        @Override
        public void handle(long l) {
            if (l - lastUpdate >= 100_000_000) {
                lastUpdate = l ;
                if (imgIndex == 0) {
                    img = Sprite.explosion_vertical1.getFxImage();
                    imgIndex = 1;
                } else if (imgIndex == 1) {
                    img = Sprite.explosion_vertical2.getFxImage();
                    imgIndex = 2;
                } else if (imgIndex == 2) {
                    img = Sprite.explosion_vertical1.getFxImage();
                    imgIndex = 3;
                } else if (imgIndex == 3) {
                    img = Sprite.explosion_vertical.getFxImage();
                    imgIndex = 0;
                }
            }
        }
    };

    public Vertical(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.explosion_vertical.getFxImage());
    }

    public void active() {
        explosionAnimation.start();
    }

    public void deactivate() {
        explosionAnimation.stop();
    }
}
