package uet.oop.bomberman.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Entity;


import java.util.List;

public class GraphicsMGR {
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    public GraphicsContext gc;

    public GraphicsMGR(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public void clrscr(Canvas canvas) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
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

    public void renderBricks(GameMap gameMap) {
        gameMap.getBricks().forEach(g -> g.render(gc));
    }

    public void render(GameMap gameMap) {
        renderWallAndGrass(gameMap);
        renderItems(gameMap);
        renderBricks(gameMap);
        renderBomber(gameMap);
    }
}
