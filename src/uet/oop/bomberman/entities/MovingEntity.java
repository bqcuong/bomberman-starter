package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.events.Direction;

public class MovingEntity extends Entity {
    protected boolean isMoving = false;
    public Direction direction = Direction.RIGHT;

    //default speed
    private int speedRun = 2;

    public MovingEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public int getSpeedRun() {
        return speedRun;
    }

    public void setSpeedRun(int speedRun) {
        this.speedRun = speedRun;
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }

    @Override
    public void update() {

    }

        public void updateDirection(Direction direction, boolean isAllowedToMove, int speedRun) {
        if (isAllowedToMove) {
            isMoving = true;
            this.direction = direction;
            switch (direction){
                case LEFT:
                    moveLeft(speedRun);
                    break;
                case RIGHT:
                    moveRight(speedRun);
                    break;
                case UP:
                    moveUp(speedRun);
                    break;
                case DOWN:
                    moveDown(speedRun);
                    break;
            }
        } else {
            isMoving = false;
        }
    }

    public void moveUp(int speedRun) {
        y -= speedRun;
    }

    public void moveDown(int speedRun) {
        y += speedRun;
    }

    public void moveRight(int speedRun) {
        x += speedRun;
    }

    public void moveLeft(int speedRun) {
        x -= speedRun;
    }
}
