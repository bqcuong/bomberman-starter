package uet.oop.bomberman.controllers;

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
        itemBombsCount = 1;
        itemSpeedCount = 2;
        itemFlamesCount = 1;
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
        itemBombsCount = 1;
        itemSpeedCount = 2;
        itemFlamesCount = 1;
    }
}
