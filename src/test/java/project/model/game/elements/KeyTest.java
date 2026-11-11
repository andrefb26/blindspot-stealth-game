package project.model.game.elements;

import org.junit.jupiter.api.Test;
import project.model.game.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KeyTest {

    @Test
    public void testKeyCreation() {
        Key key = new Key(1, 9);
        assertEquals(new Position(1, 9), key.getPosition());
    }
}
