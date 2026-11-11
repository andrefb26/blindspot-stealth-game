package project.viewer.menu;

import project.gui.GUI;
import project.gui.TextSpriteGenerator;
import project.model.game.Position;
import project.model.menu.Menu;
import project.viewer.Viewer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuViewer extends Viewer<Menu> {
    private final TextSpriteGenerator generator;

    public MenuViewer(Menu menu) {
        super(menu);
        this.generator = new TextSpriteGenerator();
    }

    @Override
    public void drawElements(GUI gui) {
        gui.setOffset(0, 0);
        drawCentered(gui, "BLIND SPOT", 2, new Color(255, 215, 0));

        for (int i = 0; i < getModel().getNumberEntries(); i++) {
            boolean selected = getModel().isSelected(i);
            String text = (selected ? "▶ " : "  ") + getModel().getEntry(i);
            Color color = selected ? new Color(255, 215, 0) : Color.WHITE;

            drawCentered(gui, text, 8 + i * 3, color); // Spaced out
        }
    }

    private void drawCentered(GUI gui, String text, int y, Color color) {
        BufferedImage sprite = generator.generateSprite(text, color);
        int x = (gui.getWidth() - (int) Math.ceil(sprite.getWidth() / 8.0)) / 2;
        gui.drawImage(new Position(x, y), sprite);
    }
}