package project.model.game;

import java.util.HashMap;
import java.util.Map;

public class LevelTimes {
    private static final Map<Integer, Long> Records = new HashMap<>();

    public static void registerTime(int level, long time) {
        Long current = Records.get(level); // Long pode ser null
        if ((current == null) || (time < current)) {
            Records.put(level, time);
        }
    }
    public static long calculateAdjustedTime(int coinCount, long totalElapsedTime) {



        long timeBonusMS = (long) coinCount * 3 * 1000;

        long adjustedTime = totalElapsedTime - timeBonusMS;


        return Math.max(0, adjustedTime);
    }

    public static Long getRecord(int level) {
        return Records.get(level);
    }
}
