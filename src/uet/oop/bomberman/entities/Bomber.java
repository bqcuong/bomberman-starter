package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Entity {

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    private void moveTo(int x, int y, List<Entity> entities, List<Entity> stillObjects) {
        for (Entity element: stillObjects) {
            if (element.getClass().getTypeName()
                    .equals("uet.oop.bomberman.entities.Grass")) {
                continue;
            }
            if (element.existOn(x, y)
                    || element.existOn(x, y + Sprite.SCALED_SIZE - 1)
                    || element.existOn(x + Sprite.SCALED_SIZE - 1, y)
                    || element.existOn(x + Sprite.SCALED_SIZE - 1,
                        y + Sprite.SCALED_SIZE - 1)) {
                return;
            }
        }
        this.x = x;
        this.y = y;
    }

    public void moveRight(List<Entity> entities, List<Entity> stillObjects) {
        moveTo(x + 5, y, entities, stillObjects);
    }

    public void moveLeft(List<Entity> entities, List<Entity> stillObjects) {
        moveTo(x - 5, y, entities, stillObjects);
    }

    public void moveUp(List<Entity> entities, List<Entity> stillObjects) {
        moveTo(x, y - 5, entities, stillObjects);
    }

    public void moveDown(List<Entity> entities, List<Entity> stillObjects) {
        moveTo(x, y + 5, entities, stillObjects);
    }

    @Override
    public void update() {

    }
}
