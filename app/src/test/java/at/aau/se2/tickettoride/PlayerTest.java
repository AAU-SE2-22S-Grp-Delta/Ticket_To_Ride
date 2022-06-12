package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import at.aau.se2.tickettoride.dataStructures.Player;

class PlayerTest
{
    static Player player1;
    static Player player2;

    @BeforeAll
    static void init()
    {
        player1 = new Player("a", 1);
        player2 = new Player("b", 1);
        player1.enterGame();
    }

    @Test
    void testSetNameNull()
    {
        assertThrows(IllegalArgumentException.class, () -> player1.setName(null));
    }

    @Test
    void testSetNameEmpty()
    {
        assertThrows(IllegalArgumentException.class, () -> player1.setName(""));
    }

    @Test
    void testGetColor()
    {
        assertEquals(1, player1.getPlayerColor());
    }

    @Test
    void testGetName()
    {
        assertEquals("a", player1.getName());
    }

    @Test
    void getStones()
    {
        assertEquals(23, player1.getStones());
    }

    @Test
    void testGetId()
    {
        assertEquals(0, player1.getId());
    }

    @Test
    void testSetName()
    {
        player1.setName("test");
        assertEquals("test", player1.getName());
    }

    @Test
    void testIsNotInGame()
    {
        player2.enterGame();
        assertEquals(45, player2.getStones());
    }

    @Test
    void testIsInGame()
    {
        assertThrows(IllegalStateException.class, () -> player1.enterGame());
    }

    @Test
    void testRemoveStonesEnough()
    {
        player1.removeStones(22);
        assertEquals(23, player1.getStones());
    }

    @Test
    void testRemoveStones()
    {
        assertThrows(IllegalArgumentException.class, () -> player1.removeStones(46));
    }
}
