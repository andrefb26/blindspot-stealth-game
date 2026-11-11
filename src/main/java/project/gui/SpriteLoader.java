package project.gui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SpriteLoader {

    private final Map<String, BufferedImage> cache = new HashMap<>();

    public BufferedImage load(String path) {
        if (cache.containsKey(path)) {
            return cache.get(path);
        }

        URL resource = getClass().getClassLoader().getResource(path);
        if (resource == null) {
            throw new RuntimeException("Resource not found: " + path);
        }

        try {
            BufferedImage image = ImageIO.read(resource);
            cache.put(path, image);
            return image;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load sprite: " + path, e);
        }
    }
}
