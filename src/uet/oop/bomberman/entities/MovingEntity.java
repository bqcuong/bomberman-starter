package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.events.Direction;

public class MovingEntity extends Entity {
    protected boolean isMoving = false;
    public Direction direction = Direction.RIGHT;

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

    public void update(Direction direction, boolean isSuccess) {
        updateDirection(direction, isSuccess);
    }

    public void updateDirection(Direction direction, boolean isSuccess) {
        if (isSuccess) {
            isMoving = true;
            this.direction = direction;
            if (direction == Direction.DOWN) {
                moveDown();
            }
            if (direction == Direction.UP) {
                moveUp();
            }
            if (direction == Direction.LEFT) {
                moveLeft();
            }
            if (direction == Direction.RIGHT) {
                moveRight();
            }
        } else {
            isMoving = false;
        }
    }

    public void moveUp(){
        y-=speedRun;
    }
    public void moveDown(){
        y+=speedRun;
    }
    public void moveRight(){
        x+=speedRun;
    }
    public void moveLeft(){
        x-=speedRun;
    }
}
