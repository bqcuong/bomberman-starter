package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.objects.IObstacle;
import uet.oop.bomberman.entities.MovingEntity;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Enemy extends MovingEntity implements IObstacle {

    public int REAL_WIDTH = Sprite.SCALED_SIZE - 1;
    public int REAL_HEIGHT = Sprite.SCALED_SIZE - 1;
    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }


}
