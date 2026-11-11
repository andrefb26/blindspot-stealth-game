package project.gui;

import org.junit.jupiter.api.Test;
import java.awt.*;
import java.awt.image.BufferedImage;
import static org.junit.jupiter.api.Assertions.*;

public class TextSpriteGeneratorTest {

    @Test
    public void testGenerateSprite() {
        TextSpriteGenerator generator = new TextSpriteGenerator();
        String text = "Test Score: 100";
        Color color = Color.RED;

        BufferedImage image = generator.generateSprite(text, color);

        assertNotNull(image);
        assertTrue(image.getWidth() > 0);
        assertTrue(image.getHeight() > 0);
        assertEquals(BufferedImage.TYPE_INT_ARGB, image.getType());
    }

    @Test
    public void testGenerateSpriteSpace() {
        TextSpriteGenerator generator = new TextSpriteGenerator();
        String text = " ";
        Color color = Color.WHITE;

        BufferedImage image = generator.generateSprite(text, color);

        assertNotNull(image);
        assertTrue(image.getWidth() > 0);
        assertTrue(image.getHeight() > 0);
    }
}
