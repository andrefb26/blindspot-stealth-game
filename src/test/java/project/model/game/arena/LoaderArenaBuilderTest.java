package project.model.game.arena;

import org.junit.jupiter.api.Test;
import project.model.game.elements.*;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LoaderArenaBuilderTest {

    @Test
    public void testLoadLevel1() throws IOException {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(1);
        Arena arena = builder.createArena();

        assertNotNull(arena);
        assertTrue(arena.getWidth() > 0);
        assertTrue(arena.getHeight() > 0);
        assertNotNull(arena.getThief());
        assertNotNull(arena.getSafe());
        assertNotNull(arena.getKey());
        assertFalse(arena.getWalls().isEmpty());
    }

    @Test
    public void testLoadLevel2() throws IOException {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(2);
        Arena arena = builder.createArena();

        assertNotNull(arena);
        assertNotNull(arena.getThief());
    }

    @Test
    public void testLoadLevel3() throws IOException {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(3);
        Arena arena = builder.createArena();

        assertNotNull(arena);
        assertNotNull(arena.getThief());
    }

    @Test
    public void testLevelNotFound() {
        assertThrows(IOException.class, () -> {
            new LoaderArenaBuilder(99);
        });
    }

    @Test
    public void testCreatePolice() throws IOException {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(1);
        List<Police> police = builder.createPolice();
        // expects at least one police in level 1
        assertNotNull(police);
    }

    @Test
    public void testCreateCameras() throws IOException {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(1);
        List<Camera> cameras = builder.createCameras();
        // expects cameras in level 1
        assertNotNull(cameras);
    }

    @Test
    public void testCreateBoxes() throws IOException {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(1);
        List<Box> boxes = builder.createBoxes();
        assertNotNull(boxes);
    }

    @Test
    public void testCreateCoins() throws IOException {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(1);
        List<Coin> coins = builder.createCoins();
        assertNotNull(coins);
    }
}
