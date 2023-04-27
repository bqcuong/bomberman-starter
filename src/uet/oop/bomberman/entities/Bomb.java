package uet.oop.bomberman.entities;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
  public static final int DISAPPEARTIME = 105;
  public static final int DETONATETIME = 90;
  private int detonateCounter = 0;
  private boolean disappear = false;
  private final int bombLengthLeft;
  private final int bombLengthRight;
  private final int bombLengthUp;
  private final int bombLengthDown;
  private boolean plant = false;

  // List sprite của bomb khi chưa nổ
  private final ArrayList<Sprite> bombAlive =
      new ArrayList<>(
          Arrays.asList(
              Sprite.bomb_2, Sprite.bomb_1, Sprite.bomb));

  // List sprite của bomb khi đang nổ
  private final ArrayList<Sprite> bombExplode =
      new ArrayList<>(
          Arrays.asList(
              Sprite.bomb_exploded2,
              Sprite.bomb_exploded1,
              Sprite.bomb_exploded));

  public Bomb(int x, int y, Image img, int bombLengthLeft, int bombLengthRight, int bombLengthUp, int bombLengthDown) {
    super(x, y, img);
    this.bombLengthLeft = bombLengthLeft;
    this.bombLengthRight = bombLengthRight;
    this.bombLengthUp = bombLengthUp;
    this.bombLengthDown = bombLengthDown;
  }

  public boolean isPlant() {
    return plant;
  }

  public void setPlant(boolean plant) {
    this.plant = plant;
  }

  public int getBombLengthLeft() {
    return bombLengthLeft;
  }

  public int getBombLengthRight() {
    return bombLengthRight;
  }

  public int getBombLengthUp() {
    return bombLengthUp;
  }

  public int getBombLengthDown() {
    return bombLengthDown;
  }

  public int getDetonateCnter() {
    return detonateCounter;
  }

  public boolean isDisappear() {
    return disappear;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof Bomb) {
      Bomb bomb = (Bomb) o;
      return this.getX() == bomb.getX() && this.getY() == bomb.getY();
    }
    return false;
  }

  @Override
  public void update() {
    detonateCounter++;
    if (detonateCounter < DETONATETIME) {
      this.img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, detonateCounter, DETONATETIME).getFxImage(); 
    } else if (detonateCounter > DETONATETIME) {
      this.img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, detonateCounter - DETONATETIME, DISAPPEARTIME - DETONATETIME).getFxImage();
    }

    if (detonateCounter == DISAPPEARTIME) {
      disappear = true;
    }
  }
}
