package project.controller.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.gui.GUI;
import project.model.game.Position;
import project.model.game.arena.Arena;
import project.model.game.elements.Coin;
import project.model.game.elements.Key;
import project.model.game.elements.Thief;
import project.model.game.elements.Wall;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ThiefMovementEdgeCasesTest {

    private ThiefController controller;
    private Arena arena;
    private Thief thief;

    @BeforeEach
    void setUp() {
        arena = new Arena(10, 10);
        thief = new Thief(5, 5);
        arena.setThief(thief);
        arena.setWalls(new ArrayList<>());
        arena.setCoins(new ArrayList<>());
        arena.setSafe(new project.model.game.elements.Safe(0, 0));
        controller = new ThiefController(arena);
    }

    @Test
    void testDiagonalMovementBlockedByWall() throws IOException {
        arena.setWalls(Arrays.asList(new Wall(6, 4)));
        controller.step(null, GUI.ACTION.UP_RIGHT, 0);
        assertEquals(new Position(5, 5), thief.getPosition());
    }

    @Test
    void testMultipleCoinsInPath() throws IOException {
        arena.setCoins(new ArrayList<>(Arrays.asList(
                new Coin(6, 5),
                new Coin(7, 5),
                new Coin(8, 5))));

        controller.step(null, GUI.ACTION.RIGHT, 0);
        assertEquals(1, thief.getCoinCounter());
        assertEquals(2, arena.getCoins().size());

        controller.step(null, GUI.ACTION.RIGHT, 0);
        assertEquals(2, thief.getCoinCounter());
        assertEquals(1, arena.getCoins().size());

        controller.step(null, GUI.ACTION.RIGHT, 0);
        assertEquals(3, thief.getCoinCounter());
        assertEquals(0, arena.getCoins().size());
    }

    @Test
    void testKeyAndCoinSamePosition() throws IOException {
        arena.setKey(new Key(6, 5));
        arena.setCoins(new ArrayList<>(Arrays.asList(new Coin(6, 5))));

        controller.step(null, GUI.ACTION.RIGHT, 0);

        assertTrue(thief.getHasKey());
        assertEquals(1, thief.getCoinCounter());
        assertNull(arena.getKey());
        assertEquals(0, arena.getCoins().size());
    }
}
