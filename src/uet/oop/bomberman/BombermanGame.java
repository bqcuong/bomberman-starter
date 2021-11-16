package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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

    public Map map;

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
        map = new Map(1, scene);
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

        //createMap();

        //Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage(), new KeyListener(scene));
        //entities.add(bomberman);
    }

    public void createMap() {
        // for (int i = 0; i < WIDTH; i++) {
        //     for (int j = 0; j < HEIGHT; j++) {
        //         Entity object;
        //         if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
        //             object = new Wall(i, j, Sprite.wall.getFxImage());
        //         }
        //         else {
        //             object = new Grass(i, j, Sprite.grass.getFxImage());
        //         }
        //         stillObjects.add(object);
        //     }
        // }
    }

    public void update() {
        map.update();
        //entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        map.render(gc);
        // stillObjects.forEach(g -> g.render(gc));
        // entities.forEach(g -> g.render(gc));
    }
}
