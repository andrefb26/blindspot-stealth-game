package project.states;

import org.junit.jupiter.api.Test;
import project.controller.Controller;
import project.controller.menu.WinController;
import project.model.menu.WinScreen;
import project.viewer.Viewer;
import project.viewer.menu.WinViewer;

import static org.junit.jupiter.api.Assertions.*;

public class WinStateTest {

    @Test
    public void testWinStateCreation() {
        WinScreen winScreen = new WinScreen(1, 10000, 2, 4000);
        WinState state = new WinState(winScreen);

        assertNotNull(state);
        assertEquals(winScreen, state.getModel());
    }

    @Test
    public void testGetViewer() {
        WinScreen winScreen = new WinScreen(1, 10000, 2, 4000);
        WinState state = new WinState(winScreen);

        Viewer<WinScreen> viewer = state.getViewer();

        assertNotNull(viewer);
        assertTrue(viewer instanceof WinViewer);
    }

    @Test
    public void testGetController() {
        WinScreen winScreen = new WinScreen(1, 10000, 2, 4000);
        WinState state = new WinState(winScreen);

        Controller<WinScreen> controller = state.getController();

        assertNotNull(controller);
        assertTrue(controller instanceof WinController);
    }
}
