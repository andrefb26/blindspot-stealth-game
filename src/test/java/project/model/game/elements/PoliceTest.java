package project.model.game.elements;

import org.junit.jupiter.api.Test;
import project.model.game.Position;
import project.model.game.movement.Direction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PoliceTest {

    @Test
    public void testPoliceCreation() {
        Police police = new Police(5, 10, Direction.UP);
        assertEquals(new Position(5, 10), police.getPosition());
        assertEquals(Direction.UP, police.getDirection());
    }

    @Test
    public void testGetSetDirection() {
        Police police = new Police(0, 0, Direction.UP);
        police.setDirection(Direction.DOWN);
        assertEquals(Direction.DOWN, police.getDirection());
    }

    @Test
    public void testGetNextPosition() {
        Police police = new Police(5, 5, Direction.UP);
        assertEquals(new Position(5, 4), police.getNextPosition(Direction.UP));
        assertEquals(new Position(5, 6), police.getNextPosition(Direction.DOWN));
        assertEquals(new Position(4, 5), police.getNextPosition(Direction.LEFT));
        assertEquals(new Position(6, 5), police.getNextPosition(Direction.RIGHT));
    }

    @Test
    public void testNextDirectionChangeTime() {
        Police police = new Police(0, 0, Direction.UP);
        police.setNextDirectionChangeTime(1000L);
        assertEquals(1000L, police.getNextDirectionChangeTime());
    }

    @Test
    public void testTargetDirection() {
        Police police = new Police(0, 0, Direction.UP);
        police.setTargetDirection(Direction.LEFT);
        assertEquals(Direction.LEFT, police.getTargetDirection());
    }
}
