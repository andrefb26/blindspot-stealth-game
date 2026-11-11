package project.viewer.menu;

import project.gui.GUI;
import project.gui.TextSpriteGenerator;
import project.model.game.LevelTimes;
import project.model.game.Position;
import project.model.menu.LevelMenu;
import project.viewer.Viewer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelMenuViewer extends Viewer<LevelMenu> {
    private final TextSpriteGenerator generator;

    public LevelMenuViewer(LevelMenu levelMenu) {
        super(levelMenu);
        this.generator = new TextSpriteGenerator();
    }

    @Override
    protected void drawElements(GUI gui) {
        gui.setOffset(0, 0);
        drawCentered(gui, "BLIND SPOT", 2, new Color(255, 215, 0));
        drawCentered(gui, "- Levels -", 4, Color.WHITE);

        for (int i = 0; i < getModel().getNumberEntries(); i++) {
            boolean pointer = getModel().isSelected(i);
            String prefix = pointer ? "▶ " : "  ";

            int level = i + 1;
            Long record = LevelTimes.getRecord(level);
            String recordText = (record == null) ? " [Not cleared yet]" : " [" + Time(record) + "]";

            String text = prefix + getModel().getEntry(i) + recordText;
            Color color = pointer ? new Color(255, 215, 0) : Color.WHITE;

            drawCentered(gui, text, 12 + i * 3, color);
        }
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
