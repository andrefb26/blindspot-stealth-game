package project.viewer.game;

import project.gui.GUI;
import project.gui.SpriteFactory;
import project.model.game.Position;
import project.model.game.arena.Arena;
import project.model.game.elements.Element;
import project.model.game.elements.Thief;
import project.viewer.Viewer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameViewer extends Viewer<Arena> {
    private final SpriteFactory spriteFactory;
    private final BufferedImage floorMap;

    public GameViewer(Arena arena) {
        super(arena);
        this.spriteFactory = new SpriteFactory();
        this.floorMap = createFloor(arena.getWidth(), arena.getHeight());
    }

    @Override
    public void drawElements(GUI gui) {
        gui.setOffset(0, 0);
        gui.drawText(new Position(1, 1), "Time: " + Time(getModel().getElapsedTime()), "#FFFFFF");

        //offset para centrar a arena
        int arenaWidth = getModel().getWidth();
        int arenaHeight = getModel().getHeight();

        int offsetX = (gui.getWidth() - arenaWidth) / 2;
        int offsetY = (gui.getHeight() - arenaHeight) / 2;

        //garantir que temos 2 rows para o timer
        offsetY = Math.max(2, offsetY);
        offsetX = Math.max(0, offsetX);


        gui.setOffset(offsetX, offsetY);

        gui.drawImage(new Position(0,0), floorMap);

        drawLight(gui, getModel().getLight());
        drawSingle(gui, getModel().getThief(), new ThiefViewer());
        drawSingle(gui, getModel().getKey(), new KeyViewer());
        drawSingle(gui, getModel().getSafe(), new SafeViewer());
        drawMultiple(gui, getModel().getCameras(), new CameraViewer());
        drawMultiple(gui, getModel().getWalls(), new WallViewer());
        drawMultiple(gui, getModel().getBoxes(), new BoxViewer());
        drawMultiple(gui, getModel().getPolice(), new PoliceViewer());
        drawMultiple(gui, getModel().getCoins(), new CoinViewer());

        gui.setOffset(0, 0);

        Thief thief = getModel().getThief();
        int coinsCollected = thief.getCoinCounter();

        String counterText = "🪙 " + coinsCollected;
        gui.drawText(new Position(1, arenaHeight+4), "🪙: " + coinsCollected, "#FFD700");



    }

    private <T extends Element> void drawMultiple(GUI gui, List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements)
            drawSingle(gui, element, viewer);
    }

    private <T extends Element> void drawSingle(GUI gui, T element, ElementViewer<T> elementViewer) {
        if (element == null) return;
        elementViewer.draw(element, gui);
    }

    private void drawLight(GUI gui, Set<Position> litPositions) {
        for (Position position : litPositions) {
            gui.drawLight(position);
        }
    }

    private String Time(long time) {
        long total = time / 1000;
        long minutes = total / 60;
        long seconds = total % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }

    public BufferedImage createFloor(int width, int height) {
        BufferedImage floor = spriteFactory.getFloor();
        int size = floor.getWidth();

        BufferedImage map = new BufferedImage(width*size,height*size,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = map.createGraphics();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width;x++) {
                g.drawImage(floor, x*size,y*size,null);
            }
        }
        g.dispose();
        return map;
    }
}