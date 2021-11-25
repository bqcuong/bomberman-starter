package uet.oop.bomberman;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.scene.Scene;
import uet.oop.bomberman.controller.Camera;
import uet.oop.bomberman.controller.CollisionManager;
import uet.oop.bomberman.controller.KeyListener;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

public class Map {

    private List<List<Entity>> map = new ArrayList<>();
    private Bomber bomberman;
    private Camera camera;
    
    public Map(int level, KeyListener keyListener) {
        Path currentWorkingDir = Paths.get("").toAbsolutePath();
        File file = new File(currentWorkingDir.normalize().toString() + "/res/levels/Level" + level +".txt");
        try {
            Scanner scanner = new Scanner(file);
            int height = scanner.nextInt();
            height = scanner.nextInt();
            int width = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < height; i++) {
                String tempString = scanner.nextLine();
                List<Entity> tempList = new ArrayList<>();
                for (int j = 0; j < width; j++) {
                    switch (tempString.charAt(j)) {
                        case '#':
                        tempList.add(new Wall(j, i, Sprite.wall.getFxImage()));
                        break;
                        case '*':
                        tempList.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                        // case 'x':
                        //     tempList.add(new Portal(j, i, Sprite.portal.getFxImage()));
                        default:
                        tempList.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        break;
                    }
                    
                } 
                map.add(tempList);
            }
            bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage(), keyListener, new CollisionManager(this));
            camera = new Camera(0, 0, width);
            scanner.close();
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    } 

    public void update() {
        bomberman.update();
        camera.update(bomberman);
    }

    /**
     * getter for map.
     */
    public List<List<Entity>> getMap() {
        return map;
    }

    /**
     * getter for bomberman.
     */
    public Bomber getBomberman() {
        return bomberman;
    }

    /**
     * Getter for camera.
     */
    public Camera getCamera() {
        return camera;
    }

    public Entity getCoordinate(int x, int y) {
        x -= x % Sprite.SCALED_SIZE;
        y -= y % Sprite.SCALED_SIZE;
        System.out.println("\tx: " + x);
        System.out.println("\ty: " + y);
        int modX = Math.round(x / Sprite.SCALED_SIZE);
        int modY = Math.round(y / Sprite.SCALED_SIZE);
        System.out.println("\tmodX = " + modX);
        System.out.println("\tmodY = " + modY);
        return map.get(modY).get(modX);
    }
}
