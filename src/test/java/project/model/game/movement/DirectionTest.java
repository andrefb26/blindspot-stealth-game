package project.model.game.movement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DirectionTest {

    @Test
    public void testNext() {
        assertEquals(Direction.UP_RIGHT, Direction.UP.next());
        assertEquals(Direction.RIGHT, Direction.UP_RIGHT.next());
        assertEquals(Direction.DOWN_RIGHT, Direction.RIGHT.next());
        assertEquals(Direction.DOWN, Direction.DOWN_RIGHT.next());
        assertEquals(Direction.DOWN_LEFT, Direction.DOWN.next());
        assertEquals(Direction.LEFT, Direction.DOWN_LEFT.next());
        assertEquals(Direction.UP_LEFT, Direction.LEFT.next());
        assertEquals(Direction.UP, Direction.UP_LEFT.next());
    }

    @Test
    public void testPrevious() {
        assertEquals(Direction.UP_LEFT, Direction.UP.previous());
        assertEquals(Direction.UP, Direction.UP_RIGHT.previous());
        assertEquals(Direction.UP_RIGHT, Direction.RIGHT.previous());
        assertEquals(Direction.RIGHT, Direction.DOWN_RIGHT.previous());
        assertEquals(Direction.DOWN_RIGHT, Direction.DOWN.previous());
        assertEquals(Direction.DOWN, Direction.DOWN_LEFT.previous());
        assertEquals(Direction.DOWN_LEFT, Direction.LEFT.previous());
        assertEquals(Direction.LEFT, Direction.UP_LEFT.previous());
    }

    @Test
    public void testOpposite() {
        assertEquals(Direction.DOWN, Direction.UP.opposite());
        assertEquals(Direction.DOWN_LEFT, Direction.UP_RIGHT.opposite());
        assertEquals(Direction.LEFT, Direction.RIGHT.opposite());
        assertEquals(Direction.UP_LEFT, Direction.DOWN_RIGHT.opposite());
        assertEquals(Direction.UP, Direction.DOWN.opposite());
        assertEquals(Direction.UP_RIGHT, Direction.DOWN_LEFT.opposite());
        assertEquals(Direction.RIGHT, Direction.LEFT.opposite());
        assertEquals(Direction.DOWN_RIGHT, Direction.UP_LEFT.opposite());
        assertEquals(Direction.NONE, Direction.NONE.opposite());
    }

    @Test
    public void testStepsToClockwise() {
        assertEquals(0, Direction.UP.stepsTo(Direction.UP, RotationSense.CLOCKWISE));
        assertEquals(1, Direction.UP.stepsTo(Direction.UP_RIGHT, RotationSense.CLOCKWISE));
        assertEquals(2, Direction.UP.stepsTo(Direction.RIGHT, RotationSense.CLOCKWISE));
        assertEquals(4, Direction.UP.stepsTo(Direction.DOWN, RotationSense.CLOCKWISE));
        assertEquals(7, Direction.UP.stepsTo(Direction.UP_LEFT, RotationSense.CLOCKWISE));
    }

    @Test
    public void testStepsToCounterClockwise() {
        assertEquals(0, Direction.UP.stepsTo(Direction.UP, RotationSense.COUNTER_CLOCKWISE));
        assertEquals(1, Direction.UP.stepsTo(Direction.UP_LEFT, RotationSense.COUNTER_CLOCKWISE));
        assertEquals(2, Direction.UP.stepsTo(Direction.LEFT, RotationSense.COUNTER_CLOCKWISE));
        assertEquals(4, Direction.UP.stepsTo(Direction.DOWN, RotationSense.COUNTER_CLOCKWISE));
    }

    @Test
    public void testSignedStepsTo() {
        assertEquals(0, Direction.UP.signedStepsTo(Direction.UP));
        assertEquals(1, Direction.UP.signedStepsTo(Direction.UP_RIGHT));
        assertEquals(-1, Direction.UP.signedStepsTo(Direction.UP_LEFT));
        assertEquals(4, Direction.UP.signedStepsTo(Direction.DOWN));
        assertEquals(-4, Direction.DOWN.signedStepsTo(Direction.UP));
    }

    @Test
    public void testIsDiagonal() {
        assertFalse(Direction.UP.isDiagonal());
        assertTrue(Direction.UP_RIGHT.isDiagonal());
        assertFalse(Direction.RIGHT.isDiagonal());
        assertTrue(Direction.DOWN_RIGHT.isDiagonal());
        assertFalse(Direction.DOWN.isDiagonal());
        assertTrue(Direction.DOWN_LEFT.isDiagonal());
        assertFalse(Direction.LEFT.isDiagonal());
        assertTrue(Direction.UP_LEFT.isDiagonal());
        assertFalse(Direction.NONE.isDiagonal());
    }

    @Test
    public void testGetDirection() {
        assertEquals(Direction.UP, Direction.getDirection(0, -1));
        assertEquals(Direction.DOWN, Direction.getDirection(0, 1));
        assertEquals(Direction.LEFT, Direction.getDirection(-1, 0));
        assertEquals(Direction.RIGHT, Direction.getDirection(1, 0));
        assertEquals(Direction.UP_RIGHT, Direction.getDirection(1, -1));
        assertEquals(Direction.NONE, Direction.getDirection(0, 0));
        assertEquals(Direction.NONE, Direction.getDirection(5, 5));
    }
}
