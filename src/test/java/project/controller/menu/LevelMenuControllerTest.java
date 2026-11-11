package project.controller.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.Game;
import project.gui.GUI;
import project.model.menu.LevelMenu;
import project.states.GameState;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class LevelMenuControllerTest {
    private LevelMenuController controller;
    private LevelMenu levelMenu;
    private Game game;

    @BeforeEach
    public void setUp() {
        levelMenu = new LevelMenu(3);
        controller = new LevelMenuController(levelMenu);
        game = Mockito.mock(Game.class);
    }

    @Test
    public void testUpAction() throws IOException {
        levelMenu.nextEntry(); // start at level 2

        controller.step(game, GUI.ACTION.UP, 0);

        assertEquals(1, levelMenu.getSelectedLevel());
    }

    @Test
    public void testDownAction() throws IOException {
        controller.step(game, GUI.ACTION.DOWN, 0);

        assertEquals(2, levelMenu.getSelectedLevel());
    }

    @Test
    public void testSelectLevel1() throws IOException {
        // start at level 1
        controller.step(game, GUI.ACTION.SELECT, 0);

        // switches to level 1
        verify(game, times(1)).setState(any(GameState.class));
    }

    @Test
    public void testSelectLevel2() throws IOException {
        levelMenu.nextEntry(); // move to level 2

        controller.step(game, GUI.ACTION.SELECT, 0);

        // switches to level 2
        verify(game, times(1)).setState(any(GameState.class));
    }

    @Test
    public void testSelectLevel3() throws IOException {
        levelMenu.nextEntry(); // level 2
        levelMenu.nextEntry(); // level 3

        controller.step(game, GUI.ACTION.SELECT, 0);

        // switches to level 3
        verify(game, times(1)).setState(any(GameState.class));
    }
}
