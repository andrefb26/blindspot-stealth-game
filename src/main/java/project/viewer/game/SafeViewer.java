package project.viewer.game;
import project.gui.GUI;
import project.model.game.elements.Safe;
public class SafeViewer implements ElementViewer<Safe>{
    @Override
    public void draw(Safe safe, GUI gui){gui.drawSafe(safe.getPosition());}
}

