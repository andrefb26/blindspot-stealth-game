package project.model.game.elements;

import org.junit.jupiter.api.Test;
import project.model.game.Position;
import project.model.game.movement.Direction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ThiefTest {

    @Test
    public void testThiefCreation() {
        Thief thief = new Thief(10, 15);
        assertEquals(new Position(10, 15), thief.getPosition());
        assertFalse(thief.getHasKey());
        assertEquals(0, thief.getCoinCounter());
    }

    @Test
    public void testIncreaseCoinCounter() {
        Thief thief = new Thief(5, 5);
        thief.increaseCoinCounter(3);
        assertEquals(3, thief.getCoinCounter());
        thief.increaseCoinCounter(2);
        assertEquals(5, thief.getCoinCounter());
    }

    @Test
    public void testIncreaseCoinCounterNegative() {
        Thief thief = new Thief(5, 5);
        thief.increaseCoinCounter(-5);
        assertEquals(0, thief.getCoinCounter());
    }

    @Test
    public void testResetCoinCounter() {
        Thief thief = new Thief(5, 5);
        thief.increaseCoinCounter(10);
        assertEquals(10, thief.getCoinCounter());
        thief.resetCoinCounter();
        assertEquals(0, thief.getCoinCounter());
    }

    @Test
    public void testSetHasKey() {
        Thief thief = new Thief(5, 5);
        assertFalse(thief.getHasKey());
        thief.setHasKey(true);
        assertTrue(thief.getHasKey());
        thief.setHasKey(false);
        assertFalse(thief.getHasKey());
    }

    @Test
    public void testGetNextPosition() {
        Thief thief = new Thief(5, 5);
        assertEquals(new Position(5, 4), thief.getNextPosition(Direction.UP));
        assertEquals(new Position(5, 6), thief.getNextPosition(Direction.DOWN));
        assertEquals(new Position(4, 5), thief.getNextPosition(Direction.LEFT));
        assertEquals(new Position(6, 5), thief.getNextPosition(Direction.RIGHT));
    }
}
