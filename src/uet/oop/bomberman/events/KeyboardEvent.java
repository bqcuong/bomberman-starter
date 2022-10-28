package uet.oop.bomberman.events;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardEvent implements EventHandler<KeyEvent> {
    private boolean isKeyUp = false;
    private boolean isKeyDown = false;
    private boolean isKeyLeft = false;
    private boolean isKeyRight = false;
    private boolean isKeySpace = false;

    private boolean isKeyP = false;
    private boolean isKeyR = false;


    public KeyboardEvent(Scene scene) {
        scene.setOnKeyPressed(this);
        scene.setOnKeyReleased(this);
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            switch (event.getCode()) {
                case A:
                    isKeyLeft = true;
                    break;
                case D:
                    isKeyRight = true;
                    break;
                case S:
                    isKeyDown = true;
                    break;
                case W:
                    isKeyUp = true;
                    break;
                case P:
                    isKeyP = true;
                    break;
                case R:
                    isKeyR = true;
                    break;
                case SPACE:
                    isKeySpace = true;
                    break;
            }
        } else if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
            switch (event.getCode()) {
                case A:
                    isKeyLeft = false;
                    break;
                case D:
                    isKeyRight = false;
                    break;
                case S:
                    isKeyDown = false;
                    break;
                case W:
                    isKeyUp = false;
                    break;
                case P:
                    isKeyP = false;
                    break;
                case R:
                    isKeyR = false;
                case SPACE:
                    isKeySpace = false;
                    break;
            }
        }
    }

    public boolean isPressed(KeyCode keys) {
        boolean result = false;
        switch (keys) {
            case A:
                result = isKeyLeft;
                break;
            case S:
                result = isKeyDown;
                break;
            case D:
                result = isKeyRight;
                break;
            case W:
                result = isKeyUp;
                break;
            case SPACE:
                result = isKeySpace;
                break;
            case P:
                result = isKeyP;
                break;
            case R:
                result = isKeyR;
                break;
        }
        return result;
    }

}
