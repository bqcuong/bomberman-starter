package uet.oop.bomberman.entities.tile;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Tile {

	public Portal(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý khi Bomber đi vào
		return false;
	}

}
