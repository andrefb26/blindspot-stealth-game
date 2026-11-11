package project.model.game.elements;

import project.model.game.Position;
import project.model.game.movement.Direction;

public class Thief extends Element implements Movable{

    private boolean hasKey;
    private int coinCounter;

    public Thief(int x, int y){
        super(x, y);
        this.hasKey = false;
    }

    @Override
    public Position getNextPosition(Direction direction) {


        int newX = getPosition().getX();
        int newY = getPosition().getY();

        switch (direction) {
            case UP:
                newY -= 1;
                break;
            case DOWN:
                newY += 1;
                break;
            case LEFT:
                newX -= 1;
                break;
            case RIGHT:
                newX += 1;
                break;
        }

        return new Position(newX, newY);
    }


    @Override
    public void setPosition(Position position) {

        super.setPosition(position);
    }


    @Override
    public Position getPosition() {
        return super.getPosition();
    }

    public boolean getHasKey() {
        return hasKey;
    }


    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    public int getCoinCounter() {
        return coinCounter;
    }

    public void increaseCoinCounter(int amount) {
        if (amount > 0) {
            this.coinCounter += amount;
        }
    }

    public void resetCoinCounter() {
        this.coinCounter = 0;
    }

}
