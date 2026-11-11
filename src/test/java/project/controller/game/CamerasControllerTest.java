package project.controller.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.Game;
import project.gui.GUI;
import project.model.game.arena.Arena;
import project.model.game.elements.Camera;
import project.model.game.movement.Direction;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CamerasControllerTest {

    private CamerasController controller;
    private Arena arena;
    private Game game;

    @BeforeEach
    public void setUp() {
        arena = mock(Arena.class);
        game = mock(Game.class);
        controller = new CamerasController(arena);
    }

    @Test
    public void testCameraRotation() throws IOException {
        Camera camera = new Camera(5, 5, Direction.UP);
        when(arena.getCameras()).thenReturn(Collections.singletonList(camera));

        long rotationInterval = 500;
        long pauseDuration = 2000;

        long time = 500;

        controller.step(game, GUI.ACTION.NONE, time);
        assertEquals(Direction.UP, camera.getInitialDirection());
        long nextMove = camera.getNextMovementTime();

        time = nextMove + 10;
        controller.step(game, GUI.ACTION.NONE, time);
        assertEquals(Direction.UP_RIGHT, camera.getDirection(), "Should rotate to UP_RIGHT");

        time = camera.getNextMovementTime() + 10;
        controller.step(game, GUI.ACTION.NONE, time);
        assertEquals(Direction.RIGHT, camera.getDirection(), "Should rotate to RIGHT");
        assertEquals(time + rotationInterval, camera.getNextMovementTime(),
                "Standard wait after reaching RIGHT");

        time = camera.getNextMovementTime() + 10;
        controller.step(game, GUI.ACTION.NONE, time);

        assertEquals(Direction.RIGHT, camera.getDirection(), "Should be pausing at RIGHT");

        // rotation sense flips at the boundary
        assertEquals(-1, camera.getCurrentRotationSense(), "Rotation sense flips at the boundary");
        // camera pauses at the boundary
        assertTrue(camera.getNextMovementTime() >= time + pauseDuration, "Camera pauses at the boundary");
    }

    @Test
    void testCameraRotationBoundaries() throws IOException {
        Camera camera = new Camera(5, 5, Direction.UP);
        camera.setInitialDirection(Direction.UP);
        camera.setCurrentRotationSense(-1); // start rotating counter-clockwise
        when(arena.getCameras()).thenReturn(Collections.singletonList(camera));

        long time = 1000;
        camera.setNextMovementTime(time);

        // move to up left
        controller.step(game, GUI.ACTION.NONE, time + 1);
        assertEquals(Direction.UP_LEFT, camera.getDirection());

        // move to left
        time = camera.getNextMovementTime();
        controller.step(game, GUI.ACTION.NONE, time + 1);
        assertEquals(Direction.LEFT, camera.getDirection());

        // check left boundary and flip
        time = camera.getNextMovementTime();
        controller.step(game, GUI.ACTION.NONE, time + 1);

        // reaches boundary and flips
        assertEquals(1, camera.getCurrentRotationSense(), "Should flip rotation sense to 1");
        assertTrue(camera.getNextMovementTime() > time + 1);
    }
}
