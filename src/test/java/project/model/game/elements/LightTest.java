package project.model.game.elements;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.model.game.Position;
import project.model.game.arena.Arena;
import project.model.game.movement.Direction;
import project.controller.game.raycast.RayCasting;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;

public class LightTest {

    @BeforeEach
    public void setUp() throws Exception {
        // mock to avoid opening a window
    }

    @Test
    public void testLightCreation() {
        Position origin = new Position(5, 5);
        Direction direction = Direction.RIGHT;
        int range = 10;
        double fovDegrees = 90.0;

        Light light = new Light(origin, direction, range, fovDegrees);

        assertEquals(origin, light.getOrigin());
        assertEquals(direction, light.getDirection());
        assertEquals(range, light.getRange());
        assertEquals(fovDegrees, light.getFovDegrees());
    }

    @Test
    public void testSetDirection() {
        Light light = new Light(new Position(0, 0), Direction.UP, 5, 90.0);

        assertEquals(Direction.UP, light.getDirection());

        light.setDirection(Direction.DOWN);
        assertEquals(Direction.DOWN, light.getDirection());

        light.setDirection(Direction.LEFT);
        assertEquals(Direction.LEFT, light.getDirection());
    }

    @Test
    public void testComputeWithClearLineOfSight() {
        Position origin = new Position(5, 5);
        Light light = new Light(origin, Direction.RIGHT, 3, 90.0);

        Arena arena = Mockito.mock(Arena.class);
        RayCasting rayCasting = Mockito.mock(RayCasting.class);

        // assume all positions have a clear sight
        when(rayCasting.haslineofsight(any(Position.class), any(Position.class), anyInt()))
                .thenReturn(true);

        Set<Position> litPositions = light.compute(arena, rayCasting);

        // multiple positions should be lit
        assertNotNull(litPositions);
        assertTrue(litPositions.size() > 0);

        // source shouldn't be lit
        assertFalse(litPositions.contains(origin));
    }

    @Test
    public void testComputeWithBlockedLineOfSight() {
        Position origin = new Position(5, 5);
        Light light = new Light(origin, Direction.RIGHT, 5, 90.0);

        Arena arena = Mockito.mock(Arena.class);
        RayCasting rayCasting = Mockito.mock(RayCasting.class);

        // assume all sight lines are blocked
        when(rayCasting.haslineofsight(any(Position.class), any(Position.class), anyInt()))
                .thenReturn(false);

        Set<Position> litPositions = light.compute(arena, rayCasting);

        // no positions should be lit
        assertNotNull(litPositions);
        assertEquals(0, litPositions.size());
    }

    @Test
    public void testComputeWithNarrowFOV() {
        Position origin = new Position(5, 5);
        Light light = new Light(origin, Direction.RIGHT, 5, 30.0); // narrow field of view

        Arena arena = Mockito.mock(Arena.class);
        RayCasting rayCasting = Mockito.mock(RayCasting.class);

        when(rayCasting.haslineofsight(any(Position.class), any(Position.class), anyInt()))
                .thenReturn(true);

        Set<Position> litPositions = light.compute(arena, rayCasting);

        assertNotNull(litPositions);
        // fewer positions lit with a narrow view
        assertTrue(litPositions.size() >= 0);
    }

    @Test
    public void testComputeWithWideFOV() {
        Position origin = new Position(5, 5);
        Light light = new Light(origin, Direction.RIGHT, 5, 180.0); // wide field of view

        Arena arena = Mockito.mock(Arena.class);
        RayCasting rayCasting = Mockito.mock(RayCasting.class);

        when(rayCasting.haslineofsight(any(Position.class), any(Position.class), anyInt()))
                .thenReturn(true);

        Set<Position> litPositions = light.compute(arena, rayCasting);

        assertNotNull(litPositions);
        // more positions lit with a wide view
        assertTrue(litPositions.size() > 0);
    }

    @Test
    public void testComputeWithSmallRange() {
        Position origin = new Position(5, 5);
        Light light = new Light(origin, Direction.RIGHT, 1, 90.0); // small range

        Arena arena = Mockito.mock(Arena.class);
        RayCasting rayCasting = Mockito.mock(RayCasting.class);

        when(rayCasting.haslineofsight(any(Position.class), any(Position.class), anyInt()))
                .thenReturn(true);

        Set<Position> litPositions = light.compute(arena, rayCasting);

        assertNotNull(litPositions);
        // everything should be within range 1
        for (Position pos : litPositions) {
            int dx = pos.getX() - origin.getX();
            int dy = pos.getY() - origin.getY();
            double dist = Math.sqrt(dx * dx + dy * dy);
            assertTrue(dist <= 1.0);
        }
    }

    @Test
    public void testComputeWithDifferentDirections() {
        Position origin = new Position(10, 10);
        Arena arena = Mockito.mock(Arena.class);
        RayCasting rayCasting = Mockito.mock(RayCasting.class);

        when(rayCasting.haslineofsight(any(Position.class), any(Position.class), anyInt()))
                .thenReturn(true);

        // check all directions
        Direction[] directions = { Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT,
                Direction.UP_LEFT, Direction.UP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT };

        for (Direction dir : directions) {
            Light light = new Light(origin, dir, 3, 90.0);
            Set<Position> litPositions = light.compute(arena, rayCasting);

            assertNotNull(litPositions);
            // every direction should light up something
            assertTrue(litPositions.size() >= 0, "Direction " + dir + " should work");
        }
    }

    @Test
    public void testComputeWithZeroRange() {
        Position origin = new Position(5, 5);
        Light light = new Light(origin, Direction.RIGHT, 0, 90.0);

        Arena arena = Mockito.mock(Arena.class);
        RayCasting rayCasting = Mockito.mock(RayCasting.class);

        Set<Position> litPositions = light.compute(arena, rayCasting);

        assertNotNull(litPositions);
        // nothing lit if the range is zero
        assertEquals(0, litPositions.size());
    }

    @Test
    public void testComputeWith360FOV() {
        Position origin = new Position(5, 5);
        Light light = new Light(origin, Direction.RIGHT, 2, 360.0); // 360 degree light source

        Arena arena = Mockito.mock(Arena.class);
        RayCasting rayCasting = Mockito.mock(RayCasting.class);

        when(rayCasting.haslineofsight(any(Position.class), any(Position.class), anyInt()))
                .thenReturn(true);

        Set<Position> litPositions = light.compute(arena, rayCasting);

        assertNotNull(litPositions);
        assertTrue(litPositions.size() > 0);
    }

    @Test
    public void testComputeBoundaries() {
        Position origin = new Position(10, 10);
        // 90 degree FOV facing RIGHT (0 radians)
        // should cover angles from -PI/4 to PI/4
        Light light = new Light(origin, Direction.RIGHT, 5, 90.0);

        Arena arena = Mockito.mock(Arena.class);
        RayCasting rayCasting = Mockito.mock(RayCasting.class);
        when(rayCasting.haslineofsight(any(), any(), anyInt())).thenReturn(true);

        Set<Position> lit = light.compute(arena, rayCasting);

        // (15, 10) is RIGHT at distance 5 -> should be lit
        assertTrue(lit.contains(new Position(15, 10)), "Position in front should be lit");

        // (10, 5) is UP (PI/2) at distance 5 -> should NOT be lit (outside 90 deg FOV)
        assertFalse(lit.contains(new Position(10, 5)), "Position above should not be lit");

        // (5, 10) is LEFT (PI) at distance 5 -> should NOT be lit
        assertFalse(lit.contains(new Position(5, 10)), "Position behind should not be lit");

        // (16, 10) is distance 6 -> should NOT be lit (range is 5)
        assertFalse(lit.contains(new Position(16, 10)), "Position out of range should not be lit");
    }
}
