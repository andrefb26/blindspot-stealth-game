package project.gui;

import project.model.game.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LanternaGUITest {
        private Screen screen;
        private LanternaGUI gui;
        private TextGraphics tg;

        @BeforeEach
        void setUp() throws IOException, URISyntaxException, FontFormatException {
                screen = Mockito.mock(Screen.class);
                tg = Mockito.mock(TextGraphics.class);

                Mockito.when(screen.newTextGraphics()).thenReturn(tg);

                gui = new LanternaGUI(screen);
        }

        @Test
        void drawThief() {
                gui.setOffset(5, 5);
                gui.drawThief(new Position(1, 1));

                // Should call setBackgroundColor and putString many times for the 8x8 sprite
                Mockito.verify(tg, Mockito.atLeastOnce()).setBackgroundColor(Mockito.any());
                Mockito.verify(tg, Mockito.atLeastOnce()).putString(Mockito.anyInt(), Mockito.anyInt(),
                                Mockito.anyString());
        }

        @Test
        void drawText() {
                gui.drawText(new Position(1, 1), "Hello", "#336699");

                Mockito.verify(tg, Mockito.atLeastOnce()).setBackgroundColor(Mockito.any(TextColor.class));
                Mockito.verify(tg, Mockito.atLeastOnce()).putString(Mockito.anyInt(), Mockito.anyInt(),
                                Mockito.anyString());
        }

        @Test
        void drawWall() {
                gui.drawWall(new Position(1, 1));
                Mockito.verify(tg, Mockito.atLeastOnce()).setBackgroundColor(Mockito.any());
                Mockito.verify(tg, Mockito.atLeastOnce()).putString(Mockito.anyInt(), Mockito.anyInt(),
                                Mockito.anyString());
        }

        @Test
        void drawPolice() {
                gui.drawPolice(new Position(2, 2));
                Mockito.verify(tg, Mockito.atLeastOnce()).setBackgroundColor(Mockito.any());
        }

        @Test
        void getDimensions() {
                com.googlecode.lanterna.TerminalSize size = new com.googlecode.lanterna.TerminalSize(80, 80);
                Mockito.when(screen.getTerminalSize()).thenReturn(size);

                assertEquals(10, gui.getWidth());
                assertEquals(10, gui.getHeight());
        }

        @Test
        void close() throws IOException {
                gui.close();
                Mockito.verify(screen, Mockito.times(1)).close();
        }

        @Test
        void getNextAction_Quit() throws IOException {
                // mock end of file
                Mockito.when(screen.pollInput())
                                .thenReturn(new com.googlecode.lanterna.input.KeyStroke(
                                                com.googlecode.lanterna.input.KeyType.EOF));
                assertEquals(GUI.ACTION.QUIT, gui.getNextAction());

                // mock quit key
                Mockito.when(screen.pollInput())
                                .thenReturn(new com.googlecode.lanterna.input.KeyStroke('q', false, false));
                assertEquals(GUI.ACTION.QUIT, gui.getNextAction());
        }

        @Test
        void getNextAction_Movement() throws IOException {
                Mockito.when(screen.pollInput())
                                .thenReturn(new com.googlecode.lanterna.input.KeyStroke(
                                                com.googlecode.lanterna.input.KeyType.ArrowUp))
                                .thenReturn(null);
                assertEquals(GUI.ACTION.UP, gui.getNextAction());

                Mockito.when(screen.pollInput())
                                .thenReturn(
                                                new com.googlecode.lanterna.input.KeyStroke(
                                                                com.googlecode.lanterna.input.KeyType.ArrowDown))
                                .thenReturn(null);
                assertEquals(GUI.ACTION.DOWN, gui.getNextAction());

                Mockito.when(screen.pollInput())
                                .thenReturn(
                                                new com.googlecode.lanterna.input.KeyStroke(
                                                                com.googlecode.lanterna.input.KeyType.ArrowLeft))
                                .thenReturn(null);
                assertEquals(GUI.ACTION.LEFT, gui.getNextAction());

                Mockito.when(screen.pollInput())
                                .thenReturn(
                                                new com.googlecode.lanterna.input.KeyStroke(
                                                                com.googlecode.lanterna.input.KeyType.ArrowRight))
                                .thenReturn(null);
                assertEquals(GUI.ACTION.RIGHT, gui.getNextAction());
        }

        @Test
        void getNextAction_Diagonals() throws IOException {
                Mockito.when(screen.pollInput())
                                .thenReturn(new com.googlecode.lanterna.input.KeyStroke(
                                                com.googlecode.lanterna.input.KeyType.ArrowUp))
                                .thenReturn(
                                                new com.googlecode.lanterna.input.KeyStroke(
                                                                com.googlecode.lanterna.input.KeyType.ArrowRight))
                                .thenReturn(null);
                assertEquals(GUI.ACTION.UP_RIGHT, gui.getNextAction());

                Mockito.when(screen.pollInput())
                                .thenReturn(
                                                new com.googlecode.lanterna.input.KeyStroke(
                                                                com.googlecode.lanterna.input.KeyType.ArrowDown))
                                .thenReturn(
                                                new com.googlecode.lanterna.input.KeyStroke(
                                                                com.googlecode.lanterna.input.KeyType.ArrowLeft))
                                .thenReturn(null);
                assertEquals(GUI.ACTION.DOWN_LEFT, gui.getNextAction());
        }

        @Test
        void getNextAction_Select() throws IOException {
                Mockito.when(screen.pollInput())
                                .thenReturn(new com.googlecode.lanterna.input.KeyStroke(
                                                com.googlecode.lanterna.input.KeyType.Enter))
                                .thenReturn(null);
                assertEquals(GUI.ACTION.SELECT, gui.getNextAction());
        }

        @Test
        void drawOtherElements() {
                gui.drawCoin(new Position(1, 1));
                gui.drawKey(new Position(2, 2));
                gui.drawBox(new Position(3, 3));
                gui.drawSafe(new Position(4, 4));
                gui.drawCamera(new Position(5, 5));

                Mockito.verify(tg, Mockito.atLeast(5)).setBackgroundColor(Mockito.any());
        }

        @Test
        void drawLight() {
                gui.drawLight(new Position(1, 1));
                Mockito.verify(tg, Mockito.atLeastOnce()).setBackgroundColor(Mockito.any());
                Mockito.verify(tg, Mockito.atLeastOnce()).putString(Mockito.anyInt(), Mockito.anyInt(),
                                Mockito.anyString());
        }

        @Test
        void clearAndRefresh() throws IOException {
                gui.clear();
                Mockito.verify(screen, Mockito.times(1)).clear();

                gui.refresh();
                Mockito.verify(screen, Mockito.times(1)).refresh();
        }
}