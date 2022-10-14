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

    private int bombLevel = 0;

    private List<Entity> flameUp = new ArrayList<>();
    private List<Entity> flameDown = new ArrayList<>();

    //Left flame
    private List<Entity> flameLeft = new ArrayList<>();
    private boolean brickLeftcheck = false;
    private boolean wallLeftCheck = false;
    private boolean itemLeftCheck = false;
    private int flameLeftLength = 0;
    private Entity brickLeftExplosion = null;

    //Right flame
    private List<Entity> flameRight = new ArrayList<>();
    private boolean brickRightcheck = false;
    private boolean wallRightCheck = false;
    private boolean itemRightCheck = false;
    private int flameRightLength = 0;
    private Entity brickRightExplosion = null;

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

    public Bomb(int xUnit, int yUnit, Image img, int bombLevel, GameMap gameMap) {
        super(xUnit, yUnit, img);
        this.gameMap = gameMap;
        this.bombLevel = bombLevel;
        timer.schedule(timerTask, 0, 1000);

        //Right check
        flameRightLength = bombLevel;
        for (int i = xUnit + 1; i != xUnit + bombLevel + 1; i++) {
            if (gameMap.getBrickAtPosition(i * Sprite.SCALED_SIZE,
                    yUnit * Sprite.SCALED_SIZE) instanceof Brick) {
                brickRightcheck = true;
                flameRightLength = i - xUnit - 1;
                brickRightExplosion = gameMap.getBrickAtPosition(i * Sprite.SCALED_SIZE,
                        yUnit * Sprite.SCALED_SIZE);
                break;
            }
            if (gameMap.getWallsAndGrassAtPosition(i * Sprite.SCALED_SIZE,
                    yUnit * Sprite.SCALED_SIZE) instanceof Wall) {
                wallRightCheck = true;
                flameRightLength = i - xUnit - 1;
                break;
            }
            if (gameMap.getItemAtPosition(i * Sprite.SCALED_SIZE,
                    yUnit * Sprite.SCALED_SIZE) instanceof IItem) {
                itemRightCheck = true;
                flameRightLength = i - xUnit - 1;
                break;
            }
        }
        if (wallRightCheck || itemRightCheck || brickRightcheck) {
            for (int i = 1; i <= flameRightLength; i++) {
                flameRight.add(new ItemFlames(xUnit + i,
                        yUnit, Sprite.explosion_horizontal.getImage()));
            }
        } else {
            for (int i = 1; i < bombLevel; i++) {
                flameRight.add(new ItemFlames(xUnit + i, yUnit, Sprite.explosion_horizontal.getImage()));
            }
            flameRight.add(new ItemFlames(xUnit + bombLevel, yUnit, Sprite.explosion_horizontal_right_last.getImage()));
        }

        //Left
        flameLeftLength = bombLevel;
        for (int i = xUnit - 1; i != xUnit - bombLevel - 1; i--) {
            if (gameMap.getBrickAtPosition(i * Sprite.SCALED_SIZE,
                    yUnit * Sprite.SCALED_SIZE) instanceof Brick) {
                brickLeftcheck = true;
                flameLeftLength = xUnit - i - 1;
                brickLeftExplosion = gameMap.getBrickAtPosition(i * Sprite.SCALED_SIZE,
                        yUnit * Sprite.SCALED_SIZE);
                break;
            }
            if (gameMap.getWallsAndGrassAtPosition(i * Sprite.SCALED_SIZE,
                    yUnit * Sprite.SCALED_SIZE) instanceof Wall) {
                brickLeftcheck = true;
                flameLeftLength = xUnit - i - 1;
                break;
            }
            if (gameMap.getItemAtPosition(i * Sprite.SCALED_SIZE,
                    yUnit * Sprite.SCALED_SIZE) instanceof IItem) {
                brickLeftcheck = true;
                flameLeftLength = xUnit - i - 1;
                break;
            }
        }
        if (wallLeftCheck || itemLeftCheck || brickLeftcheck) {
            for (int i = 1; i <= flameLeftLength; i++) {
                flameLeft.add(new ItemFlames(xUnit - i,
                        yUnit, Sprite.explosion_horizontal.getImage()));
            }
        } else {
            for (int i = 1; i < bombLevel; i++) {
                flameLeft.add(new ItemFlames(xUnit - i, yUnit, Sprite.explosion_horizontal.getImage()));
            }
            flameLeft.add(new ItemFlames(xUnit - bombLevel, yUnit, Sprite.explosion_horizontal_left_last.getImage()));
        }
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
                    this.setImg(Sprite.movingSprite(Sprite.bomb_exploded,
                            Sprite.bomb_exploded1, Sprite.bomb_exploded2,
                            indexBombSprite, 10).getImage());

                    //Right flame opening
                    if (wallRightCheck || itemRightCheck || brickRightcheck) {
                        for (Entity element : flameRight) {
                            element.setImg(Sprite.movingSprite(Sprite.explosion_horizontal,
                                    Sprite.explosion_horizontal1, Sprite.explosion_horizontal2,
                                    indexBombSprite, 10).getImage());
                        }
                    } else {
                        for (int i = 0; i < flameRight.size() - 1; i++) {
                            flameRight.get(i).setImg(Sprite.movingSprite(Sprite.explosion_horizontal,
                                    Sprite.explosion_horizontal1, Sprite.explosion_horizontal2,
                                    indexBombSprite, 10).getImage());
                        }
                        flameRight.get(flameRight.size() - 1).setImg(Sprite.movingSprite(Sprite.explosion_horizontal_right_last,
                                Sprite.explosion_horizontal_right_last1,
                                Sprite.explosion_horizontal_right_last2, indexBombSprite, 10).getImage());
                    }

                    //Left flame opening
                    if (wallLeftCheck || itemLeftCheck || brickLeftcheck) {
                        for (Entity element : flameLeft) {
                            element.setImg(Sprite.movingSprite(Sprite.explosion_horizontal,
                                    Sprite.explosion_horizontal1, Sprite.explosion_horizontal2,
                                    indexBombSprite, 10).getImage());
                        }
                    } else {
                        for (int i = 0; i < flameLeft.size() - 1; i++) {
                            flameLeft.get(i).setImg(Sprite.movingSprite(Sprite.explosion_horizontal,
                                    Sprite.explosion_horizontal1, Sprite.explosion_horizontal2,
                                    indexBombSprite, 10).getImage());
                        }
                        flameLeft.get(flameLeft.size() - 1).setImg(Sprite.movingSprite(Sprite.explosion_horizontal_left_last,
                                Sprite.explosion_horizontal_left_last1,
                                Sprite.explosion_horizontal_left_last2, indexBombSprite, 10).getImage());
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

                    //Right flame closing
                    if (wallRightCheck || itemRightCheck || brickRightcheck) {
                        for (Entity element : flameRight) {
                            element.setImg(Sprite.movingSprite(Sprite.explosion_horizontal2,
                                    Sprite.explosion_horizontal1, Sprite.explosion_horizontal,
                                    indexBombSprite, 10).getImage());
                        }
                        if (brickRightcheck) {
                            brickRightExplosion.setImg(Sprite.movingSprite(Sprite.brick_exploded,
                                    Sprite.brick_exploded1, Sprite.brick_exploded2, indexBombSprite, 10).getImage());
                        }
                    } else {
                        for (int i = 0; i < flameRight.size() - 1; i++) {
                            flameRight.get(i).setImg(Sprite.movingSprite(Sprite.explosion_horizontal2,
                                    Sprite.explosion_horizontal1, Sprite.explosion_horizontal,
                                    indexBombSprite, 10).getImage());
                        }
                        flameRight.get(flameRight.size() - 1).setImg(Sprite.movingSprite(Sprite.explosion_horizontal_right_last2,
                                Sprite.explosion_horizontal_right_last1,
                                Sprite.explosion_horizontal_right_last, indexBombSprite, 10).getImage());
                    }

                    //Left flame closing
                    if (wallLeftCheck || itemLeftCheck || brickLeftcheck) {
                        for (Entity element : flameLeft) {
                            element.setImg(Sprite.movingSprite(Sprite.explosion_horizontal2,
                                    Sprite.explosion_horizontal1, Sprite.explosion_horizontal,
                                    indexBombSprite, 10).getImage());
                        }
                        if (brickLeftcheck) {
                            brickLeftExplosion.setImg(Sprite.movingSprite(Sprite.brick_exploded,
                                    Sprite.brick_exploded1, Sprite.brick_exploded2, indexBombSprite, 10).getImage());
                        }
                    } else {
                        for (int i = 0; i < flameLeft.size() - 1; i++) {
                            flameLeft.get(i).setImg(Sprite.movingSprite(Sprite.explosion_horizontal2,
                                    Sprite.explosion_horizontal1, Sprite.explosion_horizontal,
                                    indexBombSprite, 10).getImage());
                        }
                        flameLeft.get(flameLeft.size() - 1).setImg(Sprite.movingSprite(Sprite.explosion_horizontal_left_last2,
                                Sprite.explosion_horizontal_left_last1,
                                Sprite.explosion_horizontal_left_last, indexBombSprite, 10).getImage());
                    }

                    if (indexBombSprite == 10) {
                        bombStatus = BombStatus.DISAPEAR;
                        gameMap.getBricks().remove(brickRightExplosion);
                        gameMap.getBricks().remove(brickLeftExplosion);
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
                for (Entity element : flameRight) {
                    element.render(gc);
                }
                for (Entity element : flameLeft) {
                    element.render(gc);
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
}
