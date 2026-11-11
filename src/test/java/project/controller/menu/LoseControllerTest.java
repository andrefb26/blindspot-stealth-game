package project.controller.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.Game;
import project.gui.GUI;
import project.model.menu.LoseScreen;
import project.states.GameState;
import project.states.LevelMenuState;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class LoseControllerTest {
    private LoseController controller;
    private LoseScreen loseScreen;
    private Game game;

    @BeforeEach
    public void setUp() {
        loseScreen = new LoseScreen(1);
        controller = new LoseController(loseScreen);
        game = Mockito.mock(Game.class);
    }

    @Test
    public void testQuitAction() throws IOException {
        controller.step(game, GUI.ACTION.QUIT, 0);

        // returns to the level menu
        verify(game, times(1)).setState(any(LevelMenuState.class));
    }

    @Test
    public void testSelectAction() throws IOException {
        controller.step(game, GUI.ACTION.SELECT, 0);

        // restarts the level
        verify(game, times(1)).setState(any(GameState.class));
    }

    @Test
    public void testSelectActionWithDifferentLevels() throws IOException {
        LoseScreen level2 = new LoseScreen(2);
        LoseController controller2 = new LoseController(level2);

        controller2.step(game, GUI.ACTION.SELECT, 0);

        // restarts level 2
        verify(game, times(1)).setState(any(GameState.class));
    }

    @Test
    public void testSelectActionWithInvalidLevel() throws IOException {
        LoseScreen invalidLevel = new LoseScreen(0);
        LoseController controllerInvalid = new LoseController(invalidLevel);

        controllerInvalid.step(game, GUI.ACTION.SELECT, 0);

        // restarts level 1 if the level is invalid
        verify(game, times(1)).setState(any(GameState.class));
    }
}
