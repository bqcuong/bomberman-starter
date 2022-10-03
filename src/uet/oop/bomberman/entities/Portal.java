package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Entity {

    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    public boolean collidesWithBomber(Entity bomber) {
        if ((x-bomber.getX())/ Sprite.SCALED_SIZE == 0 && (y- bomber.getY())/Sprite.SCALED_SIZE == 0) {
            return true;
        }
        return false;
    }

    public void update() {

    }
}
