package uet.oop.bomberman;

//import com.sun.javafx.perf.PerformanceTracker;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import uet.oop.bomberman.events.KeyboardEvent;
import uet.oop.bomberman.graphics.GraphicsMGR;
import uet.oop.bomberman.graphics.GameMap;
import uet.oop.bomberman.graphics.Sprite;

public class BombermanGame extends Application {

    private GameMap gameMap;
    private GraphicsMGR graphics;
    private Canvas canvas;

    //private static PerformanceTracker tracker;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas.
        canvas = new Canvas(Sprite.SCALED_SIZE * GraphicsMGR.WIDTH, Sprite.SCALED_SIZE * GraphicsMGR.HEIGHT);
        graphics = new GraphicsMGR(canvas);

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Tao keyboard event
        KeyboardEvent keyboardEvent = new KeyboardEvent(scene);

        // Tao map
        gameMap = new GameMap();
        gameMap.createMap(1, keyboardEvent);

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


