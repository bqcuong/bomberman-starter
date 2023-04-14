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
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {

  public static int WIDTH ;
  public static int HEIGHT ;

  private GraphicsContext gc;
  private Canvas canvas;

  private List<Entity> entities = new ArrayList<>();
  private List<Entity> stillObjects = new ArrayList<>();

  public static final int SPEED = 4;

  public static void main(String[] args) {
    Application.launch(BombermanGame.class);
  }

  @Override
  public void start(Stage stage) {

    File file = new File("C:\\Users\\HI\\Documents\\GitHub\\bomberman-starter\\res\\levels\\Level1.txt");
    try {
      Scanner scanner = new Scanner(file);
      int height = scanner.nextInt();
      int width = scanner.nextInt();
      scanner.nextLine();
      HEIGHT = height;
      WIDTH = width;
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
      for (int i = 0; i < height; i++) {
        String cur = scanner.nextLine();
        for (int j = 0; j < width; j++) {
          stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
          switch (cur.charAt(j)) {
            case '#' :
              stillObjects.add(new Wall(j, i, Sprite.wall.getFxImage()));
              break;
            case '*' :
              entities.add(new Brick(j, i, Sprite.brick.getFxImage()));
              break;
            case '1' :
              entities.add(new Balloom(j, i, Sprite.balloom_right1.getFxImage()));
              break;
            case '2' :
              entities.add(new Doll(j, i, Sprite.doll_right1.getFxImage()));
              break;
          }

        }
      }
      scanner.close();

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
                  if (!input.isEmpty()) {
                    if (input.get(input.size() - 1).equalsIgnoreCase("D")) {
                      bomberman.setDx(SPEED);
                      bomberman.setMoving(true);
                      ((Bomber) bomberman).setDirection("D");
                    } else if (input.get(input.size() - 1).equalsIgnoreCase("A")) {
                      bomberman.setDx(-SPEED);
                      bomberman.setMoving(true);
                      ((Bomber) bomberman).setDirection("A");
                    } else if (input.get(input.size() - 1).equalsIgnoreCase("W")) {
                      bomberman.setDy(-SPEED);
                      bomberman.setMoving(true);
                      ((Bomber) bomberman).setDirection("W");
                    } else if (input.get(input.size() - 1).equalsIgnoreCase("S")) {
                      bomberman.setDy(SPEED);
                      bomberman.setMoving(true);
                      ((Bomber) bomberman).setDirection("S");
                    }
                    if (!input.contains("D") && !input.contains("A")) {
                      bomberman.setDx(0);
                    }
                    if (!input.contains("W") && !input.contains("S")) {
                      bomberman.setDy(0);
                    }
                  }
                  if (!input.contains("W")
                          && !input.contains("A")
                          && !input.contains("S")
                          && !input.contains("D")) bomberman.setMoving(false);
                }
              };
      timer.start();

      createMap();
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  public void createMap() {
    for (int i = 0; i < WIDTH; i++) {
      for (int j = 0; j < HEIGHT; j++) {
        if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
          stillObjects.add(new Wall(i, j, Sprite.wall.getFxImage()));
        }
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