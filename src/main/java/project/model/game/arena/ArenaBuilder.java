package project.model.game.arena;

import project.model.game.elements.*;

import java.util.List;

public abstract class ArenaBuilder {
    public Arena createArena() {
        Arena arena = new Arena(getWidth(), getHeight());

        arena.setPolice(createPolice());
        arena.setBoxes(createBoxes());
        arena.setWalls(createWalls());
        arena.setThief(createThief());
        arena.setCameras(createCameras());
        arena.setSafe(createSafe());
        arena.setKey(createKey());
        arena.setCoins(createCoins());
        return arena;
    }

    protected abstract int getWidth();

    protected abstract int getHeight();

    protected abstract List<Wall> createWalls();

    protected abstract List<Box> createBoxes();

    protected abstract List<Police> createPolice();

    protected abstract Thief createThief();

    protected abstract List<Camera> createCameras();

    protected abstract Safe createSafe();

    protected abstract Key createKey();

    protected abstract List<Coin> createCoins();
}
