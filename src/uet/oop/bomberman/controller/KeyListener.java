package uet.oop.bomberman.controller;

import java.util.HashSet;
import java.util.Set;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyListener implements EventHandler<KeyEvent> {
    final private Set<KeyCode> activeKeys = new HashSet<>();

    public KeyListener(Scene scene) {
        scene.setOnKeyPressed(this);
        scene.setOnKeyReleased(this);
    }
    
    @Override
    public void handle(KeyEvent event) {
        if (KeyEvent.KEY_PRESSED.equals(event.getEventType())) {

            activeKeys.add(event.getCode());
        } else if (KeyEvent.KEY_RELEASED.equals(event.getEventType())) {
            activeKeys.remove(event.getCode());
        }
    }

    public boolean isPressed(KeyCode keyCode) {
        return activeKeys.contains(keyCode);
    }
}