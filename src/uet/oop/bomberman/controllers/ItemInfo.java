package uet.oop.bomberman.controllers;

import uet.oop.bomberman.entities.Bomber;

public class ItemInfo {
    private int itemSpeedCount;
    private int itemFlamesCount;
    private int itemBombsCount;

    public void increaseItemSpeedCount() {
        itemSpeedCount++;
    }

    public void increaseItemFlamesCount() {
        itemFlamesCount++;
    }

    public void increaseItemBombsCount() {
        itemBombsCount++;
    }

    public ItemInfo() {
        itemBombsCount = Bomber.BOMBER_BOMB_LIST_SIZE_DEFAULT;
        itemSpeedCount = Bomber.BOMBER_SPEED_DEFAULT;
        itemFlamesCount = Bomber.BOMBER_BOMB_LEVEL_DEFAULT;
    }

    public int getItemBombsCount() {
        return itemBombsCount;
    }

    public int getItemFlamesCount() {
        return itemFlamesCount;
    }

    public int getItemSpeedCount() {
        return itemSpeedCount;
    }

    public void reset() {
        itemBombsCount = Bomber.BOMBER_BOMB_LIST_SIZE_DEFAULT;
        itemSpeedCount = Bomber.BOMBER_SPEED_DEFAULT;
        itemFlamesCount = Bomber.BOMBER_BOMB_LEVEL_DEFAULT;
    }
}
