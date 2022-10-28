package uet.oop.bomberman.scenes;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.graphics.Sprite;

public class LoseGameScene {
    private Scene loseGameScene;
    private Text gameOverText;

    private static final LoseGameScene instance = new LoseGameScene();

    public static LoseGameScene getInstance() {
        return instance;
    }

    private LoseGameScene() {

    }

    public void createLoseGameScene(int sceneWidth, int sceneHeight, Font font) {
        BorderPane borderPane = new BorderPane();
        gameOverText = new Text("GAME OVER");
        gameOverText.setFont(font);
        gameOverText.setFill(Color.GHOSTWHITE);
        borderPane.setCenter(gameOverText);

        loseGameScene = new Scene(borderPane, Sprite.SCALED_SIZE * sceneWidth,
                Sprite.SCALED_SIZE * sceneHeight);
        loseGameScene.setFill(Color.BLACK);
    }

    public Scene getLoseGameScene() {
        return loseGameScene;
    }
}
