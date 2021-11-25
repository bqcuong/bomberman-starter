package uet.oop.bomberman.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Graphics;
import uet.oop.bomberman.graphics.Sprite;


public class GameMenu {
    public enum GAME_STATE {
        IN_MENU, IN_SINGLE_GAME, IN_MULTIPLAYER_GAME, IN_PAUSE, END;
    }

    private final int SINGLE_GAME = 0;
    private final int MULTI_GAME = 1;
    private final int EXIT = 2;

    private Text titleText;

    List<Button> button = new ArrayList<>();

    private int choosenButton;

    private KeyListener keyListener;
    private GAME_STATE gameState;

    public GameMenu(KeyListener keyListener) {
        this.gameState = GAME_STATE.IN_MENU;
        this.keyListener = keyListener;
        
        Text text = new Text("SINGLE PLAY");
        text.setFont(Graphics.DEFAULTFONT); 
        text.setFill(Color.WHITE);
        button.add(new Button(Graphics.WIDTH / 7 * Sprite.SCALED_SIZE,
            Graphics.HEIGHT / 2 * Sprite.SCALED_SIZE - (int) text.getLayoutBounds().getCenterY(), text));


        text = new Text("MULTI PLAY");
        text.setFont(Graphics.DEFAULTFONT);
        text.setFill(Color.WHITE);
        button.add(new Button(Graphics.WIDTH / 7 * Sprite.SCALED_SIZE,
            Graphics.HEIGHT / 8 * 7 * Sprite.SCALED_SIZE - (int) text.getLayoutBounds().getCenterY(), text));

        text = new Text("EXIT");
        text.setFont(Graphics.DEFAULTFONT);
        text.setFill(Color.WHITE);
        button.add(new Button(Graphics.WIDTH / 7 * Sprite.SCALED_SIZE,
            Graphics.HEIGHT / 3 * 2 * Sprite.SCALED_SIZE - (int) text.getLayoutBounds().getCenterY(), text));

        titleText = new Text("BOMBERMAN");
        titleText.setFont(Graphics.TITLEFONT);
        titleText.setFill(Color.CYAN);

        choosenButton = SINGLE_GAME;
    }

    public void update() {
        switch (gameState) {
            case IN_MENU:
                if (keyListener.isPressed(KeyCode.SPACE)) {
                    switch (choosenButton) {
                        case SINGLE_GAME:
                            System.out.println("[ENTER SINGLE GAME]");
                            gameState = GAME_STATE.IN_SINGLE_GAME;
                            break;
                        case MULTI_GAME:
                            System.out.println("[ENTER MULTIPLAYER GAME]");
                            gameState = GAME_STATE.IN_MULTIPLAYER_GAME;
                            break;
                        case EXIT: 
                            System.out.println("[ENTER END STATE]");
                            gameState = GAME_STATE.END; 
                            break;
                    }
                } else if (keyListener.isPressed(KeyCode.S)) {
                    choosenButton++;
                    if (choosenButton == button.size()) {
                        choosenButton = 0;
                    }
                } else if (keyListener.isPressed(KeyCode.W)) {
                    choosenButton--;
                    if (choosenButton < 0) {
                        choosenButton = button.size() - 1;
                    }
                } else if (keyListener.isPressed(KeyCode.C)) {
                    choosenButton = SINGLE_GAME;
                } else if (keyListener.isPressed(KeyCode.V)) {
                    choosenButton = MULTI_GAME;
                } else if (keyListener.isPressed(KeyCode.B)) {
                    choosenButton = EXIT;
                }
                Timer.delayInGameMenu();
                break;

            case IN_SINGLE_GAME:
                
                break;

            case IN_MULTIPLAYER_GAME:
                
                break;

            case IN_PAUSE:
                
                break;

            case END:
                break;
        }
    }

    /**
     * Getter for gameState.
     */
    public GAME_STATE getGameState() {
        return gameState;
    }

    /**
     * Render menu.
     */
    public void render(GraphicsContext gc) {       
        for (int i = 0; i < button.size(); i++) {
            if (choosenButton == i) {
                button.get(i).renderChoosen(gc);
            } else {
                button.get(i).render(gc);
            }
        } 
    }
    
    /**
     * Get titleText.
     */
    public Text getTitleText() {
        return titleText;
    }
}
