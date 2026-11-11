package project.controller.menu;

import project.Game;
import project.controller.Controller;
import project.gui.GUI;
import project.model.menu.LevelMenu;
import project.model.menu.WinScreen;
import project.states.LevelMenuState;

import java.io.IOException;

public class WinController extends Controller<WinScreen> {
    public WinController(WinScreen winScreen) {
        super(winScreen);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action){
            case SELECT:
                game.setState(new LevelMenuState(new LevelMenu(3)));
        }
    }
}
