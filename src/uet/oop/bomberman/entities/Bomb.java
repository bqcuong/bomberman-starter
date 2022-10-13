package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.GameMap;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Timer;
import java.util.TimerTask;

public class Bomb extends Entity implements IObstacle {

    GameMap gameMap;

    private boolean isAllowedToGoThrough = true;
    private int indexBombSprite = 0;

    private int bombLevel = 1;

    private BombStatus bombStatus = BombStatus.WAIT;
    private Timer timer = new Timer();
    private TimerTask timerTask = new TimerTask() {
        int count = 0;

        @Override
        public void run() {
            final int countDownBombWait = 3;
            count++;
            if (countDownBombWait - count >= 0) {
                bombStatus = BombStatus.WAIT;
            } else {
                bombStatus = BombStatus.WENTOFF;
                timer.cancel();
                indexBombSprite = 0;
            }
        }
    };

    public Bomb(int xUnit, int yUnit, Image img, GameMap gameMap) {
        super(xUnit, yUnit, img);
        this.gameMap = gameMap;
        timer.schedule(timerTask, 0, 1000);
    }

    public BombStatus getBombStatus() {
        return bombStatus;
    }

    @Override
    public void update() {
        switch (bombStatus) {
            case WAIT:
                ++indexBombSprite;
                setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, indexBombSprite, 60).getImage());
                break;
            case WENTOFF:
                ++indexBombSprite;
                setImg(Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, indexBombSprite, 30).getImage());
                if (indexBombSprite == 30) {
                    bombStatus = BombStatus.DISAPEAR;
                }
                break;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }

    public boolean isAllowedToGoThrough() {
        return isAllowedToGoThrough;
    }

    public void setAllowedToGoThrough(boolean allowedToGoThrough) {
        isAllowedToGoThrough = allowedToGoThrough;
    }
}
