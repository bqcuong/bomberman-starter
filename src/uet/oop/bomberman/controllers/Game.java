package uet.oop.bomberman.controllers;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.objects.ScoreTitle;
import uet.oop.bomberman.events.KeyboardEvent;
import uet.oop.bomberman.graphics.GameMap;
import uet.oop.bomberman.graphics.GraphicsMGR;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class Game {
    // Tao Canvas.
    public GameMap gameMap;
    // Tao root container
    Group root = new Group();

    // Tao scene
    Scene sceneInGame;

    // Tao keyboard event
    KeyboardEvent keyboardEvent;
    ScoreTitle scoreTitle;
    public Canvas canvas;
    public GraphicsContext gc;
    public Stage stage;

    private static final Game instance = new Game();

    public static Game getInstance() {
        return instance;
    }

    private Game() {
    }

    public void createGame(Stage stage) {
        this.stage = stage;
        canvas = new Canvas(Sprite.SCALED_SIZE * GraphicsMGR.WIDTH, Sprite.SCALED_SIZE * GraphicsMGR.HEIGHT);
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        scoreTitle = ScoreTitle.getInstance();
        scoreTitle.createScoreTitle(root);
        sceneInGame = new Scene(root);
        keyboardEvent = new KeyboardEvent(sceneInGame);
        // Tao map
        gameMap = new GameMap();
        gameMap.createMap(1, keyboardEvent);
        stage.setScene(sceneInGame);
    }

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            render();
            update();
        }
    };

    public void render() {
        clrscr(canvas);
        //Render map, entities
        render(gameMap);
    }


    public void update() {
        gameMap.update();
        scoreTitle.update(gameMap.getPlayer().getLeft(), gameMap.getPlayer().getScore(), sceneInGame);
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
}
