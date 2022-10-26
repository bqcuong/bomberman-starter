package uet.oop.bomberman;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageSetting {

    public Stage settingStage = new Stage();

    public StageSetting() {
        Button music = new Button("MUSIC");
        Button effect = new Button("EFFECT");
        Button exit = new Button("CANCEL");
        setButton(exit);
        setButton(effect);
        setButton(music);
        music.setLayoutY(20);
        effect.setLayoutY(100);
        exit.setLayoutY(180);

        Group settingGroup = new Group();
        settingGroup.getChildren().addAll(music, effect, exit);
        Scene setting = new Scene(settingGroup);
        setting.setFill(Color.rgb(194, 214, 214));
        Image image = new Image("file:res/setting.png");

        settingStage.getIcons().add(image);
        settingStage.setWidth(200);
        settingStage.setHeight(250);
        settingStage.setResizable(false);
        settingStage.setScene(setting);
        settingStage.initStyle(StageStyle.UNDECORATED);

        music.setOnAction(event -> {
            if (Sound.lobby.isPlaying()) {
                Sound.lobby.stop();
            } else {
                Sound.lobby.play();
            }
        });
        exit.setOnAction(event -> settingStage.close());
    }

    public void setButton(Button button) {
        button.setLayoutX(50);
        button.setPrefHeight(50);
        button.setPrefWidth(100);
        button.setTextFill(Color.rgb(0, 153, 153));
        button.setFont(Font.font(14));
        button.setOnMouseMoved(event -> {
            button.setFont(Font.font(18));
            button.setTextFill(Color.rgb(255, 0, 0));
        });
        button.setOnMouseExited(event -> {
            button.setFont(Font.font(14));
            button.setTextFill(Color.rgb(0, 153, 153));
        });
    }
}
