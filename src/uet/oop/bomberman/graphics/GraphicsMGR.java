package uet.oop.bomberman.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Entity;


import java.util.List;

public class GraphicsMGR {
    public static final int WIDTH = 31;
    public static final int HEIGHT = 15;

    public GraphicsContext gc;

    public GraphicsMGR(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

}
