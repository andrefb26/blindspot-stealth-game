package project.gui;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class SpriteFactoryTest {

    @Test
    public void testSpriteFactoryCreation() {
        SpriteFactory factory = new SpriteFactory();
        assertNotNull(factory);
    }

    @Test
    public void testGetThief() {
        SpriteFactory factory = new SpriteFactory();
        BufferedImage sprite = factory.getThief();

        assertNotNull(sprite);
    }

    @Test
    public void testGetPolice() {
        SpriteFactory factory = new SpriteFactory();
        BufferedImage sprite = factory.getPolice();

        assertNotNull(sprite);
    }

    @Test
    public void testGetWall() {
        SpriteFactory factory = new SpriteFactory();
        BufferedImage sprite = factory.getWall();

        assertNotNull(sprite);
    }

    @Test
    public void testGetBox() {
        SpriteFactory factory = new SpriteFactory();
        BufferedImage sprite = factory.getBox();

        assertNotNull(sprite);
    }

    @Test
    public void testGetSafe() {
        SpriteFactory factory = new SpriteFactory();
        BufferedImage sprite = factory.getSafe();

        assertNotNull(sprite);
    }

    @Test
    public void testGetCamera() {
        SpriteFactory factory = new SpriteFactory();
        BufferedImage sprite = factory.getCamera();

        assertNotNull(sprite);
    }

    @Test
    public void testGetFloor() {
        SpriteFactory factory = new SpriteFactory();
        BufferedImage sprite = factory.getFloor();

        assertNotNull(sprite);
    }

    @Test
    public void testGetKeyAnimation() {
        SpriteFactory factory = new SpriteFactory();

        BufferedImage key1 = factory.getKey(0);
        BufferedImage key2 = factory.getKey(250);
        BufferedImage key3 = factory.getKey(500);

        assertNotNull(key1);
        assertNotNull(key2);
        assertNotNull(key3);
    }

    @Test
    public void testGetCoinAnimation() {
        SpriteFactory factory = new SpriteFactory();

        BufferedImage coin1 = factory.getCoin(0);
        BufferedImage coin2 = factory.getCoin(250);
        BufferedImage coin3 = factory.getCoin(500);

        assertNotNull(coin1);
        assertNotNull(coin2);
        assertNotNull(coin3);
    }

    @Test
    public void testKeyAnimationCycles() {
        SpriteFactory factory = new SpriteFactory();

        BufferedImage frame0 = factory.getKey(0);
        BufferedImage frame1000 = factory.getKey(1000);

        assertNotNull(frame0);
        assertNotNull(frame1000);
    }

    @Test
    public void testCoinAnimationCycles() {
        SpriteFactory factory = new SpriteFactory();

        BufferedImage frame0 = factory.getCoin(0);
        BufferedImage frame500 = factory.getCoin(500);

        assertNotNull(frame0);
        assertNotNull(frame500);
    }
}
