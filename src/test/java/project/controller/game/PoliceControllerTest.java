package project.controller.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.Game;
import project.gui.GUI;
import project.model.game.Position;
import project.model.game.arena.Arena;
import project.model.game.elements.Police;
import project.model.game.movement.Direction;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PoliceControllerTest {

    private PoliceController controller;
    private Arena arena;
    private Game game;

    @BeforeEach
    public void setUp() {
        arena = mock(Arena.class);
        game = mock(Game.class);
        controller = new PoliceController(arena);
    }

    @Test
    public void testPoliceRotationSteps() throws IOException {
        Police police = new Police(10, 10, Direction.UP);
        police.setTargetDirection(Direction.RIGHT);

        police.setNextDirectionChangeTime(Long.MAX_VALUE);

        when(arena.getPolice()).thenReturn(Collections.singletonList(police));

        long currentTime = 1000;
        controller.step(game, GUI.ACTION.NONE, currentTime);

        assertEquals(Direction.UP_RIGHT, police.getDirection(), "Police should rotate clockwise towards target");
        assertEquals(new Position(10, 10), police.getPosition(), "Police should rotate in place, not move");

        currentTime += 800;
        controller.step(game, GUI.ACTION.NONE, currentTime);

        assertEquals(Direction.RIGHT, police.getDirection(), "Police should now face RIGHT");

        when(arena.isEmpty(any(Position.class))).thenReturn(true);
        currentTime += 800;
        controller.step(game, GUI.ACTION.NONE, currentTime);

        assertEquals(new Position(11, 10), police.getPosition(), "Police should move RIGHT");
    }

    @Test
    public void testPoliceInversionOnCollision() throws IOException {
        Police police = new Police(10, 10, Direction.UP);
        police.setTargetDirection(Direction.UP);

        police.setNextDirectionChangeTime(Long.MAX_VALUE);

        when(arena.getPolice()).thenReturn(Collections.singletonList(police));
        when(arena.isEmpty(new Position(10, 9))).thenReturn(false);
        when(arena.isEmpty(new Position(10, 11))).thenReturn(true);

        long currentTime = 5000;

        controller.step(game, GUI.ACTION.NONE, currentTime);

        assertEquals(Direction.DOWN, police.getDirection(), "Police should flip direction on collision");
        assertEquals(Direction.DOWN, police.getTargetDirection(), "Target direction should also flip");

        assertEquals(new Position(10, 11), police.getPosition(), "Police should rebound to valid position");

        assertNotEquals(Long.MAX_VALUE, police.getNextDirectionChangeTime(),
                "Police should update change time after collision");
        assertTrue(police.getNextDirectionChangeTime() > currentTime, "Police should pause after collision");
    }

    @Test
    public void testPoliceCounterClockwiseRotation() throws IOException {
        Police police = new Police(10, 10, Direction.UP);
        //rotates counter clockwise if faster
        police.setTargetDirection(Direction.UP_LEFT);
        police.setNextDirectionChangeTime(Long.MAX_VALUE);

        when(arena.getPolice()).thenReturn(Collections.singletonList(police));

        long currentTime = 1000;
        controller.step(game, GUI.ACTION.NONE, currentTime);

        assertEquals(Direction.UP_LEFT, police.getDirection(), "Police should rotate counter-clockwise towards target");
    }

    @Test
    public void testPoliceDirectionChangeInterval() throws IOException {
        Police police = new Police(10, 10, Direction.UP);
        police.setTargetDirection(Direction.UP);
        police.setNextDirectionChangeTime(1000);

        when(arena.getPolice()).thenReturn(Collections.singletonList(police));
        when(arena.isEmpty(any(Position.class))).thenReturn(true);

        controller.step(game, GUI.ACTION.NONE, 1000);

        assertTrue(police.getNextDirectionChangeTime() > 1000);
    }
}
