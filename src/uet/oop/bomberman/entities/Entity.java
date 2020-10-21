package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {
    protected int i;
    protected int j;
    protected int x;
    protected int y;
    protected Image img;

    public Entity(int i, int j, int x, int y, Image img) {
        this.i = i;
        this.j = j;
        this.x = x;
        this.y = y;
        this.img = img;
    }

    public abstract void render(GraphicsContext gc);
    public abstract void update();
}
