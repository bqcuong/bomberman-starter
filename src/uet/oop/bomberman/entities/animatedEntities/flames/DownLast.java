package uet.oop.bomberman.entities.animatedEntities.flames;

import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

public class DownLast extends Explosion {
    public DownLast(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.explosion_vertical_down_last.getFxImage());
        sprites = new ArrayList<>();
        sprites.add(Sprite.explosion_vertical_down_last);
        sprites.add(Sprite.explosion_vertical_down_last1);
        sprites.add(Sprite.explosion_vertical_down_last2);

    }
}
