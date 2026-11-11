package project.model.game.elements;

import org.junit.jupiter.api.Test;
import project.model.game.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoxTest {

    @Test
    public void testBoxCreation() {
        Box box = new Box(7, 8);
        assertEquals(new Position(7, 8), box.getPosition());
    }
}
