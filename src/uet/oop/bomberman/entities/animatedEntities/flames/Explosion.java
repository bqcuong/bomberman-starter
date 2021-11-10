package uet.oop.bomberman.entities.animatedEntities.flames;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.animatedEntities.AnimatedEntity;
import uet.oop.bomberman.entities.animatedEntities.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

public class Explosion extends AnimatedEntity {
    private long limitedTime = 2_000_000_000l;
    protected ArrayList<Sprite> sprites;

    public Explosion(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        sprites = new ArrayList<>();
        sprites.add(Sprite.bomb_exploded);
        sprites.add(Sprite.bomb_exploded1);
        sprites.add(Sprite.bomb_exploded2);
    }

    public Explosion(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.bomb_exploded.getFxImage());
        sprites = new ArrayList<>();
        sprites.add(Sprite.bomb_exploded);
        sprites.add(Sprite.bomb_exploded1);
        sprites.add(Sprite.bomb_exploded2);

    }

    public boolean canExplode() {
        for (Entity entity: BombermanGame.stillObjects) {
            if (this.existOn(entity.getX(), entity.getY()) &&
                !entity.getClass().getTypeName().contains("Grass")) {
                return false;
            }
        }
        for (Entity entity: BombermanGame.entities) {
            if (this.existOnSquare(entity.getX(), entity.getY()) &&
                entity.getClass().getTypeName().contains("Bomber")) {
                ((Bomber) entity).setAlive(false);
                break;
            }
        }
        return true;
    }

    @Override
    public void update() {
        animate();
        if (animatedTime < limitedTime) {
            img = Sprite.movingSprite(sprites.get(0),
                    sprites.get(1),
                    sprites.get(2),
                    animatedTime, 500_000_000).getFxImage();

        } else{
            setVisible(false);
        }
    }
}
