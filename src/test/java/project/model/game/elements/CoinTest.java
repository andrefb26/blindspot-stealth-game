package project.model.game.elements;

import org.junit.jupiter.api.Test;
import project.model.game.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoinTest {

    @Test
    public void testCoinCreation() {
        Coin coin = new Coin(3, 3);
        assertEquals(new Position(3, 3), coin.getPosition());
    }
}
