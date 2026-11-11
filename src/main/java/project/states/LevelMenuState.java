package project.states;

import project.controller.Controller;
import project.controller.menu.LevelMenuController;
import project.model.menu.LevelMenu;
import project.viewer.Viewer;
import project.viewer.menu.LevelMenuViewer;

public class LevelMenuState extends State<LevelMenu> {
    public LevelMenuState(LevelMenu model) {
        super(model);
    }

    @Override
    protected Viewer<LevelMenu> getViewer() {
        return new LevelMenuViewer(getModel());
    }

    @Override
    protected Controller<LevelMenu> getController() {
        return new LevelMenuController(getModel());
    }
}
