package project.model.menu;

public class WinScreen {
    private final int level;
    private final long rawElapsedTime;
    private final int coinCount;
    private final long finalAdjustedTime;


    public WinScreen(int level, long rawElapsedTime, int coinCount, long finalAdjustedTime) {
        this.level = level;
        this.rawElapsedTime = rawElapsedTime;
        this.coinCount = coinCount;
        this.finalAdjustedTime = finalAdjustedTime;
    }


    public long getElapsedTime() {
        return rawElapsedTime;
    }


    public long getAdjustedTime() {
        return finalAdjustedTime;
    }


    public int getCoinCount() {
        return coinCount;
    }

    public int getLevel() {
        return level;
}
}
