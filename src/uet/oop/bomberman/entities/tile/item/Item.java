package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Item extends Tile {

	public Item(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
}
