package project.controller.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.Game;
import project.gui.GUI;
import project.model.menu.WinScreen;
import project.states.LevelMenuState;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class WinControllerTest {
    private WinController controller;
    private WinScreen winScreen;
    private Game game;

    @BeforeEach
    public void setUp() {
        winScreen = new WinScreen(1, 10000, 2, 4000);
        controller = new WinController(winScreen);
        game = Mockito.mock(Game.class);
    }

    @Test
    public void testSelectAction() throws IOException {
        controller.step(game, GUI.ACTION.SELECT, 0);

        verify(game, times(1)).setState(any(LevelMenuState.class));
    }

    @Test
    public void testNoActionOnOtherInputs() throws IOException {
        controller.step(game, GUI.ACTION.NONE, 0);

        verify(game, never()).setState(any());
    }

    @Test
    public void testSelectWithDifferentWinScreens() throws IOException {
        WinScreen level2Win = new WinScreen(2, 5000, 0, 5000);
        WinController controller2 = new WinController(level2Win);

        controller2.step(game, GUI.ACTION.SELECT, 0);

        // returns to the level menu
        verify(game, times(1)).setState(any(LevelMenuState.class));
    }
}
