package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Brick extends Entity implements Obstacle, Destroyable {
    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }
}
