package at.aau.se2.tickettoride;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import android.graphics.Color;

import org.junit.Before;
import org.junit.Test;

import at.aau.se2.tickettoride.dataStructures.Player;



public class PlayerTest
{
    Player player1;
    Player player2;

    @Before
    public void init()
    {
        player1 = new Player("a", 1);
    }

    @Test
    public void testSetNameNull()
    {
        assertThrows(IllegalArgumentException.class, () -> player1.setName(null));
    }

    @Test
    public void testSetNameEmpty()
    {
        assertThrows(IllegalArgumentException.class, () -> player1.setName(""));
    }



}
