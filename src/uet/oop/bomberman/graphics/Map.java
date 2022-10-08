package uet.oop.bomberman.graphics;

import uet.oop.bomberman.controllers.CollisionDetector;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.events.KeyboardEvent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map {
    private List<Entity> entities = new ArrayList<>();
    private List<List<Entity>> map = new ArrayList<>();

    private int mapHeight;
    private int mapWidth;

    private int level = 1;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public void createMap(int level, KeyboardEvent keyboardEvent) {
        try {
            BufferedReader file = new BufferedReader(new FileReader("res/levels/Level" + level + ".txt"));
            Scanner sc = new Scanner(file);
            mapHeight = sc.nextInt();
            mapWidth = sc.nextInt();
            sc.nextLine();

            /// Display Bomber
            Entity bomber = new Bomber(1, 1, Sprite.player_right.getImage(), keyboardEvent, new CollisionDetector(this));
            entities.add(bomber);

            /// Display Map
            for (int i = 0; i < mapHeight; i++) {
                String tempLine = sc.nextLine();
                List<Entity> tmpMapList = new ArrayList<>();
                for (int j = 0; j < mapWidth; j++) {
                    switch (tempLine.charAt(j)) {
                        case '#':
                            tmpMapList.add(new Wall(j, i, Sprite.wall.getImage()));
                            break;
                        case '*':
                            tmpMapList.add(new Brick(j, i, Sprite.brick.getImage()));
                            break;
                        default:
                            tmpMapList.add(new Grass(j, i, Sprite.grass.getImage()));
                            break;
                    }
                }
                map.add(tmpMapList);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }


    public List<List<Entity>> getMap() {
        return map;
    }

    public Entity getEntityAtPosition(int x, int y) {
        int X =  x / Sprite.SCALED_SIZE;
        int Y = y / Sprite.SCALED_SIZE;
        return map.get(Y).get(X);
    }

    public List<Entity> getEntities() {
        return entities;
    }

}
