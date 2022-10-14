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

    public static Animal bomber;
    public static Animal balloom;
    public static Animal oneal;
    public static SpeedItem speedItem;

    private GraphicsContext gc;
    private Canvas canvas;

    public static List<Animal> entity = new ArrayList<>();
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    public static int [][] checkWall = new int[WIDTH][HEIGHT];
    public static final List<Entity> block = new ArrayList<>(); // chứa bomb


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
                case SPACE:
                    Bomb.set_Bomb();
                    break;
            }
        });
        // init bomber
        bomber = new Bomber(1, 1, Sprite.player_right.getFxImage());
        balloom = new Balloom(15, 1, Sprite.balloom_right1.getFxImage());
        oneal = new Oneal(10, 10, Sprite.oneal_right1.getFxImage());
        speedItem = new SpeedItem(2, 1, Sprite.powerup_speed.getFxImage());

        entity.add(balloom);
        entity.add(oneal);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();
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

                    checkWall[j][i] = codeID;
                    System.out.print(checkWall[j][i]);
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
                        case Sprite.CODE_ID_BRICK: {
                            entity = new Brick(j, i, Sprite.brick.getFxImage());
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
                    else
                   stillObjects.add(entity);
                }
                System.out.println();
            }
        }
    }

    public void update() {
        for (Entity ett : entities) {
            ett.update();
        }
        for (Entity ett: block) {
            ett.update();
        }
        for (Entity ett: entity) {
            ett.update();
        }

        //update items truoc
        speedItem.update();
        //update bomber
        bomber.update();
        balloom.update();
        oneal.update();


        bomber.setCountToRun(bomber.getCountToRun() + 1);
        if (bomber.getCountToRun() == 4) {
            Move.checkRun(bomber);
            bomber.setCountToRun(0);
        }
        balloom.setCountToRun(balloom.getCountToRun() + 1);
        if (balloom.getCountToRun() == 4) {
            Move.checkRun(balloom);
            balloom.setCountToRun(0);
        }
        oneal.setCountToRun(oneal.getCountToRun() + 1);
        if (oneal.getCountToRun() == 4) {
            Move.checkRun(oneal);
            oneal.setCountToRun(0);
        }
    }


    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        block.forEach(g -> g.render(gc));
        entity.forEach(g -> g.render(gc));

        balloom.render(gc);
        oneal.render(gc);
        speedItem.render(gc);
        //render bomber cuoi cung
        bomber.render(gc);
    }
}
