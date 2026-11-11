package project.model.game.arena;

import project.model.game.elements.*;
import project.model.game.movement.Direction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoaderArenaBuilder extends ArenaBuilder {
    private final int level;
    private final List<String> lines;

    public LoaderArenaBuilder(int level) throws IOException {
        this.level = level;

        URL resource = LoaderArenaBuilder.class.getResource("/levels/level" + level + ".lvl");
        if (resource == null) {
            throw new IOException("Level file not found: /levels/level" + level + ".lvl");
        }

        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));
        this.lines = readLines(br);
    }

    private List<String> readLines(BufferedReader br) throws IOException {
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }

    @Override
    protected int getWidth() {
        int width = 0;
        for (String line : lines)
            width = Math.max(width, line.length());
        return width;
    }

    @Override
    protected int getHeight() {
        return lines.size();
    }

    @Override
    protected List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == '#' ) {
                    walls.add(new Wall(x, y));
                }
            }
        }

        return walls;
    }

    @Override
    protected List<Box> createBoxes() {
        List<Box> boxes = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'B') {
                    boxes.add(new Box(x, y));
                }
            }
        }

        return boxes;
    }

    @Override
    protected List<Police> createPolice() {

        List<Police> policeList = new ArrayList<>();


        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'P') {

                    Police newPolice = new Police(x,y, Direction.getRandomDirection());
                    policeList.add(newPolice);
                }
            }
        }


        return policeList;
    }

    @Override
    protected Thief createThief() {
        for (int y=0; y<lines.size(); y++){
            String line = lines.get(y);
            for (int x=0; x< line.length(); x++){
                if (line.charAt(x)=='T'){
                    return new Thief(x,y);
                }
            }
        }
        return null; //nao encontrou ladroes
    }

    @Override
    protected List<Camera> createCameras() {
        List<Camera> cameras= new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                char symbol = line.charAt(x);
                Direction initialDirection = null;

                if (symbol == '0') {
                    initialDirection = Direction.UP;
                } else if (symbol == '2') {
                    initialDirection = Direction.UP_RIGHT;
                } else if (symbol == '3') {
                    initialDirection = Direction.RIGHT;
                } else if (symbol == '5') {
                    initialDirection = Direction.DOWN_RIGHT;
                } else if (symbol == '6') {
                    initialDirection = Direction.DOWN;
                } else if (symbol == '8') {
                    initialDirection = Direction.DOWN_LEFT;
                } else if (symbol == '9') {
                    initialDirection = Direction.LEFT;
                } else if (symbol == '1') {
                    initialDirection = Direction.UP_LEFT;
                }


                if (initialDirection != null) {

                    cameras.add(new Camera(x, y, initialDirection));
                }
            }
        }

        return cameras;
    }

    @Override
    protected Safe createSafe() {
        for (int y=0; y<lines.size(); y++){
            String line = lines.get(y);
            for (int x=0; x< line.length(); x++){
                if (line.charAt(x)=='S'){
                    return new Safe(x,y);
                }
            }
        }
        return null; //nao encontrou cofres
    }

    @Override
    protected Key createKey() {
        for (int y=0; y<lines.size(); y++){
            String line = lines.get(y);
            for (int x=0; x< line.length(); x++){
                if (line.charAt(x)=='K'){
                    return new Key(x,y);
                }
            }
        }
        return null; //nao encontrou chaves
    }

    @Override
    protected List<Coin> createCoins() {

        List<Coin> coins = new ArrayList<>();


        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'G') {

                    Coin newCoin = new Coin(x, y);
                    coins.add(newCoin);
                }
            }
        }

        return coins;
    }

}
