package uet.oop.bomberman.event;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Mob.*;
import uet.oop.bomberman.entities.Bomb.*;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class Movement {
    public static void move(Mob entity, int dx, int dy, List<Entity> stillObjects, int HEIGHT, int WIDTH, List<Bomb> bomb) {

        int realX = entity.getX()/ Sprite.SCALED_SIZE;
        int realY = entity.getY()/Sprite.SCALED_SIZE;
        int tempX = entity.getX()%Sprite.SCALED_SIZE;
        int tempY = entity.getY()%Sprite.SCALED_SIZE;
        if(entity.getState().equals("right")){

            //làm tròn Y để dễ di chuyển
            if(tempY<=10 && entity.collidesWith(stillObjects.get(realX * HEIGHT + realY + HEIGHT ))) entity.setY(realY * Sprite.SCALED_SIZE);
            if(tempY>=22 && entity.collidesWith(stillObjects.get(realX * HEIGHT + realY + HEIGHT + 1))) entity.setY((realY + 1) * Sprite.SCALED_SIZE);

            //kiểm tra va chạm để di chuyển
            if(tempY == 0) {
                if (entity.collidesWith(stillObjects.get(realX * HEIGHT + realY + HEIGHT))
                && entity.collidesWithBomb(bomb)) {
                    entity.setX(entity.getX() + dx);
//                    System.out.println(entity.getX() + "   " + entity.getY());
                }
            }
        }
        if(entity.getState().equals("up")){

            //làm tròn X để dễ di chuyển
            if(tempX<=10 && entity.collidesWith(stillObjects.get(realX * HEIGHT + realY - 1))) entity.setX(realX * Sprite.SCALED_SIZE);
            if(tempX>=22 && entity.collidesWith(stillObjects.get(realX * HEIGHT + realY + HEIGHT - 1))) entity.setX((realX + 1) * Sprite.SCALED_SIZE);

            //kiểm tra va chạm để di chuyển
            if(tempX == 0) {
                if(tempY == 0) {
                    if (entity.collidesWith(stillObjects.get(realX * HEIGHT + realY - 1))
                            && entity.collidesWithBomb(bomb)) {
                        entity.setY(entity.getY() + dy);
                        //System.out.println(entity.getX() + "   " + entity.getY());
                    }
                }
                else {
                    if(entity.collidesWith(stillObjects.get(realX * HEIGHT + realY ))
                            && entity.collidesWithBomb(bomb)) {
                        entity.setY(entity.getY() + dy);
                        //System.out.println(entity.getX() + "   " + entity.getY());
                    }
                }
            }
        }
        if(entity.getState().equals("left")){

            //làm tròn Y để dễ di chuyển
            if(tempY<=10 && entity.collidesWith(stillObjects.get(realX * HEIGHT + realY - HEIGHT ))) entity.setY(realY * Sprite.SCALED_SIZE);
            if(tempY>=22 && entity.collidesWith(stillObjects.get(realX * HEIGHT + realY - HEIGHT + 1))) entity.setY((realY + 1) * Sprite.SCALED_SIZE);

            //kiểm tra va chạm để di chuyển
            if(tempY == 0) {
                if(tempX == 0) {
                    if (entity.collidesWith(stillObjects.get(realX * HEIGHT + realY - HEIGHT))
                            && entity.collidesWithBomb(bomb)) {
                        entity.setX(entity.getX() + dx);
                        //System.out.println(entity.getX() + "   " + entity.getY());
                    }
                }
                else {
                    if (entity.collidesWith(stillObjects.get(realX * HEIGHT + realY))
                            && entity.collidesWithBomb(bomb)) {
                        entity.setX(entity.getX() + dx);
                        //System.out.println(entity.getX() + "   " + entity.getY());
                    }
                }
            }
        }
        if(entity.getState().equals("down")){

            //làm tròn X để dễ di chuyển
            if (tempX <= 10 && entity.collidesWith(stillObjects.get(realX * HEIGHT + realY + 1 ))) entity.setX(realX * Sprite.SCALED_SIZE);
            if (tempX >= 22 && entity.collidesWith(stillObjects.get(realX * HEIGHT + realY + HEIGHT + 1))) entity.setX((realX + 1) * Sprite.SCALED_SIZE);

            //kiểm tra va chạm để di chuyển
            if (tempX == 0) {
                if (entity.collidesWith(stillObjects.get(realX * HEIGHT + realY + 1))
                        && entity.collidesWithBomb(bomb)) {
                    entity.setY(entity.getY() + dy);
                    //System.out.println(entity.getX() + "   " + entity.getY());
                }
            }
        }
    }
