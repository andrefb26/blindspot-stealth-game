package project.controller.game;

import project.Game;
import project.gui.GUI;
import project.model.game.Position;
import project.model.game.arena.Arena;
import project.model.game.elements.Police;
import project.model.game.movement.Direction;
import project.model.game.movement.RotationSense;

import java.io.IOException;
import java.util.List;

public class PoliceController extends GameController {
    private long lastmove;

    public PoliceController(Arena arena) {
        super(arena);
        this.lastmove = 0;
    }

    private void movePoliceRandom(Police police) {
        Direction dir = Direction.getRandomDirection();
        Position current = police.getPosition();
        Position next = current.translate(dir);

        if(getModel().isEmpty(next)) {
            police.setPosition(next);
        }
    }

    private Direction determineNextRotation(Direction current, Direction target) {



        int clockwiseSteps = current.stepsTo(target, RotationSense.CLOCKWISE);
        int counterClockwiseSteps = current.stepsTo(target, RotationSense.COUNTER_CLOCKWISE);

        //decide qual é o caminho mais curto
        if (clockwiseSteps <= counterClockwiseSteps) {

            return current.next();
        } else {

            return current.previous();
        }
    }

    private void movePoliceGradual(Police police, long currentTime) {
        //verificar e definir uma nova direção alvo se o tempo de intervalo expirou
        if (currentTime >= police.getNextDirectionChangeTime()) {

            long minInterval = 3000;
            long maxInterval = 6000;
            long randomInterval = minInterval + (long) (Math.random() * (maxInterval - minInterval));

            police.setNextDirectionChangeTime(currentTime + randomInterval);


            Direction newTarget = Direction.getRandomDirection();
            police.setTargetDirection(newTarget);
        }


        Direction currentDir = police.getDirection();
        Direction targetDir = police.getTargetDirection();

        //se a direção atual é diferente da alvo, roda em direção à alvo.
        if (currentDir != targetDir) {


            Direction nextRotation = determineNextRotation(currentDir, targetDir);
            police.setDirection(nextRotation);

            return;
        }


        Position currentPos = police.getPosition();
        Position nextPos = currentPos.translate(currentDir);

        if (getModel().isEmpty(nextPos)) {
            police.setPosition(nextPos);
        } else {
            Direction oppositeDirection = currentDir.opposite();


            police.setDirection(oppositeDirection);
            police.setTargetDirection(oppositeDirection);


            Position reboundPos = currentPos.translate(oppositeDirection);

            if (getModel().isEmpty(reboundPos)) {
                police.setPosition(reboundPos);
            }

            long minTime = 1000;
            police.setNextDirectionChangeTime(currentTime + minTime);
        }
    }


    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (time - lastmove > 700) {

            List<Police> policeList = getModel().getPolice();
            for (Police police : policeList) {
                movePoliceGradual(police, time);
            }
            lastmove = time;
        }
    }
}
