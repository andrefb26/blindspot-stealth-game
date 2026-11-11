package project.controller.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.Game;
import project.gui.GUI;
import project.model.menu.Menu;
import project.states.LevelMenuState;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MenuControllerTest {
    private MenuController controller;
    private Menu menu;
    private Game game;

    @BeforeEach
    public void setUp() {
        menu = new Menu();
        controller = new MenuController(menu);
        game = Mockito.mock(Game.class);
    }

    @Test
    public void testUpAction() throws IOException {
        menu.nextEntry(); // move selection to exit

        controller.step(game, GUI.ACTION.UP, 0);

        assertTrue(menu.isSelectedStart());
    }

    @Test
    public void testDownAction() throws IOException {
        controller.step(game, GUI.ACTION.DOWN, 0);

        assertTrue(menu.isSelectedExit());
    }

    @Test
    public void testSelectOnExit() throws IOException {
        menu.nextEntry(); // move selection to exit

        controller.step(game, GUI.ACTION.SELECT, 0);

        verify(game, times(1)).setState(null);
    }

    @Test
    public void testSelectOnStart() throws IOException {
        // start at play
        controller.step(game, GUI.ACTION.SELECT, 0);

        // switches to level menu
        verify(game, times(1)).setState(any(LevelMenuState.class));
    }

    @Test
    public void testNoActionOnNone() throws IOException {
        controller.step(game, GUI.ACTION.NONE, 0);

        // state stays the same
        verify(game, never()).setState(any());
    }
}
