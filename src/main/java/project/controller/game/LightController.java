package project.controller.game;

import project.controller.game.raycast.RayCasting;
import project.model.game.Position;
import project.model.game.arena.Arena;
import project.model.game.elements.Camera;
import project.model.game.elements.Light;
import project.model.game.elements.Police;

import java.util.HashSet;
import java.util.Set;

public class LightController {
    private final RayCasting rayCasting;

    public LightController(Arena arena) {
        this.rayCasting = new RayCasting(arena);
    }

    private Set<Position> policeLight(Arena arena) {
        Set<Position> result = new HashSet<>();

        for (Police officer : arena.getPolice()) {
            Light light = new Light(
                    officer.getPosition(),
                    officer.getDirection(),
                    12,
                    60.0
            );
            result.addAll(light.compute(arena, rayCasting));
        }

        return result;
    }

    private Set<Position> cameraLight(Arena arena) {
        Set<Position> result = new HashSet<>();

        for (Camera camera : arena.getCameras()) {
            Light light = new Light(
                    camera.getPosition(),
                    camera.getDirection(),
                    9,
                    70.0
            );
            result.addAll(light.compute(arena, rayCasting));
        }

        return result;
    }

    public Set<Position> AllLight(Arena arena) {
        Set<Position> all = new HashSet<>();
        all.addAll(policeLight(arena));
        all.addAll(cameraLight(arena));
        return all;
    }
}
