package project.states;

import project.controller.Controller;
import project.controller.menu.LoseController;
import project.model.menu.LoseScreen;
import project.viewer.Viewer;
import project.viewer.menu.LoseViewer;

public class LoseState extends State<LoseScreen> {
    public LoseState(LoseScreen loseScreen) {
        super(loseScreen);
    }

    @Override
    protected Viewer<LoseScreen> getViewer() {
        return new LoseViewer(getModel());
    }

    @Override
    protected Controller<LoseScreen> getController() {
        return new LoseController(getModel());
    }
}
