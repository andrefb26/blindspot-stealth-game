package project.gui;

import java.awt.image.BufferedImage;

public class SpriteFactory {

    private final SpriteLoader loader;
    private final BufferedImage[] keyFrames;
    private final BufferedImage[] coinFrames;

    public SpriteFactory() {
        this.loader = new SpriteLoader();
        this.keyFrames = new BufferedImage[]{
                loader.load("sprites/key.png"),
                loader.load("sprites/key2.png"),
                loader.load("sprites/key3.png"),
                loader.load("sprites/key4.png")
        };
        this.coinFrames = new BufferedImage[] {
                loader.load("sprites/coin.png"),
                loader.load("sprites/coin1.png")
        };

    }

    public BufferedImage getThief() {
        return loader.load("sprites/thief.png");
    }

    public BufferedImage getPolice() {
        return loader.load("sprites/police.png");
    }

    public BufferedImage getWall() {
        return loader.load("sprites/wall.png");
    }

    public BufferedImage getBox() {
        return loader.load("sprites/box.png");
    }

    public BufferedImage getKey(long currentTime) {
        int frames = 250;
        int index = (int)((currentTime / frames ) % keyFrames.length);
        return keyFrames[index];
    }

    public BufferedImage getSafe() {
        return loader.load("sprites/safe.png");
    }

    public BufferedImage getCamera() {
        return loader.load("sprites/camera.png");
    }

    public BufferedImage getFloor() {
        return loader.load("sprites/floor.png");
    }


    public BufferedImage getCoin(long currentTime) {
        int frames = 250;
        int index = (int)((currentTime / frames) % coinFrames.length);
        return coinFrames[index];
    }
}
