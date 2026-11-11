package project.model.game.elements;

import org.junit.jupiter.api.Test;
import project.model.game.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WallTest {

    @Test
    public void testWallCreation() {
        Wall wall = new Wall(2, 4);
        assertEquals(new Position(2, 4), wall.getPosition());
    }
}
