package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

  public static final int WIDTH = 31;
  public static final int HEIGHT = 13;

  private GraphicsContext gc;
  private Canvas canvas;
  private List<Entity> entities = new ArrayList<>();
  private List<Entity> stillObjects = new ArrayList<>();

  public static void main(String[] args) {
    Application.launch(BombermanGame.class);
  }

  @Override
  public void start(Stage stage) {
    // Tao Canvas
    canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
    gc = canvas.getGraphicsContext2D();

    // Tao root container
    Group root = new Group();
    root.getChildren().add(canvas);

    // Tao scene
    Scene scene = new Scene(root);

    // Them scene vao stage
    stage.setScene(scene);
    stage.show();

    Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
    entities.add(bomberman);

    ArrayList<String> input = new ArrayList<String>();

    scene.setOnKeyPressed(
        new EventHandler<KeyEvent>() {

          public void handle(KeyEvent e) {

            String code = e.getCode().toString();

            // only add once... prevent duplicates

            if (!input.contains(code)) {
              input.add(code);
              ((Bomber)bomberman).setDirection(code);
            }
          }
        });

    scene.setOnKeyReleased(
        new EventHandler<KeyEvent>() {
          public void handle(KeyEvent e) {

            String code = e.getCode().toString();

            input.remove(code);
          }
        });

    AnimationTimer timer =
        new AnimationTimer() {
          @Override
          public void handle(long l) {
            render();
            update();
            if (input.contains("D")){
              bomberman.setDx(Sprite.SCALED_SIZE / 16);
            }
            if (input.contains("A")){
              bomberman.setDx(-Sprite.SCALED_SIZE / 16);
            }
            if (input.contains("W")){
              bomberman.setDy(-Sprite.SCALED_SIZE / 16);
            }
            if (input.contains("S")){
              bomberman.setDy(Sprite.SCALED_SIZE / 16);
            }
            if (!input.contains("D") && !input.contains("A")){
              bomberman.setDx(0);
            }
            if (!input.contains("W") && !input.contains("S")){
              bomberman.setDy(0);
            }

            ((Bomber)bomberman).moveBomber(WIDTH, HEIGHT);
          }
        };
    timer.start();

    createMap();


  }

  public void createMap() {
    for (int i = 0; i < WIDTH; i++) {
      for (int j = 0; j < HEIGHT; j++) {
        Entity object;
        if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
          object = new Wall(i, j, Sprite.wall.getFxImage());
        } else {
          object = new Grass(i, j, Sprite.grass.getFxImage());
        }
        stillObjects.add(object);
      }
    }
  }

  public void update() {
    entities.forEach(Entity::update);
  }

  public void render() {
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    stillObjects.forEach(g -> g.render(gc));
    entities.forEach(g -> g.render(gc));
  }
}
