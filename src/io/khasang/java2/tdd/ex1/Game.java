package io.khasang.java2.tdd.ex1;

public class Game {
    public static final int SIZE = 3;
    private char[][] cells;
    private User user1;
    private User user2;

    public void initCells() {
        cells = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = '.';
            }
        }
    }

    public char getCharAtPoint(Point point) {
        return cells[point.getX()][point.getY()];
    }

    public char[][] getCells() {
        return cells;
    }

    public boolean setShootPoint(Point point, char c) {
        if (cells[point.getX()][point.getY()] != '.') {
            return false;
        }
        cells[point.getX()][point.getY()] = c;
        return true;
    }

    public Status getGameStatus() {
        // Проверка строк на победу
        for (int i = 0; i < SIZE; i++) {
            if (cells[i][0] == cells[i][1] && cells[i][1] == cells[i][2] && cells[i][0] != '.') {
                return cells[i][0] == 'X' ? Status.X_WIN : Status.O_WIN;
            }
        }

        // Проверка столбцов на победу
        for (int i = 0; i < SIZE; i++) {
            if (cells[0][i] == cells[1][i] && cells[1][i] == cells[2][i] && cells[0][i] != '.') {
                return cells[0][i] == 'X' ? Status.X_WIN : Status.O_WIN;
            }
        }

        // Проверка диагоналей на победу
        if (cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2] && cells[0][0] != '.') {
            return cells[0][0] == 'X' ? Status.X_WIN : Status.O_WIN;
        }

        if (cells[0][2] == cells[1][1] && cells[1][1] == cells[2][0] && cells[0][2] != '.') {
            return cells[0][2] == 'X' ? Status.X_WIN : Status.O_WIN;
        }

        // Проверка на ничью
        boolean allCellsFilled = true;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (cells[i][j] == '.') {
                    allCellsFilled = false;
                    break;
                }
            }
            if (!allCellsFilled) {
                break;
            }
        }
        if (allCellsFilled) {
            return Status.DRAW;
        }

        // Если нет победителя и игра не завершена
        return Status.IN_PROGRESS;
    }

    public String getFieldForOutput() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                result.append(cells[i][j]);
                if (j < SIZE - 1) {
                    result.append(" ");
                }
            }
            if (i < SIZE - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }


    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public User getUser2() {
        return user2;
    }

    public void start() {
        User currentUser = user1;
        do {
            System.out.println("Введите координаты в формате: x y (от 0 до 2)");
            Point shootPoint = currentUser.getShootPoint();
            boolean shootResult = setShootPoint(shootPoint, currentUser.getSign());
            if (!shootResult) {
                continue;
            }
            System.out.println(getFieldForOutput());
            currentUser = (currentUser == user1) ? user2 : user1;
        } while (getGameStatus() == Status.IN_PROGRESS);
        System.out.println(getGameStatus());
    }

    public void init() {
        ShootStrategy shootStrategy1 = new RandomShootStratagy();
        ShootStrategy shootStrategy2 = new RandomShootStratagy();

        User user1 = new User(shootStrategy1, 'X');
        User user2 = new User(shootStrategy2, 'O');

        setUser1(user1);
        setUser2(user2);

        initCells();
    }
}
