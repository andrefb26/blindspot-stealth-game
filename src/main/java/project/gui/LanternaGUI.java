package project.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;
import project.model.game.Position;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaGUI implements GUI {

    private final Screen screen;
    private final SpriteFactory spriteFactory;
    private final TextSpriteGenerator textSpriteGenerator;
    private final TextGraphics tg;
    private static final int SPRITE_SIZE = 8;

    private int offsetX;
    private int offsetY;

    public LanternaGUI(Screen screen) {
        this.screen = screen;
        this.spriteFactory = new SpriteFactory();
        this.textSpriteGenerator = new TextSpriteGenerator();
        this.tg = screen.newTextGraphics();
        this.offsetX = 0;
        this.offsetY = 0;
    }

    public LanternaGUI(int arenaWidth, int arenaHeight) throws IOException, URISyntaxException, FontFormatException {
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();

        // criação do terminal com tamanho mínimo necessário
        Terminal terminal = createTerminal(arenaWidth * SPRITE_SIZE, arenaHeight * SPRITE_SIZE, fontConfig);
        this.screen = createScreen(terminal);

        // centrar
        if (terminal instanceof Window) {
            ((Window) terminal).setLocationRelativeTo(null);
        } else if (terminal instanceof SwingTerminalFrame) {
            ((SwingTerminalFrame) terminal).setLocationRelativeTo(null);
        }

        this.tg = screen.newTextGraphics();

        this.spriteFactory = new SpriteFactory();
        this.textSpriteGenerator = new TextSpriteGenerator();

    }

    private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration font) throws IOException {

        TerminalSize terminalSize = new TerminalSize(width, height + 3 * SPRITE_SIZE);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(font);

        return terminalFactory.createTerminal();
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        Screen screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    private AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, FontFormatException, IOException {
        URL resource = getClass().getClassLoader().getResource("fonts/source.ttf");
        if (resource == null)
            throw new IOException("Font file not found: fonts/source.ttf");

        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 3);
        return AWTTerminalFontConfiguration.newInstance(loadedFont);
    }

    // INPUT

    @Override
    public int getWidth() {
        screen.doResizeIfNecessary();
        return screen.getTerminalSize().getColumns() / SPRITE_SIZE;
    }

    @Override
    public int getHeight() {
        screen.doResizeIfNecessary();
        return screen.getTerminalSize().getRows() / SPRITE_SIZE;
    }

    @Override
    public void setOffset(int x, int y) {
        this.offsetX = x;
        this.offsetY = y;
    }

    @Override
    public ACTION getNextAction() throws IOException {
        screen.doResizeIfNecessary();

        int dx = 0;
        int dy = 0;

        KeyStroke keyStroke;

        while ((keyStroke = screen.pollInput()) != null) {
            KeyType type = keyStroke.getKeyType();

            if (keyStroke.getKeyType() == KeyType.EOF)
                return ACTION.QUIT;
            if (keyStroke.getKeyType() == KeyType.Character &&
                    (keyStroke.getCharacter() == 'q' || keyStroke.getCharacter() == 'Q'))
                return ACTION.QUIT;
            if (keyStroke.getKeyType() == KeyType.Enter)
                return ACTION.SELECT;

            if (type == KeyType.ArrowUp)
                dy -= 1;
            if (type == KeyType.ArrowDown)
                dy += 1;
            if (type == KeyType.ArrowLeft)
                dx -= 1;
            if (type == KeyType.ArrowRight)
                dx += 1;

        }

        if (dx == 0 && dy == 0)
            return ACTION.NONE;

        if (dy < 0 && dx == 0)
            return ACTION.UP;
        if (dy > 0 && dx == 0)
            return ACTION.DOWN;
        if (dx > 0 && dy == 0)
            return ACTION.RIGHT;
        if (dx < 0 && dy == 0)
            return ACTION.LEFT;
        if (dx > 0 && dy < 0)
            return ACTION.UP_RIGHT;
        if (dx < 0 && dy < 0)
            return ACTION.UP_LEFT;
        if (dx > 0 && dy > 0)
            return ACTION.DOWN_RIGHT;
        if (dx < 0 && dy > 0)
            return ACTION.DOWN_LEFT;

        return ACTION.NONE;
    }

    //

    @Override
    public void drawImage(Position position, BufferedImage image) {
        drawSprite(position.getX(), position.getY(), image);
    }

    private void drawSprite(int x, int y, BufferedImage sprite) {
        if (sprite == null)
            return;
        // TextGraphics tg = screen.newTextGraphics();

        int width = sprite.getWidth();
        int height = sprite.getHeight();

        int drawX = x + offsetX;
        int drawY = y + offsetY;

        int startX = drawX * SPRITE_SIZE;
        int startY = drawY * SPRITE_SIZE;

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                int color = sprite.getRGB(i, j);
                if ((color >> 24) == 0x00)
                    continue; // Transparent

                int runLength = 1;
                while (i + runLength < width) {
                    int nextColor = sprite.getRGB(i + runLength, j);
                    if (nextColor != color)
                        break;
                    runLength++;
                    if (runLength > width)
                        break;
                }
                if (runLength < 1)
                    runLength = 1;

                Color c = new Color(color);
                String colorString = String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());

                tg.setBackgroundColor(TextColor.Factory.fromString(colorString));

                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < runLength; k++)
                    sb.append(" ");

                tg.putString(startX + i, startY + j, sb.toString());

                i += runLength - 1;
            }
        }
    }

    @Override
    public void drawPolice(Position position) {
        drawSprite(position.getX(), position.getY(), spriteFactory.getPolice());
    }

    @Override
    public void drawThief(Position position) {
        drawSprite(position.getX(), position.getY(), spriteFactory.getThief());
    }

    @Override
    public void drawWall(Position position) {
        drawSprite(position.getX(), position.getY(), spriteFactory.getWall());
    }

    @Override
    public void drawBox(Position position) {
        drawSprite(position.getX(), position.getY(), spriteFactory.getBox());
    }

    @Override
    public void drawCamera(Position position) {
        drawSprite(position.getX(), position.getY(), spriteFactory.getCamera());
    }

    @Override
    public void drawSafe(Position position) {
        drawSprite(position.getX(), position.getY(), spriteFactory.getSafe());
    }

    @Override
    public void drawKey(Position position) {
        long time = System.currentTimeMillis();
        drawSprite(position.getX(), position.getY(), spriteFactory.getKey(time));
    }

    @Override
    public void drawCoin(Position position) {
        long time = System.currentTimeMillis();
        drawSprite(position.getX(), position.getY(), spriteFactory.getCoin(time));
    }

    @Override
    public void drawLight(Position position) {

        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(TextColor.Factory.fromString("#FFFFAA"));

        int x = position.getX() + offsetX;
        int y = position.getY() + offsetY;

        int startX = x * SPRITE_SIZE;
        int startY = y * SPRITE_SIZE;
        for (int i = 0; i < SPRITE_SIZE; i++) {
            tg.putString(startX, startY + i, "      ");
        }

    }

    @Override
    public void drawText(Position position, String text, String color) {
        Color c;
        if (color.startsWith("#")) {
            c = Color.decode(color);
        } else {
            try {
                Field field = Class.forName("java.awt.Color").getField(color);
                c = (Color) field.get(null);
            } catch (Exception e) {
                c = Color.WHITE; // Default
            }
        }

        BufferedImage textImage = textSpriteGenerator.generateSprite(text, c);
        drawSprite(position.getX(), position.getY(), textImage);
    }


    @Override
    public void clear() {
        screen.doResizeIfNecessary();
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}