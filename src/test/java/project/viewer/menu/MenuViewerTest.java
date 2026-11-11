package project.viewer.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.gui.GUI;
import project.model.menu.Menu;

import java.awt.*;

import static org.mockito.Mockito.*;

public class MenuViewerTest {
    private MenuViewer viewer;
    private Menu menu;
    private GUI gui;

    @BeforeEach
    public void setUp() {
        menu = new Menu();
        viewer = new MenuViewer(menu);
        gui = Mockito.mock(GUI.class);
        when(gui.getWidth()).thenReturn(40);
        when(gui.getHeight()).thenReturn(20);
    }

    @Test
    public void testDrawElements() {
        viewer.drawElements(gui);

        verify(gui, times(1)).setOffset(0, 0);
        //expects title and options
        verify(gui, atLeast(3)).drawImage(any(), any());
    }

    @Test
    public void testDrawSelected() {
        menu.nextEntry(); //move to exit
        viewer.drawElements(gui);

        verify(gui, atLeast(3)).drawImage(any(), any());
    }
}
