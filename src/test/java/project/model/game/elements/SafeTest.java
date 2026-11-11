package project.model.game.elements;

import org.junit.jupiter.api.Test;
import project.model.game.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SafeTest {

    @Test
    public void testSafeCreation() {
        Safe safe = new Safe(5, 5);
        assertEquals(new Position(5, 5), safe.getPosition());
    }
}
