package project.controller.game;

import project.gui.GUI;
import project.model.game.Position;
import project.model.game.arena.Arena;
import project.model.game.elements.Police;
import project.model.game.elements.Thief;
import project.model.game.elements.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.model.game.movement.Direction;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ThiefControllerTest {
    private ThiefController controller;
    private Thief thief;
    private Police police;
    private Arena arena;

    @BeforeEach
    void setUp() {
        arena = new Arena(10, 10);

        thief = new Thief(5, 5);
        police = new Police(10, 10, Direction.UP);
        arena.setThief(thief);
        arena.setPolice(Arrays.asList(police));
        arena.setWalls(Arrays.asList());
        arena.setSafe(new project.model.game.elements.Safe(0, 0));

        controller = new ThiefController(arena);
    }

    @Test
    void moveThiefRightEmpty() throws IOException {
        controller.step(null, GUI.ACTION.RIGHT, 0);
        assertEquals(new Position(6, 5), thief.getPosition());
    }

    @Test
    void moveHeroRightNotEmpty() throws IOException {
        arena.setWalls(Arrays.asList(new Wall(6, 5)));
        controller.step(null, GUI.ACTION.RIGHT, 0);
        assertEquals(new Position(5, 5), thief.getPosition());
    }

    @Test
    void collectCoin() throws IOException {
        project.model.game.elements.Coin coin = new project.model.game.elements.Coin(6, 5);
        arena.setCoins(new java.util.ArrayList<>(Arrays.asList(coin)));

        controller.step(null, GUI.ACTION.RIGHT, 0);

        assertEquals(new Position(6, 5), thief.getPosition());
        assertEquals(1, thief.getCoinCounter());
        assertEquals(0, arena.getCoins().size());
    }

    @Test
    void collectKey() throws IOException {
        project.model.game.elements.Key key = new project.model.game.elements.Key(6, 5);
        arena.setKey(key);

        controller.step(null, GUI.ACTION.RIGHT, 0);

        assertEquals(new Position(6, 5), thief.getPosition());
        assertTrue(thief.getHasKey());
        assertNull(arena.getKey());
    }

    @Test
    void moveThiefDiagonals() throws IOException {
        // up left
        controller.step(null, GUI.ACTION.UP_LEFT, 0);
        assertEquals(new Position(4, 4), thief.getPosition());
        thief.setPosition(new Position(5, 5));

        // up right
        controller.step(null, GUI.ACTION.UP_RIGHT, 0);
        assertEquals(new Position(6, 4), thief.getPosition());
        thief.setPosition(new Position(5, 5));

        // down left
        controller.step(null, GUI.ACTION.DOWN_LEFT, 0);
        assertEquals(new Position(4, 6), thief.getPosition());
        thief.setPosition(new Position(5, 5));

        // down right
        controller.step(null, GUI.ACTION.DOWN_RIGHT, 0);
        assertEquals(new Position(6, 6), thief.getPosition());
    }

    @Test
    void moveThiefNone() throws IOException {
        controller.step(null, GUI.ACTION.NONE, 0);
        assertEquals(new Position(5, 5), thief.getPosition());
    }
}