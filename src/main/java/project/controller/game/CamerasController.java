package project.controller.game;

import project.Game;
import project.gui.GUI;
import project.model.game.arena.Arena;
import project.model.game.elements.Camera;
import project.model.game.movement.Direction;

import java.io.IOException;
import java.util.List;


public class CamerasController extends GameController {

    private final long PAUSE_DURATION = 2000;
    private final int MAX_ANGLE_STEPS = 2; // +/- 90 graus
    private final long rotationInterval = 500;
    private long lastRotationTime;

    public CamerasController(Arena arena) {
        super(arena);
        this.lastRotationTime = 0;
    }

    private void rotateCamera(Camera camera, long currentTime) {

        if (currentTime < camera.getNextMovementTime()) {
            return;
        }

        if (camera.getInitialDirection() == null) {
            camera.setInitialDirection(camera.getDirection());
            camera.setCurrentRotationSense(1);
            // Define o primeiro tempo de movimento.
            camera.setNextMovementTime(currentTime + rotationInterval);
            return;
        }

        Direction currentDir = camera.getDirection();
        Direction initialDirection = camera.getInitialDirection();
        int currentRotationSense = camera.getCurrentRotationSense();
        int signedSteps = initialDirection.signedStepsTo(currentDir);

        //lógica de inversão de sentido
        boolean boundaryReached = false;
        int nextRotationSense = currentRotationSense;

        if (signedSteps >= MAX_ANGLE_STEPS) {
            nextRotationSense = -1;
            boundaryReached = true;
        } else if (signedSteps <= -MAX_ANGLE_STEPS) {
            nextRotationSense = 1;
            boundaryReached = true;
        }

        //pausa e movimento de inversão
        if (boundaryReached) {

            camera.setCurrentRotationSense(nextRotationSense);


            if (currentRotationSense != nextRotationSense) {
                camera.setNextMovementTime(currentTime + PAUSE_DURATION);
                return;
            }


        }


        //rotação
        Direction nextDir;

        if (camera.getCurrentRotationSense() == 1) {
            nextDir = currentDir.next();
        } else {
            nextDir = currentDir.previous();
        }

        camera.setDirection(nextDir);
        camera.setNextMovementTime(currentTime + rotationInterval);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (time - lastRotationTime < rotationInterval) {
            return; //espera pelo intervalo de rotação
        }

        List<Camera> cameras = getModel().getCameras();

        for (Camera camera : cameras) {
            rotateCamera(camera, time);
        }

        lastRotationTime = time;
    }
}