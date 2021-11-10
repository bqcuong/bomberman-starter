package uet.oop.bomberman.entities.animatedEntities.flames;

import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

public class TopLast extends Explosion {
    public TopLast(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.explosion_vertical_top_last.getFxImage());
        sprites = new ArrayList<>();
        sprites.add(Sprite.explosion_vertical_top_last);
        sprites.add(Sprite.explosion_vertical_top_last1);
        sprites.add(Sprite.explosion_vertical_top_last2);

    }
}
