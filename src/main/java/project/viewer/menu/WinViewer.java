package project.viewer.menu;

import project.gui.GUI;
import project.gui.TextSpriteGenerator;
import project.model.game.Position;
import project.model.menu.WinScreen;
import project.viewer.Viewer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WinViewer extends Viewer<WinScreen> {
    private final TextSpriteGenerator generator;

    public WinViewer(WinScreen winScreen) {
        super(winScreen);
        this.generator = new TextSpriteGenerator();
    }

    @Override
    public void drawElements(GUI gui) {

        long rawTime = getModel().getElapsedTime();
        long adjustedTime = getModel().getAdjustedTime();

        long timeBonus = rawTime - adjustedTime;

        gui.setOffset(0, 0);
        drawCentered(gui, "YOU WERE SUCCESSFUL!", 4, new Color(15, 255, 0));
        drawCentered(gui, "TIME:" + Time(getModel().getElapsedTime()), 10, Color.LIGHT_GRAY);
        drawCentered(gui, "COIN BONUS: -" + Time(timeBonus) , 12, Color.YELLOW);
        drawCentered(gui, "FINAL TIME: " + Time(adjustedTime), 14, Color.YELLOW);
        drawCentered(gui, "RETURN TO LEVELS (PRESS ⏎)", 18, Color.WHITE);
        drawCentered(gui, "RETURN TO MAIN MENU (PRESS Q)", 20, Color.WHITE);
    }

    private void drawCentered(GUI gui, String text, int y, Color color) {
        BufferedImage sprite = generator.generateSprite(text, color);
        int x = (gui.getWidth() - (int) Math.ceil(sprite.getWidth() / 8.0)) / 2;
        gui.drawImage(new Position(x, y), sprite);
    }

    private String Time(long time) {
        long total = time / 1000;
        long minutes = total / 60;
        long seconds = total % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }
}
