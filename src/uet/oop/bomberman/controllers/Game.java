package uet.oop.bomberman.controllers;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.objects.ScoreTitle;
import uet.oop.bomberman.events.KeyboardEvent;
import uet.oop.bomberman.graphics.GameMap;
import uet.oop.bomberman.graphics.GraphicsMGR;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int currentLevel = 1;
    public final static int MAX_LEVEL = 3;
    // Tao root container
    private Group root = new Group();

    private ItemInfo itemInfo;

    private GameStatus gameStatus;

    private int bomberLeft;
    private int bomberScore;

    private List<GameMap> gameMapList;
    // Tao scene
    private Scene sceneInGame;

    // Tao keyboard event
    private KeyboardEvent keyboardEvent;
    private ScoreTitle scoreTitle;
    // Tao Canvas.
    public Canvas canvas;
    public GraphicsContext gc;
    public Stage stage;

    private Text pausedText;

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
        bomberLeft = 2;
        bomberScore = 0;
        itemInfo = new ItemInfo();
        gameStatus = GameStatus.PLAYING;

        canvas = new Canvas(Sprite.SCALED_SIZE * GraphicsMGR.WIDTH, Sprite.SCALED_SIZE * GraphicsMGR.HEIGHT);
        gc = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);

        scoreTitle = ScoreTitle.getInstance();
        scoreTitle.createScoreTitle(root, font);
        sceneInGame = new Scene(root);
        keyboardEvent = new KeyboardEvent(sceneInGame);

        // Tao map
        gameMapList = new ArrayList<>(MAX_LEVEL);
        createMapList();

        pausedText = new Text("PAUSED");
        pausedText.setFill(Color.YELLOW);
        pausedText.setStroke(Color.DIMGRAY);
        pausedText.setStrokeWidth(3);
        pausedText.setFont(font);
        pausedText.setX((Sprite.SCALED_SIZE * GraphicsMGR.WIDTH
                - pausedText.getBoundsInLocal().getWidth()) / 2);
        pausedText.setY((Sprite.SCALED_SIZE * GraphicsMGR.HEIGHT
                - pausedText.getBoundsInLocal().getHeight()) / 2 + Sprite.SCALED_SIZE);
        pausedText.setVisible(false);

        root.getChildren().add(pausedText);


        stage.setScene(sceneInGame);
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
        if (currentLevel + 1 <= MAX_LEVEL) {
            currentLevel++;
        }
    }

    public void render() {
        clrscr(canvas);
        //Render map, entities
        render(getCurrentGameMap());
    }

    public GameMap getCurrentGameMap() {
        return gameMapList.get(currentLevel - 1);
    }

    public void update() {
        if (gameStatus.equals(GameStatus.PLAYING)) {
            getCurrentGameMap().update();
            getCurrentGameMap().getPlayer().updatePauseHandle();
            scoreTitle.update(getBomberLeft(),
                    getBomberScore(), sceneInGame);
            pausedText.setVisible(false);
        } else if (gameStatus.equals(GameStatus.PAUSE)) {
            getCurrentGameMap().getPlayer().updatePauseHandle();
            pausedText.setVisible(true);
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
}
