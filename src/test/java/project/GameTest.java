package project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.gui.LanternaGUI;
import project.states.State;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class GameTest {
    private Game game;
    private LanternaGUI gui;

    @BeforeEach
    public void setUp() throws Exception {
        gui = Mockito.mock(LanternaGUI.class);
        game = new Game(gui);
    }

    @Test
    public void testSetState() {
        State state = Mockito.mock(State.class);
        game.setState(state);
        //verify state can be set and accessed 
    }

    @Test
    public void testStart() throws IOException {
        State state = Mockito.mock(State.class);
        game.setState(state);

        //exit the loop after one step
        doAnswer(invocation -> {
            game.setState(null);
            return null;
        }).when(state).step(eq(game), eq(gui), anyLong());

        game.start();

        verify(state, times(1)).step(eq(game), eq(gui), anyLong());
        verify(gui, times(1)).close();
    }
}
