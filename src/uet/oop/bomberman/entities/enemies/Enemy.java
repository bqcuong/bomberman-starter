package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.objects.IObstacle;
import uet.oop.bomberman.entities.MovingEntity;

public abstract class Enemy extends MovingEntity implements IObstacle {

    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

}
