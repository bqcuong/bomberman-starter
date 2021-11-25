package uet.oop.bomberman.controller;

import uet.oop.bomberman.Map;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Obstacle;
import uet.oop.bomberman.graphics.Sprite;

public class CollisionManager {
    private Map map;

    public final static int STEP = 3;
    public enum DIRECTION {
        UP, DOWN, LEFT, RIGHT;
    }


    public CollisionManager(Map map) {
        this.map = map;
    }

    // public boolean checkCollision(int x, int y) {
    //     Entity object = map.getCoordinate(x, y);
    //     if (object instanceof Obstacle) return false;
    //     if (x < object.getX() + Sprite.SCALED_SIZE - STEP && x + Sprite.SCALED_SIZE - STEP > object.getX()
    //             && y < object.getY() + Sprite.SCALED_SIZE - STEP && Sprite.SCALED_SIZE + y - STEP > object.getY()) {
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }

    public boolean go(DIRECTION direction, int x, int y) {
        switch (direction) {
            case RIGHT:
                // int temp = x % Sprite.SCALED_SIZE;
                // Entity object = map.getCoordinate(x, y);

                // if (x < object.getX() + Sprite.SCALED_SIZE &&
                //     x + Sprite.SCALED_SIZE > object.getX() &&
                //     y < object.getY() + Sprite.SCALED_SIZE &&
                //     Sprite.SCALED_SIZE + y > object.getY()) {
                //     return true;
                // } else {
                //     return false;
                // }
                
                // if (map.getCoordinate(x + 1 + Sprite.SCALED_SIZE, y) instanceof Obstacle) {
                //     return false;
                // } 
                // if (x % Sprite.SCALED_SIZE > FIXED_POINT) {
                //     if (map.getCoordinate(x + 1 + Sprite.SCALED_SIZE, y - Sprite.SCALED_SIZE) instanceof Obstacle) {
                //         return false;
                //     }
                // }
                //return true;
            
            case LEFT:
                //return true;

            case UP:
                //return true;

            case DOWN:
                //return true;
        
            default:
                throw new ArithmeticException("WRONG DRIECTION in [CollisionManager]");

        }
    }
}