package project.controller.game;

import project.gui.GUI;

import project.model.game.arena.Arena;
import project.model.game.elements.Thief;
import project.model.game.elements.Wall;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.IntRange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArenaIsClosedTest {

    @Property(tries = 50)
    void thiefStaysWithinBounds(@ForAll @IntRange(min = 5, max = 50) int width,
            @ForAll @IntRange(min = 5, max = 50) int height,
            @ForAll List<GUI.ACTION> actions) throws IOException {

        Arena arena = new Arena(width, height);
        Thief thief = new Thief(width / 2, height / 2);

        arena.setThief(thief);
        arena.setPolice(new ArrayList<>());
        arena.setSafe(new project.model.game.elements.Safe(0, 0));

        List<Wall> walls = new ArrayList<>();

        // horizontal walls
        for (int x = 0; x < width; x++) {
            walls.add(new Wall(x, 0));
            walls.add(new Wall(x, height - 1));
        }

        // vertical walls
        for (int y = 1; y < height - 1; y++) {
            walls.add(new Wall(0, y));
            walls.add(new Wall(width - 1, y));
        }
        arena.setWalls(walls);

        ThiefController controller = new ThiefController(arena);

        for (GUI.ACTION action : actions) {
            controller.step(null, action, 100);

            assert (thief.getPosition().getX() > 0);
            assert (thief.getPosition().getY() > 0);
            assert (thief.getPosition().getX() < width - 1);
            assert (thief.getPosition().getY() < height - 1);
        }
    }

    @Provide

    public Arbitrary<GUI.ACTION> moveActions() {
        return Arbitraries.of(
                GUI.ACTION.UP,
                GUI.ACTION.RIGHT,
                GUI.ACTION.DOWN,
                GUI.ACTION.LEFT,
                GUI.ACTION.UP_RIGHT,
                GUI.ACTION.UP_LEFT,
                GUI.ACTION.DOWN_LEFT,
                GUI.ACTION.DOWN_RIGHT);
    }
}