package uet.oop.bomberman.entities;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Explosion extends Entity {
    private double activeTime = 2 * 60;
    private AnimationTimer explosionAnimation = new AnimationTimer() {
        int imgIndex = 0; // == Sprite.bomb_exploded.getFxImage()
        private long lastUpdate = 0;

        @Override
        public void handle(long l) {
            if (l - lastUpdate >= 100_000_000) {
                lastUpdate = l ;
                if (imgIndex == 0) {
                    img = Sprite.bomb_exploded1.getFxImage();
                    imgIndex = 1;
                } else if (imgIndex == 1) {
                    img = Sprite.bomb_exploded2.getFxImage();
                    imgIndex = 2;
                } else if (imgIndex == 2) {
                    img = Sprite.bomb_exploded1.getFxImage();
                    imgIndex = 3;
                } else if (imgIndex == 3) {
                    img = Sprite.bomb_exploded.getFxImage();
                    imgIndex = 0;
                }
            }
        }
    };

    public Explosion(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Explosion(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.bomb_exploded.getFxImage());
    }

    public void active() {
        explosionAnimation.start();
    }

    public void deactivate() {
        explosionAnimation.stop();
    }

    @Override
    public void update() {
        if (activeTime > 0) {
            activeTime--;
        } else if (isVisible()) {
            setVisible(false);
            this.deactivate();
        }
    }
}
