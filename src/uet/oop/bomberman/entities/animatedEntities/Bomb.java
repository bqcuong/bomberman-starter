package uet.oop.bomberman.entities.animatedEntities;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.animatedEntities.flames.*;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends AnimatedEntity{
    private final long limitedTime = 2_000_000_000l; // 2s
    private boolean exploded;

    public Bomb(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.bomb.getFxImage());
        exploded = false;
    }

    public boolean isExploded() {
        return exploded;
    }

    /** Add an explosion to position (x, y)
     * of the unit coordinates (not canvas coordinates),
     * and add it into entities */

    public void explode() {
        explosion(new Explosion(xUnit, yUnit));

        int leftUnit = xUnit - 1;
        int rightUnit = xUnit + 1;
        int topUnit = yUnit - 1;
        int bottomUnit = yUnit + 1;
        int leftLastUnit = xUnit - 2;
        int rightLastUnit = xUnit + 2;
        int topLastUnit = yUnit - 2;
        int bottomLastUnit = yUnit + 2;

        int left = leftUnit * Sprite.SCALED_SIZE;
        int right = rightUnit * Sprite.SCALED_SIZE;
        int top = topUnit * Sprite.SCALED_SIZE;
        int bottom = bottomUnit * Sprite.SCALED_SIZE;
        int leftLast = leftLastUnit * Sprite.SCALED_SIZE;
        int rightLast = rightLastUnit * Sprite.SCALED_SIZE;
        int topLast = topLastUnit * Sprite.SCALED_SIZE;
        int bottomLast = bottomLastUnit * Sprite.SCALED_SIZE;

        int xScaled = xUnit * Sprite.SCALED_SIZE;
        int yScaled = yUnit * Sprite.SCALED_SIZE;

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
                .equals("uet.oop.bomberman.entities.staticEntities.Wall")) {
                if (element.existOn(left, yScaled)) {
                    leftCanExplode = false;
                }
                if (element.existOn(right, yScaled)) {
                    rightCanExplode = false;
                }
                if (element.existOn(xScaled, top)) {
                    topCanExplode = false;
                }
                if (element.existOn(xScaled, bottom)) {
                    bottomCanExplode = false;
                }
                if (element.existOn(leftLast, yScaled)) {
                    leftLastCanExplode = false;
                }
                if (element.existOn(rightLast, yScaled)) {
                    rightLastCanExplode = false;
                }
                if (element.existOn(xScaled, topLast)) {
                    topLastCanExplode = false;
                }
                if (element.existOn(xScaled, bottomLast)) {
                    bottomLastCanExplode = false;
                }
            }
        }
        if (leftCanExplode) {
            explosion(new Horizontal(leftUnit, yUnit));
        }
        if (rightCanExplode) {
            explosion(new Horizontal(rightUnit, yUnit));
        }
        if (topCanExplode) {
            explosion(new Vertical(xUnit, topUnit));
        }
        if (bottomCanExplode) {
            explosion(new Vertical(xUnit, bottomUnit));
        }
        if (leftLastCanExplode) {
            explosion(new LeftLast(leftLastUnit, yUnit));
        }
        if (rightLastCanExplode) {
            explosion(new RightLast(rightLastUnit, yUnit));
        }
        if (topLastCanExplode) {
            explosion(new TopLast(xUnit, topLastUnit));
        }
        if (bottomLastCanExplode) {
            explosion(new DownLast(xUnit, bottomLastUnit));
        }
    }

    private void explosion(Explosion flame) {
        BombermanGame.updateQueue.add(flame);
    }
    @Override
    public void update() {
        animate();
        if (animatedTime < limitedTime) {
            img = Sprite.movingSprite(Sprite.bomb,
                    Sprite.bomb_1,
                    Sprite.bomb_2,
                    animatedTime, 500_000_000).getFxImage();
        } else if (!exploded) {
            setVisible(false);
//            explode(x, y);
            explode();
            exploded = true;
        }
    }
}
