package uet.oop.bomberman.entities.animatedEntities;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.animatedEntities.flames.*;

public class ExplosionController {
    private int xUnit;
    private int yUnit;
    private int maxRange;

    public ExplosionController(int xUnit, int yUnit) {
        this.xUnit = xUnit;
        this.yUnit = yUnit;
        maxRange = 2;
    }

    public void begin() {
        Explosion center = new Explosion(xUnit, yUnit);
        BombermanGame.updateQueue.add(center);
        for (int i = 0; i < maxRange; i++) {
            int range = i + 1;
            Explosion up, down, left, right;

            if (range == maxRange) {
                up = new TopLast(xUnit, yUnit - maxRange);
                down = new DownLast(xUnit, yUnit + maxRange);
                left = new LeftLast(xUnit - maxRange, yUnit);
                right = new RightLast(xUnit + maxRange, yUnit);
            } else {
                up = new Vertical(xUnit, yUnit - range);
                down = new Vertical(xUnit, yUnit + range);
                left = new Horizontal(xUnit - range, yUnit);
                right = new Horizontal(xUnit + range, yUnit);
            }
            if (up.canExplode()) {
                BombermanGame.updateQueue.add(up);
            }
            if (down.canExplode()) {
                BombermanGame.updateQueue.add(down);
            }
            if (left.canExplode()) {
                BombermanGame.updateQueue.add(left);
            }
            if (right.canExplode()) {
                BombermanGame.updateQueue.add(right);
            }
        }
    }
}
