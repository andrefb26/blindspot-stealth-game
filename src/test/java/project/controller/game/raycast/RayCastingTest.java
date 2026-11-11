package project.controller.game.raycast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.model.game.Position;
import project.model.game.arena.Arena;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class RayCastingTest {
    private RayCasting rayCasting;
    private Arena arena;

    @BeforeEach
    public void setUp() {
        arena = Mockito.mock(Arena.class);
        rayCasting = new RayCasting(arena);
    }

    @Test
    public void testHasLineOfSightClear() {
        when(arena.iswall(any(Position.class))).thenReturn(false);
        when(arena.isbox(any(Position.class))).thenReturn(false);
        when(arena.issafe(any(Position.class))).thenReturn(false);

        Position start = new Position(0, 0);
        Position end = new Position(0, 5);

        assertTrue(rayCasting.haslineofsight(start, end, 10));
    }

    @Test
    public void testHasLineOfSightBlocked() {
        // block ray with wall
        when(arena.iswall(new Position(0, 2))).thenReturn(true);
        when(arena.isbox(any(Position.class))).thenReturn(false);
        when(arena.issafe(any(Position.class))).thenReturn(false);

        Position start = new Position(0, 0);
        Position end = new Position(0, 5);

        assertFalse(rayCasting.haslineofsight(start, end, 10));
    }

    @Test
    public void testHasLineOfSightOutOfRange() {
        Position start = new Position(0, 0);
        Position end = new Position(0, 11);

        assertFalse(rayCasting.haslineofsight(start, end, 10));
    }

    @Test
    public void testIsBlocking() {
        Position pos = new Position(1, 1);

        //test for wall
        when(arena.iswall(pos)).thenReturn(true);
        assertTrue(rayCasting.isBlocking(pos));
        when(arena.iswall(pos)).thenReturn(false);

        //test for box
        when(arena.isbox(pos)).thenReturn(true);
        assertTrue(rayCasting.isBlocking(pos));
        when(arena.isbox(pos)).thenReturn(false);

        //test for safe
        when(arena.issafe(pos)).thenReturn(true);
        assertTrue(rayCasting.isBlocking(pos));
        when(arena.issafe(pos)).thenReturn(false);

        // est for clear path
        assertFalse(rayCasting.isBlocking(pos));
    }

    @Test
    public void testHasLineOfSightModuloZero() {
        Position start = new Position(2, 2);
        Position end = new Position(2, 2);
        assertTrue(rayCasting.haslineofsight(start, end, 5));
    }

    @Test
    public void testHasLineOfSightExactRange() {
        Position start = new Position(0, 0);
        Position end = new Position(0, 5);
        when(arena.iswall(any(Position.class))).thenReturn(false);
        assertTrue(rayCasting.haslineofsight(start, end, 5));
    }
}
