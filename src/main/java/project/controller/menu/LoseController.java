package project.controller.menu;

import project.Game;
import project.controller.Controller;
import project.gui.GUI;
import project.model.game.arena.Arena;
import project.model.game.arena.LoaderArenaBuilder;
import project.model.menu.LevelMenu;
import project.model.menu.LoseScreen;
import project.states.GameState;
import project.states.LevelMenuState;

import java.io.IOException;

public class LoseController extends Controller<LoseScreen> {
    public LoseController(LoseScreen loseScreen) {
        super(loseScreen);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action){
            case QUIT:
                game.setState(new LevelMenuState(new LevelMenu(3)));
                break;
            case SELECT:
                int level = getModel().getLevel();

                if(level < 1) level = 1;

                LoaderArenaBuilder builder = new LoaderArenaBuilder(level);
                Arena arena = builder.createArena();
                game.setState(new GameState(arena, level));

                break;
        }
    }
}
