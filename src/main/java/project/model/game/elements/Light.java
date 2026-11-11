package project.model.game.elements;

import project.model.game.movement.Direction;
import project.model.game.Position;
import project.model.game.arena.Arena;
import project.controller.game.raycast.RayCasting;

import java.util.HashSet;
import java.util.Set;

public class Light {

    private final Position origin;
    private Direction direction;
    private final int range;
    private final double fovDegrees;

    public Light(Position origin, Direction direction, int range, double fovDegrees) {
        this.origin = origin;
        this.direction = direction;
        this.range = range;
        this.fovDegrees = fovDegrees;
    }

    public Position getOrigin() {
        return origin;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getRange() {
        return range;
    }

    public double getFovDegrees() {
        return fovDegrees;
    }

    public void setDirection(Direction newDirection) {
        this.direction = newDirection;
    }

    public Set<Position> compute(Arena arena, RayCasting rayCasting) {

        Set<Position> lit = new HashSet<>();

        int ox = origin.getX();
        int oy = origin.getY();

        double facing = directionToAngle(direction);
        double halfFov = Math.toRadians(fovDegrees / 2.0);

        int r = Math.min(range, 50);
        for (int x = ox - r; x <= ox + r; x++) {
            for (int y = oy - r; y <= oy + r; y++) {

                if (x == ox && y == oy)
                    continue;

                Position target = new Position(x, y);

                int dx = x - ox;
                int dy = y - oy;
                double dist = Math.sqrt(dx * dx + dy * dy);
                if (dist > range)
                    continue;

                // converter dy para coordenadas matemáticas
                double dyMath = -dy;
                double angleToTarget = Math.atan2(dyMath, dx);

                double diff = normalizeAngle(angleToTarget - facing);
                if (Math.abs(diff) > halfFov)
                    continue;

                if (!rayCasting.haslineofsight(origin, target, range))
                    continue;

                lit.add(target);
            }
        }

        return lit;
    }

    private double directionToAngle(Direction dir) {
        return switch (dir) {
            case RIGHT -> 0.0;
            case UP_RIGHT -> Math.PI / 4.0;
            case NONE -> 0.0;
            case UP -> Math.PI / 2.0;
            case UP_LEFT -> 3 * Math.PI / 4.0;
            case LEFT -> Math.PI;
            case DOWN_LEFT -> -3 * Math.PI / 4.0;
            case DOWN -> -Math.PI / 2.0;
            case DOWN_RIGHT -> -Math.PI / 4.0;
        };
    }

    private double normalizeAngle(double a) {
        double result = a;
        for (int i = 0; i < 10 && result <= -Math.PI; i++)
            result += 2 * Math.PI;
        for (int i = 0; i < 10 && result > Math.PI; i++)
            result -= 2 * Math.PI;
        return result;
    }
}
