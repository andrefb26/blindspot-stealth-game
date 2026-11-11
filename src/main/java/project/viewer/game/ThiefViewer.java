package project.viewer.game;

import project.gui.GUI;
import project.model.game.elements.Thief;

public class ThiefViewer implements ElementViewer<Thief>{
    @Override
    public void draw( Thief thief, GUI gui){
        gui.drawThief(thief.getPosition());
    }

}
