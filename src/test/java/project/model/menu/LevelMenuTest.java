package project.model.menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LevelMenuTest {

    @Test
    public void testLevelMenuCreation() {
        LevelMenu menu = new LevelMenu(3);

        assertEquals(3, menu.getNumberEntries());
        assertEquals("Level 1", menu.getEntry(0));
        assertEquals("Level 2", menu.getEntry(1));
        assertEquals("Level 3", menu.getEntry(2));
    }

    @Test
    public void testInitialSelection() {
        LevelMenu menu = new LevelMenu(5);

        assertTrue(menu.isSelected(0));
        assertEquals(1, menu.getSelectedLevel());
    }

    @Test
    public void testNextEntry() {
        LevelMenu menu = new LevelMenu(3);

        assertEquals(1, menu.getSelectedLevel());

        menu.nextEntry();
        assertTrue(menu.isSelected(1));
        assertEquals(2, menu.getSelectedLevel());

        menu.nextEntry();
        assertTrue(menu.isSelected(2));
        assertEquals(3, menu.getSelectedLevel());
    }

    @Test
    public void testNextEntryWrapping() {
        LevelMenu menu = new LevelMenu(3);

        menu.nextEntry(); // level 2
        menu.nextEntry(); // level 3
        menu.nextEntry(); // wraps to level 1

        assertTrue(menu.isSelected(0));
        assertEquals(1, menu.getSelectedLevel());
    }

    @Test
    public void testPreviousEntry() {
        LevelMenu menu = new LevelMenu(3);
        menu.nextEntry(); // level 2
        menu.nextEntry(); // level 3

        assertEquals(3, menu.getSelectedLevel());

        menu.previousEntry();
        assertEquals(2, menu.getSelectedLevel());

        menu.previousEntry();
        assertEquals(1, menu.getSelectedLevel());
    }

    @Test
    public void testPreviousEntryWrapping() {
        LevelMenu menu = new LevelMenu(3);

        assertEquals(1, menu.getSelectedLevel());

        menu.previousEntry(); // returns to level 3
        assertTrue(menu.isSelected(2));
        assertEquals(3, menu.getSelectedLevel());
    }

    @Test
    public void testSingleLevel() {
        LevelMenu menu = new LevelMenu(1);

        assertEquals(1, menu.getNumberEntries());
        assertEquals(1, menu.getSelectedLevel());

        menu.nextEntry();
        assertEquals(1, menu.getSelectedLevel()); // stays at level 1

        menu.previousEntry();
        assertEquals(1, menu.getSelectedLevel()); // stays at level 1
    }

    @Test
    public void testManyLevels() {
        LevelMenu menu = new LevelMenu(10);

        assertEquals(10, menu.getNumberEntries());

        for (int i = 0; i < 10; i++) {
            assertEquals("Level " + (i + 1), menu.getEntry(i));
        }
    }

    @Test
    public void testGetCurrentEntry() {
        LevelMenu menu = new LevelMenu(3);

        assertEquals("Level 1", menu.getCurrentEntry(0));
        assertEquals("Level 2", menu.getCurrentEntry(1));
        assertEquals("Level 3", menu.getCurrentEntry(2));
    }
}
