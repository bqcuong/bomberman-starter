package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.GameMap;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Bomb extends Entity implements IObstacle {

    public enum WentOffPhraseStatus {
        OPENNING, CLOSING
    }

    GameMap gameMap;

    private boolean isAllowedToGoThrough = true;
    private int indexBombSprite = 0;

    private int bombLevel = 1;

    private List<Entity> flameUp = new ArrayList<>();
    private List<Entity> flameDown = new ArrayList<>();
    private List<Entity> flameLeft = new ArrayList<>();
    private List<Entity> flameRight = new ArrayList<>();

    private WentOffPhraseStatus wentOffPhrase;
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
                wentOffPhrase = WentOffPhraseStatus.OPENNING;
                timer.cancel();
                indexBombSprite = 0;
            }
        }
    };

    public Bomb(int xUnit, int yUnit, Image img, GameMap gameMap) {
        super(xUnit, yUnit, img);
        this.gameMap = gameMap;
        timer.schedule(timerTask, 0, 1000);
        flameUp.add(new Flame(xUnit, yUnit - 1, Sprite.explosion_vertical_top_last.getImage()));
        flameDown.add(new Flame(xUnit, yUnit + 1, Sprite.explosion_vertical_down_last.getImage()));
        flameLeft.add(new Flame(xUnit - 1, yUnit, Sprite.explosion_horizontal_left_last.getImage()));
        flameRight.add(new Flame(xUnit + 1, yUnit, Sprite.explosion_horizontal_right_last.getImage()));
//        increaseBombLevel();
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
                if (wentOffPhrase == WentOffPhraseStatus.OPENNING) {
                    ++indexBombSprite;
                    this.setImg(Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, indexBombSprite, 10).getImage());
                    for (int i = 0; i < bombLevel - 1; i++) {
                        flameUp.get(i).setImg(Sprite.movingSprite(Sprite.explosion_vertical
                                , Sprite.explosion_vertical1, Sprite.explosion_vertical2, indexBombSprite, 10).getImage());
                        flameDown.get(i).setImg(Sprite.movingSprite(Sprite.explosion_vertical
                                , Sprite.explosion_vertical1, Sprite.explosion_vertical2, indexBombSprite, 10).getImage());
                        flameLeft.get(i).setImg(Sprite.movingSprite(Sprite.explosion_horizontal
                                , Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, indexBombSprite, 10).getImage());
                        flameRight.get(i).setImg(Sprite.movingSprite(Sprite.explosion_horizontal
                                , Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, indexBombSprite, 10).getImage());
                    }

                    flameUp.get(bombLevel - 1).setImg(Sprite.movingSprite(Sprite.explosion_vertical_top_last
                            , Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, indexBombSprite, 10).getImage());
                    flameDown.get(bombLevel - 1).setImg(Sprite.movingSprite(Sprite.explosion_vertical_down_last
                            , Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, indexBombSprite, 10).getImage());
                    flameLeft.get(bombLevel - 1).setImg(Sprite.movingSprite(Sprite.explosion_horizontal_left_last
                            , Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, indexBombSprite, 10).getImage());
                    flameRight.get(bombLevel - 1).setImg(Sprite.movingSprite(Sprite.explosion_horizontal_right_last
                            , Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, indexBombSprite, 10).getImage());

                    if (indexBombSprite == 10) {
                        wentOffPhrase = WentOffPhraseStatus.CLOSING;
                        indexBombSprite = 0;
                    }

                }
                if (wentOffPhrase == WentOffPhraseStatus.CLOSING) {
                    ++indexBombSprite;
                    this.setImg(Sprite.movingSprite(Sprite.bomb_exploded2, Sprite.bomb_exploded1, Sprite.bomb_exploded, indexBombSprite, 10).getImage());
                    for (int i = 0; i < bombLevel - 1; i++) {
                        flameUp.get(i).setImg(Sprite.movingSprite(Sprite.explosion_vertical2
                                , Sprite.explosion_vertical1, Sprite.explosion_vertical, indexBombSprite, 10).getImage());
                        flameDown.get(i).setImg(Sprite.movingSprite(Sprite.explosion_vertical2
                                , Sprite.explosion_vertical1, Sprite.explosion_vertical, indexBombSprite, 10).getImage());
                        flameLeft.get(i).setImg(Sprite.movingSprite(Sprite.explosion_horizontal2
                                , Sprite.explosion_horizontal1, Sprite.explosion_horizontal, indexBombSprite, 10).getImage());
                        flameRight.get(i).setImg(Sprite.movingSprite(Sprite.explosion_horizontal2
                                , Sprite.explosion_horizontal1, Sprite.explosion_horizontal, indexBombSprite, 10).getImage());
                    }

                    flameUp.get(bombLevel - 1).setImg(Sprite.movingSprite(Sprite.explosion_vertical_top_last2
                            , Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last, indexBombSprite, 10).getImage());
                    flameDown.get(bombLevel - 1).setImg(Sprite.movingSprite(Sprite.explosion_vertical_down_last2
                            , Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last, indexBombSprite, 10).getImage());
                    flameLeft.get(bombLevel - 1).setImg(Sprite.movingSprite(Sprite.explosion_horizontal_left_last2
                            , Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last, indexBombSprite, 10).getImage());
                    flameRight.get(bombLevel - 1).setImg(Sprite.movingSprite(Sprite.explosion_horizontal_right_last2
                            , Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last, indexBombSprite, 10).getImage());
                    if (indexBombSprite == 10) {
                        bombStatus = BombStatus.DISAPEAR;
                    }
                }
                break;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        switch (bombStatus) {
            case WAIT:
                super.render(gc);
                break;
            case WENTOFF:
                super.render(gc);
                for (int i = 0; i < bombLevel; i++) {
                    flameUp.get(i).render(gc);
                    flameDown.get(i).render(gc);
                    flameLeft.get(i).render(gc);
                    flameRight.get(i).render(gc);
                }
                break;
        }
    }

    public boolean isAllowedToGoThrough() {
        return isAllowedToGoThrough;
    }

    public void setAllowedToGoThrough(boolean allowedToGoThrough) {
        isAllowedToGoThrough = allowedToGoThrough;
    }

    public int getBombLevel() {
        return bombLevel;
    }

    public void setBombLevel(int bombLevel) {
        this.bombLevel = bombLevel;
    }

    public void increaseBombLevel() {
        for (int i = 0; i < bombLevel; i++) {
            flameUp.get(i).changeYByValue(-Sprite.SCALED_SIZE);
            flameDown.get(i).changeYByValue(Sprite.SCALED_SIZE);
            flameLeft.get(i).changeXByValue(-Sprite.SCALED_SIZE);
            flameRight.get(i).changeXByValue(Sprite.SCALED_SIZE);
        }
        ++this.bombLevel;
        flameUp.add(0, new Flame(this.getX() / Sprite.SCALED_SIZE
                , this.getY() / Sprite.SCALED_SIZE - 1, Sprite.explosion_vertical.getImage()));
        flameDown.add(0, new Flame(this.getX() / Sprite.SCALED_SIZE
                , this.getY() / Sprite.SCALED_SIZE + 1, Sprite.explosion_vertical.getImage()));
        flameLeft.add(0, new Flame(this.getX() / Sprite.SCALED_SIZE - 1
                , this.getY() / Sprite.SCALED_SIZE, Sprite.explosion_horizontal.getImage()));
        flameRight.add(0, new Flame(this.getX() / Sprite.SCALED_SIZE + 1
                , this.getY() / Sprite.SCALED_SIZE, Sprite.explosion_horizontal.getImage()));
    }
}
