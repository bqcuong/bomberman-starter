package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import uet.oop.bomberman.entities.Item.BombItem;
import uet.oop.bomberman.entities.Item.FlameItem;
import uet.oop.bomberman.entities.Item.SpeedItem;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Portal;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Bomb.*;
import uet.oop.bomberman.entities.Mob.*;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.event.*;

import java.util.*;

public class BombermanGame extends Application {

    public static String[] map = {
            "###############################",
            "#p     ** *  1 * 2 *  * * *   #",
            "# # # #*# # #*#*# # # #*#*#*# #",
            "#  x*     ***  *  1   * 2 * * #",
            "# # # # # #*# # #*#*# #*#*# #*#",
            "#f         x **  *  *         #",
            "# # # # # # # # # #*# #*# # # #",
            "#*  *      *  *      *        #",
            "# # # # #*# # # #*#*# # # # # #",
            "#* 1  **  *       *           #",
            "# #*# # # # # # #*# # # # # # #",
            "#           *   *  *          #",
            "###############################"
    };

    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;

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

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();

        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
    }

    public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                }
                else {
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