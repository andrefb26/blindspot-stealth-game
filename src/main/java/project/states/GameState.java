package project.states;

import project.model.game.arena.Arena;
import project.viewer.Viewer;
import project.viewer.game.GameViewer;
import project.controller.Controller;
import project.controller.game.ArenaController;

public class GameState extends State<Arena> {
    private final int level;

    public GameState(Arena arena, int level) {
        super(arena);
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    @Override
    protected Viewer<Arena> getViewer() {
        return new GameViewer(getModel());
    }

    @Override
    protected Controller<Arena> getController() {
        return new ArenaController(getModel(), level);
    }
}