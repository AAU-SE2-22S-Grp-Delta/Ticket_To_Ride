package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import at.aau.se2.tickettoride.dataStructures.Player;

public class PlayerTest {
    Player player1;

    @BeforeEach
    public void init() {
        player1 = new Player("a", 1);
    }

    @Test
    public void testSetNameNull() {
        assertThrows(IllegalArgumentException.class, () -> player1.setName(null));
    }

    @Test
    public void testSetNameEmpty() {
        assertThrows(IllegalArgumentException.class, () -> player1.setName(""));
    }
}
