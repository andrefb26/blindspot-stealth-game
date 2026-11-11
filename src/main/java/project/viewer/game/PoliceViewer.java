package project.viewer.game;

import project.gui.GUI;
import project.model.game.elements.Police;

public class PoliceViewer implements ElementViewer<Police> {

    @Override
    public void draw(Police police, GUI gui){ gui.drawPolice(police.getPosition());}
}
