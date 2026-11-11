package project.gui;

import project.model.game.Position;
import project.model.game.elements.Light;

import java.io.IOException;

public interface GUI {
    ACTION getNextAction() throws IOException;

    int getWidth();

    int getHeight();

    void setOffset(int x, int y);

    void drawPolice(Position position);

    void drawThief(Position position);

    void drawBox(Position position);

    void drawWall(Position position);

    void drawCamera(Position position);

    void drawSafe(Position position);

    void drawKey(Position position);

    void drawCoin(Position position);

    void drawLight(Position position);

    void drawText(Position position, String text, String color);

    void drawImage(Position position, java.awt.image.BufferedImage image); // Add support for raw images

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;


    enum ACTION {
        UP, RIGHT, DOWN, LEFT, UP_RIGHT, UP_LEFT, DOWN_LEFT, DOWN_RIGHT, NONE, QUIT, SELECT
    }
}
