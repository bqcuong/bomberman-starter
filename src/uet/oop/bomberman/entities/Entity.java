package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    protected int count_img = 0;
    protected int location_x;
    protected int location_y;
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    public void setImg(Image img) {
        this.img = img;
    }

    protected char go = ' ';

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        this.location_x = xUnit * Sprite.SCALED_SIZE;
        this.location_y = yUnit * Sprite.SCALED_SIZE;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();

    public int getLocation_x() {
        return location_x;
    }

    public int getLocation_y() {
        return location_y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setLocation() {

    }

    public void entityCollide() {
        int x1 = (location_x + 1) / Sprite.SCALED_SIZE;
        int y1 = (location_y + 1) / Sprite.SCALED_SIZE - 2;
        int y2 = (location_y + Sprite.SCALED_SIZE - 2) / Sprite.SCALED_SIZE - 2;
        int x2 = (location_x + Sprite.SCALED_SIZE - 2) / Sprite.SCALED_SIZE;

        switch (go) {
            case 'A': {
                if (Game.mapGame.getMap(y1, x1) != ' ' || Game.mapGame.getMap(y2, x1) != ' ') {
                    location_x = (x1 + 1) * Sprite.SCALED_SIZE;
                }
                break;
            }
            case 'D': {
                if (Game.mapGame.getMap(y1, x2) != ' ' || Game.mapGame.getMap(y2, x2) != ' ') {
                    location_x = (x2 - 1) * Sprite.SCALED_SIZE;
                }
                break;
            }
            case 'W': {
                if (Game.mapGame.getMap(y1, x1) != ' ' || Game.mapGame.getMap(y1, x2) != ' ') {
                    location_y = (y1 + 3) * Sprite.SCALED_SIZE;
                }
                break;
            }
            case 'S': {
                if (Game.mapGame.getMap(y2, x1) != ' ' || Game.mapGame.getMap(y2, x2) != ' ') {
                    location_y = (y2 + 1) * Sprite.SCALED_SIZE;
                }
                break;
            }
        }
    }
}
