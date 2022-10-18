package uet.oop.bomberman;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.graphics.Sprite;

public class Menu {
    public Button setting = new Button("SETTING");
    public Button play = new Button("PLAY");
    public Button exit = new Button("EXIT");
    public Scene menu;
    public Group menuGroup = new Group();

    public Menu() {
        Image background = new Image("file:res/menu.png");
        ImageView viewBackground = new ImageView(background);
        Image logo = new Image("file:res/logo.png");
        ImageView viewLogo = new ImageView(logo);
        viewLogo.setX(16);
        viewLogo.setY(16);
        Text luan = new Text(380, 69, "Bùi Đức Luân - 21020775");
        Text phong = new Text(380, 107, "Đoàn Văn Phong - 21020375");
        textSet(luan);
        textSet(phong);
        setting.setLayoutX(256);
        exit.setLayoutX(448);
        play.setLayoutX(64);
        buttonSet(play);
        buttonSet(setting);
        buttonSet(exit);
        menuGroup.getChildren().addAll(viewBackground, viewLogo, luan, phong, setting, exit, play);
        menu = new Scene(menuGroup, Sprite.SCALED_SIZE * BombermanGame.WIDTH,
                Sprite.SCALED_SIZE * BombermanGame.HEIGHT);
    }

    private void buttonSet(Button button) {
        button.setLayoutY(400);
        button.setPrefWidth(128);
        button.setPrefHeight(38);
        button.setTextFill(Color.rgb(0, 153, 153));
        button.setFont(Font.font(18));
        button.setOnMouseMoved(event -> {
            button.setLayoutY(396);
            button.setFont(Font.font(22));
            button.setTextFill(Color.rgb(255, 0, 0));
        });
        button.setOnMouseExited(event -> {
            button.setLayoutY(400);
            button.setFont(Font.font(18));
            button.setTextFill(Color.rgb(0, 153, 153));
        });
    }

    private void textSet(Text text) {
        text.setFont(Font.font(20));
        text.setFill(Color.rgb(255, 255, 0));
    }
}