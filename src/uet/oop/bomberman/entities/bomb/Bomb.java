package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.controllers.AudioController;
import uet.oop.bomberman.controllers.BombFlameInfo;
import uet.oop.bomberman.controllers.Game;
import uet.oop.bomberman.entities.objects.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.objects.IObstacle;
import uet.oop.bomberman.entities.objects.Wall;
import uet.oop.bomberman.entities.items.IItem;
import uet.oop.bomberman.entities.items.ItemFlames;
import uet.oop.bomberman.graphics.GameMap;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Bomb extends Entity implements IObstacle {

    public enum WentOffPhraseStatus {
        OPENING, CLOSING
    }

    public enum BombStatus {
        WAIT, WENTOFF, DISAPEAR
    }


    GameMap gameMap;

    private boolean isAllowedToGoThrough = true;
    private int indexBombSprite = 0;

    private int bombLevel;
    //Up flame
    private List<Entity> upFlameList = new ArrayList<>();
    private BombFlameInfo upFlameInfo = new BombFlameInfo();

    //Down flame
    private List<Entity> downFlameList = new ArrayList<>();
    private BombFlameInfo downFlameInfo = new BombFlameInfo();

    //Left flame
    private List<Entity> leftFlameList = new ArrayList<>();
    private BombFlameInfo leftFlameInfo = new BombFlameInfo();

    //Right flame
    private List<Entity> rightFlameList = new ArrayList<>();
    private BombFlameInfo rightFlameInfo = new BombFlameInfo();

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
                wentOffPhrase = WentOffPhraseStatus.OPENING;
                indexBombSprite = 0;
                timer.cancel();
            }
        }
    };

    public Bomb(int xUnit, int yUnit, Image img, int bombLevel, GameMap gameMap) {
        super(xUnit, yUnit, img);
        this.gameMap = gameMap;
        this.bombLevel = bombLevel;
        timer.schedule(timerTask, 0, 1000);

        //make left flame
        checkLeftFlame(xUnit, yUnit);
        makeLeftFlame(xUnit, yUnit);

        //make right flame
        checkRightFlame(xUnit, yUnit);
        makeRightFlame(xUnit, yUnit);

        //make down flame
        checkDownFlame(xUnit, yUnit);
        makeDownFlame(xUnit, yUnit);

        //make up flame
        checkUpFlame(xUnit, yUnit);
        makeUpFlame(xUnit, yUnit);
    }

    public void updateNearbyBombActivation() {
        int xUnit = x / Sprite.SCALED_SIZE;
        int yUnit = y / Sprite.SCALED_SIZE;
        Bomb bomb;
        //Up
        for (int i = yUnit - 1; i != yUnit - bombLevel - 1; i--) {

            if (gameMap.getPlayer().getBombAtPosition(xUnit * Sprite.SCALED_SIZE,
                    i * Sprite.SCALED_SIZE) != null) {
                upFlameInfo.setBombCheck(true);
                upFlameInfo.setIntersectionLength(bombLevel - (yUnit - i - 1));
                bomb = (Bomb) gameMap.getPlayer().getBombAtPosition(xUnit * Sprite.SCALED_SIZE,
                        i * Sprite.SCALED_SIZE);
                if (this.getBombStatus().equals(BombStatus.WENTOFF)
                        && this.getIndexBombSprite() == 3
                        && bomb.getBombStatus().equals(BombStatus.WAIT)) {
                    bomb.setBombStatus(BombStatus.WENTOFF);
                    bomb.setWentOffPhrase(WentOffPhraseStatus.OPENING);
                    bomb.setIndexBombSprite(0);
                    bomb.timer.cancel();
                }
            }
        }

        //Down
        for (int i = yUnit + 1; i != yUnit + bombLevel + 1; i++) {
            if (gameMap.getPlayer().getBombAtPosition(xUnit * Sprite.SCALED_SIZE,
                    i * Sprite.SCALED_SIZE) != null) {
                downFlameInfo.setBombCheck(true);
                downFlameInfo.setIntersectionLength(bombLevel - (i - yUnit - 1));
                bomb = (Bomb) gameMap.getPlayer().getBombAtPosition(xUnit * Sprite.SCALED_SIZE,
                        i * Sprite.SCALED_SIZE);
                if (this.getBombStatus().equals(BombStatus.WENTOFF)
                        && this.getIndexBombSprite() == 3
                        && bomb.getBombStatus().equals(BombStatus.WAIT)) {
                    bomb.setBombStatus(BombStatus.WENTOFF);
                    bomb.setWentOffPhrase(WentOffPhraseStatus.OPENING);
                    bomb.setIndexBombSprite(0);
                    bomb.timer.cancel();
                }
            }
        }

        //Left
        for (int i = xUnit - 1; i != xUnit - bombLevel - 1; i--) {
            if (gameMap.getPlayer().getBombAtPosition(i * Sprite.SCALED_SIZE,
                    yUnit * Sprite.SCALED_SIZE) != null) {
                leftFlameInfo.setBombCheck(true);
                leftFlameInfo.setIntersectionLength(bombLevel - (xUnit - i - 1));
                bomb = (Bomb) gameMap.getPlayer().getBombAtPosition(i * Sprite.SCALED_SIZE,
                        yUnit * Sprite.SCALED_SIZE);
                if (this.getBombStatus().equals(BombStatus.WENTOFF)
                        && this.getIndexBombSprite() == 3
                        && bomb.getBombStatus().equals(BombStatus.WAIT)) {
                    bomb.setBombStatus(BombStatus.WENTOFF);
                    bomb.setWentOffPhrase(WentOffPhraseStatus.OPENING);
                    bomb.setIndexBombSprite(0);
                    bomb.timer.cancel();
                }
            }
        }

        //Right
        for (int i = xUnit + 1; i != xUnit + bombLevel + 1; i++) {
            if (gameMap.getPlayer().getBombAtPosition(i * Sprite.SCALED_SIZE,
                    yUnit * Sprite.SCALED_SIZE) != null) {
                rightFlameInfo.setBombCheck(true);
                rightFlameInfo.setIntersectionLength(bombLevel - (i - xUnit - 1));
                bomb = (Bomb) gameMap.getPlayer().getBombAtPosition(i * Sprite.SCALED_SIZE,
                        yUnit * Sprite.SCALED_SIZE);
                if (this.getBombStatus().equals(BombStatus.WENTOFF)
                        && this.getIndexBombSprite() == 3
                        && bomb.getBombStatus().equals(BombStatus.WAIT)) {
                    bomb.setBombStatus(BombStatus.WENTOFF);
                    bomb.setWentOffPhrase(WentOffPhraseStatus.OPENING);
                    bomb.setIndexBombSprite(0);
                    bomb.timer.cancel();
                }
            }
        }
    }

    public void checkUpFlame(int xUnit, int yUnit) {
        //Up check
        upFlameInfo.setFlameLength(bombLevel);
        for (int i = yUnit - 1; i != yUnit - bombLevel - 1; i--) {
            if (gameMap.getBrickAtPosition(xUnit * Sprite.SCALED_SIZE,
                    i * Sprite.SCALED_SIZE) instanceof Brick) {
                upFlameInfo.setBrickCheck(true);
                upFlameInfo.setFlameLength(yUnit - i - 1);
                upFlameInfo.setBrickExplosion(gameMap.getBrickAtPosition(xUnit * Sprite.SCALED_SIZE,
                        i * Sprite.SCALED_SIZE));
                break;
            }
            if (gameMap.getWallsAndGrassAtPosition(xUnit * Sprite.SCALED_SIZE,
                    i * Sprite.SCALED_SIZE) instanceof Wall) {
                upFlameInfo.setWallCheck(true);
                upFlameInfo.setFlameLength(yUnit - i - 1);
                break;
            }
            if (gameMap.getItemAtPosition(xUnit * Sprite.SCALED_SIZE,
                    i * Sprite.SCALED_SIZE) instanceof IItem) {
                upFlameInfo.setItemCheck(true);
                upFlameInfo.setFlameLength(yUnit - i - 1);
                break;
            }
        }
    }

    public void makeUpFlame(int xUnit, int yUnit) {

        //Make Up flame list
        if (upFlameInfo.isBrickCheck() || upFlameInfo.isWallCheck()
                || upFlameInfo.isItemCheck()) {
            for (int i = 1; i <= upFlameInfo.getFlameLength(); i++) {
                upFlameList.add(new ItemFlames(xUnit,
                        yUnit - i, Sprite.explosion_vertical.getImage()));
            }
        } else {
            for (int i = 1; i < bombLevel; i++) {
                upFlameList.add(new ItemFlames(xUnit, yUnit - i, Sprite.explosion_vertical.getImage()));
            }
            upFlameList.add(new ItemFlames(xUnit, yUnit - bombLevel, Sprite.explosion_vertical_top_last.getImage()));
        }
    }

    public void checkDownFlame(int xUnit, int yUnit) {
        //Down check
        downFlameInfo.setFlameLength(bombLevel);
        for (int i = yUnit + 1; i != yUnit + bombLevel + 1; i++) {
            if (gameMap.getBrickAtPosition(xUnit * Sprite.SCALED_SIZE,
                    i * Sprite.SCALED_SIZE) instanceof Brick) {
                downFlameInfo.setBrickCheck(true);
                downFlameInfo.setFlameLength(yUnit - i - 1);
                downFlameInfo.setBrickExplosion(gameMap.getBrickAtPosition(xUnit * Sprite.SCALED_SIZE,
                        i * Sprite.SCALED_SIZE));
                break;
            }
            if (gameMap.getWallsAndGrassAtPosition(xUnit * Sprite.SCALED_SIZE,
                    i * Sprite.SCALED_SIZE) instanceof Wall) {
                downFlameInfo.setWallCheck(true);
                downFlameInfo.setFlameLength(i - yUnit - 1);
                break;
            }
            if (gameMap.getItemAtPosition(xUnit * Sprite.SCALED_SIZE,
                    i * Sprite.SCALED_SIZE) instanceof IItem) {
                downFlameInfo.setItemCheck(true);
                downFlameInfo.setFlameLength(i - yUnit - 1);
                break;
            }
        }
    }

    public void makeDownFlame(int xUnit, int yUnit) {
        //Make Down flame list
        if (downFlameInfo.isBrickCheck() || downFlameInfo.isWallCheck() || downFlameInfo.isItemCheck()) {
            for (int i = 1; i <= downFlameInfo.getFlameLength(); i++) {
                downFlameList.add(new ItemFlames(xUnit,
                        yUnit + i, Sprite.explosion_vertical.getImage()));
            }
        } else {
            for (int i = 1; i < bombLevel; i++) {
                downFlameList.add(new ItemFlames(xUnit, yUnit + i, Sprite.explosion_vertical.getImage()));
            }
            downFlameList.add(new ItemFlames(xUnit, yUnit + bombLevel, Sprite.explosion_vertical_down_last.getImage()));
        }
    }

    public void checkLeftFlame(int xUnit, int yUnit) {
        //Left
        leftFlameInfo.setFlameLength(bombLevel);
        for (int i = xUnit - 1; i != xUnit - bombLevel - 1; i--) {
            if (gameMap.getBrickAtPosition(i * Sprite.SCALED_SIZE,
                    yUnit * Sprite.SCALED_SIZE) instanceof Brick) {
                leftFlameInfo.setBrickCheck(true);
                leftFlameInfo.setFlameLength(xUnit - i - 1);
                leftFlameInfo.setBrickExplosion(gameMap.getBrickAtPosition(i * Sprite.SCALED_SIZE,
                        yUnit * Sprite.SCALED_SIZE));
                break;
            }
            if (gameMap.getWallsAndGrassAtPosition(i * Sprite.SCALED_SIZE,
                    yUnit * Sprite.SCALED_SIZE) instanceof Wall) {
                leftFlameInfo.setWallCheck(true);
                leftFlameInfo.setFlameLength(xUnit - i - 1);
                break;
            }
            if (gameMap.getItemAtPosition(i * Sprite.SCALED_SIZE,
                    yUnit * Sprite.SCALED_SIZE) instanceof IItem) {
                leftFlameInfo.setItemCheck(true);
                leftFlameInfo.setFlameLength(xUnit - i - 1);
                break;
            }
        }
    }

    public void makeLeftFlame(int xUnit, int yUnit) {
        //Make left flame list
        if (leftFlameInfo.isBrickCheck() || leftFlameInfo.isWallCheck() || leftFlameInfo.isItemCheck()) {
            for (int i = 1; i <= leftFlameInfo.getFlameLength(); i++) {
                leftFlameList.add(new ItemFlames(xUnit - i,
                        yUnit, Sprite.explosion_horizontal.getImage()));
            }
        } else {
            for (int i = 1; i < bombLevel; i++) {
                leftFlameList.add(new ItemFlames(xUnit - i, yUnit, Sprite.explosion_horizontal.getImage()));
            }
            leftFlameList.add(new ItemFlames(xUnit - bombLevel, yUnit, Sprite.explosion_horizontal_left_last.getImage()));
        }
    }

    public void checkRightFlame(int xUnit, int yUnit) {
        //Right check
        rightFlameInfo.setFlameLength(bombLevel);
        for (int i = xUnit + 1; i != xUnit + bombLevel + 1; i++) {
            if (gameMap.getBrickAtPosition(i * Sprite.SCALED_SIZE,
                    yUnit * Sprite.SCALED_SIZE) instanceof Brick) {
                rightFlameInfo.setBrickCheck(true);
                rightFlameInfo.setFlameLength(i - xUnit - 1);
                rightFlameInfo.setBrickExplosion(gameMap.getBrickAtPosition(i * Sprite.SCALED_SIZE,
                        yUnit * Sprite.SCALED_SIZE));
                break;
            }
            if (gameMap.getWallsAndGrassAtPosition(i * Sprite.SCALED_SIZE,
                    yUnit * Sprite.SCALED_SIZE) instanceof Wall) {
                rightFlameInfo.setWallCheck(true);
                rightFlameInfo.setFlameLength(i - xUnit - 1);
                break;
            }
            if (gameMap.getItemAtPosition(i * Sprite.SCALED_SIZE,
                    yUnit * Sprite.SCALED_SIZE) instanceof IItem) {
                rightFlameInfo.setItemCheck(true);
                rightFlameInfo.setFlameLength(i - xUnit - 1);
                break;
            }
        }
    }

    public void makeRightFlame(int xUnit, int yUnit) {
        //Make right flame list
        if (rightFlameInfo.isBrickCheck() || rightFlameInfo.isWallCheck() || rightFlameInfo.isItemCheck()) {
            for (int i = 1; i <= rightFlameInfo.getFlameLength(); i++) {
                rightFlameList.add(new ItemFlames(xUnit + i,
                        yUnit, Sprite.explosion_horizontal.getImage()));
            }
        } else {
            for (int i = 1; i < bombLevel; i++) {
                rightFlameList.add(new ItemFlames(xUnit + i, yUnit, Sprite.explosion_horizontal.getImage()));
            }
            rightFlameList.add(new ItemFlames(xUnit + bombLevel, yUnit, Sprite.explosion_horizontal_right_last.getImage()));
        }
    }

    public BombStatus getBombStatus() {
        return bombStatus;
    }

    @Override
    public void update() {
        updateNearbyBombActivation();
        switch (bombStatus) {
            case WAIT:
                ++indexBombSprite;
                setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, indexBombSprite, 60).getImage());
                break;
            case WENTOFF:
                if (!Game.getInstance().getAudioController().isPlaying(AudioController.AudioType.BOMB_DESTROY)) {
                    Game.getInstance().getAudioController().playSoundEffect(AudioController.AudioType.BOMB_DESTROY);
                }
                if (wentOffPhrase == WentOffPhraseStatus.OPENING) {
                    ++indexBombSprite;
                    this.setImg(Sprite.movingSprite(Sprite.bomb_exploded,
                            Sprite.bomb_exploded1, Sprite.bomb_exploded2,
                            indexBombSprite, 10).getImage());

                    //Right flame opening animation update
                    if (rightFlameInfo.isBrickCheck() || rightFlameInfo.isWallCheck()
                            || rightFlameInfo.isItemCheck() || rightFlameInfo.isBombCheck()) {
                        for (int i = 0; i < rightFlameList.size() - rightFlameInfo.getIntersectionLength(); i++) {
                            rightFlameList.get(i).setImg(Sprite.movingSprite(Sprite.explosion_horizontal,
                                    Sprite.explosion_horizontal1, Sprite.explosion_horizontal2,
                                    indexBombSprite, 10).getImage());
                        }
                    } else {
                        for (int i = 0; i < rightFlameList.size() - 1; i++) {
                            rightFlameList.get(i).setImg(Sprite.movingSprite(Sprite.explosion_horizontal,
                                    Sprite.explosion_horizontal1, Sprite.explosion_horizontal2,
                                    indexBombSprite, 10).getImage());
                        }
                        rightFlameList.get(rightFlameList.size() - 1).setImg(Sprite.movingSprite(Sprite.explosion_horizontal_right_last,
                                Sprite.explosion_horizontal_right_last1,
                                Sprite.explosion_horizontal_right_last2, indexBombSprite, 10).getImage());
                    }

                    //Left flame opening animation update
                    if (leftFlameInfo.isBrickCheck() || leftFlameInfo.isWallCheck()
                            || leftFlameInfo.isItemCheck() || leftFlameInfo.isBombCheck()) {
                        for (int i = 0; i < leftFlameList.size() - leftFlameInfo.getIntersectionLength(); i++) {
                            leftFlameList.get(i).setImg(Sprite.movingSprite(Sprite.explosion_horizontal,
                                    Sprite.explosion_horizontal1, Sprite.explosion_horizontal2,
                                    indexBombSprite, 10).getImage());
                        }
                    } else {
                        for (int i = 0; i < leftFlameList.size() - 1; i++) {
                            leftFlameList.get(i).setImg(Sprite.movingSprite(Sprite.explosion_horizontal,
                                    Sprite.explosion_horizontal1, Sprite.explosion_horizontal2,
                                    indexBombSprite, 10).getImage());
                        }
                        leftFlameList.get(leftFlameList.size() - 1).setImg(Sprite.movingSprite(Sprite.explosion_horizontal_left_last,
                                Sprite.explosion_horizontal_left_last1,
                                Sprite.explosion_horizontal_left_last2, indexBombSprite, 10).getImage());
                    }

                    //Down flame opening animation update
                    if (downFlameInfo.isBrickCheck() || downFlameInfo.isWallCheck()
                            || downFlameInfo.isItemCheck() || downFlameInfo.isBombCheck()) {
                        for (int i = 0; i < downFlameList.size() - downFlameInfo.getIntersectionLength(); i++) {
                            downFlameList.get(i).setImg(Sprite.movingSprite(Sprite.explosion_vertical,
                                    Sprite.explosion_vertical1, Sprite.explosion_vertical2,
                                    indexBombSprite, 10).getImage());
                        }
                    } else {
                        for (int i = 0; i < downFlameList.size() - 1; i++) {
                            downFlameList.get(i).setImg(Sprite.movingSprite(Sprite.explosion_vertical,
                                    Sprite.explosion_vertical1, Sprite.explosion_vertical2,
                                    indexBombSprite, 10).getImage());
                        }
                        downFlameList.get(downFlameList.size() - 1).setImg(Sprite.movingSprite(Sprite.explosion_vertical_down_last,
                                Sprite.explosion_vertical_down_last1,
                                Sprite.explosion_vertical_down_last2, indexBombSprite, 10).getImage());
                    }

                    //Up flame opening animation update
                    if (upFlameInfo.isBrickCheck() || upFlameInfo.isWallCheck()
                            || upFlameInfo.isItemCheck() || upFlameInfo.isBombCheck()) {
                        for (int i = 0; i < upFlameList.size() - upFlameInfo.getIntersectionLength(); i++) {
                            upFlameList.get(i).setImg(Sprite.movingSprite(Sprite.explosion_vertical,
                                    Sprite.explosion_vertical1, Sprite.explosion_vertical2,
                                    indexBombSprite, 10).getImage());
                        }
                    } else {
                        for (int i = 0; i < upFlameList.size() - 1; i++) {
                            upFlameList.get(i).setImg(Sprite.movingSprite(Sprite.explosion_vertical,
                                    Sprite.explosion_vertical1, Sprite.explosion_vertical2,
                                    indexBombSprite, 10).getImage());
                        }
                        upFlameList.get(upFlameList.size() - 1).setImg(Sprite.movingSprite(Sprite.explosion_vertical_top_last,
                                Sprite.explosion_vertical_top_last1,
                                Sprite.explosion_vertical_top_last2, indexBombSprite, 10).getImage());
                    }

                    if (indexBombSprite == 10) {
                        wentOffPhrase = WentOffPhraseStatus.CLOSING;
                        indexBombSprite = 0;
                    }
                }

                if (wentOffPhrase == WentOffPhraseStatus.CLOSING) {
                    ++indexBombSprite;
                    this.setImg(Sprite.movingSprite(Sprite.bomb_exploded2, Sprite.bomb_exploded1,
                            Sprite.bomb_exploded, indexBombSprite, 10).getImage());

                    //Right flame closing animation update
                    if (rightFlameInfo.isBrickCheck() || rightFlameInfo.isWallCheck() ||
                            rightFlameInfo.isItemCheck() || rightFlameInfo.isBombCheck()) {
                        for (int i = 0; i < rightFlameList.size() - rightFlameInfo.getIntersectionLength(); i++) {
                            rightFlameList.get(i).setImg(Sprite.movingSprite(Sprite.explosion_horizontal2,
                                    Sprite.explosion_horizontal1, Sprite.explosion_horizontal,
                                    indexBombSprite, 10).getImage());
                        }
                        if (rightFlameInfo.isBrickCheck()) {
                            rightFlameInfo.getBrickExplosion().setImg(Sprite.movingSprite(Sprite.brick_exploded,
                                    Sprite.brick_exploded1, Sprite.brick_exploded2, indexBombSprite, 10).getImage());
                        }
                    } else {
                        for (int i = 0; i < rightFlameList.size() - 1; i++) {
                            rightFlameList.get(i).setImg(Sprite.movingSprite(Sprite.explosion_horizontal2,
                                    Sprite.explosion_horizontal1, Sprite.explosion_horizontal,
                                    indexBombSprite, 10).getImage());
                        }
                        rightFlameList.get(rightFlameList.size() - 1).setImg(Sprite.movingSprite(Sprite.explosion_horizontal_right_last2,
                                Sprite.explosion_horizontal_right_last1,
                                Sprite.explosion_horizontal_right_last, indexBombSprite, 10).getImage());
                    }

                    //Left flame closing animation update
                    if (leftFlameInfo.isBrickCheck() || leftFlameInfo.isWallCheck()
                            || leftFlameInfo.isItemCheck() || leftFlameInfo.isBombCheck()) {
                        for (int i = 0; i < leftFlameList.size() - leftFlameInfo.getIntersectionLength(); i++) {
                            leftFlameList.get(i).setImg(Sprite.movingSprite(Sprite.explosion_horizontal2,
                                    Sprite.explosion_horizontal1, Sprite.explosion_horizontal,
                                    indexBombSprite, 10).getImage());
                        }
                        if (leftFlameInfo.isBrickCheck()) {
                            leftFlameInfo.getBrickExplosion().setImg(Sprite.movingSprite(Sprite.brick_exploded,
                                    Sprite.brick_exploded1, Sprite.brick_exploded2, indexBombSprite, 10).getImage());
                        }
                    } else {
                        for (int i = 0; i < leftFlameList.size() - 1; i++) {
                            leftFlameList.get(i).setImg(Sprite.movingSprite(Sprite.explosion_horizontal2,
                                    Sprite.explosion_horizontal1, Sprite.explosion_horizontal,
                                    indexBombSprite, 10).getImage());
                        }
                        leftFlameList.get(leftFlameList.size() - 1).setImg(Sprite.movingSprite(Sprite.explosion_horizontal_left_last2,
                                Sprite.explosion_horizontal_left_last1,
                                Sprite.explosion_horizontal_left_last, indexBombSprite, 10).getImage());
                    }

                    //Down flame closing animation update
                    if (downFlameInfo.isBrickCheck() || downFlameInfo.isWallCheck()
                            || downFlameInfo.isItemCheck() || downFlameInfo.isBombCheck()) {
                        for (int i = 0; i < downFlameList.size() - downFlameInfo.getIntersectionLength(); i++) {
                            downFlameList.get(i).setImg(Sprite.movingSprite(Sprite.explosion_vertical2,
                                    Sprite.explosion_vertical1, Sprite.explosion_vertical,
                                    indexBombSprite, 10).getImage());
                        }
                        if (downFlameInfo.isBrickCheck()) {
                            downFlameInfo.getBrickExplosion().setImg(Sprite.movingSprite(Sprite.brick_exploded,
                                    Sprite.brick_exploded1, Sprite.brick_exploded2, indexBombSprite, 10).getImage());
                        }
                    } else {
                        for (int i = 0; i < downFlameList.size() - 1; i++) {
                            downFlameList.get(i).setImg(Sprite.movingSprite(Sprite.explosion_vertical2,
                                    Sprite.explosion_vertical1, Sprite.explosion_vertical,
                                    indexBombSprite, 10).getImage());
                        }
                        downFlameList.get(downFlameList.size() - 1).setImg(Sprite.movingSprite(Sprite.explosion_vertical_down_last2,
                                Sprite.explosion_vertical_down_last1,
                                Sprite.explosion_vertical_down_last, indexBombSprite, 10).getImage());
                    }

                    //Up flame closing animation update
                    if (upFlameInfo.isBrickCheck() || upFlameInfo.isWallCheck()
                            || upFlameInfo.isItemCheck() || upFlameInfo.isBombCheck()) {
                        for (int i = 0; i < upFlameList.size() - upFlameInfo.getIntersectionLength(); i++) {
                            upFlameList.get(i).setImg(Sprite.movingSprite(Sprite.explosion_vertical2,
                                    Sprite.explosion_vertical1, Sprite.explosion_vertical,
                                    indexBombSprite, 10).getImage());
                        }
                        if (upFlameInfo.isBrickCheck()) {
                            upFlameInfo.getBrickExplosion().setImg(Sprite.movingSprite(Sprite.brick_exploded,
                                    Sprite.brick_exploded1, Sprite.brick_exploded2, indexBombSprite, 10).getImage());
                        }
                    } else {
                        for (int i = 0; i < upFlameList.size() - 1; i++) {
                            upFlameList.get(i).setImg(Sprite.movingSprite(Sprite.explosion_vertical2,
                                    Sprite.explosion_vertical1, Sprite.explosion_vertical,
                                    indexBombSprite, 10).getImage());
                        }
                        upFlameList.get(upFlameList.size() - 1).setImg(Sprite.movingSprite(Sprite.explosion_vertical_top_last2,
                                Sprite.explosion_vertical_top_last1,
                                Sprite.explosion_vertical_top_last, indexBombSprite, 10).getImage());
                    }

                    if (indexBombSprite == 10) {
                        bombStatus = BombStatus.DISAPEAR;
                        gameMap.getBricks().remove(rightFlameInfo.getBrickExplosion());
                        gameMap.getBricks().remove(leftFlameInfo.getBrickExplosion());
                        gameMap.getBricks().remove(downFlameInfo.getBrickExplosion());
                        gameMap.getBricks().remove(upFlameInfo.getBrickExplosion());
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
                for (int i = 0; i < rightFlameList.size() - rightFlameInfo.getIntersectionLength(); i++)
                    rightFlameList.get(i).render(gc);

                for (int i = 0; i < leftFlameList.size() - leftFlameInfo.getIntersectionLength(); i++)
                    leftFlameList.get(i).render(gc);

                for (int i = 0; i < upFlameList.size() - upFlameInfo.getIntersectionLength(); i++)
                    upFlameList.get(i).render(gc);

                for (int i = 0; i < downFlameList.size() - downFlameInfo.getIntersectionLength(); i++)
                    downFlameList.get(i).render(gc);

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

    public void setBombStatus(BombStatus bombStatus) {
        this.bombStatus = bombStatus;
    }

    public void cancelTimer() {
        timer.cancel();
    }

    public void setWentOffPhrase(WentOffPhraseStatus wentOffPhrase) {
        this.wentOffPhrase = wentOffPhrase;
    }

    public WentOffPhraseStatus getWentOffPhrase() {
        return wentOffPhrase;
    }

    public void setIndexBombSprite(int indexBombSprite) {
        this.indexBombSprite = indexBombSprite;
    }

    public int getIndexBombSprite() {
        return indexBombSprite;
    }

    public List<Entity> getDownFlameList() {
        return downFlameList;
    }

    public List<Entity> getLeftFlameList() {
        return leftFlameList;
    }

    public List<Entity> getUpFlameList() {
        return upFlameList;
    }

    public List<Entity> getRightFlameList() {
        return rightFlameList;
    }
}
