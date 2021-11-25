package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.controller.Camera;


public class Brick extends Tile implements Obstacle, Destroyable {
    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }

}
