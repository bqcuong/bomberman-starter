package uet.oop.bomberman.graphics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.controller.Button;
import uet.oop.bomberman.controller.GameMenu;
import uet.oop.bomberman.graphics.Sprite;



public class Graphics {
    public static final int WIDTH = 18;
    public static final int HEIGHT = 13;

    public static Font TITLEFONT;
    public static Font DEFAULTFONT;
    public static Font CHOOSENFONT;

    private GraphicsContext gc;

    public Graphics(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();
        try {
            TITLEFONT = Font.loadFont(Files.newInputStream(Paths.get("res/font/title.ttf")), 50);
            DEFAULTFONT = Font.loadFont(Files.newInputStream(Paths.get("res/font/default.TTF")), 30);
            CHOOSENFONT = Font .loadFont(Files.newInputStream(Paths.get("res/font/title.ttf")), 25);
        } catch (IOException e) {
            System.out.println("[IOException] Wrong filepaths.");
        }
    }

    public void clearScreen(Canvas canvas) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void renderMap(Map map) {
        for (int i = 0; i < map.getMap().size(); i++) {
            map.getMap().get(i).forEach(g -> g.render(gc,map.getCamera()));    
        }
        map.getBomberman().render(gc, map.getCamera());
    }

    public void renderText(Font font, Text text, int x, int y) {
        gc.setFont(font);
        gc.setFill(text.getFill());
        gc.fillText(text.getText(), x, y);
    }

    public void renderMenu(GameMenu menu) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH * Sprite.SCALED_SIZE, HEIGHT * Sprite.SCALED_SIZE);

        renderText(TITLEFONT, menu.getTitleText(), 
            WIDTH / 2 * Sprite.SCALED_SIZE - (int) menu.getTitleText().getLayoutBounds().getCenterX(), 
            HEIGHT / 6 * Sprite.SCALED_SIZE - (int) menu.getTitleText().getLayoutBounds().getCenterY());

        menu.render(gc);
    }

    public void renderButton(Button button) {
        renderText(button.getFont(), button.getText(), button.getX(), button.getY());
    }
}
