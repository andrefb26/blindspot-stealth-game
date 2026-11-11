package project.model.game;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.junit.jupiter.api.Test;
import project.model.game.movement.Direction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PositionTest {
    private Position position;

    @Property(tries = 50)
    void translateWithAnyDirection(@ForAll int x, @ForAll int y, @ForAll Direction direction) {

        Position original = new Position(x, y);
        Position next = original.translate(direction);

        assertEquals(x + direction.getDx(), next.getX());

        assertEquals(y + direction.getDy(), next.getY());
    }

    @Test
    void testEquals() {
        Position p1 = new Position(1, 2);
        Position p2 = new Position(1, 2);
        Position p3 = new Position(2, 1);

        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
        assertNotEquals(p1, null);
        assertNotEquals(p1, "string");
    }

    @Test
    void testHashCode() {
        Position p1 = new Position(10, 20);
        Position p2 = new Position(10, 20);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void testTranslate() {
        Position p = new Position(5, 5);
        assertEquals(new Position(5, 4), p.translate(Direction.UP));
        assertEquals(new Position(5, 6), p.translate(Direction.DOWN));
        assertEquals(new Position(4, 5), p.translate(Direction.LEFT));
        assertEquals(new Position(6, 5), p.translate(Direction.RIGHT));
        assertEquals(new Position(5, 5), p.translate(Direction.NONE));
    }

    @Test
    void testSetters() {
        Position p = new Position(1, 1);
        p.setX(10);
        p.setY(20);
        assertEquals(10, p.getX());
        assertEquals(20, p.getY());
    }

    @Test
    void testTranslateInt() {
        Position p = new Position(5, 5);
        Position next = p.translate(1, -1);
        assertEquals(6, next.getX());
        assertEquals(4, next.getY());
    }
}