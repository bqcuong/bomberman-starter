package uet.oop.bomberman.entities.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Bomb extends Entity{

    private int timer = 2; // hen gio bom no
    private int level = 1; //cap do cua bomb va flame

    private int maxSizeTop = 0;
    private int maxSizeDown = 0;
    private int maxSizeLeft = 0;
    private int maxSizeRight = 0;

    public Bomb(int x, int y, Image img) {
        super( x, y, img);
        super.state = "live";
    }

    public void update() {

    }

    public void update(int time) {
        if(!this.getState().equals("dead")) this.setImg(Sprite.movingSprite(Sprite.bomb_2,Sprite.bomb_1,Sprite.bomb,time,3).getFxImage());
        else
            Bomb.super.img.cancel();
    }

    public void startCount() {
        Timer temp = new Timer();
        temp.schedule(new TimerTask() {
            @Override
            public void run() {
                setState("dead");
                temp.cancel();
            }
        },timer * 1000,1);
    }

    public void explode(List<Entity> stillObject, int HEIGHT, List<Flame> aroundObject) {
        if(this.getState().equals("dead")) {
            maxSizeTop = 0;
            maxSizeRight = 0;
            maxSizeDown = 0;
            maxSizeLeft = 0;
            int bombX = this.getX()/Sprite.SCALED_SIZE;
            int bombY = this.getY()/Sprite.SCALED_SIZE;
            aroundObject.add(new Flame(bombX, bombY, Sprite.bomb_exploded1.getFxImage()));
            for(int i=1; i <= this.getLevel(); i++) {
                if(stillObject.get(bombX * HEIGHT + bombY - i) instanceof Grass) maxSizeTop++;
                else break;
            }
            for(int i=1; i <= this.getLevel(); i++) {
                if(stillObject.get(bombX * HEIGHT + bombY + i) instanceof Grass) maxSizeDown++;
                else break;
            }
            for(int i=1; i <= this.getLevel(); i++) {
                if(stillObject.get(bombX * HEIGHT + bombY - i * HEIGHT) instanceof Grass) maxSizeLeft++;
                else break;
            }
            for(int i=1; i <= this.getLevel(); i++) {
                if(stillObject.get(bombX * HEIGHT + bombY + i * HEIGHT) instanceof Grass) maxSizeRight++;
                else break;
            }
            for(int i = maxSizeTop ; i >= 1; i--) {
                if(i == maxSizeTop) aroundObject.add(new Flame(bombX, bombY - i, Sprite.explosion_vertical_top_last1.getFxImage()));
                else aroundObject.add(new Flame(bombX, bombY - i, Sprite.explosion_vertical1.getFxImage()));
            }
            for(int i = maxSizeDown ; i >= 1; i--) {
                if(i == maxSizeDown) aroundObject.add(new Flame(bombX, bombY + i, Sprite.explosion_vertical_down_last1.getFxImage()));
                else aroundObject.add(new Flame(bombX, bombY + i, Sprite.explosion_vertical1.getFxImage()));
            }
            for(int i = maxSizeLeft ; i >= 1; i--) {
                if(i == maxSizeLeft) aroundObject.add(new Flame(bombX - i, bombY, Sprite.explosion_horizontal_left_last1.getFxImage()));
                else aroundObject.add(new Flame(bombX - i, bombY, Sprite.explosion_horizontal1.getFxImage()));
            }
            for(int i = maxSizeRight ; i >= 1; i--) {
                if(i == maxSizeRight) aroundObject.add(new Flame(bombX + i, bombY, Sprite.explosion_horizontal_right_last1.getFxImage()));
                else aroundObject.add(new Flame(bombX + i, bombY, Sprite.explosion_horizontal1.getFxImage()));
            }
        }
    }

    public void collideWithAnotherBomb(List<Bomb> bomb, int HEIGHT, List<Flame> aroundObject, List<Entity> stillObject) {
        if(state.equals("dead")) {
            for(Bomb g : bomb) {
                if(!g.getState().equals("dead")) {
                    if (g.getX() <= this.getX() + maxSizeRight * Sprite.SCALED_SIZE && g.getX() >= this.getX() - maxSizeLeft * Sprite.SCALED_SIZE && g.getY() == this.getY()) {
                        g.setState("dead");
                        g.explode(stillObject, HEIGHT, aroundObject);
                        g.collideWithBrick(stillObject, HEIGHT);
                    }
                    if (g.getY() <= this.getY() + maxSizeDown * Sprite.SCALED_SIZE && g.getY() >= this.getY() - maxSizeTop * Sprite.SCALED_SIZE && g.getX() == this.getX()) {
                        g.setState("dead");
                        g.explode(stillObject, HEIGHT, aroundObject);
                        g.collideWithBrick(stillObject, HEIGHT);
                    }
                }
            }
        }
    }

    public void collideWithBrick(List<Entity> stillObjects, int HEIGHT) {
        int bombX = this.getX()/Sprite.SCALED_SIZE;
        int bombY = this.getY()/Sprite.SCALED_SIZE;
        if(maxSizeTop<level) {
            if(stillObjects.get(bombX * HEIGHT + bombY - maxSizeTop -1) instanceof Brick) {
                stillObjects.get(bombX * HEIGHT + bombY - maxSizeTop -1).setState("dead");
            }
        }
        if(maxSizeDown<level) {
            if(stillObjects.get(bombX * HEIGHT + bombY + maxSizeDown +1) instanceof Brick) {
                stillObjects.get(bombX * HEIGHT + bombY + maxSizeDown +1).setState("dead");
                Timer temp = new Timer();
                temp.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        stillObjects.set(bombX * HEIGHT + bombY + maxSizeDown +1, new Grass(bombX,bombY+maxSizeDown+1,Sprite.grass.getFxImage()));
                        temp.cancel();
                    }
                },200,1);
            }
        }
        if(maxSizeLeft<level) {
            if(stillObjects.get(bombX * HEIGHT + bombY - (maxSizeLeft +1) * HEIGHT) instanceof Brick) {
                stillObjects.get(bombX * HEIGHT + bombY - (maxSizeLeft +1) * HEIGHT).setState("dead");
                Timer temp = new Timer();
                temp.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        stillObjects.set(bombX * HEIGHT + bombY - (maxSizeLeft +1) * HEIGHT, new Grass(bombX-maxSizeLeft-1,bombY,Sprite.grass.getFxImage()));
                        temp.cancel();
                    }
                },200,1);
            }
        }
        if(maxSizeRight<level) {
            if(stillObjects.get(bombX * HEIGHT + bombY + (maxSizeRight +1) * HEIGHT) instanceof Brick) {
                stillObjects.get(bombX * HEIGHT + bombY + (maxSizeRight +1) * HEIGHT).setState("dead");
                Timer temp = new Timer();
                temp.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        stillObjects.set(bombX * HEIGHT + bombY + (maxSizeRight +1) * HEIGHT, new Grass(bombX+maxSizeRight+1,bombY,Sprite.grass.getFxImage()));
                        temp.cancel();
                    }
                },200,1);
            }
        }
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxSizeTop() {
        return maxSizeTop;
    }

    public int getMaxSizeDown() {
        return maxSizeDown;
    }

    public int getMaxSizeLeft() {
        return maxSizeLeft;
    }

    public int getMaxSizeRight() {
        return maxSizeRight;
    }
}