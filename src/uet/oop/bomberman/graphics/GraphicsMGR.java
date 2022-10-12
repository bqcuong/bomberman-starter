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

    public void renderMap(GameMap gameMap) {
        for (int i = 0; i < gameMap.getMap().size(); i++) {
            List<Entity> tempMap = gameMap.getMap().get(i);
            tempMap.forEach(g -> g.render(gc));
        }
    }

    public void renderItem(GameMap gameMap) {
        gameMap.getItem().forEach(g -> g.render(gc));
    }

    public void renderBomber(GameMap gameMap) {
        gameMap.getEntities().forEach(g -> g.render(gc));
    }

    public void render(GameMap gameMap) {
        renderMap(gameMap);
        renderItem(gameMap);
        renderBomber(gameMap);
    }
}
