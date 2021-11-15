package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.animatedEntities.Bomber;
import uet.oop.bomberman.entities.buffItems.IncreaseBombs;
import uet.oop.bomberman.entities.staticEntities.Grass;
import uet.oop.bomberman.entities.staticEntities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    
    public static final int WINDOW_WIDTH = 20;
    public static final int WIDTH = 30;
    public static final int HEIGHT = 15;
    public static final long TIME_UNIT = 10_000_000; // 10 ms
    public static final int MOVING_UNIT = 2;

    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Entity> updateQueue = new ArrayList<>();
    private GraphicsContext gc;
    private Canvas canvas;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao bomberman
        Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);

        /** testing */
        entities.add(new IncreaseBombs(4, 5, Sprite.powerup_bombs.getFxImage()));
        /** end test */

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root,
                Sprite.SCALED_SIZE * WINDOW_WIDTH,
                Sprite.SCALED_SIZE * HEIGHT);
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case RIGHT:
                    bomberman.setDirection(Bomber.RIGHT);
                    break;
                case LEFT:
                    bomberman.setDirection(Bomber.LEFT);
                    break;
                case UP:
                    bomberman.setDirection(Bomber.UP);
                    break;
                case DOWN:
                    bomberman.setDirection(Bomber.DOWN);
                    break;
                case SPACE:
                    bomberman.planBomb();
                    break;
            }
        });
        scene.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.UP ||
                    keyEvent.getCode() == KeyCode.DOWN ||
                    keyEvent.getCode() == KeyCode.LEFT ||
                    keyEvent.getCode() == KeyCode.RIGHT) {
                bomberman.setDirection(Bomber.CENTER);
            }
        });

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0 ;
            @Override
            public void handle(long l) {
                /** update and render every TIME_UNIT. */
                if (l - lastUpdate >= TIME_UNIT) {
                    lastUpdate = l;
                    update();
                    render();

                    if (bomberman.getX() > (WINDOW_WIDTH * Sprite.SCALED_SIZE) / 2 &&
                    bomberman.getX() < (WIDTH - WINDOW_WIDTH / 2) * Sprite.SCALED_SIZE) {
                        root.setLayoutX((WINDOW_WIDTH * Sprite.SCALED_SIZE) / 2 - bomberman.getX());
                    }

                }

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
                }
                else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
    }

    public void update() {
        // add waiting entities to this.entities
        updateQueue.forEach(entity -> entities.add(entity));
        updateQueue.clear();

        // update each entity
        entities.forEach(entity -> {
            if (entity.isVisible()) {
                entity.update();
            }
        });
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> {
            if (g.isVisible()) {
                g.render(gc);
            }
        });
        entities.forEach(g -> {
            if (g.isVisible()) {
                g.render(gc);
            }
        });
    }
}
