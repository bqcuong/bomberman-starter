package uet.oop.bomberman;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;
import static uet.oop.bomberman.graphics.Sprite.brick;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Enemy.*;
import uet.oop.bomberman.entities.Map.*;
import uet.oop.bomberman.entities.Player.*;
import uet.oop.bomberman.entities.Powerup.*;
import uet.oop.bomberman.gamelogic.Collision;
import uet.oop.bomberman.graphics.Sprite;

public class BombermanGame extends Application {

  public static int WIDTH;
  public static int HEIGHT;
  public static final double SCALING = 1.3;

  private static double VIEW_X;
  private static double VIEW_Y;

  private static double offsetMaxX;
  private static double offsetMaxY;
  private static final double offsetMinX = 0;
  private static final double offsetMinY = 0;

  private double camX;
  private double camY;

  private GraphicsContext gc;
  private Canvas canvas;

  private List<Entity> entities = new ArrayList<>();
  private List<Entity> stillObjects = new ArrayList<>();

  public static int SPEED = 2;
  private int bombDelayCnter = 0;

  private ArrayList<String> input = new ArrayList<>();

  // Loại bỏ bomb sau khi nổ
  private void bombRemoval(Bomber bomber) {
    for (Entity explosion : stillObjects) {
      if (explosion instanceof Explosion) {
        for (Entity brick : stillObjects) {
          if ((brick instanceof Brick) && Collision.checkCollision2(brick, explosion)) {
            ((Brick) brick).setDamaged(true);
          }
        }
        for (Entity enemy : entities) {
          if (enemy instanceof Balloom && Collision.checkCollision2(enemy, explosion)) {
            ((Balloom) enemy).setDead(true);
          }
          if (enemy instanceof Oneal && Collision.checkCollision2(enemy, explosion)) {
            ((Oneal) enemy).setDead(true);
          }
          if (enemy instanceof Doll && Collision.checkCollision2(enemy, explosion)) {
            ((Oneal) enemy).setDead(true);
          }
        }
        
      }
    }
    stillObjects.removeIf(bomb -> bomb instanceof Bomb && ((Bomb) bomb).isDisappear());
    stillObjects.removeIf(
        explosion -> explosion instanceof Explosion && ((Explosion) explosion).isDisappear());
    entities.removeIf(balloom -> balloom instanceof Balloom && ((Balloom) balloom).isDisappear());
    entities.removeIf(oneal -> oneal instanceof Oneal && ((Oneal) oneal).isDisappear());
    stillObjects.removeIf(brick -> brick instanceof Brick && ((Brick) brick).isDisappear());
    for (Entity powerup : stillObjects) {
      if (powerup instanceof FlameItem && Collision.checkCollision2(bomber, powerup)) {
        stillObjects.remove(powerup);
        bomber.setBombLength(bomber.getBombLength() + 1);
        System.out.println(powerup + " consumed");
        break;
      } else if (powerup instanceof BombItem && Collision.checkCollision2(bomber, powerup)) {
        stillObjects.remove(powerup);
        bomber.setMaxBomb(bomber.getMaxBomb() + 1);
        System.out.println(powerup + " consumed");
        break;
      } else if (powerup instanceof SpeedItem && Collision.checkCollision2(bomber, powerup)) {
        stillObjects.remove(powerup);
        SPEED++;
        System.out.println(powerup + " consumed");
        break;
      }
    }
  }

  // Đặt bom theo input của người chơi
  private void bombPlant(Bomber bomberman) {
    if (bomberman.getMaxBomb() > 0 && input.contains("SPACE") && bombDelayCnter == 0) {
      int bombLengthLeft = bomberman.getBombLength();
      int bombLengthRight = bomberman.getBombLength();
      int bombLengthUp = bomberman.getBombLength();
      int bombLengthDown = bomberman.getBombLength();
      for (int i = 1; i <= bomberman.getBombLength(); i++) {
        // Find obstacle on the left
        if (stillObjects.contains(
            new Wall(
                (bomberman.getX() + SCALED_SIZE / 2) / SCALED_SIZE - i,
                (bomberman.getY() + SCALED_SIZE / 2) / SCALED_SIZE,
                Sprite.wall.getFxImage()))) {
          bombLengthLeft = i - 1;

          break;
        }
      }
      for (int i = 1; i <= bomberman.getBombLength(); i++) {
        if (stillObjects.contains(
            new Brick(
                (bomberman.getX() + SCALED_SIZE / 2) / SCALED_SIZE - i,
                (bomberman.getY() + SCALED_SIZE / 2) / SCALED_SIZE,
                brick.getFxImage()))) {
          bombLengthLeft = i;

          break;
        }
      }
      for (int i = 1; i <= bomberman.getBombLength(); i++) {
        if (stillObjects.contains(
            new Wall(
                (bomberman.getX() + SCALED_SIZE / 2) / SCALED_SIZE + i,
                (bomberman.getY() + SCALED_SIZE / 2) / SCALED_SIZE,
                Sprite.wall.getFxImage()))) {
          bombLengthRight = i - 1;

          break;
        }
        if (stillObjects.contains(
            new Brick(
                (bomberman.getX() + SCALED_SIZE / 2) / SCALED_SIZE + i,
                (bomberman.getY() + SCALED_SIZE / 2) / SCALED_SIZE,
                brick.getFxImage()))) {
          bombLengthRight = i;

          break;
        }
      }
      for (int i = 1; i <= bomberman.getBombLength(); i++) {
        if (stillObjects.contains(
            new Wall(
                (bomberman.getX() + SCALED_SIZE / 2) / SCALED_SIZE,
                (bomberman.getY() + SCALED_SIZE / 2) / SCALED_SIZE - i,
                Sprite.wall.getFxImage()))) {
          bombLengthUp = i - 1;

          break;
        }
        if (stillObjects.contains(
            new Brick(
                (bomberman.getX() + SCALED_SIZE / 2) / SCALED_SIZE,
                (bomberman.getY() + SCALED_SIZE / 2) / SCALED_SIZE - i,
                brick.getFxImage()))) {
          bombLengthUp = i;

          break;
        }
      }
      for (int i = 1; i <= bomberman.getBombLength(); i++) {
        if (stillObjects.contains(
            new Wall(
                (bomberman.getX() + SCALED_SIZE / 2) / SCALED_SIZE,
                (bomberman.getY() + SCALED_SIZE / 2) / SCALED_SIZE + i,
                Sprite.wall.getFxImage()))) {
          bombLengthDown = i - 1;

          break;
        }
        if (stillObjects.contains(
            new Brick(
                (bomberman.getX() + SCALED_SIZE / 2) / SCALED_SIZE,
                (bomberman.getY() + SCALED_SIZE / 2) / SCALED_SIZE + i,
                brick.getFxImage()))) {
          bombLengthDown = i;

          break;
        }
      }
      stillObjects.add(
          new Bomb(
              ((bomberman.getX() + SCALED_SIZE / 2) / SCALED_SIZE),
              ((bomberman.getY() + SCALED_SIZE / 2) / SCALED_SIZE),
              Sprite.bomb.getFxImage(),
              bombLengthLeft,
              bombLengthRight,
              bombLengthUp,
              bombLengthDown));
      bomberman.setMaxBomb(bomberman.getMaxBomb() - 1);
      bombDelayCnter = 11;
    }

    for (Entity entity : stillObjects) {
      if (entity instanceof Bomb
          && ((Bomb) entity).getDetonateCnter() == Bomb.DETONATE_TIME
          && !(((Bomb) entity).isPlant())) {
        Bomb bomb = (Bomb) entity;
        bomb.setPlant(true);
        if (bomb.getBombLengthLeft() > 0) {
          stillObjects.add(
              new Explosion(
                  (bomb.getX() / SCALED_SIZE - bomb.getBombLengthLeft()),
                  (entity.getY() / SCALED_SIZE),
                  Sprite.explosion_horizontal_left_last.getFxImage(),
                  "left",
                  true));
        }
        if (bomb.getBombLengthRight() > 0) {
          stillObjects.add(
              new Explosion(
                  (entity.getX() / SCALED_SIZE + bomb.getBombLengthRight()),
                  (entity.getY() / SCALED_SIZE),
                  Sprite.explosion_horizontal_right_last.getFxImage(),
                  "right",
                  true));
        }
        if (bomb.getBombLengthUp() > 0) {
          stillObjects.add(
              new Explosion(
                  (entity.getX() / SCALED_SIZE),
                  (entity.getY() / SCALED_SIZE - bomb.getBombLengthUp()),
                  Sprite.explosion_vertical_top_last.getFxImage(),
                  "up",
                  true));
        }
        if (bomb.getBombLengthDown() > 0) {
          stillObjects.add(
              new Explosion(
                  (entity.getX() / SCALED_SIZE),
                  (entity.getY() / SCALED_SIZE + bomb.getBombLengthDown()),
                  Sprite.explosion_vertical_down_last.getFxImage(),
                  "down",
                  true));
        }
        for (int j = 1; j < bomb.getBombLengthLeft(); j++) {
          stillObjects.add(
              new Explosion(
                  (entity.getX() / SCALED_SIZE - j),
                  (entity.getY() / SCALED_SIZE),
                  Sprite.explosion_horizontal.getFxImage(),
                  "left",
                  false));
        }
        for (int j = 1; j < bomb.getBombLengthRight(); j++) {
          stillObjects.add(
              new Explosion(
                  (entity.getX() / SCALED_SIZE + j),
                  (entity.getY() / SCALED_SIZE),
                  Sprite.explosion_horizontal.getFxImage(),
                  "right",
                  false));
        }
        for (int j = 1; j < bomb.getBombLengthDown(); j++) {
          stillObjects.add(
              new Explosion(
                  (entity.getX() / SCALED_SIZE),
                  (entity.getY() / SCALED_SIZE + j),
                  Sprite.explosion_vertical.getFxImage(),
                  "down",
                  false));
        }
        for (int j = 1; j < bomb.getBombLengthUp(); j++) {
          stillObjects.add(
              new Explosion(
                  (entity.getX() / SCALED_SIZE),
                  (entity.getY() / SCALED_SIZE - j),
                  Sprite.explosion_vertical.getFxImage(),
                  "up",
                  false));
        }
        bomberman.setMaxBomb(bomberman.getMaxBomb() + 1);
        break;
      }
    }
  }

  // Điều khiển di chuyển
  private void movementControl(Bomber bomberman) {

    if (input.size() >= 2) {
      if (input.get(input.size() - 2).equalsIgnoreCase("D")) {
        for (int i = 0; i < SPEED; i++) {
          if (Collision.checkLegalMove(bomberman, "right", stillObjects)) {
            bomberman.setX(bomberman.getX() + 1);
          } else {
            break;
          }
        }
      } else if (input.get(input.size() - 2).equalsIgnoreCase("A")) {
        for (int i = 0; i < SPEED; i++) {
          if (Collision.checkLegalMove(bomberman, "left", stillObjects)) {
            bomberman.setX(bomberman.getX() - 1);
          } else {
            break;
          }
        }
      } else if (input.get(input.size() - 2).equalsIgnoreCase("W")) {
        for (int i = 0; i < SPEED; i++) {
          if (Collision.checkLegalMove(bomberman, "up", stillObjects)) {
            bomberman.setY(bomberman.getY() - 1);
          } else {
            break;
          }
        }

      } else if (input.get(input.size() - 2).equalsIgnoreCase("S")) {
        for (int i = 0; i < SPEED; i++) {
          if (Collision.checkLegalMove(bomberman, "down", stillObjects)) {
            bomberman.setY(bomberman.getY() + 1);
          } else {
            break;
          }
        }
      }
    }

    if (!input.isEmpty()) {
      if (input.contains("J")) {
        bomberman.setBombLength(bomberman.getBombLength() + 1);
      }
      if (input.get(input.size() - 1).equalsIgnoreCase("D")) {
        for (int i = 0; i < SPEED; i++) {
          if (Collision.checkLegalMove(bomberman, "right", stillObjects)) {
            bomberman.setX(bomberman.getX() + 1);

          } else {
            break;
          }
        }
        bomberman.setMoving(true);
        bomberman.setDirection("D");

      } else if (input.get(input.size() - 1).equalsIgnoreCase("A")) {
        for (int i = 0; i < SPEED; i++) {
          if (Collision.checkLegalMove(bomberman, "left", stillObjects)) {
            bomberman.setX(bomberman.getX() - 1);

          } else {
            break;
          }
        }
        bomberman.setMoving(true);
        bomberman.setDirection("A");

      } else if (input.get(input.size() - 1).equalsIgnoreCase("W")) {
        for (int i = 0; i < SPEED; i++) {
          if (Collision.checkLegalMove(bomberman, "up", stillObjects)) {
            bomberman.setY(bomberman.getY() - 1);

          } else {
            break;
          }
        }
        bomberman.setMoving(true);
        bomberman.setDirection("W");

      } else if (input.get(input.size() - 1).equalsIgnoreCase("S")) {
        for (int i = 0; i < SPEED; i++) {
          if (Collision.checkLegalMove(bomberman, "down", stillObjects)) {
            bomberman.setY(bomberman.getY() + 1);

          } else {
            break;
          }
        }
        bomberman.setMoving(true);
        bomberman.setDirection("S");
      }
    }

    if (!input.contains("W")
        && !input.contains("A")
        && !input.contains("S")
        && !input.contains("D")) {
      bomberman.setDx(0);
      bomberman.setDy(0);
      bomberman.setMoving(false);
    }
  }

  public void Camera(Bomber bomber) {
    camX = bomber.getX() - (VIEW_X / 2);
    camY = bomber.getY() - (VIEW_Y / 2);
    if (camX > offsetMaxX) {
      camX = offsetMaxX;
    } else if (camX < offsetMinX) {
      camX = offsetMinX;
    }
    if (camY > offsetMaxY) {
      camY = offsetMaxY;
    } else if (camY < offsetMinY) {
      camY = offsetMinY;
    }
  }

  public static void main(String[] args) {
    Application.launch(BombermanGame.class);
  }

  @Override
  public void start(Stage stage) {

    // Create map
    File file = new File(System.getProperty("user.dir") + "\\res\\levels\\Level1.txt");
    try {
      Scanner scanner = new Scanner(file);
      int height = scanner.nextInt();
      int width = scanner.nextInt();
      scanner.nextLine();
      HEIGHT = height;
      WIDTH = width;
      VIEW_X = (SCALED_SIZE * WIDTH / SCALING);
      VIEW_Y = (SCALED_SIZE * HEIGHT / SCALING);
      offsetMaxX = WIDTH * SCALED_SIZE - VIEW_X;
      offsetMaxY = HEIGHT * SCALED_SIZE - VIEW_Y;
      // Tao Canvas
      canvas = new Canvas(SCALED_SIZE * WIDTH, SCALED_SIZE * HEIGHT);
      gc = canvas.getGraphicsContext2D();
      gc.scale(SCALING, SCALING);
      gc.save();

      // Tao root container
      Group root = new Group();
      root.getChildren().add(canvas);

      // Tao scene
      Scene scene = new Scene(root);

      // Them scene vao stage
      stage.setScene(scene);

      stage.show();
      for (int i = 0; i < height; i++) {
        String cur = scanner.nextLine();
        for (int j = 0; j < width; j++) {
          stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
          switch (cur.charAt(j)) {
            case 'p':
            case ' ':
              break;
            case '#':
              stillObjects.add(new Wall(j, i, Sprite.wall.getFxImage()));
              break;
            case '*':
              stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
              break;
            case '1':
              entities.add(new Balloom(j, i, Sprite.balloom_right1.getFxImage()));
              break;
            case '2':
              entities.add(new Oneal(j, i, Sprite.oneal_right1.getFxImage()));
              break;
            case '3':
              entities.add(new Doll(j, i, Sprite.doll_right1.getFxImage()));
              break;
            case 'x':
              stillObjects.add(new Portal(j, i, Sprite.portal.getFxImage()));
              stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
              break;
            case 'b':
              stillObjects.add(new BombItem(j, i, Sprite.powerup_bombs.getFxImage()));
              stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
              break;
            case 'f':
              stillObjects.add(new FlameItem(j, i, Sprite.powerup_flames.getFxImage()));
              stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
              break;
            case 's':
              stillObjects.add(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage()));
              stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
              break;
            default:
              throw new IllegalStateException("Unexpected value: " + cur.charAt(j));
          }
        }
      }
      scanner.close();
      Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
      entities.add(bomberman);

      // Xử lí input
      scene.setOnKeyPressed(
          e -> {
            String code = e.getCode().toString();

            // only add once... prevent duplicates
            if (!input.contains(code)) {
              input.add(code);
              ((Bomber) bomberman).setDirection(code);
            }
          });

      scene.setOnKeyReleased(
          e -> {
            String code = e.getCode().toString();

            input.remove(code);
          });

      AnimationTimer timer =
          new AnimationTimer() {
            @Override
            public void handle(long l) {
              render();
              update();
              // Đếm thời gian giữa 2 lần đặt bom
              if (bombDelayCnter > 0) bombDelayCnter--;
              movementControl((Bomber) bomberman);
              bombPlant((Bomber) bomberman);
              bombRemoval((Bomber) bomberman);
              Collision.checkCollisionBomber1(((Bomber) bomberman), entities);
              Collision.checkCollisionBomber1(((Bomber) bomberman), stillObjects);
              Camera((Bomber) bomberman);
            }
          };
      timer.start();
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  public void update() {
    stillObjects.forEach(Entity::update);
    entities.forEach(Entity::update);
  }

  public void render() {
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    gc.restore();
    gc.save();
    gc.translate(-camX, -camY);
    stillObjects.forEach(g -> g.render(gc));
    entities.forEach(g -> g.render(gc));
  }
}
