package uet.oop.bomberman;

//import com.sun.javafx.perf.PerformanceTracker;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.events.KeyboardEvent;
import uet.oop.bomberman.graphics.GraphicsMGR;
import uet.oop.bomberman.graphics.GameMap;
import uet.oop.bomberman.graphics.Sprite;

public class BombermanGame extends Application {

    private GameMap gameMap;
    private GraphicsMGR graphics;
    private Canvas canvas;

    private Stage window;

    //private static PerformanceTracker tracker;

    @Override
    public void start(Stage stage) {
        window = stage;
//        // Test start menu
//        Button buttonStart = new Button("START");
//        buttonStart.setScaleX(2);
//        buttonStart.setScaleY(2);
//        buttonStart.setLayoutX((Sprite.SCALED_SIZE * GraphicsMGR.WIDTH - buttonStart.getWidth()) / 2);
//        buttonStart.setLayoutY((Sprite.SCALED_SIZE * GraphicsMGR.HEIGHT - buttonStart.getHeight()) / 2);
//        Group buttons = new Group(buttonStart);
//        Scene startMenu = new Scene(buttons,
//                Sprite.SCALED_SIZE * GraphicsMGR.WIDTH, Sprite.SCALED_SIZE * GraphicsMGR.HEIGHT);


        // Tao Canvas.
        canvas = new Canvas(Sprite.SCALED_SIZE * GraphicsMGR.WIDTH, Sprite.SCALED_SIZE * GraphicsMGR.HEIGHT);
        graphics = new GraphicsMGR(canvas);

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene sceneInGame = new Scene(root);


        // Tao keyboard event
        KeyboardEvent keyboardEvent = new KeyboardEvent(sceneInGame);

//        buttonStart.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                window.setScene(sceneInGame);
//            }
//        });

        // Tao map
        gameMap = new GameMap();
        gameMap.createMap(1, keyboardEvent);

        // Them scene vao stage
        window.setScene(sceneInGame);
        window.setResizable(false);
        window.setTitle("Bomberman OOP");
        window.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        //tracker = PerformanceTracker.getSceneTracker(scene);

        //getFPS
//        AnimationTimer frameRateMeter = new AnimationTimer() {
//
//            @Override
//            public void handle(long now) {
//                System.out.println(String.format("%.0f", getFPS()));
//            }
//        };
//        frameRateMeter.start();
    }

    public void render() {
        graphics.clrscr(canvas);
        //Render map, entities
        graphics.render(gameMap);
    }

//    private float getFPS() {
//        float fps = tracker.getAverageFPS();
//        tracker.resetAverageFPS();
//        return fps;
//    }

    public void update() {
        gameMap.update();
    }
}


