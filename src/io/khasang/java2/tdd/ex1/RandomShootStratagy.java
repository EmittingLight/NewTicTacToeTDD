package io.khasang.java2.tdd.ex1;

public class RandomShootStratagy implements ShootStrategy {
    @Override
    public Point getShootPoint() {
        return Point.getRandomPoint();
    }
}
