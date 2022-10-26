package uet.oop.bomberman;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.entities.*;

import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    Text lever = new Text(8, 43, "LEVER: " + mapGame.mapLever);
    Text items = new Text(160, 43, "ITEM: ");
    public Button setting = new Button("SETTING");
    public Button exit = new Button("EXIT");

    public static Map mapGame = new Map();

    private final List<Entity> entities = new ArrayList<>();

    public static final List<Bomb> bombs = new ArrayList<>();
    private final List<Entity> stillObjects = new ArrayList<>();

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


    public Game() {
        setting.setLayoutY(0);
        exit.setLayoutY(32);
        buttonSet(setting);
        buttonSet(exit);
        textSet(items);
        textSet(lever);
    }
    public void setGame() {
        mapGame.upMap();
        createMap();
    }

    public Bomber getBomber() {
        return (Bomber) entities.get(0);
    }

    public void createMap() {
        Bomb.keyBomb();
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
                        Bomber.coordinatesX = j * Sprite.SCALED_SIZE;
                        Bomber.coordinatesY = i * Sprite.SCALED_SIZE;
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

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        BombermanGame.gc.clearRect(0, 0, BombermanGame.canvas.getWidth(), BombermanGame.canvas.getHeight());
        if (Bomber.coordinatesX <= Sprite.SCALED_SIZE * (BombermanGame.WIDTH - 1) / 2) {
            for (int i = 2; i < Map.r + 2; i += 1) {
                for (int j = 0; j < Map.c; j += 1) {
                    Entity objects = stillObjects.get((i - 2) * Map.c + j);
                    objects.setX(j * Sprite.SCALED_SIZE);
                    objects.setY(i * Sprite.SCALED_SIZE);
                }
            }
            entities.forEach(g -> g.setX(g.getLocation_x()));
        } else if (Bomber.coordinatesX < Sprite.SCALED_SIZE * (Map.c  - 0.5 - 0.5 * BombermanGame.WIDTH)) {
            for (int i = 2; i < Map.r + 2; i += 1) {
                for (int j = 0; j < Map.c; j += 1) {
                    Entity objects = stillObjects.get((i - 2) * Map.c + j);
                    objects.setX(j * Sprite.SCALED_SIZE - (Bomber.coordinatesX - Sprite.SCALED_SIZE * (BombermanGame.WIDTH - 1) / 2));
                    objects.setY(i * Sprite.SCALED_SIZE);
                }
            }
            bombs.forEach(g -> g.setX(Sprite.SCALED_SIZE * (BombermanGame.WIDTH - 1) / 2 - Bomber.coordinatesX + g.getLocation_x()));
            entities.forEach(g -> g.setX(g.getLocation_x() - (Bomber.coordinatesX - Sprite.SCALED_SIZE * (BombermanGame.WIDTH - 1) / 2)));
        } else {
            for (int i = 2; i < Map.r + 2; i += 1) {
                for (int j = 0; j < Map.c; j += 1) {
                    Entity objects = stillObjects.get((i - 2) * Map.c + j);
                    objects.setX(j * Sprite.SCALED_SIZE - Sprite.SCALED_SIZE * (Map.c - BombermanGame.WIDTH));
                    objects.setY(i * Sprite.SCALED_SIZE);
                }
            }
            bombs.forEach(g -> g.setX(g.getLocation_x() - Sprite.SCALED_SIZE * (Map.c - BombermanGame.WIDTH)));
            entities.forEach(g -> g.setX(g.getLocation_x() - Sprite.SCALED_SIZE * (Map.c - BombermanGame.WIDTH)));
        }
        stillObjects.forEach(g -> g.render(BombermanGame.gc));
        entities.forEach(g -> g.render(BombermanGame.gc));
        bombs.forEach(g -> g.render(BombermanGame.gc));
    }

    public void downDataGame() {
        try {
            FileWriter file = new FileWriter("res/data_game.txt");
            StringBuilder data = new StringBuilder("1\n" + Map.r + " " + Map.c + " " + Bomber.coordinatesX + "\n");
            for (Entity s : stillObjects) {
                data.append(s.getLocation_x()).append(" ").append(s.getLocation_y()).append(" ").append(s.getX()).append(" ").append(s.getY()).append("\n");
            }
            for (Entity e : entities) {
                data.append(e.getLocation_x()).append(" ").append(e.getLocation_y()).append(" ").append(e.getX()).append(" ").append(e.getY()).append("\n");
            }
            file.write(data.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean live() {
        int bomberX1 = Bomber.coordinatesX + 1;
        int bomberX2 = bomberX1 + Sprite.SCALED_SIZE - 1;
        int bomberY1 = Bomber.coordinatesY + 1;
        int bomberY2 = bomberY1 + Sprite.SCALED_SIZE - 1;

        for (int i = 1; i < entities.size(); ++i) {

            int monsterX1 = entities.get(i).getLocation_x() + 4;
            int monsterX2 = monsterX1 + Sprite.SCALED_SIZE - 4;
            int monsterY1 = entities.get(i).getLocation_y() + 4;
            int monsterY2 = monsterY1 + Sprite.SCALED_SIZE - 4;

            if (((bomberX1 < monsterX1 && monsterX1 < bomberX2)
                    || (bomberX1 < monsterX2 && monsterX2 < bomberX2))
                    && ((bomberY1 < monsterY1 && monsterY1 < bomberY2)
                    || (bomberY1 < monsterY2 && monsterY2 < bomberY2))) {
                return false;
            }
        }
        return true;
    }

    public void resetGame() {
        for (int i = 0; i < entities.size(); ++i) {
            entities.remove(i);
            --i;
        }
        for (int i = 0; i < stillObjects.size(); ++i) {
            stillObjects.remove(i);
            --i;
        }
        for (int i = 0; i < bombs.size(); ++i) {
            bombs.remove(i);
            --i;
        }
        setGame();
    }
}