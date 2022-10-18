package uet.oop.bomberman;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.entities.*;

import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** Phần chơi trò chơi.*/
public class Game {

    Text lever = new Text(8, 43, "LEVER: " + mapGame.mapLever);
    Text items = new Text(160, 43, "ITEAMS: ");
    public Button setting = new Button("SETTING");
    public Button exit = new Button("EXIT");

    /** Khai báo một Map lưu map của Game.*/
    public static Map mapGame;

    private void textSet(Text text) {
        text.setFont(Font.font(20));
        text.setFill(Color.rgb(51, 51, 0));
        text.setFont(Font.font("Harrington", 32));
    }

    private void buttonSet(Button button) {
        button.setLayoutX(544);
        button.setPrefWidth(96);
        button.setPrefHeight(32);
        button.setTextFill(Color.rgb(0, 153, 153));
        button.setFont(Font.font(12));
        button.setOnMouseMoved(event -> {
            button.setTextFill(Color.rgb(255, 0, 0));
            button.setFont(Font.font(14));
        });
        button.setOnMouseExited(event -> {
            button.setTextFill(Color.rgb(0, 153, 153));
            button.setFont(Font.font(12));
        });
    }

    /** List entities lưu các đối tượng Entity chuyển động.*/
    private List<Entity> entities = new ArrayList<>();

    /** List entities lưu các đối tượng Entity đứng yên.*/
    public static List<Entity> stillObjects = new ArrayList<>();

    /** Khởi tạo game.*/
    public Game() {
        mapGame = new Map();
        createMap();
        setting.setLayoutY(0);
        exit.setLayoutY(32);
        buttonSet(setting);
        buttonSet(exit);
        textSet(items);
        textSet(lever);
    }

    /** Thêm các đối tượng vào List tương ứng dựa trên map.*/
    public void createMap() {
        for (int i = 2; i < Map.r + 2; ++i) {
            for (int j = 0; j < Map.c; ++j) {
                Entity object;
                switch (mapGame.getMap(i - 2, j)) {
                    case '#':
                        object = new Wall(j, i);
                        stillObjects.add(object);
                        break;
                    case '*':
                        object = new Brick(j, i);
                        stillObjects.add(object);
                        break;
                    case 'p':
                        object = new Bomber(j, i, Sprite.player_right.getFxImage());
                        entities.add(object);
                        object = new Grass(j, i);
                        stillObjects.add(object);
                        mapGame.setMap(i - 2, j, ' ');
                        break;
                    case '1':
                        object = new Balloom(j, i, Sprite.player_right.getFxImage());
                        entities.add(object);
                        object = new Grass(j, i);
                        stillObjects.add(object);
                        mapGame.setMap(i - 2, j, ' ');
                        break;
                    case '2':
                        object = new Oneal(j, i, Sprite.player_right.getFxImage());
                        entities.add(object);
                        object = new Grass(j, i);
                        stillObjects.add(object);
                        mapGame.setMap(i - 2, j, ' ');
                        break;
                    default:
                        object = new Grass(j, i);
                        stillObjects.add(object);
                        break;
                }
            }
        }
    }

    /** Chạy game.*/
    public void runGame() {
        update();
        render();
    }

    /** Cập nhật trạng thái các đối tượng.*/
    public void update() {
        entities.forEach(Entity::update);
    }

    /** In hình ảnh các đối tượng ra màn hình.*/
    public void render() {
        BombermanGame.gc.clearRect(0, 0, BombermanGame.canvas.getWidth(), BombermanGame.canvas.getHeight());
        if (Bomber.coordinates <= Sprite.SCALED_SIZE * (BombermanGame.WIDTH - 1) / 2) {
            for (int i = 2; i < Map.r + 2; i += 1) {
                for (int j = 0; j < Map.c; j += 1) {
                    Entity objects = stillObjects.get((i - 2) * Map.c + j);
                    objects.setX(j * Sprite.SCALED_SIZE);
                    objects.setY(i * Sprite.SCALED_SIZE);
                }
            }
            entities.forEach(g -> g.setX(g.getLocation_x()));
        } else if (Bomber.coordinates < Sprite.SCALED_SIZE * (Map.c  - 0.5 - 0.5 * BombermanGame.WIDTH)) {
            for (int i = 2; i < Map.r + 2; i += 1) {
                for (int j = 0; j < Map.c; j += 1) {
                    Entity objects = stillObjects.get((i - 2) * Map.c + j);
                    objects.setX(j * Sprite.SCALED_SIZE - (Bomber.coordinates - Sprite.SCALED_SIZE * (BombermanGame.WIDTH - 1) / 2));
                    objects.setY(i * Sprite.SCALED_SIZE);
                }
            }
            entities.forEach(g -> g.setX(g.getLocation_x() - (Bomber.coordinates - Sprite.SCALED_SIZE * (BombermanGame.WIDTH - 1) / 2)));
        } else {
            for (int i = 2; i < Map.r + 2; i += 1) {
                for (int j = 0; j < Map.c; j += 1) {
                    Entity objects = stillObjects.get((i - 2) * Map.c + j);
                    objects.setX(j * Sprite.SCALED_SIZE - Sprite.SCALED_SIZE * (Map.c - BombermanGame.WIDTH));
                    objects.setY(i * Sprite.SCALED_SIZE);
                }
            }
            entities.forEach(g -> g.setX(g.getLocation_x() - Sprite.SCALED_SIZE * (Map.c - BombermanGame.WIDTH)));
        }
        stillObjects.forEach(g -> g.render(BombermanGame.gc));
        entities.forEach(g -> g.render(BombermanGame.gc));
    }

    public void downDataGame() {
        try {
            FileWriter file = new FileWriter("res/datagame.txt");
            String data = "1\n" + Map.r + " " + Map.c + " " + Bomber.coordinates + "\n";
            for (Entity s : stillObjects) {
                data += s.getLocation_x() + " " + s.getLocation_y() + " " + s.getX() + " " + s.getY() + "\n";
            }
            for (Entity e : entities) {
                data += e.getLocation_x() + " " + e.getLocation_y() + " " + e.getX() + " " + e.getY() + "\n";
            }
            file.write(data);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}