package project.viewer.game;
import project.gui.GUI;
import project.model.game.elements.Element;
public interface ElementViewer  <T extends Element> {
    void draw(T element , GUI gui);
}
