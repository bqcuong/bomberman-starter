package uet.oop.bomberman.entities.animatedEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends AnimatedEntity {
    public static final String UP = "UP";
    public static final String DOWN = "DOWN";
    public static final String LEFT = "LEFT";
    public static final String RIGHT = "RIGHT";
    public static final String CENTER = "CENTER";

    private String direction;
//    private boolean planBomb;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        direction = CENTER;
//        planBomb = false;
    }

    private void moveTo(int x, int y) {
        for (Entity element: BombermanGame.stillObjects) {
            if (element.getClass().getTypeName()
                    .equals("uet.oop.bomberman.entities.staticEntities.Grass")) {
                continue;
            }
            if (element.existOn(x, y)
                    || element.existOn(x, y + Sprite.SCALED_SIZE - 1)
                    || element.existOn(x + Sprite.SCALED_SIZE - 1, y)
                    || element.existOn(x + Sprite.SCALED_SIZE - 1,
                        y + Sprite.SCALED_SIZE - 1)) {
                return;
            }
        }
        this.x = x;
        this.y = y;
    }

    public void moveRight() {
        moveTo(x + BombermanGame.MOVING_UNIT, y);
    }

    public void moveLeft() {
        moveTo(x - BombermanGame.MOVING_UNIT, y);
    }

    public void moveUp() {
        moveTo(x, y - BombermanGame.MOVING_UNIT);
    }

    public void moveDown() {
        moveTo(x, y + BombermanGame.MOVING_UNIT);
    }

    public void planBomb() {
//        planBomb = true;
        int xBomb = (int) Math.round((1.0 * x / Sprite.SCALED_SIZE) / 1.0);
        int yBomb = (int) Math.round((1.0 * y / Sprite.SCALED_SIZE) / 1.0);
        Bomb bomb = new Bomb(xBomb, yBomb);
        BombermanGame.updateQueue.add(bomb);
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public void update() {
        animate();

        switch (direction) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
            case CENTER:
                break;
        }
    }
}
