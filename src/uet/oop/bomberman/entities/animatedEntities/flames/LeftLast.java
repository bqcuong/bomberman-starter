package uet.oop.bomberman.entities.animatedEntities.flames;

import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

public class LeftLast extends Explosion {
    public LeftLast(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.explosion_horizontal_left_last.getFxImage());
        sprites = new ArrayList<>();
        sprites.add(Sprite.explosion_horizontal_left_last);
        sprites.add(Sprite.explosion_horizontal_left_last1);
        sprites.add(Sprite.explosion_horizontal_left_last2);

    }
}
