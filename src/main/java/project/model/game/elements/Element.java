package project.model.game.elements;

import project.model.game.Position;

public class Element {

    protected Position position;

    public Element(int x, int y) {
        this.position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
