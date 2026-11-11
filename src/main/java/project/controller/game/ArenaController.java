package project.controller.game;

import project.Game;
import project.gui.GUI;
import project.model.game.LevelTimes;
import project.model.game.Position;
import project.model.game.arena.Arena;
import project.model.game.elements.Thief;
import project.model.menu.LoseScreen;
import project.model.menu.WinScreen;
import project.states.LoseState;
import project.states.WinState;


import java.io.IOException;

public class ArenaController extends GameController {
    private final ThiefController thiefcontroller;
    private final PoliceController policeController;
    private final CamerasController camerasController;
    private final int level;
    private final LightController lightController;


    private long startTime = -1;

    public ArenaController(Arena arena, int level) {
        super(arena);
        this.level = level;
        this.thiefcontroller = new ThiefController(arena);
        this.policeController = new PoliceController(arena);
        this.camerasController= new CamerasController(arena);
        this.lightController = new LightController(arena);
        arena.setElapsedTime(0);
    }

    private boolean isAdjacent(Position a, Position b) {
        int dx = Math.abs(a.getX() - b.getX());
        int dy = Math.abs(a.getY() - b.getY());
        return (dx != 0 || dy != 0) && (dx <= 1 && dy <= 1);
    }

    private void win(Game game, long elapsed) {
        if (!getModel().getThief().getHasKey()) return;

        Position thiefPosition = getModel().getThief().getPosition();
        Position safePosition = getModel().getSafe().getPosition();

        if(isAdjacent(thiefPosition, safePosition)) {
            Thief thief = getModel().getThief();
            int coinsCollected = thief.getCoinCounter();

            long adjustedTime = LevelTimes.calculateAdjustedTime(coinsCollected, elapsed);

            LevelTimes.registerTime(level, adjustedTime);

            System.out.println("WIN level=" + level + " time=" + adjustedTime + " (Bonus Applied)");

            WinScreen winScreen = new WinScreen(level, elapsed, coinsCollected, adjustedTime);

            game.setState(new WinState(winScreen));
        }
    }

    private void lose(Game game) {
        Thief thief = getModel().getThief();
        Position position = thief.getPosition();
        if (getModel().getLight().contains(position)) {
            game.setState(new LoseState(new LoseScreen(level)));
        }

    }

    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (startTime == -1) startTime = time; //iniciar tempo do nível
        long elapsed = time - startTime;
        if (elapsed < 0) elapsed = 0;
        getModel().setElapsedTime(elapsed);

        thiefcontroller.step(game,action,time);
        policeController.step(game,GUI.ACTION.NONE,time);
        camerasController.step(game, GUI.ACTION.NONE, time);

        getModel().setLight(lightController.AllLight(getModel()));

        win(game, elapsed);
        lose(game);
    }
}
