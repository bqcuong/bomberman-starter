package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import uet.oop.bomberman.events.KeyboardEvent;
import uet.oop.bomberman.graphics.GraphicsMGR;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;

public class BombermanGame extends Application {

    private Map map;
    private GraphicsMGR graphics;
    private Canvas canvas;

    private KeyboardEvent keyboardEvent;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * GraphicsMGR.WIDTH, Sprite.SCALED_SIZE * GraphicsMGR.HEIGHT);
        graphics = new GraphicsMGR(canvas);

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Tao key board event
        keyboardEvent = new KeyboardEvent(scene);

        // Tao map
        map = new Map();
        map.createMap(1, keyboardEvent);

        // Them scene vao stage
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Bomberman OOP");
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();
    }

    public void render() {
        graphics.clrscr(canvas);
        //Render map, entities, stillObject
        graphics.render(map);
    }

    public void update() {
        map.update();
    }
}


