package project.states;

import org.junit.jupiter.api.Test;
import project.controller.Controller;
import project.controller.menu.LoseController;
import project.model.menu.LoseScreen;
import project.viewer.Viewer;
import project.viewer.menu.LoseViewer;

import static org.junit.jupiter.api.Assertions.*;

public class LoseStateTest {

    @Test
    public void testLoseStateCreation() {
        LoseScreen loseScreen = new LoseScreen(1);
        LoseState state = new LoseState(loseScreen);

        assertNotNull(state);
        assertEquals(loseScreen, state.getModel());
    }

    @Test
    public void testGetViewer() {
        LoseScreen loseScreen = new LoseScreen(1);
        LoseState state = new LoseState(loseScreen);

        Viewer<LoseScreen> viewer = state.getViewer();

        assertNotNull(viewer);
        assertTrue(viewer instanceof LoseViewer);
    }

    @Test
    public void testGetController() {
        LoseScreen loseScreen = new LoseScreen(1);
        LoseState state = new LoseState(loseScreen);

        Controller<LoseScreen> controller = state.getController();

        assertNotNull(controller);
        assertTrue(controller instanceof LoseController);
    }
}
