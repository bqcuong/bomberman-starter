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
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {

    private KeyListener keyListener;
    private CollisionManager collisionManager;

    public Bomber(int x, int y, Image img, Scene scene, CollisionManager collisionManager) {
        super(x, y, img);
        this.keyListener = new KeyListener(scene);
        this.collisionManager = collisionManager;
    }

    @Override
    public void update() {
        if (keyListener.isPressed(KeyCode.D)) {
            System.out.println(x + " " + y);
            x += CollisionManager.STEP;
            // if (!collisionManager.checkCollision(x + CollisionManager.STEP, y)) {
            // }            
            //System.out.println("[Key Pressed]: D - " + x);
        }
        if (keyListener.isPressed(KeyCode.A)) {
            System.out.println(x + " " + y);
            //if (!(map.getCoordinate(x - 1, y) instanceof Obstacle)) {
                x -= CollisionManager.STEP;
            //}
            //System.out.println("[Key Pressed]: A - " + x);
        }
        if (keyListener.isPressed(KeyCode.W)) {
            System.out.println(x + " " + y);
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
}