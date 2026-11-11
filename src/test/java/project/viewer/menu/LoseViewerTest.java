package project.viewer.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.gui.GUI;
import project.model.menu.LoseScreen;

import java.awt.*;

import static org.mockito.Mockito.*;

public class LoseViewerTest {
    private LoseViewer viewer;
    private LoseScreen loseScreen;
    private GUI gui;

    @BeforeEach
    public void setUp() {
        loseScreen = new LoseScreen(1);
        viewer = new LoseViewer(loseScreen);
        gui = Mockito.mock(GUI.class);
        when(gui.getWidth()).thenReturn(80);
        when(gui.getHeight()).thenReturn(40);
    }

    @Test
    public void testDrawElements() {
        viewer.drawElements(gui);

        verify(gui, times(1)).setOffset(0, 0);
        //expects header and options
        verify(gui, atLeast(4)).drawImage(any(), any());
    }
}
