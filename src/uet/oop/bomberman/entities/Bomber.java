package uet.oop.bomberman.entities;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.controller.CollisionManager;
import uet.oop.bomberman.controller.KeyListener;
import uet.oop.bomberman.controller.CollisionManager.DIRECTION;
import uet.oop.bomberman.graphics.Graphics;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.controller.Camera;

public class Bomber extends Entity {

    private KeyListener keyListener;
    private CollisionManager collisionManager;

    public Bomber(int x, int y, Image img, KeyListener keyListener, CollisionManager collisionManager) {
        super(x, y, img);
        this.keyListener = keyListener;
        this.collisionManager = collisionManager;
    }

    @Override
    public void update() {
        if (keyListener.isPressed(KeyCode.D)) {
            x += CollisionManager.STEP;
            // if (!collisionManager.checkCollision(x + CollisionManager.STEP, y)) {
            // }            
            //System.out.println("[Key Pressed]: D - " + x);
        }
        if (keyListener.isPressed(KeyCode.A)) {
            //if (!(map.getCoordinate(x - 1, y) instanceof Obstacle)) {
                x -= CollisionManager.STEP;
            //}
            //System.out.println("[Key Pressed]: A - " + x);
        }
        if (keyListener.isPressed(KeyCode.W)) {
            //if (!(map.getCoordinate(x, y - 1) instanceof Obstacle)) {
                y -= CollisionManager.STEP;
            //}
            //System.out.println("[Key Pressed]: W - " + y);
        }
        if (keyListener.isPressed(KeyCode.S)) {
            //System.out.println(x + " " + y);
            //if (!(map.getCoordinate(x, y + 1 + Sprite.SCALED_SIZE) instanceof Obstacle)){
                y += CollisionManager.STEP;
            //}
            //System.out.println("[Key Pressed]: S - " + y);
        }
    }

    @Override
    public void render(GraphicsContext gc, Camera camera) {
        // TODO Auto-generated method stub
        if (camera.getX() > 0 && camera.getX() < camera.getScreenWidth() * Sprite.SCALED_SIZE - Graphics.WIDTH * Sprite.SCALED_SIZE) {
            int tempX = Graphics.WIDTH * Sprite.DEFAULT_SIZE;
            gc.drawImage(img, tempX, y);
        } else if (camera.getX() == camera.getScreenWidth() * Sprite.SCALED_SIZE - Graphics.WIDTH * Sprite.SCALED_SIZE) {
            int tempX = x - (camera.getScreenWidth()* Sprite.SCALED_SIZE - Graphics.WIDTH * Sprite.SCALED_SIZE);
            gc.drawImage(img, tempX, y);
        } else {
            gc.drawImage(img, x, y);
        }
    }

    
}