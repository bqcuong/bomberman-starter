package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Brick extends Entity {

    private int cnt = 0;

    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if(state.equals("dead")) {
            this.img = movingSprite(Sprite.brick_exploded,Sprite.brick_exploded1,Sprite.brick_exploded2,cnt ++,3).getFxImage();
        }
    }

    public void destroyBrick(List<Entity> stillObjects,int HEIGHT) {
        int realX = this.x/Sprite.SCALED_SIZE;
        int realY = this.y/Sprite.SCALED_SIZE;
        Timer temp = new Timer();
        temp.schedule(new TimerTask() {
            @Override
            public void run() {
                stillObjects.set(realX * HEIGHT + realY, new Grass(realX,realY,Sprite.grass.getFxImage()));
                temp.cancel();
            }
        },200,1);
    }

    public Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2, int _animate, int time) {
        int calc = _animate % 30;

        if(calc < 10) {
            return normal;
        }

        else if(calc < 20) {
            return x1;
        }

        return x2;
    }
}
