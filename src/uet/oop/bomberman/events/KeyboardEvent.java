package uet.oop.bomberman.events;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;
import java.util.Set;

public class KeyboardEvent implements EventHandler<KeyEvent> {
    private Set<KeyCode> pressedKeys = new HashSet<KeyCode>();

    public KeyboardEvent(Scene scene) {
        scene.setOnKeyPressed(this);
        scene.setOnKeyReleased(this);
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            pressedKeys.add(event.getCode());
        } else if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
            pressedKeys.remove(event.getCode());
        }
    }

    public boolean isPressed(KeyCode keys) {
        return pressedKeys.contains(keys);
    }

}
