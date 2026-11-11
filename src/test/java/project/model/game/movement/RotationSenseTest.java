package project.model.game.movement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RotationSenseTest {

    @Test
    public void testEnumValues() {
        RotationSense[] values = RotationSense.values();

        assertEquals(2, values.length);
        assertEquals(RotationSense.CLOCKWISE, values[0]);
        assertEquals(RotationSense.COUNTER_CLOCKWISE, values[1]);
    }

    @Test
    public void testValueOf() {
        assertEquals(RotationSense.CLOCKWISE, RotationSense.valueOf("CLOCKWISE"));
        assertEquals(RotationSense.COUNTER_CLOCKWISE, RotationSense.valueOf("COUNTER_CLOCKWISE"));
    }

    @Test
    public void testValueOfInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            RotationSense.valueOf("INVALID");
        });
    }

    @Test
    public void testEnumEquality() {
        RotationSense cw1 = RotationSense.CLOCKWISE;
        RotationSense cw2 = RotationSense.CLOCKWISE;
        RotationSense ccw = RotationSense.COUNTER_CLOCKWISE;

        assertEquals(cw1, cw2);
        assertNotEquals(cw1, ccw);
        assertSame(cw1, cw2);
    }
}
