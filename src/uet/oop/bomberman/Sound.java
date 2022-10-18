package uet.oop.bomberman;

import javafx.scene.media.AudioClip;

public class Sound {
    public static AudioClip lobby;
    public static AudioClip musicGame;

    public static void upSound() {
        try {
            lobby = new AudioClip("file:res/sound/lobby.mp3");
            lobby.setVolume(38);
        } catch (Exception i) {
            i.printStackTrace();
        }
    }
}
