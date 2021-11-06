package uet.oop.bomberman.entities;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.flames.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utilities.SetTimeout;

import java.util.List;

public class Bomb extends Entity{
    private AnimationTimer bombAnimation = new AnimationTimer() {
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

    /** Add an explosion to position (x, y)
     * of the unit coordinates (not canvas coordinates),
     * and add it into entities */

    public void explode(int x, int y) {
        explosion(new Explosion(x, y));

        int left = x - 1;
        int right = x + 1;
        int top = y - 1;
        int bottom = y + 1;
        int leftLast = x - 2;
        int rightLast = x + 2;
        int topLast = y - 2;
        int bottomLast = y + 2;

        int leftScaled = left * Sprite.SCALED_SIZE;
        int rightScaled = right * Sprite.SCALED_SIZE;
        int topScaled = top * Sprite.SCALED_SIZE;
        int bottomScaled = bottom * Sprite.SCALED_SIZE;
        int leftLastScaled = leftLast * Sprite.SCALED_SIZE;
        int rightLastScaled = rightLast * Sprite.SCALED_SIZE;
        int topLastScaled = topLast * Sprite.SCALED_SIZE;
        int bottomLastScaled = bottomLast * Sprite.SCALED_SIZE;

        int xScaled = x * Sprite.SCALED_SIZE;
        int yScaled = y * Sprite.SCALED_SIZE;

        boolean leftCanExplode = true;
        boolean rightCanExplode = true;
        boolean topCanExplode = true;
        boolean bottomCanExplode = true;
        boolean leftLastCanExplode = true;
        boolean rightLastCanExplode = true;
        boolean topLastCanExplode = true;
        boolean bottomLastCanExplode = true;

        for (Entity element: BombermanGame.stillObjects) {
            if (element.getClass().getTypeName()
                .equals("uet.oop.bomberman.entities.Wall")) {
                if (element.existOn(leftScaled, yScaled)) {
                    leftCanExplode = false;
                }
                if (element.existOn(rightScaled, yScaled)) {
                    rightCanExplode = false;
                }
                if (element.existOn(xScaled, topScaled)) {
                    topCanExplode = false;
                }
                if (element.existOn(xScaled, bottomScaled)) {
                    bottomCanExplode = false;
                }
                if (element.existOn(leftLastScaled, yScaled)) {
                    leftLastCanExplode = false;
                }
                if (element.existOn(rightLastScaled, yScaled)) {
                    rightLastCanExplode = false;
                }
                if (element.existOn(xScaled, topLastScaled)) {
                    topLastCanExplode = false;
                }
                if (element.existOn(xScaled, bottomLastScaled)) {
                    bottomLastCanExplode = false;
                }
            }
        }
        if (leftCanExplode) {
            explosion(new Horizontal(left, y));
        }
        if (rightCanExplode) {
            explosion(new Horizontal(right, y));
        }
        if (topCanExplode) {
            explosion(new Vertical(x, top));
        }
        if (bottomCanExplode) {
            explosion(new Vertical(x, bottom));
        }
        if (leftLastCanExplode) {
            explosion(new LeftLast(leftLast, y));
        }
        if (rightLastCanExplode) {
            explosion(new RightLast(rightLast, y));
        }
        if (topLastCanExplode) {
            explosion(new TopLast(x, topLast));
        }
        if (bottomLastCanExplode) {
            explosion(new DownLast(x, bottomLast));
        }
    }

    private void explosion(Explosion flame) {
//        entities.add(flame);
        BombermanGame.updateQueue.add(flame);
        flame.active();
        SetTimeout.run(() -> {
            flame.deactivate();
//            entities.remove(flame);
            flame.setVisible(false);
        }, 1000);
    }
    @Override
    public void update() {}
}
