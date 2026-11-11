package project.gui;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class SpriteLoaderTest {

    @Test
    public void testSpriteLoaderCreation() {
        SpriteLoader loader = new SpriteLoader();
        assertNotNull(loader);
    }

    @Test
    public void testLoadValidSprite() {
        SpriteLoader loader = new SpriteLoader();
        BufferedImage sprite = loader.load("sprites/thief.png");

        assertNotNull(sprite);
    }

    @Test
    public void testLoadMultipleSprites() {
        SpriteLoader loader = new SpriteLoader();

        BufferedImage thief = loader.load("sprites/thief.png");
        BufferedImage police = loader.load("sprites/police.png");
        BufferedImage wall = loader.load("sprites/wall.png");

        assertNotNull(thief);
        assertNotNull(police);
        assertNotNull(wall);
    }

    @Test
    public void testLoadSameSpriteTwice() {
        SpriteLoader loader = new SpriteLoader();

        BufferedImage sprite1 = loader.load("sprites/thief.png");
        BufferedImage sprite2 = loader.load("sprites/thief.png");

        assertNotNull(sprite1);
        assertNotNull(sprite2);
    }
}
