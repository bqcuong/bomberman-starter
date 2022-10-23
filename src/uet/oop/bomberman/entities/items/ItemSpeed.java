package uet.oop.bomberman.entities.items;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class ItemSpeed extends Entity implements IItem{
    public ItemSpeed(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }

    @Override
    public void update() {

    }

    @Override
    public void setImg(Image img) {
        super.setImg(img);
    }
}
