package project.viewer.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.gui.GUI;
import project.model.game.Position;
import project.model.game.arena.Arena;
import project.model.game.elements.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class GameViewerTest {
    private GameViewer viewer;
    private Arena arena;
    private GUI gui;

    @BeforeEach
    public void setUp() {
        arena = Mockito.mock(Arena.class);
        gui = Mockito.mock(GUI.class);

        when(arena.getWidth()).thenReturn(10);
        when(arena.getHeight()).thenReturn(10);

        viewer = new GameViewer(arena);

        when(arena.getThief()).thenReturn(new Thief(0, 0));
        when(arena.getSafe()).thenReturn(new Safe(0, 0));
        when(arena.getKey()).thenReturn(new Key(0, 0));
        when(arena.getCameras()).thenReturn(new ArrayList<>());
        when(arena.getWalls()).thenReturn(new ArrayList<>());
        when(arena.getBoxes()).thenReturn(new ArrayList<>());
        when(arena.getPolice()).thenReturn(new ArrayList<>());
        when(arena.getCoins()).thenReturn(new ArrayList<>());
        when(arena.getLight()).thenReturn(new HashSet<>());
    }

    @Test
    public void testDrawElements() {
        when(gui.getWidth()).thenReturn(60);
        when(gui.getHeight()).thenReturn(25);
        when(arena.getElapsedTime()).thenReturn(65000L); // 01:05
        Thief thief = new Thief(0, 0);
        thief.increaseCoinCounter(5);
        when(arena.getThief()).thenReturn(thief);

        List<Wall> walls = new ArrayList<>();
        walls.add(new Wall(1, 1));
        when(arena.getWalls()).thenReturn(walls);

        viewer.drawElements(gui);

        // header and timer
        verify(gui, atLeastOnce()).setOffset(0, 0);
        verify(gui, times(1)).drawText(new Position(1, 1), "Time: 01:05", "#FFFFFF");

        // centering
        // arena 10x10 gui 60x25
        // offsetX = (60 - 10) / 2 = 25
        // offsetY = (25 - 10) / 2 = 7.5 -> 7
        verify(gui, times(1)).setOffset(25, 7);

        // elements
        verify(gui, times(1)).drawImage(eq(new Position(0, 0)), any());
        verify(gui, times(1)).drawWall(new Position(1, 1));

        // footer and coins
        // arenaHeight=10 position(1, 14)
        verify(gui, times(1)).drawText(new Position(1, 14), "🪙: 5", "#FFD700");
    }

    @Test
    public void testDrawLight() {
        when(gui.getWidth()).thenReturn(60);
        when(gui.getHeight()).thenReturn(25);
        HashSet<Position> lights = new HashSet<>();
        lights.add(new Position(5, 5));
        when(arena.getLight()).thenReturn(lights);

        viewer.drawElements(gui);

        verify(gui, times(1)).drawLight(new Position(5, 5));
    }
}
