package project.model.game.arena;

import org.junit.jupiter.api.Test;
import project.model.game.elements.*;
import project.model.game.movement.Direction;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArenaBuilderTest {

    // test implementation of the arena builder
    private static class TestArenaBuilder extends ArenaBuilder {
        private final int width;
        private final int height;

        public TestArenaBuilder(int width, int height) {
            this.width = width;
            this.height = height;
        }

        @Override
        protected int getWidth() {
            return width;
        }

        @Override
        protected int getHeight() {
            return height;
        }

        @Override
        protected List<Wall> createWalls() {
            return Arrays.asList(
                    new Wall(0, 0),
                    new Wall(1, 0),
                    new Wall(2, 0));
        }

        @Override
        protected List<Box> createBoxes() {
            return Arrays.asList(
                    new Box(5, 5),
                    new Box(6, 6));
        }

        @Override
        protected List<Police> createPolice() {
            return Arrays.asList(
                    new Police(10, 10, Direction.UP));
        }

        @Override
        protected Thief createThief() {
            return new Thief(3, 3);
        }

        @Override
        protected List<Camera> createCameras() {
            return Arrays.asList(
                    new Camera(7, 7, Direction.RIGHT));
        }

        @Override
        protected Safe createSafe() {
            return new Safe(15, 15);
        }

        @Override
        protected Key createKey() {
            return new Key(4, 4);
        }

        @Override
        protected List<Coin> createCoins() {
            return Arrays.asList(
                    new Coin(8, 8),
                    new Coin(9, 9),
                    new Coin(10, 10));
        }
    }

    @Test
    public void testCreateArena() {
        TestArenaBuilder builder = new TestArenaBuilder(20, 20);
        Arena arena = builder.createArena();

        assertNotNull(arena);
        assertEquals(20, arena.getWidth());
        assertEquals(20, arena.getHeight());
    }

    @Test
    public void testArenaHasThief() {
        TestArenaBuilder builder = new TestArenaBuilder(20, 20);
        Arena arena = builder.createArena();

        Thief thief = arena.getThief();
        assertNotNull(thief);
        assertEquals(3, thief.getPosition().getX());
        assertEquals(3, thief.getPosition().getY());
    }

    @Test
    public void testArenaHasSafe() {
        TestArenaBuilder builder = new TestArenaBuilder(20, 20);
        Arena arena = builder.createArena();

        Safe safe = arena.getSafe();
        assertNotNull(safe);
        assertEquals(15, safe.getPosition().getX());
        assertEquals(15, safe.getPosition().getY());
    }

    @Test
    public void testArenaHasWalls() {
        TestArenaBuilder builder = new TestArenaBuilder(20, 20);
        Arena arena = builder.createArena();

        List<Wall> walls = arena.getWalls();
        assertNotNull(walls);
        assertEquals(3, walls.size());
    }

    @Test
    public void testArenaHasBoxes() {
        TestArenaBuilder builder = new TestArenaBuilder(20, 20);
        Arena arena = builder.createArena();

        List<Box> boxes = arena.getBoxes();
        assertNotNull(boxes);
        assertEquals(2, boxes.size());
    }

    @Test
    public void testArenaHasPolice() {
        TestArenaBuilder builder = new TestArenaBuilder(20, 20);
        Arena arena = builder.createArena();

        List<Police> police = arena.getPolice();
        assertNotNull(police);
        assertEquals(1, police.size());
        assertEquals(10, police.get(0).getPosition().getX());
        assertEquals(10, police.get(0).getPosition().getY());
    }

    @Test
    public void testArenaHasCameras() {
        TestArenaBuilder builder = new TestArenaBuilder(20, 20);
        Arena arena = builder.createArena();

        List<Camera> cameras = arena.getCameras();
        assertNotNull(cameras);
        assertEquals(1, cameras.size());
    }

    @Test
    public void testArenaHasKey() {
        TestArenaBuilder builder = new TestArenaBuilder(20, 20);
        Arena arena = builder.createArena();

        Key key = arena.getKey();
        assertNotNull(key);
        assertEquals(4, key.getPosition().getX());
        assertEquals(4, key.getPosition().getY());
    }

    @Test
    public void testArenaHasCoins() {
        TestArenaBuilder builder = new TestArenaBuilder(20, 20);
        Arena arena = builder.createArena();

        List<Coin> coins = arena.getCoins();
        assertNotNull(coins);
        assertEquals(3, coins.size());
    }

    @Test
    public void testSmallArena() {
        TestArenaBuilder builder = new TestArenaBuilder(5, 5);
        Arena arena = builder.createArena();

        assertEquals(5, arena.getWidth());
        assertEquals(5, arena.getHeight());
        assertNotNull(arena.getThief());
        assertNotNull(arena.getSafe());
    }

    @Test
    public void testLargeArena() {
        TestArenaBuilder builder = new TestArenaBuilder(100, 100);
        Arena arena = builder.createArena();

        assertEquals(100, arena.getWidth());
        assertEquals(100, arena.getHeight());
    }
}
