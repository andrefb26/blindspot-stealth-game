package project.model.game.elements;

import project.model.game.movement.Direction;

public class Camera extends Element {
    private Direction direction;
    private Direction initialDirection;
    private int currentRotationSense;
    private long nextMovementTime;

    public Camera(int x, int y, Direction direction) {
        super(x,y);
        this.direction=direction;

    }
    public Direction getDirection(){
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public Direction getInitialDirection() {
        return initialDirection;
    }

    public void setInitialDirection(Direction initialDirection) {
        this.initialDirection = initialDirection;
    }


    public int getCurrentRotationSense() {
        return currentRotationSense;
    }

    public void setCurrentRotationSense(int currentRotationSense) {
        this.currentRotationSense = currentRotationSense;
    }
    public long getNextMovementTime() {
        return nextMovementTime;
    }

    public void setNextMovementTime(long nextMovementTime) {
        this.nextMovementTime = nextMovementTime;
    }
}
