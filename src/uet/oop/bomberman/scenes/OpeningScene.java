package uet.oop.bomberman.scenes;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.graphics.Sprite;

public class OpeningScene {
    private Scene openingScene;
    private Text stageNumber;

    private static final OpeningScene instance = new OpeningScene();

    public static OpeningScene getInstance() {
        return instance;
    }

    private OpeningScene() {

    }

    public void createOpeningScene(int sceneWidth, int sceneHeight, Font font) {
        BorderPane borderPane = new BorderPane();
        stageNumber = new Text("STAGE");
        stageNumber.setFont(font);
        stageNumber.setFill(Color.GHOSTWHITE);
        borderPane.setCenter(stageNumber);

        openingScene = new Scene(borderPane, Sprite.SCALED_SIZE * sceneWidth,
                Sprite.SCALED_SIZE * sceneHeight);
        openingScene.setFill(Color.BLACK);
    }

    public Scene getOpeningScene() {
        return openingScene;
    }

    public Text getStageNumber() {
        return stageNumber;
    }

    public void updateStageNumber(int currentLevel) {
        this.stageNumber.setText("STAGE" + " " + currentLevel);
    }
}
