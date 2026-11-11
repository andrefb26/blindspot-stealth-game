package project.states;

import org.junit.jupiter.api.Test;
import project.controller.Controller;
import project.controller.menu.LevelMenuController;
import project.model.menu.LevelMenu;
import project.viewer.Viewer;
import project.viewer.menu.LevelMenuViewer;

import static org.junit.jupiter.api.Assertions.*;

public class LevelMenuStateTest {

    @Test
    public void testLevelMenuStateCreation() {
        LevelMenu levelMenu = new LevelMenu(3);
        LevelMenuState state = new LevelMenuState(levelMenu);

        assertNotNull(state);
        assertEquals(levelMenu, state.getModel());
    }

    @Test
    public void testGetViewer() {
        LevelMenu levelMenu = new LevelMenu(3);
        LevelMenuState state = new LevelMenuState(levelMenu);

        Viewer<LevelMenu> viewer = state.getViewer();

        assertNotNull(viewer);
        assertTrue(viewer instanceof LevelMenuViewer);
    }

    @Test
    public void testGetController() {
        LevelMenu levelMenu = new LevelMenu(3);
        LevelMenuState state = new LevelMenuState(levelMenu);

        Controller<LevelMenu> controller = state.getController();

        assertNotNull(controller);
        assertTrue(controller instanceof LevelMenuController);
    }
}
