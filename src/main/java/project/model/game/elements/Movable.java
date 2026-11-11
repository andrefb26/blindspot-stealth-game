package project.model.game.elements;

import project.model.game.Position;
import project.model.game.movement.Direction;

public interface Movable {

    Position getNextPosition(Direction direction);
    Position getPosition();

    void setPosition(Position newPosition);


}