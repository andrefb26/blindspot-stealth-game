
package project.model.game.arena;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.model.game.Position;
import project.model.game.elements.*;
import project.model.game.movement.Direction;
import java.util.Set;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArenaTest {
    private Arena arena;

    @BeforeEach
    public void setUp() {
        arena = new Arena(20, 20);
        arena.setWalls(new ArrayList<>());
        arena.setBoxes(new ArrayList<>());
        arena.setCameras(new ArrayList<>());
        arena.setPolice(new ArrayList<>());
        arena.setCoins(new ArrayList<>());
        arena.setSafe(new Safe(0, 0));
    }

    @Test
    public void testIsEmpty() {
        Position pos = new Position(5, 5);
        assertTrue(arena.isEmpty(pos));

        // Wall
        arena.setWalls(List.of(new Wall(5, 5)));
        assertFalse(arena.isEmpty(pos));
        arena.setWalls(new ArrayList<>());

        // Box
        arena.setBoxes(List.of(new Box(5, 5)));
        assertFalse(arena.isEmpty(pos));
        arena.setBoxes(new ArrayList<>());

        // Camera
        arena.setCameras(List.of(new Camera(5, 5, Direction.UP)));
        assertFalse(arena.isEmpty(pos));
        arena.setCameras(new ArrayList<>());

        // Police
        arena.setPolice(List.of(new Police(5, 5, Direction.UP)));
        assertFalse(arena.isEmpty(pos));
        arena.setPolice(new ArrayList<>());

        // Safe
        arena.setSafe(new Safe(5, 5));
        assertFalse(arena.isEmpty(pos));
    }

    @Test
    public void testIsBox() {
        List<Box> boxes = new ArrayList<>();
        boxes.add(new Box(3, 3));
        arena.setBoxes(boxes);

        assertTrue(arena.isbox(new Position(3, 3)));
        assertFalse(arena.isbox(new Position(3, 4)));
    }

    @Test
    public void testIsSafe() {
        Safe safe = new Safe(10, 10);
        arena.setSafe(safe);

        assertTrue(arena.issafe(new Position(10, 10)));
        assertFalse(arena.issafe(new Position(10, 11)));
        assertFalse(arena.isEmpty(new Position(10, 10)));
    }

    @Test
    public void testIsCamera() {
        List<Camera> cameras = new ArrayList<>();
        cameras.add(new Camera(2, 2, null));
        arena.setCameras(cameras);

        assertTrue(arena.iscamera(new Position(2, 2)));
        assertFalse(arena.iscamera(new Position(2, 3)));
    }

    @Test
    public void testIsWall() {
        List<Wall> walls = new ArrayList<>();
        walls.add(new Wall(1, 1));
        arena.setWalls(walls);

        assertTrue(arena.iswall(new Position(1, 1)));
        assertFalse(arena.iswall(new Position(1, 2)));
    }

    @Test
    public void testCoinPositionAndRemove() {
        List<Coin> coins = new ArrayList<>();
        coins.add(new Coin(4, 4));
        coins.add(new Coin(5, 5));
        arena.setCoins(coins);

        assertTrue(arena.coinPosition(new Position(4, 4)));
        assertTrue(arena.coinPosition(new Position(5, 5)));
        assertFalse(arena.coinPosition(new Position(6, 6)));

        arena.removeCoin(new Position(4, 4));
        assertFalse(arena.coinPosition(new Position(4, 4)));
        assertTrue(arena.coinPosition(new Position(5, 5)));
    }

    @Test
    public void testKeyPositionAndRemove() {
        Key key = new Key(7, 7);
        arena.setKey(key);

        assertTrue(arena.keyPosition(new Position(7, 7)));
        assertFalse(arena.keyPosition(new Position(7, 8)));

        arena.removeKey();
        assertFalse(arena.keyPosition(new Position(7, 7)));
        assertNull(arena.getKey());
    }

    @Test
    public void testLightMethods() {
        arena.setPolice(List.of(new Police(1, 1, Direction.RIGHT)));
        arena.setCameras(List.of(new Camera(5, 5, Direction.DOWN)));
        arena.setSafe(new Safe(10, 10)); // Safe is blocking

        project.controller.game.LightController lightController = new project.controller.game.LightController(arena);
        Set<Position> allLight = lightController.AllLight(arena);

        assertFalse(allLight.isEmpty());
    }

    @Test
    public void testElapsedTime() {
        arena.setElapsedTime(5000L);
        assertEquals(5000L, arena.getElapsedTime());
    }

    @Test
    public void testThief() {
        Thief thief = new Thief(2, 2);
        arena.setThief(thief);
        assertEquals(thief, arena.getThief());
    }

    @Test
    public void testRemoveCoinEdgeCases() {
        arena.setCoins(null);
        arena.removeCoin(new Position(1, 1)); // Should not crash
        assertFalse(arena.coinPosition(new Position(1, 1)));
    }
}
