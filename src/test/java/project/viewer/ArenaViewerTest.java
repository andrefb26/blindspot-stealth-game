package project.viewer;

import project.gui.GUI;
import project.model.game.Position;
import project.model.game.arena.Arena;
import project.model.game.elements.*;
import project.model.game.movement.Direction;
import project.viewer.game.GameViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

class ArenaViewerTest {
    private GUI gui;
    private GameViewer viewer;
    private Arena arena;
    private Safe safe;

    @BeforeEach
    void setUp() {
        arena = new Arena(10, 10);
        gui = Mockito.mock(GUI.class);
        viewer = new GameViewer(arena);

        arena.setCameras(Arrays.asList(new Camera(1, 5, Direction.UP), new Camera(2, 7, Direction.LEFT),
                new Camera(9, 3, Direction.DOWN)));
        arena.setWalls(Arrays.asList(new Wall(1, 2), new Wall(2, 3), new Wall(3, 4)));
        arena.setBoxes(Arrays.asList(new Box(4, 5), new Box(5, 6)));
        arena.setThief(new Thief(5, 8));
        arena.setKey(new Key(2, 4));
        arena.setSafe(this.safe = new Safe(1, 8));
        arena.setPolice(Arrays.asList(new Police(7, 10, Direction.RIGHT)));
        arena.setCoins(new java.util.ArrayList<>());
    }

    @Test
    void drawWalls() throws IOException {
        viewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawWall(new Position(1, 2));
        Mockito.verify(gui, Mockito.times(1)).drawWall(new Position(2, 3));
        Mockito.verify(gui, Mockito.times(1)).drawWall(new Position(3, 4));
        Mockito.verify(gui, Mockito.times(3)).drawWall(Mockito.any(Position.class));
    }

    @Test
    void drawCameras() throws IOException {
        viewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawCamera(new Position(1, 5));
        Mockito.verify(gui, Mockito.times(1)).drawCamera(new Position(2, 7));
        Mockito.verify(gui, Mockito.times(1)).drawCamera(new Position(9, 3));
        Mockito.verify(gui, Mockito.times(3)).drawCamera(Mockito.any(Position.class));
    }

    @Test
    void drawBoxes() throws IOException {
        viewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawBox(new Position(4, 5));
        Mockito.verify(gui, Mockito.times(1)).drawBox(new Position(5, 6));
        Mockito.verify(gui, Mockito.times(2)).drawBox(Mockito.any(Position.class));
    }

    @Test
    void drawThief() throws IOException {
        viewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawThief(new Position(5, 8));
        Mockito.verify(gui, Mockito.times(1)).drawThief(Mockito.any(Position.class));
    }

    @Test
    void drawPolice() throws IOException {
        viewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawPolice(new Position(7, 10));
        Mockito.verify(gui, Mockito.times(1)).drawPolice(Mockito.any(Position.class));
    }

    @Test
    void drawKey() throws IOException {
        viewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawKey(new Position(2, 4));
        Mockito.verify(gui, Mockito.times(1)).drawKey(Mockito.any(Position.class));
    }

    @Test
    void drawSafe() throws IOException {
        viewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawSafe(new Position(1, 8));
        Mockito.verify(gui, Mockito.times(1)).drawSafe(Mockito.any(Position.class));
    }

    @Test
    void refreshAndClear() throws IOException {
        viewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).clear();
        Mockito.verify(gui, Mockito.times(1)).refresh();
    }
}