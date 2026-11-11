package project.model.game.elements;

import project.model.game.Position;
import project.model.game.movement.Direction;

public class Police extends Element implements Movable {
    private Direction direction;
    private long nextDirectionChangeTime;
    private Direction targetDirection;

    public Police(int x, int y, Direction direction) {
        super(x, y);
        this.direction=direction;

    }
    public Direction getDirection(){
        return this.direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    @Override
    public Position getNextPosition(Direction direction) {
        int newX = getPosition().getX() + direction.getDx();
        int newY = getPosition().getY() + direction.getDy();
        return new Position(newX, newY);
    }

    public long getNextDirectionChangeTime() {
        return this.nextDirectionChangeTime;
    }

    public void setNextDirectionChangeTime(long nextDirectionChangeTime) {
        this.nextDirectionChangeTime = nextDirectionChangeTime;
    }

    public Direction getTargetDirection() {
        return this.targetDirection;
    }

    public void setTargetDirection(Direction targetDirection) {
        this.targetDirection = targetDirection;
    }
}
