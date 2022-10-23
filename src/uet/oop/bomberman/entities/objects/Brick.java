package uet.oop.bomberman.entities.objects;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Brick extends Entity implements IObstacle {

    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }
}
