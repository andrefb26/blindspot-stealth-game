package project.model.game.arena;

import project.model.game.Position;
import project.model.game.elements.*;

import java.util.*;

public class Arena {
    private final int width;
    private final int height;

    private long elapsedTime;

    private List<Police> police;
    private Thief thief;
    private Safe safe;
    private Key key;
    private List<Box> boxes;
    private List<Wall> walls;
    private List<Camera> cameras;
    private List<Coin> coins;

    private Set<Position> light = new HashSet<>();

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.police = new ArrayList<>();
        this.boxes = new ArrayList<>();
        this.walls = new ArrayList<>();
        this.cameras = new ArrayList<>();
        this.coins = new ArrayList<>();
    }

    // time
    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    // police
    public List<Police> getPolice() {
        return police;
    }

    public void setPolice(List<Police> police) {
        this.police = police;
    }

    // thief
    public Thief getThief() {
        return thief;
    }

    public void setThief(Thief thief) {
        this.thief = thief;
    }

    // walls
    public List<Wall> getWalls() {
        return walls;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    // boxes
    public List<Box> getBoxes() {
        return boxes;
    }

    public void setBoxes(List<Box> boxes) {
        this.boxes = boxes;
    }

    // cameras
    public List<Camera> getCameras() {
        return cameras;
    }

    public void setCameras(List<Camera> cameras) {
        this.cameras = cameras;
    }

    // safe
    public Safe getSafe() {
        return safe;
    }

    public void setSafe(Safe safe) {
        this.safe = safe;
    }

    // key
    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    // coins
    public List<Coin> getCoins() {
        return coins;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    // light
    public Set<Position> getLight() {
        return light;
    }

    public void setLight(Set<Position> light) {
        this.light = light;
    }

    // checks
    public boolean isEmpty(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position))
                return false;
        }
        for (Box box : boxes) {
            if (box.getPosition().equals(position))
                return false;
        }
        for (Camera camera : cameras) {
            if (camera.getPosition().equals(position))
                return false;
        }
        for (Police officer : police) {
            if (officer.getPosition().equals(position))
                return false;
        }
        if (safe.getPosition().equals(position)) {
            return false;
        }

        return true;
    }

    public boolean isbox(Position position) {
        for (Box box : boxes)
            if (box.getPosition().equals(position))
                return true;
        return false;
    }

    public boolean issafe(Position position) {
        if (safe.getPosition().equals(position))
            return true;
        return false;
    }

    public boolean iscamera(Position position) {
        for (Camera camera : cameras) {
            if (camera.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    public boolean iswall(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    public boolean coinPosition(Position position) {
        if (coins == null)
            return false;

        for (Coin coin : coins) {
            if (coin.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    public void removeCoin(Position position) {
        if (coins == null)
            return;

        coins.removeIf(coin -> coin.getPosition().equals(position));
    }

    public boolean keyPosition(Position position) {
        return key != null && key.getPosition().equals(position);
    }

    public void removeKey() {
        this.key = null;
    }
}

