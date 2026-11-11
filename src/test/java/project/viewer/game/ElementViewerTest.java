package project.viewer.game;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.gui.GUI;

import project.model.game.elements.*;

public class ElementViewerTest {

    @Test
    public void testElementViewers() {
        GUI gui = Mockito.mock(GUI.class);

        // thief viewer
        Thief thief = new Thief(1, 1);
        ThiefViewer thiefViewer = new ThiefViewer();
        thiefViewer.draw(thief, gui);
        Mockito.verify(gui, Mockito.times(1)).drawThief(thief.getPosition());

        // police viewer
        Police police = new Police(2, 2, project.model.game.movement.Direction.UP);
        PoliceViewer policeViewer = new PoliceViewer();
        policeViewer.draw(police, gui);
        Mockito.verify(gui, Mockito.times(1)).drawPolice(police.getPosition());

        // wall viewer
        Wall wall = new Wall(3, 3);
        WallViewer wallViewer = new WallViewer();
        wallViewer.draw(wall, gui);
        Mockito.verify(gui, Mockito.times(1)).drawWall(wall.getPosition());

        //box viewer
        Box box = new Box(4, 4);
        BoxViewer boxViewer = new BoxViewer();
        boxViewer.draw(box, gui);
        Mockito.verify(gui, Mockito.times(1)).drawBox(box.getPosition());

        // safe viewer
        Safe safe = new Safe(5, 5);
        SafeViewer safeViewer = new SafeViewer();
        safeViewer.draw(safe, gui);
        Mockito.verify(gui, Mockito.times(1)).drawSafe(safe.getPosition());

        //key viewer
        Key key = new Key(6, 6);
        KeyViewer keyViewer = new KeyViewer();
        keyViewer.draw(key, gui);
        Mockito.verify(gui, Mockito.times(1)).drawKey(key.getPosition());

        //coin viewer
        Coin coin = new Coin(7, 7);
        CoinViewer coinViewer = new CoinViewer();
        coinViewer.draw(coin, gui);
        Mockito.verify(gui, Mockito.times(1)).drawCoin(coin.getPosition());

        // camera viewer
        Camera camera = new Camera(8, 8, null);
        CameraViewer cameraViewer = new CameraViewer();
        cameraViewer.draw(camera, gui);
        Mockito.verify(gui, Mockito.times(1)).drawCamera(camera.getPosition());
    }
}
