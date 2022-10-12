package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.controllers.CheckCollisionObject;
import uet.oop.bomberman.controllers.CollisionDetector;
import uet.oop.bomberman.events.DirectionStatus;
import uet.oop.bomberman.events.KeyboardEvent;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends MovingEntity {
    private KeyboardEvent keyboardEvent;
    public static int REAL_WIDTH = 20;
    public static int REAL_HEIGHT = 29;

    public static int MAX_SPEED = 4;
    private int speedRun = 2;
    private CollisionDetector collisionDetector;

    //Bomb list
    private List<Entity> bombList = new ArrayList<>();
    private List<Integer> bombListTest = new ArrayList<>();
    //Max number of bomb can set at the same time
    private int bombListMaxSize = 1;
    //Check if current bomb is place or not
    boolean isPlantBomb = false;

    private int indexBomberSprite = 0;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    public Bomber(int x, int y, Image img, KeyboardEvent keyboardEvent, CollisionDetector collisionDetector) {
        super(x, y, img);
        this.keyboardEvent = keyboardEvent;
        this.collisionDetector = collisionDetector;
    }

    private void updateKeyHandle() {
        boolean isPressed = false;
        if (keyboardEvent.isPressed(KeyCode.W)) {
            isPressed = true;
            if (collisionDetector.checkCollision(this.x, this.y - speedRun, CheckCollisionObject.OBSTACLE)) {
                super.updateDirection(directionStatus.UP, false, speedRun);
                indexBomberSprite = 0;
            } else {
                super.updateDirection(directionStatus.UP, true, speedRun);
            }
            setImg(Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1,
                    Sprite.player_up_2, indexBomberSprite, 15).getImage());
            ++indexBomberSprite;
        }
        if (keyboardEvent.isPressed(KeyCode.A)) {
            isPressed = true;
            if (collisionDetector.checkCollision(this.x - speedRun, this.y, CheckCollisionObject.OBSTACLE)) {
                super.updateDirection(directionStatus.LEFT, false, speedRun);
                indexBomberSprite = 0;
            } else {
                super.updateDirection(directionStatus.LEFT, true, speedRun);
            }

            setImg(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1,
                    Sprite.player_left_2, indexBomberSprite, 15).getImage());
            ++indexBomberSprite;
        }
        if (keyboardEvent.isPressed(KeyCode.S)) {
            isPressed = true;
            if (collisionDetector.checkCollision(this.x, this.y + speedRun, CheckCollisionObject.OBSTACLE)) {
                super.updateDirection(directionStatus.DOWN, false, speedRun);
                indexBomberSprite = 0;
            } else {
                super.updateDirection(directionStatus.DOWN, true, speedRun);
            }
            setImg(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1,
                    Sprite.player_down_2, indexBomberSprite, 15).getImage());
            ++indexBomberSprite;
        }
        if (keyboardEvent.isPressed(KeyCode.D)) {
            isPressed = true;
            if (collisionDetector.checkCollision(this.x + speedRun, this.y, CheckCollisionObject.OBSTACLE)) {
                super.updateDirection(directionStatus.RIGHT, false, speedRun);
                indexBomberSprite = 0;
            } else {
                super.updateDirection(directionStatus.RIGHT, true, speedRun);
            }
            setImg(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1,
                    Sprite.player_right_2, indexBomberSprite, 15).getImage());
            ++indexBomberSprite;
        }
        if (keyboardEvent.isPressed(KeyCode.SPACE)) {
            isPlantBomb = true;
            isPressed = true;
        }

//        if (collisionDetector.checkCollision(x, y, CheckCollisionObject.ITEM_SPEED)) {
//            System.out.println("bum");
//        }
        if (!isPressed) {
            indexBomberSprite = 0;
        }
    }

    @Override
    public void update() {
        updateKeyHandle();
        updatePlantBomb();
        for (Entity element:bombList){
            element.update();
        }
    }
    @Override
    public void render(GraphicsContext gc){
        for (Entity element: bombList){
            element.render(gc);
        }
        super.render(gc);
    }

    public void updatePlantBomb() {
        if (isPlantBomb && bombList.size() < bombListMaxSize) {
            System.out.println("is place");
            int xUnit = getX() / Sprite.SCALED_SIZE;
            int yUnit = getY() / Sprite.SCALED_SIZE;
            bombList.add(new Bomb(xUnit,yUnit,Sprite.bomb.getImage()));
        }
    }

    @Override
    public void updateDirection(DirectionStatus directionStatus, boolean isAllowedToMove, int speedRun) {
        super.updateDirection(directionStatus, isAllowedToMove, speedRun);
    }

    //Bomb Item
    public void increaseBombListMaxSize() {
        ++bombListMaxSize;
    }

    public void setBombListMaxSize(int bombListMaxSize) {
        this.bombListMaxSize = bombListMaxSize;
    }

    public int getBombListMaxSize() {
        return bombListMaxSize;
    }
}
