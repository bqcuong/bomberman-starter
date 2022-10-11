package uet.oop.bomberman.entities;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.controllers.CheckCollisionObject;
import uet.oop.bomberman.controllers.CollisionDetector;
import uet.oop.bomberman.events.Direction;
import uet.oop.bomberman.events.KeyboardEvent;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends MovingEntity {
    private KeyboardEvent keyboardEvent;

    public static int REAL_WIDTH = 20;
    public static int REAL_HEIGHT = 29;

    private static int MAX_SPEED = 4;
    private int speedRun = 2;
    private CollisionDetector collisionDetector;

    private int indexBomberSprite = 0;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    public Bomber(int x, int y, Image img, KeyboardEvent keyboardEvent, CollisionDetector collisionDetector) {
        super(x, y, img);
        this.keyboardEvent = keyboardEvent;
        this.collisionDetector = collisionDetector;
    }

    private void updatePosition() {
        boolean isPressed = false;
        if (keyboardEvent.isPressed(KeyCode.W)) {
            isPressed = true;
            if (collisionDetector.checkCollision(this.x, this.y - speedRun, CheckCollisionObject.OBSTACLE)) {
                super.updateDirection(direction.UP, false, speedRun);
                indexBomberSprite = 0;
            } else {
                super.updateDirection(direction.UP, true, speedRun);
            }
            setImg(Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1,
                    Sprite.player_up_2, indexBomberSprite, 15).getImage());
            ++indexBomberSprite;
        }
        if (keyboardEvent.isPressed(KeyCode.A)) {
            isPressed = true;
            if (collisionDetector.checkCollision(this.x - speedRun, this.y, CheckCollisionObject.OBSTACLE)) {
                super.updateDirection(direction.LEFT, false, speedRun);
                indexBomberSprite = 0;
            } else {
                super.updateDirection(direction.LEFT, true, speedRun);
            }

            setImg(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1,
                    Sprite.player_left_2, indexBomberSprite, 15).getImage());
            ++indexBomberSprite;
        }
        if (keyboardEvent.isPressed(KeyCode.S)) {
            isPressed = true;
            if (collisionDetector.checkCollision(this.x, this.y + speedRun, CheckCollisionObject.OBSTACLE)) {
                super.updateDirection(direction.DOWN, false, speedRun);
                indexBomberSprite = 0;
            } else {
                super.updateDirection(direction.DOWN, true, speedRun);
            }
            setImg(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1,
                    Sprite.player_down_2, indexBomberSprite, 15).getImage());
            ++indexBomberSprite;
        }
        if (keyboardEvent.isPressed(KeyCode.D)) {
            isPressed = true;
            if (collisionDetector.checkCollision(this.x + speedRun, this.y, CheckCollisionObject.OBSTACLE)) {
                super.updateDirection(direction.RIGHT, false, speedRun);
                indexBomberSprite = 0;
            } else {
                super.updateDirection(direction.RIGHT, true, speedRun);
            }
            setImg(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1,
                    Sprite.player_right_2, indexBomberSprite, 15).getImage());
            ++indexBomberSprite;
        }
        if (collisionDetector.checkCollision(x, y, CheckCollisionObject.ITEM_SPEED)) {
            if (speedRun < MAX_SPEED) speedRun++;
        }
        if (!isPressed) {
            indexBomberSprite = 0;
        }
    }

    @Override
    public void update() {
        updatePosition();
    }

    @Override
    public void updateDirection(Direction direction, boolean isAllowedToMove, int speedRun) {
        super.updateDirection(direction, isAllowedToMove, speedRun);
    }
}
