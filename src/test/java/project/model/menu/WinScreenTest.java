package project.model.menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WinScreenTest {

    @Test
    public void testWinScreenCreation() {
        WinScreen winScreen = new WinScreen(1, 10000, 2, 4000);

        assertEquals(1, winScreen.getLevel());
        assertEquals(10000, winScreen.getElapsedTime());
        assertEquals(2, winScreen.getCoinCount());
        assertEquals(4000, winScreen.getAdjustedTime());
    }

    @Test
    public void testWinScreenWithNoCoins() {
        WinScreen winScreen = new WinScreen(1, 5000, 0, 5000);

        assertEquals(0, winScreen.getCoinCount());
        assertEquals(5000, winScreen.getElapsedTime());
        assertEquals(5000, winScreen.getAdjustedTime());
    }

    @Test
    public void testWinScreenWithManyCoins() {
        WinScreen winScreen = new WinScreen(2, 20000, 5, 5000);

        assertEquals(5, winScreen.getCoinCount());
        assertEquals(20000, winScreen.getElapsedTime());
        assertEquals(5000, winScreen.getAdjustedTime());
    }

    @Test
    public void testWinScreenWithZeroTime() {
        WinScreen winScreen = new WinScreen(1, 6000, 2, 0);

        assertEquals(0, winScreen.getAdjustedTime());
    }

    @Test
    public void testWinScreenDifferentLevels() {
        WinScreen level1 = new WinScreen(1, 1000, 0, 1000);
        WinScreen level2 = new WinScreen(2, 2000, 1, 0);
        WinScreen level3 = new WinScreen(3, 3000, 3, 0);

        assertEquals(1, level1.getLevel());
        assertEquals(2, level2.getLevel());
        assertEquals(3, level3.getLevel());
    }
}
