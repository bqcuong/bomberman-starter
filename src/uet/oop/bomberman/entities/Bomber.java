package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.controllers.CollisionDetector;
import uet.oop.bomberman.events.Direction;
import uet.oop.bomberman.events.KeyboardEvent;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends MovingEntity {
    private KeyboardEvent keyboardEvent;

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
            if (collisionDetector.checkCollision(this.x, this.y - speedRun)) {
                super.updateDirection(direction.UP, false, speedRun);
                indexBomberSprite = 0;
            } else {
                super.updateDirection(direction.UP, true, speedRun);
            }
            System.out.println("W");
            setImg(Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1,
                    Sprite.player_up_2, indexBomberSprite, 15).getImage());
            ++indexBomberSprite;
        }
        if (keyboardEvent.isPressed(KeyCode.A)) {
            isPressed = true;
            if (collisionDetector.checkCollision(this.x - speedRun, this.y)) {
                super.updateDirection(direction.LEFT, false, speedRun);
                indexBomberSprite = 0;
            } else {
                super.updateDirection(direction.LEFT, true, speedRun);
            }
            System.out.println("A");
            setImg(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1,
                    Sprite.player_left_2, indexBomberSprite, 15).getImage());
            ++indexBomberSprite;
        }
        if (keyboardEvent.isPressed(KeyCode.S)) {
            isPressed = true;
            if (collisionDetector.checkCollision(this.x, this.y + speedRun)) {
                super.updateDirection(direction.DOWN, false, speedRun);
                indexBomberSprite = 0;
            } else {
                super.updateDirection(direction.DOWN, true, speedRun);
            }
            System.out.println("S");
            setImg(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1,
                    Sprite.player_down_2, indexBomberSprite, 15).getImage());
            ++indexBomberSprite;
        }
        if (keyboardEvent.isPressed(KeyCode.D)) {
            isPressed = true;
            if (collisionDetector.checkCollision(this.x + speedRun, this.y)) {
                super.updateDirection(direction.RIGHT, false, speedRun);
                indexBomberSprite = 0;
            } else {
                super.updateDirection(direction.RIGHT, true, speedRun);
            }
            System.out.println("D");
            setImg(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1,
                    Sprite.player_right_2, indexBomberSprite, 15).getImage());
            ++indexBomberSprite;
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
