package project.viewer.game;
import project.gui.GUI;
import project.model.game.elements.Camera;
public class CameraViewer implements ElementViewer<Camera>{
    @Override
    public void draw(Camera camera, GUI gui){gui.drawCamera(camera.getPosition());}
}
