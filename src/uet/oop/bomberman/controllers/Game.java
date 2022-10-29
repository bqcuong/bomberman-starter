package uet.oop.bomberman.controllers;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.LifeStatus;
import uet.oop.bomberman.events.KeyboardEvent;
import uet.oop.bomberman.graphics.GameMap;
import uet.oop.bomberman.graphics.GraphicsMGR;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.scenes.LoseGameScene;
import uet.oop.bomberman.scenes.OpeningScene;
import uet.oop.bomberman.scenes.InGameScene;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private int currentLevel = 1;
    public final static int MAX_LEVEL = 3;
    // Tao root container

    private ItemInfo itemInfo;

    private GameStatus gameStatus;

    private int bomberLeft;
    private int bomberScore;

    private List<GameMap> gameMapList;
    // Tao scene
    private InGameScene inGameScene;
    private OpeningScene openingScene;

    private boolean isPassLevel = false;

    private LoseGameScene loseGameScene;

    public AudioController audioController = AudioController.getInstance();


    // Tao keyboard event
    private KeyboardEvent keyboardEvent;
    // Tao Canvas.
    public GraphicsContext gc;
    public Stage stage;

    private Font font;
    private static final Game instance = new Game();

    public static Game getInstance() {
        return instance;
    }

    private Game() {
    }

    public ItemInfo getItemInfo() {
        return itemInfo;
    }

    public void createGame(Stage stage) {
        try {
            font = Font.loadFont("file:res/PixelEmulator-xq08.ttf", 80);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        this.stage = stage;
        init();
        gameStatus = GameStatus.OPENING;
        stage.setScene(openingScene.getOpeningScene());
    }

    public void increaseBomberLeft() {
        bomberLeft++;
    }

    public int getBomberLeft() {
        return bomberLeft;
    }

    public void decreaseBomberLeft() {
        bomberLeft--;
    }

    public int getBomberScore() {
        return bomberScore;
    }

    public void setBomberScore(int bomberScore) {
        this.bomberScore = bomberScore;
    }

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            render();
            update();
        }
    };


    public void createMapList() {
        for (int i = 1; i <= MAX_LEVEL; i++) {
            GameMap gameMap = new GameMap();
            gameMap.createMap(i, keyboardEvent);
            gameMapList.add(gameMap);
        }
    }

    public void nextLevel() {
        if (currentLevel + 1 <= MAX_LEVEL &&
                !gameStatus.equals(GameStatus.OPENING)&&
                !gameStatus.equals(GameStatus.PLAYING)) {
            currentLevel++;
            increaseBomberLeft();
            getCurrentGameMap().getPlayer().initItemInfo(getItemInfo());
            System.out.println(currentLevel);

            gameStatus = GameStatus.OPENING;
        }
    }

    public void render() {
        clrscr(inGameScene.getCanvas());
        //Render map, entities
        render(getCurrentGameMap());
    }

    public GameMap getCurrentGameMap() {
        return gameMapList.get(currentLevel - 1);
    }

    public void update() {

        if (gameStatus.equals(GameStatus.NEXT_LEVEL_BRIDGE)) {
            if (audioController.isPlaying(AudioController.AudioType.GAME_BGM)) audioController.stopBgm();

            if (!Game.getInstance().getAudioController().isPlaying(AudioController.AudioType.PLAYER_WIN)) {
                Game.getInstance().getAudioController().playSoundEffect(AudioController.AudioType.PLAYER_WIN);
            }
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                int count = 6;

                @Override
                public void run() {
                    if (count > 0) {
                        count--;
                    } else {
                        timer.cancel();
                        nextLevel();
                    }
                }
            };
            timer.schedule(timerTask, 0, 1000);
        }
        if (gameStatus.equals(GameStatus.OPENING)) {
            if (audioController.isPlaying(AudioController.AudioType.GAME_BGM)) audioController.stopBgm();

            if (!audioController.isPlaying(AudioController.AudioType.STAGE_START)) {
                audioController.playSoundEffect(AudioController.AudioType.STAGE_START);
            }

            stage.setScene(openingScene.getOpeningScene());
            openingScene.updateStageNumber(currentLevel);
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                int count = 3;

                @Override
                public void run() {
                    if (count > 0) {
                        count--;
                    } else {
                        timer.cancel();
                        gameStatus = GameStatus.PLAYING;
                    }
                }
            };
            timer.schedule(timerTask, 0, 1000);
        }
        if (gameStatus.equals(GameStatus.PLAYING)) {
            if (!audioController.isPlaying(AudioController.AudioType.GAME_BGM)) {
                audioController.restartBgm();
            }

            stage.setScene(inGameScene.getInGameScene());
            getCurrentGameMap().update();
            getCurrentGameMap().getPlayer().updatePauseHandle();
            inGameScene.getScoreTitle().update(getBomberLeft(),
                    getBomberScore(), inGameScene.getInGameScene());
            inGameScene.getPausedText().setVisible(false);
        } else if (gameStatus.equals(GameStatus.PAUSED)) {
            getCurrentGameMap().getPlayer().updatePauseHandle();
            inGameScene.getPausedText().setVisible(true);
        }

        if (gameStatus.equals(GameStatus.LOSE)) {
            if (audioController.isPlaying(AudioController.AudioType.GAME_BGM)) audioController.stopBgm();
            if (!audioController.isPlaying(AudioController.AudioType.PLAYER_LOSE)) {
                audioController.playSoundEffect(AudioController.AudioType.PLAYER_LOSE);
            }
            stage.setScene(LoseGameScene.getInstance().getLoseGameScene());
            init();
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                int count = 5;

                @Override
                public void run() {
                    if (count > 0) {
                        count--;
                    } else {
                        timer.cancel();
                        gameStatus = GameStatus.OPENING;
                    }
                }
            };
            timer.schedule(timerTask, 0, 1000);
        }
    }

    public void renderWallAndGrass(GameMap gameMap) {
        for (int i = 0; i < gameMap.getWallAndGrass().size(); i++) {
            List<Entity> tempMap = gameMap.getWallAndGrass().get(i);
            tempMap.forEach(g -> g.render(gc));
        }
    }

    public void renderItems(GameMap gameMap) {
        gameMap.getItems().forEach(g -> g.render(gc));
    }

    public void renderBomber(GameMap gameMap) {
        gameMap.getPlayer().render(gc);
    }

    public void renderEnemies(GameMap gameMap) {
        gameMap.getEnemies().forEach(g -> g.render(gc));
    }

    public void renderBricks(GameMap gameMap) {
        gameMap.getBricks().forEach(g -> g.render(gc));
    }

    public void renderBombList(GameMap gameMap) {
        for (Entity element : gameMap.getBombList()) {
            element.render(gc);
        }
    }

    public void render(GameMap gameMap) {
        renderWallAndGrass(gameMap);
        renderItems(gameMap);
        renderBricks(gameMap);
        renderBombList(gameMap);
        renderEnemies(gameMap);
        renderBomber(gameMap);
    }

    public void start() {
        timer.start();
    }

    public void clrscr(Canvas canvas) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void init() {
        currentLevel = 1;
        bomberLeft = Bomber.BOMBER_LEFT_DEFAULT;
        bomberScore = Bomber.BOMBER_SCORE_DEFAULT;
        itemInfo = new ItemInfo();

        inGameScene = InGameScene.getInstance();
        inGameScene.createSceneInGame(GraphicsMGR.WIDTH, GraphicsMGR.HEIGHT, font);

        keyboardEvent = new KeyboardEvent(inGameScene.getInGameScene());

        openingScene = OpeningScene.getInstance();
        openingScene.createOpeningScene(GraphicsMGR.WIDTH, GraphicsMGR.HEIGHT, font);

        loseGameScene = LoseGameScene.getInstance();
        loseGameScene.createLoseGameScene(GraphicsMGR.WIDTH, GraphicsMGR.HEIGHT, font);

        // Tao map
        gameMapList = new ArrayList<>(MAX_LEVEL);
        createMapList();

        gc = inGameScene.getGraphicContext();
    }

    public AudioController getAudioController() {
        return audioController;
    }
}
