package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Timer;
import java.util.TimerTask;

public class Bomb extends Entity {
    private int indexBombSprite = 0;

    private BombStatus bombStatus = BombStatus.WAIT;
    private Timer timer = new Timer();
    private TimerTask timerTask = new TimerTask() {
        int count = 0;

        @Override
        public void run() {
            final int countDown = 3;
            count++;
            if (countDown - count >= 0) {
                //System.out.println(count);
            } else {
                timer.cancel();
            }
        }
    };

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        timer.schedule(timerTask, 0, 1000);
    }

    @Override
    public void update() {
        indexBombSprite = indexBombSprite + 1 > 500 ? 0 : indexBombSprite + 1;
        System.out.println(indexBombSprite);
        setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, indexBombSprite, 60).getImage());
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }

}
