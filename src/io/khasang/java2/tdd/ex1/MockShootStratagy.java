package io.khasang.java2.tdd.ex1;

public class MockShootStratagy implements ShootStrategy {
    @Override
    public Point getShootPoint() {
        return new Point(0, 0);
    }
}
