package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.Event.KeyboardEvent;

public class Bomber extends MovingEntity {

    private KeyboardEvent keyboardEvent;

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

}
