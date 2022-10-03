package uet.oop.bomberman.entities;

import uet.oop.bomberman.graphics.Sprite;

public class Move {
    // chua xu li toc do khi bomber nhan duoc item buff
    public static int speed = 1;

    public static void down(Animal animal) {
        if (animal instanceof Bomber) {
            animal.setDirection("down");
            animal.setCount(4 / speed);
            checkRun(animal);
        }
    }
    private static void down_step(Animal animal) {
        if (animal instanceof  Bomber) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.move_down.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.move_down_1.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.move_down.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.move_down_2.getFxImage());
                animal.setSwap(1);
            }
        }
    }

    public static void up(Animal animal) {
        if (animal instanceof Bomber) {
            animal.setDirection("up");
            animal.setCount(4 / speed);
            checkRun(animal);
        }
    }
    private static void up_step(Animal animal) {
        if (animal instanceof  Bomber) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.move_up.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.move_up_1.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.move_up.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.move_up_2.getFxImage());
                animal.setSwap(1);
            }
        }
    }

    public static void left(Animal animal) {
        if (animal instanceof Bomber) {
            animal.setDirection("left");
            animal.setCount(4 / speed);
            checkRun(animal);
        }
    }
    private static void left_step(Animal animal) {
        if (animal instanceof  Bomber) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.move_left.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.move_left_1.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.move_left.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.move_left_2.getFxImage());
                animal.setSwap(1);
            }
        }
    }

    public static void right(Animal animal) {
        if (animal instanceof Bomber) {
            animal.setDirection("right");
            animal.setCount(4 / speed);
            checkRun(animal);
        }
    }
    private static void right_step(Animal animal) {
        if (animal instanceof  Bomber) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.move_right.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.move_right_1.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.move_right.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.move_right_2.getFxImage());
                animal.setSwap(1);
            }
        }
    }

    private static void setDirection(String direction, Animal animal, int isMove) {
        switch (direction) {
            case "down":
                down_step(animal);
                animal.setY(animal.getY() + isMove);
                break;
            case "up":
                up_step(animal);
                animal.setY(animal.getY() - isMove);
                break;
            case "left":
                left_step(animal);
                animal.setX(animal.getX() - isMove);
                break;
            case "right":
                right_step(animal);
                animal.setX(animal.getX() + isMove);
                break;
        }
    }

    public static void checkRun(Animal animal) {
        if (animal instanceof Bomber && animal.getCount() > 0) {
            setDirection(animal.getDirection(), animal, 8 * speed);
            animal.setCount(animal.getCount() - 1);
        }
    }
}
