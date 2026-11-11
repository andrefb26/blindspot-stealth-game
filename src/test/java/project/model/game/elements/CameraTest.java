package project.model.game.elements;

import org.junit.jupiter.api.Test;
import project.model.game.Position;
import project.model.game.movement.Direction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CameraTest {

    @Test
    public void testCameraCreation() {
        Camera camera = new Camera(3, 7, Direction.RIGHT);
        assertEquals(new Position(3, 7), camera.getPosition());
        assertEquals(Direction.RIGHT, camera.getDirection());
    }
}
