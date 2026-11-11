package project.controller.game;

import project.Game;
import project.gui.GUI;
import project.model.game.Position;
import project.model.game.arena.Arena;
import project.model.game.elements.Thief;
import project.model.game.movement.Direction;

import java.io.IOException;

public class ThiefController extends GameController {
    public ThiefController(Arena arena) {
        super(arena);
    }

    private void moveThief(Direction direction) {
        Thief thief = getModel().getThief();
        Position current =  thief.getPosition();
        Position next = current.translate(direction);

        if(getModel().isEmpty(next)) {
            thief.setPosition(next);

            if(getModel().keyPosition(next)) {
                thief.setHasKey(true);
                getModel().removeKey();
            }
            if(getModel().coinPosition(next)) {
                thief.increaseCoinCounter(1);
                getModel().removeCoin(next);
            }

        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        Direction dir;

        switch (action) {
            case UP -> dir = Direction.UP;
            case DOWN -> dir = Direction.DOWN;
            case LEFT -> dir = Direction.LEFT;
            case RIGHT -> dir = Direction.RIGHT;
            case UP_LEFT -> dir = Direction.UP_LEFT;
            case UP_RIGHT -> dir = Direction.UP_RIGHT;
            case DOWN_LEFT -> dir = Direction.DOWN_LEFT;
            case DOWN_RIGHT -> dir = Direction.DOWN_RIGHT;
            default -> dir = Direction.NONE;
        }

        if(dir != Direction.NONE) {
            moveThief(dir);
        }
    }


}