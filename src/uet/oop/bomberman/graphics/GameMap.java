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

public class GameMap {
    private List<Entity> entities = new ArrayList<>();
    private List<List<Entity>> map = new ArrayList<>();

    private List<Entity> item = new ArrayList<>();


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
                        case 'p':
                            /// Display Bomber
                            Entity bomber = new Bomber(j, i, Sprite.player_right.getImage(), keyboardEvent, new CollisionDetector(this), this);
                            entities.add(bomber);
                            tmpMapList.add(new Grass(j, i, Sprite.grass.getImage()));
                            break;
                        case 'b':
                            item.add(new ItemBombs(j, i, Sprite.powerup_bombs.getImage()));
                            tmpMapList.add(new Grass(j, i, Sprite.grass.getImage()));
                            break;
                        case 'f':
                            item.add(new ItemFlames(j, i, Sprite.powerup_flames.getImage()));
                            tmpMapList.add(new Grass(j, i, Sprite.grass.getImage()));
                            break;
                        case 's':
                            item.add(new ItemSpeed(j, i, Sprite.powerup_speed.getImage()));
                            tmpMapList.add(new Grass(j, i, Sprite.grass.getImage()));
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
        item.forEach(Entity::update);
    }


    public List<List<Entity>> getMap() {
        return map;
    }

    public Entity getEntityAtPosition(int x, int y) {
        int xUnit = x / Sprite.SCALED_SIZE;
        int yUnit = y / Sprite.SCALED_SIZE;
        return map.get(yUnit).get(xUnit);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Entity> getItem() {
        return item;
    }

    public Entity getItemAtPosition(int x, int y) {
        int xUnit = x / Sprite.SCALED_SIZE;
        int yUnit = y / Sprite.SCALED_SIZE;
        for (int i = 0; i < item.size(); i++) {
            if (item.get(i).getX() == xUnit && item.get(i).getY() == yUnit) {
                return item.get(i);
            }
        }
        System.out.println("NULL");
        return null;
    }
}
