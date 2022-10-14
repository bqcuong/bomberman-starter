package uet.oop.bomberman.entities;

public class BombFlameInfo {
    private boolean brickCheck;
    private boolean itemCheck;
    private boolean wallCheck;
    private int flameLength = 0;
    private Entity brickExplosion;

    public BombFlameInfo() {
        brickCheck = itemCheck = wallCheck = false;
        flameLength = 0;
        brickExplosion = null;
    }

    public boolean isBrickCheck() {
        return brickCheck;
    }

    public void setBrickCheck(boolean brickCheck) {
        this.brickCheck = brickCheck;
    }

    public boolean isItemCheck() {
        return itemCheck;
    }

    public void setItemCheck(boolean itemCheck) {
        this.itemCheck = itemCheck;
    }

    public boolean isWallCheck() {
        return wallCheck;
    }

    public void setWallCheck(boolean wallCheck) {
        this.wallCheck = wallCheck;
    }

    public int getFlameLength() {
        return flameLength;
    }

    public void setFlameLength(int flameLength) {
        this.flameLength = flameLength;
    }

    public Entity getBrickExplosion() {
        return brickExplosion;
    }

    public void setBrickExplosion(Entity brickExplosion) {
        this.brickExplosion = brickExplosion;
    }
}
