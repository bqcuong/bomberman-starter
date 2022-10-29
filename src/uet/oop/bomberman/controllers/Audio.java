package uet.oop.bomberman.controllers;

import uet.oop.bomberman.Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {
    private String filename;
    private Clip clip;

    public Audio(String filename) {
        this.filename = filename;
        try {
            clip = AudioSystem.getClip();
            AudioInputStream audioInputStream
                    = AudioSystem.getAudioInputStream(
                    getClass().getResourceAsStream("/sounds/" + this.filename + ".wav"));
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playBgm() {
        try {
            if (!isPlaying()) {
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopBgm() {
        try {
            if (isPlaying()) {
                clip.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void restartBgm() {

        try {
            if (!isPlaying()) {
                clip.setMicrosecondPosition(0);
                playBgm();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playSoundEffect() {
        try {
            clip.setMicrosecondPosition(0);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isPlaying() {
        return clip.isRunning();
    }
}
