package project.model.game.movement;

import java.util.Random;



public enum Direction {
    NONE(0, 0),
    UP(0, -1),
    UP_RIGHT(1, -1),
    RIGHT(1, 0),
    DOWN_RIGHT(1, 1),
    DOWN(0, 1),
    DOWN_LEFT(-1, 1),
    LEFT(-1, 0),
    UP_LEFT(-1, -1);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public boolean isDiagonal() {
        return dx != 0 && dy != 0;
    }

    //input polling
    public static Direction getDirection(int dx, int dy) {
        for (Direction d : Direction.values()) {
            if (d.getDx() == dx && d.getDy() == dy) {
                return d;
            }
        }
        return NONE;
    }

    //random for police
    private static final Direction[] validDir = {
            UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT,  UP_LEFT
    };
    private static final int COUNT = validDir.length;

    private static final Random random = new Random();

    public static Direction getRandomDirection() {
        int index = random.nextInt(validDir.length);
        return validDir[index];
    }

    public Direction next() {
        if (this == NONE) return UP;

        int currentIndex = -1;
        for (int i = 0; i < COUNT; i++) {
            if (validDir[i] == this) {
                currentIndex = i;
                break;
            }
        }


        if (currentIndex != -1) {

            return validDir[(currentIndex + 1) % COUNT];
        }

        return NONE;
    }

    public Direction previous() {
        if (this == NONE) return UP_LEFT;

        int currentIndex = -1;
        for (int i = 0; i < COUNT; i++) {
            if (validDir[i] == this) {
                currentIndex = i;
                break;
            }
        }

        if (currentIndex != -1) {

            return validDir[(currentIndex - 1 + COUNT) % COUNT];
        }

        return NONE;
    }
    public Direction opposite() {

        if (this == NONE) return NONE;


        int currentIndex = -1;
        for (int i = 0; i < COUNT; i++) {
            if (validDir[i] == this) {
                currentIndex = i;
                break;
            }
        }


        if (currentIndex != -1) {

            return validDir[(currentIndex + 4) % COUNT];
        }

        return NONE;
    }

    public int getIndex() {
        for (int i = 0; i < COUNT; i++) {
            if (validDir[i] == this) {
                return i;
            }
        }
        return -1;
    }

    public int signedStepsTo(Direction target) {
        if (this == target) return 0;

        int current = this.getIndex();
        int targetIndex = target.getIndex();

        if (current == -1 || targetIndex == -1) return 0;


        int diff = targetIndex - current;


        if (diff > COUNT / 2) {

            return diff - COUNT;
        } else if (diff < -COUNT / 2) {

            return diff + COUNT;
        }
        return diff;
    }

    public int stepsTo(Direction target, RotationSense sense) {
        if (this == target) return 0;
        if (this == NONE || target == NONE) return COUNT; // Ou algum valor de erro



        int currentRotationIndex = -1;
        int targetRotationIndex = -1;
        for (int i = 0; i < COUNT; i++) {
            if (validDir[i] == this) currentRotationIndex = i;
            if (validDir[i] == target) targetRotationIndex = i;
        }

        if (currentRotationIndex == -1 || targetRotationIndex == -1) {

            return 0;
        }

        int steps;

        if (sense == RotationSense.CLOCKWISE) {

            steps = (targetRotationIndex - currentRotationIndex + COUNT) % COUNT;
        } else {
            steps = (currentRotationIndex - targetRotationIndex + COUNT) % COUNT;
        }


        return steps;
    }
}
