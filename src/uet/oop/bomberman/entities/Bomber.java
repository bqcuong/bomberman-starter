package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.events.Direction;
import uet.oop.bomberman.events.KeyboardEvent;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends MovingEntity {

    private KeyboardEvent keyboardEvent;

    private int statusBomberMoveUpSprite = 0;
    private int statusBomberMoveDownSprite = 0;
    private int statusBomberMoveRightSprite = 0;
    private int statusBomberMoveLeftSprite = 0;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    public Bomber(int x, int y, Image img, KeyboardEvent keyboardEvent) {
        super(x, y, img);
        this.keyboardEvent = keyboardEvent;
    }

    private void updatePosition() {
        if (keyboardEvent.isPressed(KeyCode.W)) {
            super.update(direction.UP, true);
            System.out.println("W");
        } else if (keyboardEvent.isPressed(KeyCode.S)) {
            super.update(direction.DOWN, true);
            System.out.println("S");
        } else if (keyboardEvent.isPressed(KeyCode.A)) {
            super.update(direction.LEFT, true);
            System.out.println("A");
        } else if (keyboardEvent.isPressed(KeyCode.D)) {
            super.update(direction.RIGHT, true);
            System.out.println("D");
        }
    }

    @Override
    public void update() {
        updatePosition();
    }

    @Override
    public void updateDirection(Direction direction, boolean isSuccess) {
        super.updateDirection(direction, isSuccess);
    }

    @Override
    public void moveUp() {
        super.moveUp();
        if (statusBomberMoveUpSprite >= 0 && statusBomberMoveUpSprite < 3) {
            setImg(Sprite.player_up.getFxImage());
            statusBomberMoveUpSprite++;
        } else {
            if (statusBomberMoveUpSprite >= 3 && statusBomberMoveUpSprite < 6) {
                setImg(Sprite.player_up_1.getFxImage());
                statusBomberMoveUpSprite++;
            } else {
                if (statusBomberMoveUpSprite >= 6 && statusBomberMoveUpSprite < 9) {
                    setImg(Sprite.player_up_2.getFxImage());
                    statusBomberMoveUpSprite++;
                } else {
                    if (statusBomberMoveUpSprite == 9) {
                        statusBomberMoveUpSprite = 0;
                    }
                }
            }
        }
    }

    @Override
    public void moveDown() {
        super.moveDown();
        if (statusBomberMoveDownSprite >= 0 && statusBomberMoveDownSprite < 3) {
            setImg(Sprite.player_down.getFxImage());
            statusBomberMoveDownSprite++;
        } else {
            if (statusBomberMoveDownSprite >= 3 && statusBomberMoveDownSprite < 6) {
                setImg(Sprite.player_down_1.getFxImage());
                statusBomberMoveDownSprite++;
            } else {
                if (statusBomberMoveDownSprite >= 6 && statusBomberMoveDownSprite < 9) {
                    setImg(Sprite.player_down_2.getFxImage());
                    statusBomberMoveDownSprite++;
                } else {
                    if (statusBomberMoveDownSprite == 9) {
                        statusBomberMoveDownSprite = 0;
                    }
                }
            }
        }
    }

    @Override
    public void moveRight() {
        super.moveRight();
        if (statusBomberMoveRightSprite >= 0 && statusBomberMoveRightSprite < 3) {
            setImg(Sprite.player_right.getFxImage());
            statusBomberMoveRightSprite++;
        } else {
            if (statusBomberMoveRightSprite >= 3 && statusBomberMoveRightSprite < 6) {
                setImg(Sprite.player_right_1.getFxImage());
                statusBomberMoveRightSprite++;
            } else {
                if (statusBomberMoveRightSprite >= 6 && statusBomberMoveRightSprite < 9) {
                    setImg(Sprite.player_right_2.getFxImage());
                    statusBomberMoveRightSprite++;
                } else {
                    if (statusBomberMoveRightSprite == 9) {
                        statusBomberMoveRightSprite = 0;
                    }
                }
            }
        }
    }

    @Override
    public void moveLeft() {
        super.moveLeft();
        if (statusBomberMoveLeftSprite >= 0 && statusBomberMoveLeftSprite < 3) {
            setImg(Sprite.player_left.getFxImage());
            statusBomberMoveLeftSprite++;
        } else {
            if (statusBomberMoveLeftSprite >= 3 && statusBomberMoveLeftSprite < 6) {
                setImg(Sprite.player_left_1.getFxImage());
                statusBomberMoveLeftSprite++;
            } else {
                if (statusBomberMoveLeftSprite >= 6 && statusBomberMoveLeftSprite < 9) {
                    setImg(Sprite.player_left_2.getFxImage());
                    statusBomberMoveLeftSprite++;
                } else {
                    if (statusBomberMoveLeftSprite == 9) {
                        statusBomberMoveLeftSprite = 0;
                    }
                }
            }
        }
    }
}
