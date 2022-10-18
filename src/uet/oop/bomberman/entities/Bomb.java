package uet.oop.bomberman.entities;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomb extends Entity {

    private static long time_bomb;
    private static long time_tmp;
    private static Entity bomb;
    private static int number_bomb = 1;
    private static int power_bomb = 1;
    private static int swap_active = 0;

    public static void placeBomb() {
        if (number_bomb > 0) {
            number_bomb--;
            int x = Math.round(Bomber.coordinates / Sprite.SCALED_SIZE);
            int y = Math.round(Bomber.coordinatesY / Sprite.SCALED_SIZE);
            Entity bomb = new Bomb(x, y, Sprite.bomb.getFxImage());
            time_bomb = System.currentTimeMillis();
            time_tmp = time_bomb;
            Game.stillObjects.add(bomb);
        }
    }

    public void countDownToExplosion() {
        if (System.currentTimeMillis() - time_bomb < 3000L) {
            if (System.currentTimeMillis() - time_tmp >= 800L) {
                time_tmp += 800L;
                if (swap_active == 0) {
                    bomb.setImg(Sprite.bomb.getFxImage());
                    swap_active = 1;
                } else if (swap_active == 1) {
                    bomb.setImg(Sprite.bomb_1.getFxImage());
                    swap_active = 2;
                } else if (swap_active == 2) {
                    bomb.setImg(Sprite.bomb_2.getFxImage());
                    Explosion();
                    //swap_active = 0;
                }
            }
        }
        if (System.currentTimeMillis() - time_bomb > 3200L) {
            afterExplosion();
        }
    }

    public void Explosion() {
        Flame[] flames = new Flame[2 + 4 * power_bomb];
        int x = Math.round(bomb.getX() / Sprite.SCALED_SIZE);
        int y = Math.round(bomb.getY() / Sprite.SCALED_SIZE);

        flames[0] = new Flame(x, y, Sprite.explosion_horizontal1.getFxImage());
        flames[1 + 4 * power_bomb] = new Flame(x, y, Sprite.explosion_vertical1.getFxImage());
        for (int i = 1; i < 1 + 4 * power_bomb; i++) {
            if (i % 4 == 1 && i != 4 * power_bomb - 3) {

                flames[i] = new Flame(x + 1 + i / 4, y, Sprite.explosion_horizontal1.getFxImage());
            } else if (i % 4 == 2 && i != 4 * power_bomb - 2) {

                flames[i] = new Flame(x - 1 - i / 4, y, Sprite.explosion_horizontal1.getFxImage());
            } else if (i % 4 == 3 && i != 4 * power_bomb - 1) {

                flames[i] = new Flame(x, y + 1 + i / 4, Sprite.explosion_vertical1.getFxImage());
            } else if (i % 4 == 0 && i != 4 * power_bomb) {

                flames[i] = new Flame(x, y - i / 4, Sprite.explosion_vertical1.getFxImage());
            } else if (i == 4 * power_bomb - 3) {

                flames[i] = new Flame(x + power_bomb, y,
                        Sprite.explosion_horizontal_right_last1.getFxImage());
            } else if (i == 4 * power_bomb - 2) {

                flames[i] = new Flame(x - power_bomb, y,
                        Sprite.explosion_horizontal_left_last1.getFxImage());
            } else if (i == 4 * power_bomb - 1) {
                flames[i] = new Flame(x, y + power_bomb,
                        Sprite.explosion_vertical_down_last1.getFxImage());//
            } else if (i == 4 * power_bomb) {
                flames[i] = new Flame(x, y - power_bomb, Sprite.explosion_vertical_top_last1.getFxImage());
            }
        }

        for (Flame a : flames) {
            char position = Game.mapGame.getMap(a.getY() / Sprite.SCALED_SIZE, a.getX() / Sprite.SCALED_SIZE);
            if (position == '#') {
                a.setImg(Sprite.wall.getFxImage());
            }
        }

        try {
            Game.stillObjects.addAll(Arrays.asList(flames).subList(0, 2 + 4 * power_bomb));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void afterExplosion() {
        Game.stillObjects.removeIf(i -> i instanceof Flame);
        time_bomb = System.currentTimeMillis();
        time_tmp = time_bomb;
        swap_active = 0;
        try {
            Game.stillObjects.remove(bomb);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        number_bomb += 1;
    }

    public void collisionWithEntity() {
    }

    @Override
    public void update() {
        countDownToExplosion();
    }

}
