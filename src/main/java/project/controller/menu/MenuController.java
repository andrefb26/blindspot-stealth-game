package project.controller.menu;

import project.Game;
import project.controller.Controller;
import project.gui.GUI;
import project.model.game.arena.LoaderArenaBuilder;
import project.model.menu.LevelMenu;
import project.model.menu.Menu;
import project.states.GameState;
import project.states.LevelMenuState;

import java.io.IOException;

public class MenuController extends Controller<Menu> {
    public MenuController(Menu menu) {
        super(menu);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP:
                getModel().previousEntry();
                break;
            case DOWN:
                getModel().nextEntry();
                break;
            case SELECT:
                if (getModel().isSelectedExit()) game.setState(null);
                if (getModel().isSelectedStart()) game.setState(new LevelMenuState(new LevelMenu(3)));
        }
    }
}