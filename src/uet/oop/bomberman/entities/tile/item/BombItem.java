package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class BombItem extends Item {

	public BombItem(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý Bomber ăn Item
		return false;
	}
	


}
