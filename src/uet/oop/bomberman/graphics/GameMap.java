package uet.oop.bomberman.graphics;

import uet.oop.bomberman.controllers.CollisionDetector;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.enemies.Ballom;
import uet.oop.bomberman.entities.enemies.Oneal;
import uet.oop.bomberman.entities.items.ItemBombs;
import uet.oop.bomberman.entities.items.ItemFlames;
import uet.oop.bomberman.entities.items.ItemSpeed;
import uet.oop.bomberman.entities.objects.Brick;
import uet.oop.bomberman.entities.objects.Grass;
import uet.oop.bomberman.entities.objects.Wall;
import uet.oop.bomberman.events.KeyboardEvent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameMap {
    private Bomber player;
    private List<List<Entity>> wallAndGrass = new ArrayList<>();
    private List<Entity> bricks = new ArrayList<>();
    private List<Entity> items = new ArrayList<>();

    private List<Entity> enemies = new ArrayList<>();

    private List<Entity> bombList = new ArrayList<>();

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
                List<Entity> tmpWallAndGrass = new ArrayList<>();
                for (int j = 0; j < mapWidth; j++) {
                    switch (tempLine.charAt(j)) {
                        case '#':
                            tmpWallAndGrass.add(new Wall(j, i, Sprite.wall.getImage()));
                            break;
                        case '*':
                            bricks.add(new Brick(j, i, Sprite.brick.getImage()));
                            tmpWallAndGrass.add(new Grass(j, i, Sprite.grass.getImage()));
                            break;
                        case 'p':
                            /// Display Bomber
                            player = new Bomber(j, i, Sprite.player_right.getImage(), keyboardEvent,
                                    new CollisionDetector(this), this);
                            tmpWallAndGrass.add(new Grass(j, i, Sprite.grass.getImage()));
                            break;
                        case 'b':
                            bricks.add(new Brick(j, i, Sprite.brick.getImage()));
                            items.add(new ItemBombs(j, i, Sprite.powerup_bombs.getImage()));
                            tmpWallAndGrass.add(new Grass(j, i, Sprite.grass.getImage()));
                            break;
                        case 'f':
                            bricks.add(new Brick(j, i, Sprite.brick.getImage()));
                            items.add(new ItemFlames(j, i, Sprite.powerup_flames.getImage()));
                            tmpWallAndGrass.add(new Grass(j, i, Sprite.grass.getImage()));
                            break;
                        case 's':
                            bricks.add(new Brick(j, i, Sprite.brick.getImage()));
                            items.add(new ItemSpeed(j, i, Sprite.powerup_speed.getImage()));
                            tmpWallAndGrass.add(new Grass(j, i, Sprite.grass.getImage()));
                            break;
                        case '1':
                            enemies.add(new Ballom(j, i, Sprite.balloom_right1.getImage(), this,
                                    new CollisionDetector(this)));
                            tmpWallAndGrass.add(new Grass(j, i, Sprite.grass.getImage()));
                            break;
                        case '2':
                            enemies.add(new Oneal(j, i, Sprite.oneal_right1.getImage(), this,
                                    new CollisionDetector(this)));
                            tmpWallAndGrass.add(new Grass(j, i, Sprite.grass.getImage()));
                            break;
                        default:
                            tmpWallAndGrass.add(new Grass(j, i, Sprite.grass.getImage()));
                            break;
                    }
                }
                wallAndGrass.add(tmpWallAndGrass);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        items.forEach(Entity::update);
        bricks.forEach(Entity::update);
        enemies.forEach(Entity::update);
        player.update();
    }


    public List<List<Entity>> getWallAndGrass() {
        return wallAndGrass;
    }

    public Entity getWallsAndGrassAtPosition(int x, int y) {
        int xUnit = x / Sprite.SCALED_SIZE;
        int yUnit = y / Sprite.SCALED_SIZE;
        return wallAndGrass.get(yUnit).get(xUnit);
    }

    public Entity getBrickAtPosition(int x, int y) {
        int xUnit = x / Sprite.SCALED_SIZE;
        int yUnit = y / Sprite.SCALED_SIZE;
        for (int i = 0; i < bricks.size(); i++) {
            if ((bricks.get(i).getX() / Sprite.SCALED_SIZE) == xUnit
                    && (bricks.get(i).getY() / Sprite.SCALED_SIZE) == yUnit) {
                return bricks.get(i);
            }
        }
        return null;
    }


    public List<Entity> getItems() {
        return items;
    }

    public List<Entity> getEnemies() {
        return enemies;
    }

    public void setWallAndGrass(List<List<Entity>> wallAndGrass) {
        this.wallAndGrass = wallAndGrass;
    }

    public List<Entity> getBricks() {
        return bricks;
    }

    public void setBricks(List<Entity> bricks) {
        this.bricks = bricks;
    }

    public void setItems(List<Entity> items) {
        this.items = items;
    }

    public Entity getItemAtPosition(int x, int y) {
        int xUnit = x / Sprite.SCALED_SIZE;
        int yUnit = y / Sprite.SCALED_SIZE;
        for (int i = 0; i < items.size(); i++) {
            if ((items.get(i).getX() / Sprite.SCALED_SIZE) == xUnit
                    && (items.get(i).getY() / Sprite.SCALED_SIZE) == yUnit) {
                return items.get(i);
            }
        }
        return null;
    }

    public List<Entity> getBombList() {
        return bombList;
    }

    public Bomber getPlayer() {
        return player;
    }
}
