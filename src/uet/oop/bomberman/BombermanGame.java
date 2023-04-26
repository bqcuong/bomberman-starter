package uet.oop.bomberman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.gamelogic.Collision;
import uet.oop.bomberman.graphics.Sprite;

public class BombermanGame extends Application {

    public static int WIDTH;
    public static int HEIGHT;

    private GraphicsContext gc;
    private Canvas canvas;

    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    public static final int SPEED = 2;
    private int bombDelayCnter = 0;

    private ArrayList<String> input = new ArrayList<>();

    // Loại bỏ bomb sau khi nổ
    private void bombRemoval() {
        entities.removeIf(bomb -> bomb instanceof Bomb && ((Bomb) bomb).isDisappear());
        entities.removeIf(
                explosion -> explosion instanceof Explosion && ((Explosion) explosion).isDisappear());
    }

    // Đặt bom theo input của người chơi
    private void bombPlant(Bomber bomberman) {
        if (bomberman.getMaxBomb() > 0 && input.contains("SPACE") && bombDelayCnter == 0) {
            int bombLengthLeft = bomberman.getBombLength();
            int bombLengthRight = bomberman.getBombLength();
            int bombLengthUp = bomberman.getBombLength();
            int bombLengthDown = bomberman.getBombLength();
            for (int i = 1; i <= bomberman.getBombLength(); i++) {
                // Find obstacle on the left
                if (stillObjects.contains(
                        new Wall((bomberman.getX() + 15) / 32 - i, (bomberman.getY() + 15) / 32, Sprite.wall.getFxImage()))) {
                    bombLengthLeft = i - 1;
                    System.out.println(bombLengthLeft);
                    break;
                }
            }
            for (int i = 1; i <= bomberman.getBombLength(); i++) {
                if (entities.contains(
                        new Brick((bomberman.getX() + 15) / 32 - i, (bomberman.getY() + 15) / 32, Sprite.brick.getFxImage()))) {
                    bombLengthLeft = i;
                    System.out.println((bombLengthLeft));
                    break;
                }
            }
            for (int i = 1; i <= bomberman.getBombLength(); i++) {
                if (stillObjects.contains(
                        new Wall((bomberman.getX() + 15) / 32 + i, (bomberman.getY() + 15) / 32, Sprite.wall.getFxImage()))) {
                    bombLengthRight = i - 1;
                    System.out.println(bombLengthRight);
                    break;
                }
                if (entities.contains(
                        new Brick((bomberman.getX() + 15) / 32 + i, (bomberman.getY() + 15) / 32, Sprite.brick.getFxImage()))) {
                    bombLengthRight = i;
                    System.out.println((bombLengthRight));
                    break;
                }
            }
            for (int i = 1; i <= bomberman.getBombLength(); i++) {
                if (stillObjects.contains(
                        new Wall((bomberman.getX() + 15) / 32, (bomberman.getY() + 15) / 32 - i, Sprite.wall.getFxImage()))) {
                    bombLengthUp = i - 1;
                    System.out.println(bombLengthUp);
                    break;
                }
                if (entities.contains(
                        new Brick((bomberman.getX() + 15) / 32, (bomberman.getY() + 15) / 32 - i, Sprite.brick.getFxImage()))) {
                    bombLengthUp = i;
                    System.out.println((bombLengthUp));
                    break;
                }
            }
            for (int i = 1; i <= bomberman.getBombLength(); i++) {
                if (stillObjects.contains(
                        new Wall((bomberman.getX() + 15) / 32, (bomberman.getY() + 15) / 32 + i, Sprite.wall.getFxImage()))) {
                    bombLengthDown = i - 1;
                    System.out.println(bombLengthDown);
                    break;
                }
                if (entities.contains(
                        new Brick((bomberman.getX() + 15) / 32, (bomberman.getY() + 15) / 32 + i, Sprite.brick.getFxImage()))) {
                    bombLengthDown = i;
                    System.out.println((bombLengthDown));
                    break;
                }
            }
            entities.add(
                    new Bomb(
                            ((bomberman.getX() + 15) / 32),
                            ((bomberman.getY() + 15) / 32),
                            Sprite.bomb.getFxImage(),
                            bombLengthLeft,
                            bombLengthRight,
                            bombLengthUp,
                            bombLengthDown));
            bomberman.setMaxBomb(bomberman.getMaxBomb() - 1);
            bombDelayCnter = 11;
            // System.out.println((bomberman.getX() / 32) + " " + (bomberman.getY() / 32));
        }

        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Bomb
                    && ((Bomb) entities.get(i)).getDetonateCnter() == 15
                    && !(((Bomb) entities.get(i)).isPlant())) {
                Bomb bomb = (Bomb) entities.get(i);
                bomb.setPlant(true);
                if (bomb.getBombLengthLeft() > 0) {
                    entities.add(
                            new Explosion(
                                    (bomb.getX() / 32 - bomb.getBombLengthLeft()),
                                    (entities.get(i).getY() / 32),
                                    Sprite.explosion_horizontal_left_last.getFxImage(),
                                    "left",
                                    true));
                }
                if (bomb.getBombLengthRight() > 0) {
                    entities.add(
                            new Explosion(
                                    (entities.get(i).getX() / 32 + bomb.getBombLengthRight()),
                                    (entities.get(i).getY() / 32),
                                    Sprite.explosion_horizontal_right_last.getFxImage(),
                                    "right",
                                    true));
                }
                if (bomb.getBombLengthUp() > 0) {
                    entities.add(
                            new Explosion(
                                    (entities.get(i).getX() / 32),
                                    (entities.get(i).getY() / 32 - bomb.getBombLengthUp()),
                                    Sprite.explosion_vertical_top_last.getFxImage(),
                                    "up",
                                    true));
                }
                if (bomb.getBombLengthDown() > 0) {
                    entities.add(
                            new Explosion(
                                    (entities.get(i).getX() / 32),
                                    (entities.get(i).getY() / 32 + bomb.getBombLengthDown()),
                                    Sprite.explosion_vertical_down_last.getFxImage(),
                                    "down",
                                    true));
                }
                for (int j = 1; j < bomb.getBombLengthLeft(); j++) {
                    entities.add(
                            new Explosion(
                                    (entities.get(i).getX() / 32 - j),
                                    (entities.get(i).getY() / 32),
                                    Sprite.explosion_horizontal.getFxImage(),
                                    "left",
                                    false));
                }
                for (int j = 1; j < bomb.getBombLengthRight(); j++) {
                    entities.add(
                            new Explosion(
                                    (entities.get(i).getX() / 32 + j),
                                    (entities.get(i).getY() / 32),
                                    Sprite.explosion_horizontal.getFxImage(),
                                    "right",
                                    false));
                }
                for (int j = 1; j < bomb.getBombLengthDown(); j++) {
                    entities.add(
                            new Explosion(
                                    (entities.get(i).getX() / 32),
                                    (entities.get(i).getY() / 32 + j),
                                    Sprite.explosion_vertical.getFxImage(),
                                    "down",
                                    false));
                }
                for (int j = 1; j < bomb.getBombLengthUp(); j++) {
                    entities.add(
                            new Explosion(
                                    (entities.get(i).getX() / 32),
                                    (entities.get(i).getY() / 32 - j),
                                    Sprite.explosion_vertical.getFxImage(),
                                    "up",
                                    false));
                }
                bomberman.setMaxBomb(bomberman.getMaxBomb() + 1);
                break;
            }
        }
    }

    // Điều khiển di chuyển
    private void movementControl(Bomber bomberman) {
        if (!input.isEmpty()) {
            if (input.contains("J")) {
                bomberman.setBombLength(bomberman.getBombLength() + 1);
            }
            if (input.get(input.size() - 1).equalsIgnoreCase("D")) {
                bomberman.setDx(SPEED);
                bomberman.setDy(0);
                bomberman.setMoving(true);
                bomberman.setDirection("D");
            } else if (input.get(input.size() - 1).equalsIgnoreCase("A")) {
                bomberman.setDx(-SPEED);
                bomberman.setDy(0);
                bomberman.setMoving(true);
                bomberman.setDirection("A");
            } else if (input.get(input.size() - 1).equalsIgnoreCase("W")) {
                bomberman.setDy(-SPEED);
                bomberman.setDx(0);
                bomberman.setMoving(true);
                bomberman.setDirection("W");
            } else if (input.get(input.size() - 1).equalsIgnoreCase("S")) {
                bomberman.setDy(SPEED);
                bomberman.setDx(0);
                bomberman.setMoving(true);
                bomberman.setDirection("S");
            }
        }
        if (!input.contains("W")
                && !input.contains("A")
                && !input.contains("S")
                && !input.contains("D")) {
            bomberman.setDx(0);
            bomberman.setDy(0);
            bomberman.setMoving(false);
        }
    }

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {

        // Create map
        File file =
                new File("C:\\Users\\sinhd\\OneDrive\\Documents\\GitHub\\bomberman-starter\\res\\levels\\Level1.txt");
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
                        case '#':
                            stillObjects.add(new Wall(j, i, Sprite.wall.getFxImage()));
                            break;
                        case '*':
                            entities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        case '1':
                            entities.add(new Balloom(j, i, Sprite.balloom_right1.getFxImage()));
                            break;
                        case '2':
                            entities.add(new Oneal(j, i, Sprite.oneal_right1.getFxImage()));
                            break;
                        case 'x':
                            stillObjects.add(new Portal(j, i, Sprite.portal.getFxImage()));
                            break;
                    }
                }
            }
            scanner.close();
            Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
            entities.add(bomberman);

            // Xử lí input
            scene.setOnKeyPressed(
                    e -> {
                        String code = e.getCode().toString();

                        // only add once... prevent duplicates
                        if (!input.contains(code)) {
                            input.add(code);
                            ((Bomber) bomberman).setDirection(code);
                        }
                    });

            scene.setOnKeyReleased(
                    e -> {
                        String code = e.getCode().toString();

                        input.remove(code);
                    });

            AnimationTimer timer =
                    new AnimationTimer() {
                        @Override
                        public void handle(long l) {
                            render();
                            update();
                            // Đếm thời gian giữa 2 lần đặt bom
                            if (bombDelayCnter > 0) bombDelayCnter--;
                            movementControl((Bomber) bomberman);
                            bombPlant((Bomber) bomberman);
                            bombRemoval();
                            Collision.checkCollision1(((Bomber) bomberman), entities);
                        }
                    };
            timer.start();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
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
