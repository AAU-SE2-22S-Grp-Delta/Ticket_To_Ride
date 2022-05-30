package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import at.aau.se2.tickettoride.dataStructures.Player;

public class PlayerTest {
   static Player player1;

    @BeforeAll
    public static void init() {
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

    @Test
    public void testGetColor()
    {
        assertEquals(1, player1.getPlayerColor());
    }

    @Test
    public void testGetName()
    {
        assertEquals("a", player1.getName());
    }

    @Test
    public void getStones()
    {
        assertEquals(23, player1.getStones());
    }

    @Test
    public void testGetId()
    {
        assertEquals(0, player1.getId());
    }

    @Test
    public void testSetName()
    {
        player1.setName("test");
        assertEquals("test", player1.getName());
    }

    @Test
    @BeforeAll
    public static void testIsNotInGame()
    {
        player1.enterGame();
        assertEquals(45, player1.getStones());
    }

    @Test
    public void testIsInGame()
    {
        assertThrows(IllegalStateException.class,() -> player1.enterGame());
    }

    @Test
    public void testRemoveStonesEnough()
    {
        player1.removeStones(22);
        assertEquals(23, player1.getStones());
    }

    @Test
    public void testRemoveStones()
    {
        assertThrows(IllegalArgumentException.class, ()-> player1.removeStones(46));
    }
}
