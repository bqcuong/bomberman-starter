package uet.oop.bomberman;

import javafx.application.Application;
import javafx.stage.Stage;
import uet.oop.bomberman.controllers.Audio;
import uet.oop.bomberman.controllers.AudioController;
import uet.oop.bomberman.controllers.Game;

import javax.sound.midi.Soundbank;

public class BombermanGame extends Application {

    @Override
    public void start(Stage stage) {
        Game game = Game.getInstance();
        game.createGame(stage);
        stage.setResizable(false);
        stage.setTitle("Bomberman OOP");
        stage.show();
        game.start();
    }

}
