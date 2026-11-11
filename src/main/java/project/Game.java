package project;

import project.gui.LanternaGUI;
import project.states.MenuState;
import project.states.State;
import project.model.menu.Menu;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final LanternaGUI gui;
    private State state;

    public Game() throws FontFormatException, IOException, URISyntaxException {
        this(new LanternaGUI(60, 25));
    }

    public Game(LanternaGUI gui) {
        this.gui = gui;
        this.state = new MenuState(new Menu());
    }

    public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException {
        new Game().start();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void start() throws IOException {
        int FPS = 30;
        int frameTime = 1000 / FPS;

        while (this.state != null) {
            long startTime = System.currentTimeMillis();

            state.step(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime; 

            try {
                if (sleepTime > 0)
                    Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
        }

        gui.close();
    }

}