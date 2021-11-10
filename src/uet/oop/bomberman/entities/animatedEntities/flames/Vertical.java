package uet.oop.bomberman.entities.animatedEntities.flames;

import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

public class Vertical extends Explosion {
    public Vertical(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.explosion_vertical.getFxImage());
        sprites = new ArrayList<>();
        sprites.add(Sprite.explosion_vertical);
        sprites.add(Sprite.explosion_vertical1);
        sprites.add(Sprite.explosion_vertical2);

    }
}
