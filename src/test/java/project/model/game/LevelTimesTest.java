package project.model.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LevelTimesTest {

    @Test
    public void testCalculateAdjustedTime_StandardDeduction() {
        int coins = 1;
        long totalElapsedTime = 10000; // 10 seconds

        // 3 second bonus per coin
        // expect 7 seconds
        long expected = 7000;

        Assertions.assertEquals(expected, LevelTimes.calculateAdjustedTime(coins, totalElapsedTime));
    }

    @Test
    public void testCalculateAdjustedTime_NoCoins() {
        int coins = 0;
        long totalElapsedTime = 10000;

        // no coins no bonus
        Assertions.assertEquals(totalElapsedTime, LevelTimes.calculateAdjustedTime(coins, totalElapsedTime));
    }

    @Test
    public void testCalculateAdjustedTime_OverDeduction() {
        int coins = 5; // 15 seconds deduction
        long totalElapsedTime = 10000; // 10 seconds elapsed

        // expect 0 if bonus > time
        Assertions.assertEquals(0, LevelTimes.calculateAdjustedTime(coins, totalElapsedTime));
    }

    @Test
    public void testCalculateAdjustedTime_Zero() {
        int coins = 1; // 3 seconds
        long totalElapsedTime = 3000;

        // expect 0 if bonus == time
        Assertions.assertEquals(0, LevelTimes.calculateAdjustedTime(coins, totalElapsedTime));
    }
}
