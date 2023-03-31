package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;
import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public abstract class Entity {
  // Tọa độ X tính từ góc trái trên trong Canvas
  protected int x;

  // Tọa độ Y tính từ góc trái trên trong Canvas
  protected int y;

  protected Image img;

  protected int dx;
  protected int dy;

  protected boolean moving;

  protected Rectangle2D boundingBox = new Rectangle2D(x, y, SCALED_SIZE, SCALED_SIZE);

  public Rectangle2D getBoundingBox() {
    return boundingBox;
  }

  public void setBoundingBox(Rectangle2D boundingBox) {
    this.boundingBox = boundingBox;
  }

  public void setMoving(boolean moving) {
    this.moving = moving;
  }

  public boolean isMoving() {
    return this.moving;
  }

  public void playAnimation() {}

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getDx() {
    return dx;
  }

  public void setDx(int dx) {
    this.dx = dx;
  }

  public int getDy() {
    return dy;
  }

  public void setDy(int dy) {
    this.dy = dy;
  } // Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas

  public Entity(int xUnit, int yUnit, Image img) {
    this.x = xUnit * SCALED_SIZE;
    this.y = yUnit * SCALED_SIZE;
    this.img = img;
  }

  public void render(GraphicsContext gc) {
    gc.drawImage(img, x, y);
  }

  public abstract void update();
}
