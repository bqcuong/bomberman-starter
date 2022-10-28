package uet.oop.bomberman.scenes;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.entities.objects.ScoreTitle;
import uet.oop.bomberman.graphics.GraphicsMGR;
import uet.oop.bomberman.graphics.Sprite;

public class InGameScene {
    private Scene inGameScene;
    private ScoreTitle scoreTitle;
    private Font font;
    private Text pausedText;
    private Canvas canvas;

    private static final InGameScene instance = new InGameScene();

    private InGameScene() {

    }

    public static InGameScene getInstance() {
        return instance;
    }

    public void createSceneInGame(int canvasWidth, int canvasHeight, Font font) {
        this.font = font;
        canvas = new Canvas(Sprite.SCALED_SIZE * canvasWidth,
                Sprite.SCALED_SIZE * canvasHeight);
        Group root = new Group(canvas);
        scoreTitle = ScoreTitle.getInstance();
        scoreTitle.createScoreTitle(root, font);
        createPauseText();
        root.getChildren().add(pausedText);
        inGameScene = new Scene(root);
    }

    public GraphicsContext getGraphicContext() {
        return canvas.getGraphicsContext2D();
    }

    public Scene getInGameScene() {
        return inGameScene;
    }

    public void createPauseText() {
        pausedText = new Text("PAUSED");
        pausedText.setFill(Color.YELLOW);
        pausedText.setStroke(Color.DIMGRAY);
        pausedText.setStrokeWidth(3);
        pausedText.setFont(font);
        pausedText.setX((Sprite.SCALED_SIZE * GraphicsMGR.WIDTH
                - pausedText.getBoundsInLocal().getWidth()) / 2);
        pausedText.setY((Sprite.SCALED_SIZE * GraphicsMGR.HEIGHT
                - pausedText.getBoundsInLocal().getHeight()) / 2 + Sprite.SCALED_SIZE);
        pausedText.setVisible(false);
    }

    public Text getPausedText() {
        return pausedText;
    }

    public ScoreTitle getScoreTitle() {
        return scoreTitle;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvasSize(int w, int h) {
        canvas.setWidth(Sprite.SCALED_SIZE * w);
        canvas.setHeight(Sprite.SCALED_SIZE * h);
    }
}
