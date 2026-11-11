package project.viewer.game;

import project.gui.GUI;
import project.model.game.elements.Key;
public class KeyViewer implements ElementViewer<Key>{
    @Override
    public void draw(Key key, GUI gui){gui.drawKey(key.getPosition());}
}
