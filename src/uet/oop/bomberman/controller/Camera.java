package uet.oop.bomberman.controller;

import uet.oop.bomberman.Map;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.graphics.Graphics;
import uet.oop.bomberman.graphics.Sprite;

public class Camera {
    private int x;
    private int y;
    private int screenWidth;

    public Camera(int x, int y, int screenWidth) {
        this.x = x;
        this.y = y;
        this.screenWidth = screenWidth;
    }

    public void update(Bomber bomber) {
        x = bomber.getX() - Graphics.WIDTH * Sprite.DEFAULT_SIZE;
        if (x < 0) x = 0;
        if (x + Graphics.WIDTH * Sprite.SCALED_SIZE > screenWidth * Sprite.SCALED_SIZE) {
            x = screenWidth * Sprite.SCALED_SIZE - Graphics.WIDTH * Sprite.SCALED_SIZE;
        }
    }
    
    /**
     * Getter for x.
     */
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getScreenWidth() {
        return screenWidth;
    }
}
