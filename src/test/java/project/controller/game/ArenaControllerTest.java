package project.controller.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.Game;
import project.gui.GUI;
import project.model.game.Position;
import project.model.game.arena.Arena;
import project.model.game.elements.Safe;
import project.model.game.elements.Thief;
import project.states.LoseState;
import project.states.WinState;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ArenaControllerTest {
    private ArenaController controller;
    private Safe safe;
    private Arena arena;
    private Game game;

    @BeforeEach
    public void setUp() {
        arena = Mockito.mock(Arena.class);
        game = Mockito.mock(Game.class);
        controller = new ArenaController(arena, 1);
    }

    @Test
    public void testWinCondition() throws IOException {
        Thief thief = Mockito.mock(Thief.class);
        Safe safe = Mockito.mock(Safe.class);

        when(arena.getThief()).thenReturn(thief);
        when(arena.getSafe()).thenReturn(safe);

        when(thief.getHasKey()).thenReturn(true);
        when(thief.getCoinCounter()).thenReturn(2);

        when(thief.getPosition()).thenReturn(new Position(5, 5));
        when(safe.getPosition()).thenReturn(new Position(5, 6));

        long elapsed = 10000;
        controller.step(game, GUI.ACTION.NONE, elapsed);

        org.mockito.ArgumentCaptor<WinState> stateCaptor = org.mockito.ArgumentCaptor.forClass(WinState.class);
        verify(game, times(1)).setState(stateCaptor.capture());

        assertNotNull(stateCaptor.getValue());
    }

    @Test
    public void testLoseCondition() throws IOException {
        Thief thief = Mockito.mock(Thief.class);
        when(arena.getThief()).thenReturn(thief);
        Position thiefPos = new Position(1, 1);
        when(thief.getPosition()).thenReturn(thiefPos);

        // thief is in light
        Set<Position> lights = new HashSet<>();
        lights.add(thiefPos);
        when(arena.getLight()).thenReturn(lights);

        controller.step(game, GUI.ACTION.NONE, 1000);

        // check if the game switched to the lose state
        verify(game, times(1)).setState(any(LoseState.class));
    }
}
