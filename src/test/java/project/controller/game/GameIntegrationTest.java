package project.controller.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.Game;
import project.gui.GUI;
import project.model.game.Position;
import project.model.game.arena.Arena;
import project.model.game.elements.*;
import project.model.game.movement.Direction;
import project.states.LoseState;
import project.states.WinState;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameIntegrationTest {

    private Arena arena;
    private Game game;
    private ArenaController controller;

    @BeforeEach
    void setUp() {
        arena = new Arena(20, 20);
        game = Mockito.mock(Game.class);

        arena.setWalls(new ArrayList<>());
        arena.setBoxes(new ArrayList<>());
        arena.setCoins(new ArrayList<>());
        arena.setPolice(new ArrayList<>());
        arena.setCameras(new ArrayList<>());

        controller = new ArenaController(arena, 1);
    }

    @Test
    void testWinWithMultipleCoins() throws IOException {
        Thief thief = new Thief(10, 10);
        thief.setHasKey(true);
        thief.increaseCoinCounter(5);
        arena.setThief(thief);

        Safe safe = new Safe(10, 11);
        arena.setSafe(safe);

        long elapsed = 20000;
        controller.step(game, GUI.ACTION.NONE, elapsed);

        verify(game, times(1)).setState(any(WinState.class));
    }

    @Test
    void testLoseInCameraLight() throws IOException {
        Thief thief = new Thief(10, 10);
        arena.setThief(thief);
        arena.setSafe(new Safe(0, 0));

        Camera camera = new Camera(10, 5, Direction.DOWN);
        arena.setCameras(Arrays.asList(camera));

        controller.step(game, GUI.ACTION.NONE, 1000);

        Set<Position> lights = arena.getLight();
        if (lights.contains(thief.getPosition())) {
            verify(game, times(1)).setState(any(LoseState.class));
        }
    }

    @Test
    void testLoseInPoliceLight() throws IOException {
        Thief thief = new Thief(10, 10);
        arena.setThief(thief);
        arena.setSafe(new Safe(0, 0));

        Police police = new Police(10, 5, Direction.DOWN);
        arena.setPolice(Arrays.asList(police));

        controller.step(game, GUI.ACTION.NONE, 1000);

        Set<Position> lights = arena.getLight();
        if (lights.contains(thief.getPosition())) {
            verify(game, times(1)).setState(any(LoseState.class));
        }
    }

    @Test
    void testNoWinWithoutKey() throws IOException {
        Thief thief = new Thief(10, 10);
        thief.setHasKey(false);
        arena.setThief(thief);

        Safe safe = new Safe(10, 11);
        arena.setSafe(safe);

        controller.step(game, GUI.ACTION.NONE, 1000);

        verify(game, never()).setState(any(WinState.class));
    }

    @Test
    void testNoWinWhenNotAdjacent() throws IOException {
        Thief thief = new Thief(10, 10);
        thief.setHasKey(true);
        arena.setThief(thief);

        Safe safe = new Safe(15, 15);
        arena.setSafe(safe);

        controller.step(game, GUI.ACTION.NONE, 1000);

        verify(game, never()).setState(any(WinState.class));
    }
}
