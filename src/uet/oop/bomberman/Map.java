package uet.oop.bomberman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.controller.CollisionManager;
import uet.oop.bomberman.controller.KeyListener;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Portal;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

public class Map {

    private List<List<Entity>> map = new ArrayList<>();
    private Bomber bomberman;

    public Map(int level, Scene scene) {
        File file = new File("res\\levels\\Level" + level +".txt");
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
            bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage(), scene, new CollisionManager(this));
            scanner.close();
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    } 

    public void render(GraphicsContext gc) {
        for (int i = 0; i < map.size(); i++) {
            map.get(i).forEach(g -> g.render(gc));    
        }
        bomberman.render(gc);
    }

    public void update() {
        bomberman.update();
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
