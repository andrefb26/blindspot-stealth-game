package project.states;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.controller.Controller;
import project.model.game.arena.Arena;
import project.viewer.Viewer;
import project.viewer.game.GameViewer;
import project.controller.game.ArenaController;

import static org.junit.jupiter.api.Assertions.*;

public class GameStateTest {

    @Test
    public void testGameStateCreation() {
        Arena arena = Mockito.mock(Arena.class);
        GameState gameState = new GameState(arena, 1);

        assertNotNull(gameState);
        assertEquals(1, gameState.getLevel());
        assertEquals(arena, gameState.getModel());
    }

    @Test
    public void testGetViewer() {
        Arena arena = Mockito.mock(Arena.class);
        Mockito.when(arena.getWidth()).thenReturn(20);
        Mockito.when(arena.getHeight()).thenReturn(20);
        GameState gameState = new GameState(arena, 1);

        Viewer<Arena> viewer = gameState.getViewer();

        assertNotNull(viewer);
        assertTrue(viewer instanceof GameViewer);
    }

    @Test
    public void testGetController() {
        Arena arena = Mockito.mock(Arena.class);
        GameState gameState = new GameState(arena, 1);

        Controller<Arena> controller = gameState.getController();

        assertNotNull(controller);
        assertTrue(controller instanceof ArenaController);
    }

    @Test
    public void testDifferentLevels() {
        Arena arena = Mockito.mock(Arena.class);

        GameState level1 = new GameState(arena, 1);
        GameState level2 = new GameState(arena, 2);
        GameState level10 = new GameState(arena, 10);

        assertEquals(1, level1.getLevel());
        assertEquals(2, level2.getLevel());
        assertEquals(10, level10.getLevel());
    }

    @Test
    public void testViewerCreatesNewInstance() {
        Arena arena = Mockito.mock(Arena.class);
        Mockito.when(arena.getWidth()).thenReturn(20);
        Mockito.when(arena.getHeight()).thenReturn(20);
        GameState gameState = new GameState(arena, 1);

        Viewer<Arena> viewer1 = gameState.getViewer();
        Viewer<Arena> viewer2 = gameState.getViewer();

        assertNotSame(viewer1, viewer2);
    }

    @Test
    public void testControllerCreatesNewInstance() {
        Arena arena = Mockito.mock(Arena.class);
        GameState gameState = new GameState(arena, 1);

        Controller<Arena> controller1 = gameState.getController();
        Controller<Arena> controller2 = gameState.getController();

        assertNotSame(controller1, controller2);
    }
}
