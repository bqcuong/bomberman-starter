package uet.oop.bomberman.entities;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {

    public static void keyBomb() {
        EventHandler<KeyEvent> keyEvent;
        keyEvent = event -> {
            if (event.getCode() == KeyCode.ENTER) {
                placeBomb();
            }
        };
        BombermanGame.scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent);
    }
    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    public static void placeBomb() {
        Bomb bomb = new Bomb((Bomber.coordinatesX + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE,
                (Bomber.coordinatesY + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE,
                Sprite.bomb.getFxImage());
        Game.bombs.add(bomb);
    }

    @Override
    public void update() {

    }

}
