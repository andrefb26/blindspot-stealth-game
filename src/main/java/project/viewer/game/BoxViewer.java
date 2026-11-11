package project.viewer.game;

import project.gui.GUI;
import project.model.game.elements.Box;

public class BoxViewer implements ElementViewer <Box>{
    @Override
    public void draw(Box box, GUI gui){gui.drawBox(box.getPosition());}

}
