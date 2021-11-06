package uet.oop.bomberman.entities;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import uet.oop.bomberman.graphics.Sprite;

import java.util.concurrent.TimeUnit;

public class Bomb extends Entity{
    protected AnimationTimer bombAnimation = new AnimationTimer() {
        int imgIndex = 0; // == Sprite.bomb.getFxImage()
        private long lastUpdate = 0;

        @Override
        public void handle(long l) {
            if (l - lastUpdate >= 100_000_000) {
                lastUpdate = l ;
                if (imgIndex == 0) {
                    img = Sprite.bomb_1.getFxImage();
                    imgIndex = 1;
                } else if (imgIndex == 1) {
                    img = Sprite.bomb_2.getFxImage();
                    imgIndex = 2;
                } else if (imgIndex == 2) {
                    img = Sprite.bomb_1.getFxImage();
                    imgIndex = 3;
                } else if (imgIndex == 3) {
                    img = Sprite.bomb.getFxImage();
                    imgIndex = 0;
                }
            }
        }
    };

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void active() {
        bombAnimation.start();
    }

    public void deactivate() {
        bombAnimation.stop();
    }

    @Override
    public void update() {

    }
}
