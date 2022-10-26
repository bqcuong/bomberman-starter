package uet.oop.bomberman;

import javafx.scene.media.AudioClip;

public class Sound {
    public static AudioClip music;
    public static AudioClip lobby;
    public static AudioClip bgGame;
    public static AudioClip dead = new AudioClip("file:res/sound/dead.wav");

    public static void upSound() {
        try {
            lobby = new AudioClip("file:res/sound/lobby.mp3");
            bgGame = new AudioClip("file:res/sound/bgGame.mp3");
            music = new AudioClip("file:res/sound/lobby.mp3");
        } catch (Exception i) {
            i.printStackTrace();
        }
    }
}
