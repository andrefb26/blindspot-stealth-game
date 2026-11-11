package project.viewer.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.gui.GUI;
import project.model.menu.WinScreen;

import java.awt.*;

import static org.mockito.Mockito.*;

public class WinViewerTest {
    private WinViewer viewer;
    private WinScreen winScreen;
    private GUI gui;

    @BeforeEach
    public void setUp() {
        // level, time, and coin stats
        winScreen = new WinScreen(1, 60000, 5, 50000);
        viewer = new WinViewer(winScreen);
        gui = Mockito.mock(GUI.class);
        when(gui.getWidth()).thenReturn(80);
        when(gui.getHeight()).thenReturn(40);
    }

    @Test
    public void testDrawElements() {
        viewer.drawElements(gui);

        verify(gui, times(1)).setOffset(0, 0);
        //expects header, stats and options
        verify(gui, atLeast(6)).drawImage(any(), any());
    }
}
