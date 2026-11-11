package project.controller.menu;

import project.Game;
import project.controller.Controller;
import project.gui.GUI;
import project.model.game.arena.Arena;
import project.model.game.arena.LoaderArenaBuilder;
import project.model.menu.LevelMenu;
import project.states.GameState;

import java.io.IOException;

public class LevelMenuController extends Controller<LevelMenu> {
    public LevelMenuController(LevelMenu levelMenu) {
        super(levelMenu);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP -> getModel().previousEntry();
            case DOWN -> getModel().nextEntry();
            case SELECT -> {
                int level = getModel().getSelectedLevel();
                LoaderArenaBuilder builder = new LoaderArenaBuilder(level);
                Arena arena = builder.createArena();
                game.setState(new GameState(arena, level));
            }
        }
    }

}