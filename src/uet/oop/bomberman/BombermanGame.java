package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;

    public Animal bomber;
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

        // xu li yeu cau tu ban phim, di chuyen bomber
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    Move.up(bomber);
                    break;
                case DOWN:
                    Move.down(bomber);
                    break;
                case RIGHT:
                    Move.right(bomber);
                    break;
                case LEFT:
                    Move.left(bomber);
                    break;
            }
        });
        // init bomber
        bomber = new Bomber(1, 1, Sprite.player_right.getFxImage());
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

        String path = this.getClass().getResource("/map.txt").getPath();
        System.out.println(path);
        List<String> map = new ArrayList<>();
        try {
            map = FileUtils.readFileLineByLine(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (map.isEmpty()) System.out.println("Load map fail!");
        else {
            for (int i = 0; i < map.size(); i++) {
                String line = map.get(i);
                for (int j = 0; j < line.length(); j++) {
                    int codeID = Integer.parseInt(String.valueOf(line.charAt(j))) ;
                    Entity entity = null;
                    switch (codeID) {
                        case Sprite.CODE_ID_WALL: {
                            entity = new Wall(j, i, Sprite.wall.getFxImage());
                            break;
                        }
                        case Sprite.CODE_ID_GRASS: {
                            entity = new Grass(j, i, Sprite.grass.getFxImage());
                            break;
                        }
                        case Sprite.CODE_ID_BOMBERMAN: {
                            entity = bomber;
                            break;
                        }
                    }
                    if (entity instanceof Bomber) {
                        Entity grass = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(grass);
                        entities.add(entity);
                    }
                    else stillObjects.add(entity);
                }
            }
        }
    }

    public void update() {
        for (Entity ett : entities) {
            ett.update();
        }
        //update bomber
        bomber.update();
        bomber.setCountToRun(bomber.getCountToRun() + 1);
        if (bomber.getCountToRun() == 4) {
            Move.checkRun(bomber);
            bomber.setCountToRun(0);
        }
    }


    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        bomber.render(gc);
    }
}
