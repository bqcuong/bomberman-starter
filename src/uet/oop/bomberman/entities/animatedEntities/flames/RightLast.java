package uet.oop.bomberman.entities.animatedEntities.flames;

import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

public class RightLast extends Explosion {
    public RightLast(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.explosion_horizontal_right_last.getFxImage());
        sprites = new ArrayList<>();
        sprites.add(Sprite.explosion_horizontal_right_last);
        sprites.add(Sprite.explosion_horizontal_right_last1);
        sprites.add(Sprite.explosion_horizontal_right_last2);

    }
}
