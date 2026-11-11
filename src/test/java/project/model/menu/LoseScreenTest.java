package project.model.menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoseScreenTest {

    @Test
    public void testLoseScreenCreation() {
        LoseScreen loseScreen = new LoseScreen(1);

        assertEquals(1, loseScreen.getLevel());
    }

    @Test
    public void testLoseScreenWithDifferentLevels() {
        LoseScreen level1 = new LoseScreen(1);
        LoseScreen level2 = new LoseScreen(2);
        LoseScreen level10 = new LoseScreen(10);

        assertEquals(1, level1.getLevel());
        assertEquals(2, level2.getLevel());
        assertEquals(10, level10.getLevel());
    }
}
