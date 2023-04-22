package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uet.oop.bomberman.gamelogic.*;

public class Bomb extends Entity {
  private int detonateCounter = 105;
  private boolean disappear = false;

  // List sprite của bomb khi chưa nổ
  private ArrayList<Image> bombAlive =
      new ArrayList<>(
          Arrays.asList(
              Sprite.bomb_2.getFxImage(), Sprite.bomb_1.getFxImage(), Sprite.bomb.getFxImage()));

  // List sprite của bomb khi đang nổ
  private ArrayList<Image> bombExplode =
      new ArrayList<>(
          Arrays.asList(
              Sprite.bomb_exploded2.getFxImage(),
              Sprite.bomb_exploded1.getFxImage(),
              Sprite.bomb_exploded.getFxImage()));

  public Bomb(int x, int y, Image img) {
    super(x, y, img);
  }

  public int getDetonateCnter() {
    return detonateCounter;
  }

  public void setDetonateCnter(int detonateCnter) {
    this.detonateCounter = detonateCnter;
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
