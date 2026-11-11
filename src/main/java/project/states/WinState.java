package project.states;

import project.controller.Controller;
import project.controller.menu.MenuController;
import project.controller.menu.WinController;
import project.model.menu.Menu;
import project.model.menu.WinScreen;
import project.viewer.Viewer;
import project.viewer.menu.MenuViewer;
import project.viewer.menu.WinViewer;

public class WinState extends State<WinScreen>{
    public WinState(WinScreen winScreen) {
        super(winScreen);
    }

    @Override
    protected Viewer<WinScreen> getViewer() {
        return new WinViewer(getModel());
    }

    @Override
    protected Controller<WinScreen> getController() {
        return new WinController(getModel());
    }
}

