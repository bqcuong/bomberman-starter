package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class ItemBombs extends Entity implements IItem{

    public ItemBombs(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }
    
}
