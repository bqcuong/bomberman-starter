package uet.oop.bomberman.entities;

public class Hitbox {
    private int x;
    private int y;
    private int width;
    private int height;

    public Hitbox(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Getter method for x coordination.
     * @return int x
     */
    public int getX() {
        return x;
    }

    /**
     * Getter method for y coordination.
     */
    public int getY() {
        return y;
    }

    /**
     * Getter method for width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter method for height.
     */
    public int getHeight() {
        return height;
    }
}
