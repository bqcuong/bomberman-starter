package uet.oop.bomberman.entities;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public abstract class Entity {
  // Tọa độ X tính từ góc trái trên trong Canvas
  protected int x;

  // Tọa độ Y tính từ góc trái trên trong Canvas
  protected int y;

  protected Image img;

  protected int dx;
  protected int dy;

  protected boolean moving;


  private Rectangle boundingBox = new Rectangle(x, y, SCALED_SIZE, SCALED_SIZE);

  public Rectangle getBoundingBox() {
    return boundingBox;
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
    this.boundingBox.setX(xUnit * SCALED_SIZE);
    this.boundingBox.setY(yUnit * SCALED_SIZE);
  }
  
  public boolean isSameLocation(Entity e2) {
    return getX() / 32 == e2.getX() / 32 &&
            getY() / 32 == e2.getY() / 32;
  }

  public void render(GraphicsContext gc) {
    gc.drawImage(img, x, y);
  }

  public abstract void update();


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Entity)) return false;
    Entity entity = (Entity) o;
    return getX() == entity.getX() && getY() == entity.getY();
  }

}
