package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Brick extends Entity implements IObstacle {

    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }
}
