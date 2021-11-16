package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {

    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;
    protected Hitbox hitbox;

    protected Image img;

    /**
     * Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas và hitbox.
     */
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        this.hitbox = new Hitbox(xUnit - 1, yUnit - 1, x - 2, y - 2);
    }

    /**
     * Constructor 2.
     * @param xUnit     int
     * @param yUnit     int
     * @param img       Image
     * @param hitbox    Hitbox
     */
    public Entity(int xUnit, int yUnit, Image img, Hitbox hitbox) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        this.hitbox = hitbox;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    
    public abstract void update();

    /**
     * Getter method for x coordination.
     * @return int x
     */
    public int getX() {
        return x;
    }

    /**
     * Getter method for y coordination.
     * 
     * @return int y
     */
    public int getY() {
        return y;
    }

    /**
     * Setter method for hitbox.
     */
    public void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    /**
     * Getter method for hitbox.
     */
    public Hitbox getHitbox() {
        return hitbox;
    }
}
