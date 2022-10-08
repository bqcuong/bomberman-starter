package uet.oop.bomberman.controllers;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Obstacle;
import uet.oop.bomberman.graphics.Map;

public class CollisionDetector {
    public static int FIX_COLLISION_BOMBER = 4;
    private Map map;

    public CollisionDetector(Map map) {
        this.map = map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public boolean checkCollision(int x, int y) {
        Entity topLeft = map.getEntityAtPosition(x, y);
        Entity topRight = map.getEntityAtPosition(x + 20, y);
        Entity downLeft = map.getEntityAtPosition(x, y + 29);
        Entity downRight = map.getEntityAtPosition(x + 20, y + 29);
        return topLeft instanceof Obstacle || topRight instanceof Obstacle
                || downLeft instanceof Obstacle || downRight instanceof Obstacle;
    }
}
