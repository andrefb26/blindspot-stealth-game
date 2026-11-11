package project.states;

import org.junit.jupiter.api.Test;
import project.controller.Controller;
import project.controller.menu.MenuController;
import project.model.menu.Menu;
import project.viewer.Viewer;
import project.viewer.menu.MenuViewer;

import static org.junit.jupiter.api.Assertions.*;

public class MenuStateTest {

    @Test
    public void testMenuStateCreation() {
        Menu menu = new Menu();
        MenuState menuState = new MenuState(menu);

        assertNotNull(menuState);
        assertEquals(menu, menuState.getModel());
    }

    @Test
    public void testGetViewer() {
        Menu menu = new Menu();
        MenuState menuState = new MenuState(menu);

        Viewer<Menu> viewer = menuState.getViewer();

        assertNotNull(viewer);
        assertTrue(viewer instanceof MenuViewer);
    }

    @Test
    public void testGetController() {
        Menu menu = new Menu();
        MenuState menuState = new MenuState(menu);

        Controller<Menu> controller = menuState.getController();

        assertNotNull(controller);
        assertTrue(controller instanceof MenuController);
    }
}
