package at.aau.se2.tickettoride;

import static org.junit.Assert.assertThrows;

import android.graphics.Color;

import org.junit.Before;
import org.junit.Test;

import at.aau.se2.tickettoride.dataStructures.Player;

public class TestPlayer
{
    Player player1 = new Player("testplayer1aaaa", Color.GREEN);
    Player player2 = new Player("player2", Color.RED);

//    @Before
//    public void init()
//    {
//        player1

//        player2
//    }

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

    @Test
    public void testSetNameAleradyExists()
    {
        assertThrows(IllegalArgumentException.class, () -> player1.setName("player2"));

    }

}
