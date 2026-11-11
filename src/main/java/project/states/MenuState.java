package project.states;


import project.controller.Controller;
import project.controller.menu.MenuController;
import project.model.menu.Menu;
import project.viewer.Viewer;
import project.viewer.menu.MenuViewer;

public class MenuState extends State<Menu> {
    public MenuState(Menu model) {
        super(model);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer(getModel());
    }

   @Override
    protected Controller<Menu> getController() {
        return new MenuController(getModel());
    }
}