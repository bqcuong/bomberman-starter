package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    //Tọa độ tính từ góc trái trên trong Canvas
    protected int x;
    protected int y;

    // Tọa độ đơn vị
    protected int xUnit;
    protected int yUnit;

    protected Image img;

    private boolean visible = true;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getxUnit() {
        return xUnit;
    }

    public int getyUnit() {
        return yUnit;
    }

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.xUnit = xUnit;
        this.yUnit = yUnit;
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    // Kiem tra xem thuc the co nam tren diem (x, y) trong toa do canvas hay khong?
    public boolean existOn(int x, int y) {
        if (this.x <= x && x < this.x + Sprite.SCALED_SIZE
            && this.y <= y && y < this.y + Sprite.SCALED_SIZE) {
            return true;
        }
        return false;
    }

    // Kiem tra thuc the co nam tren o vuong bat dau bang toa do (x, y) hay khong
    public boolean existOnSquare(int x, int y) {
        if (existOn(x, y)
                || existOn(x, y + Sprite.SCALED_SIZE - 1)
                || existOn(x + Sprite.SCALED_SIZE - 1, y)
                || existOn(x + Sprite.SCALED_SIZE - 1,
                y + Sprite.SCALED_SIZE - 1)) {
            return true;
        }
        return false;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update();
}
