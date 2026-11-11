package project.states;


import project.Game;
import project.controller.Controller;
import project.gui.GUI;
import project.model.menu.Menu;
import project.viewer.Viewer;
import java.io.IOException;



public abstract class State<T> {
    private final T model;
    private Controller<T> controller;
    private Viewer<T> viewer;

    public State(T model) {
        this.model = model;
    }

    protected abstract Viewer<T> getViewer();

    protected abstract Controller<T> getController();

    public T getModel() {
        return model;
    }

    public void step(Game game, GUI gui, long time) throws IOException {
        if (controller == null) controller = getController();
        if (viewer == null) viewer = getViewer();

        GUI.ACTION action = gui.getNextAction();
        if (action == GUI.ACTION.QUIT) {

            game.setState(new MenuState(new Menu()));
            return;
        }
        controller.step(game, action, time);
        viewer.draw(gui);
    }
}