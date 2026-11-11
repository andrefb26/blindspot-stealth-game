package project.viewer.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.gui.GUI;
import project.model.menu.LevelMenu;

import java.awt.*;

import static org.mockito.Mockito.*;

public class LevelMenuViewerTest {
    private LevelMenuViewer viewer;
    private LevelMenu levelMenu;
    private GUI gui;

    @BeforeEach
    public void setUp() {
        levelMenu = new LevelMenu(3);
        viewer = new LevelMenuViewer(levelMenu);
        gui = Mockito.mock(GUI.class);
        when(gui.getWidth()).thenReturn(80);
        when(gui.getHeight()).thenReturn(40);
    }

    @Test
    public void testDrawElements() {
        viewer.drawElements(gui);

        verify(gui, times(1)).setOffset(0, 0);
        //expects title and level options
        verify(gui, atLeast(5)).drawImage(any(), any());
    }
}
