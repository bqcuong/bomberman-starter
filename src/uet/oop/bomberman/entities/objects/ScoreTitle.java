package uet.oop.bomberman.entities.objects;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileReader;

public class ScoreTitle {
    private static ScoreTitle instance = new ScoreTitle();
    private Font font;
    private Text timeTexture;
    private Text scoreTexture;
    private Text leftTexture;
    private int bomberLeft;

    private ScoreTitle() {
    }

    public void createScoreTitle(Group root) {
        try {
//            font = Font.font("Abyssinica SIL", FontWeight.BOLD, 80);
            font = Font.loadFont("file:res/PixelEmulator-xq08.ttf", 80);
            timeTexture = new Text();
            scoreTexture = new Text();
            leftTexture = new Text();
            timeTexture.setText("TIME:");
            timeTexture.setFont(font);
            timeTexture.setFill(Color.DIMGRAY);
            timeTexture.setX(48);
            timeTexture.setY(80);

            scoreTexture.setText("SCORE:");
            scoreTexture.setFont(font);
            scoreTexture.setFill(Color.DIMGRAY);
            scoreTexture.setX(700);
            scoreTexture.setY(80);

            leftTexture.setText("LEFT:");
            leftTexture.setFont(font);
            leftTexture.setFill(Color.DIMGRAY);
            leftTexture.setX(1150);
            leftTexture.setY(80);
            root.getChildren().addAll(timeTexture, scoreTexture, leftTexture);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ScoreTitle getInstance() {
        return instance;
    }

    public void update(int bomberLeft, int scoreBomber, Scene scene) {
        setBomberLeft(bomberLeft);

        leftTexture.setText("LEFT:" + bomberLeft);
        scoreTexture.setText(scoreBomber + "");
        scoreTexture.setX((scene.getWidth() - scoreTexture.getBoundsInLocal().getWidth()) / 2);
    }

    public int getBomberLeft() {
        return bomberLeft;
    }

    public void setBomberLeft(int bomberLeft) {
        this.bomberLeft = bomberLeft;
    }
}
