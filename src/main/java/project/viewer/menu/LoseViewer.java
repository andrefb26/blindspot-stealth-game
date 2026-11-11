package project.viewer.menu;

import project.gui.GUI;
import project.gui.TextSpriteGenerator;
import project.model.game.Position;
import project.model.menu.LoseScreen;
import project.viewer.Viewer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LoseViewer extends Viewer<LoseScreen> {
    private final TextSpriteGenerator generator;

    public LoseViewer(LoseScreen loseScreen) {
        super(loseScreen);
        this.generator = new TextSpriteGenerator();
    }

    @Override
    public void drawElements(GUI gui) {
        gui.setOffset(0, 0);
        drawCentered(gui, "YOU HAVE BEEN CAUGHT!", 4, new Color(255, 0, 0));

        drawCentered(gui, "FAILURE!", 6, new Color(255, 0, 0));

        drawCentered(gui, "TRY AGAIN (PRESS ENTER)", 12, Color.YELLOW);

        drawCentered(gui, "RETURN TO MAIN MENU (PRESS Q)", 14, Color.WHITE);
    }

    private void drawCentered(GUI gui, String text, int y, Color color) {
        BufferedImage sprite = generator.generateSprite(text, color);
        int x = (gui.getWidth() - (int) Math.ceil(sprite.getWidth() / 8.0)) / 2;
        gui.drawImage(new Position(x, y), sprite);
    }
}
