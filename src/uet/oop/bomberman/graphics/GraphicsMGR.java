package uet.oop.bomberman.graphics;

import com.sun.prism.Graphics;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Map;


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

    public void renderMap(Map map) {
        for (int i = 0; i < map.getMap().size(); i++) {
            List<Entity> tempMap = map.getMap().get(i);
            tempMap.forEach(g -> g.render(gc));
        }
    }

    public void renderBomber(Map map) {
        map.getEntities().forEach(g -> g.render(gc));
    }

    public void renderStillObjects(Map map) {
        map.getStillObjects().forEach(g -> g.render(gc));
    }

    public void render(Map map) {
        renderMap(map);
        renderStillObjects(map);
        renderBomber(map);
    }
}
