package uet.oop.bomberman.entities;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
  private int detonateCounter = 105;
  private boolean disappear = false;
  private final int bombLengthLeft;
  private final int bombLengthRight;
  private final int bombLengthUp;
  private final int bombLengthDown;
  private boolean plant = false;

  // List sprite của bomb khi chưa nổ
  private final ArrayList<Image> bombAlive =
      new ArrayList<>(
          Arrays.asList(
              Sprite.bomb_2.getFxImage(), Sprite.bomb_1.getFxImage(), Sprite.bomb.getFxImage()));

  // List sprite của bomb khi đang nổ
  private final ArrayList<Image> bombExplode =
      new ArrayList<>(
          Arrays.asList(
              Sprite.bomb_exploded2.getFxImage(),
              Sprite.bomb_exploded1.getFxImage(),
              Sprite.bomb_exploded.getFxImage()));

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
    detonateCounter--;
    if (detonateCounter > 15) {
      this.img = bombAlive.get((detonateCounter - 15) / 30);
    } else if (detonateCounter < 15) {
      this.img = bombExplode.get(detonateCounter / 5);
    }

    if (detonateCounter == 0) {
      disappear = true;
    }
  }
}
