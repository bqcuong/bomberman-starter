package uet.oop.bomberman.entities.animatedEntities.flames;

import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

public class Horizontal extends Explosion {
    public Horizontal(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.explosion_horizontal.getFxImage());
        sprites = new ArrayList<>();
        sprites.add(Sprite.explosion_horizontal);
        sprites.add(Sprite.explosion_horizontal1);
        sprites.add(Sprite.explosion_horizontal2);

    }
}
