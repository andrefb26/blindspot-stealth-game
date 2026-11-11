package project.model.menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    @Test
    public void testMenuCreation() {
        Menu menu = new Menu();

        assertEquals(2, menu.getNumberEntries());
        assertEquals("Play", menu.getEntry(0));
        assertEquals("Exit", menu.getEntry(1));
    }

    @Test
    public void testInitialSelection() {
        Menu menu = new Menu();

        assertTrue(menu.isSelected(0));
        assertFalse(menu.isSelected(1));
        assertTrue(menu.isSelectedStart());
        assertFalse(menu.isSelectedExit());
    }

    @Test
    public void testNextEntry() {
        Menu menu = new Menu();

        assertTrue(menu.isSelected(0));

        menu.nextEntry();
        assertFalse(menu.isSelected(0));
        assertTrue(menu.isSelected(1));
        assertTrue(menu.isSelectedExit());
    }

    @Test
    public void testNextEntryWrapping() {
        Menu menu = new Menu();

        menu.nextEntry(); // move to exit
        assertTrue(menu.isSelected(1));

        menu.nextEntry(); // returns to play
        assertTrue(menu.isSelected(0));
        assertTrue(menu.isSelectedStart());
    }

    @Test
    public void testPreviousEntry() {
        Menu menu = new Menu();
        menu.nextEntry(); // move to exit

        assertTrue(menu.isSelected(1));

        menu.previousEntry();
        assertTrue(menu.isSelected(0));
        assertTrue(menu.isSelectedStart());
    }

    @Test
    public void testPreviousEntryWrapping() {
        Menu menu = new Menu();

        assertTrue(menu.isSelected(0));

        menu.previousEntry(); // returns to exit
        assertTrue(menu.isSelected(1)); 
        assertTrue(menu.isSelectedExit());
    }

    @Test
    public void testMultipleNavigations() {
        Menu menu = new Menu();

        menu.nextEntry();
        menu.nextEntry();
        menu.nextEntry();
        assertTrue(menu.isSelected(1)); // selection is exit after 3 moves

        menu.previousEntry();
        menu.previousEntry();
        assertTrue(menu.isSelected(1)); // selection is exit after 2 moves
    }
}
