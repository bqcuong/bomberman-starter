package uet.oop.bomberman.controller;

import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import uet.oop.bomberman.BombermanGame;

public class Timer {

    private static final int FPS = 60;
    private static final long TIME_PER_FRAME = 1000000000 / FPS;
    private AnimationTimer timer;
    private long lastTime;
    private BombermanGame theGame;

    public Timer(BombermanGame theGame) {
        this.theGame = theGame;
        lastTime = System.nanoTime();
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                theGame.loop();
                try {
                    TimeUnit.NANOSECONDS.sleep(delay());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
        };
        timer.start();
    }

    public long delay() {
        long endTime = System.nanoTime();
        long delayTime= endTime - lastTime;
        lastTime = endTime;
        if (delayTime < TIME_PER_FRAME) {
            return TIME_PER_FRAME - delayTime;
        }
        return 0;
    }

    public static void delayInGameMenu() {
        try {
            //Delay after choose a state
            //Avoid too fast input
            TimeUnit.NANOSECONDS.sleep(155000000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
