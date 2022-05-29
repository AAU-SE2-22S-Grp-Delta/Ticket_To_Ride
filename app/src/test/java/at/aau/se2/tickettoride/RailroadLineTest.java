package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import android.graphics.Color;
import android.widget.Button;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import at.aau.se2.tickettoride.dataStructures.Destination;
import at.aau.se2.tickettoride.dataStructures.Player;
import at.aau.se2.tickettoride.dataStructures.RailroadLine;

public class RailroadLineTest {
    Destination dest1;
    Destination dest2;
    Destination dest3;
    Destination dest4;
    RailroadLine r1;
    RailroadLine r2;
    Player player1;
    Player player2;

    @BeforeEach
    public void init() {
        dest1 = new Destination("testdest1", new Button(null));
        dest2 = new Destination("testdest2", new Button(null));
        dest3 = new Destination("testdest3", new Button(null));
        dest4 = new Destination("testdest4", new Button(null));
        r1 = new RailroadLine(dest1, dest2, Color.BLUE, 3);
        player1 = new Player("testplayer1", Color.GREEN);
        player2 = new Player("testplayer2", Color.RED);

    }

    @Test
    public void testIsBuilt()
    {
        assertFalse(r1.isBuilt());
        r1.setOwner(player1);
        assertTrue(r1.isBuilt());
    }

    @Test
    public void testConnectionFirstNull() {
        assertThrows(IllegalArgumentException.class, () -> r2 = new RailroadLine(null, dest2, Color.BLUE, 3));
    }

    @Test
    public void testConnectionSecondNull() {
        assertThrows(IllegalArgumentException.class, () -> r2 = new RailroadLine(dest1, null, Color.BLUE, 3));
    }

    @Test
    public void testConnectionSameDest() {
        assertThrows(IllegalArgumentException.class, () -> r2 = new RailroadLine(dest1, dest1, Color.BLUE, 3));
    }

    @Test
    public void testDestEquals() {
        r1 = new RailroadLine(dest1, dest2, Color.BLUE, 3);
        assertEquals(r1, new RailroadLine(dest2, dest1));
    }

    @Test
    public void testSetOwner() {
        r1.setOwner(player1);
        assertThrows(IllegalStateException.class, () -> r1.setOwner(player2));
    }

    @Test
    public void testGetters() {
        assertEquals(Color.BLUE, r1.getColor());
        assertEquals(dest1, r1.getDestination1());
        assertEquals(dest2, r1.getDestination2());
        assertEquals(3, r1.getDistance());
        assertNull(r1.getOwner());
        r1.setOwner(player1);
        assertEquals(player1, r1.getOwner());
    }

    @Test
    public void testEquals() {
        assertEquals(r1, new RailroadLine(dest1, dest2, Color.BLUE, 3));
        assertEquals(r1, new RailroadLine(dest2, dest1, Color.BLUE, 3));
        assertNotEquals(r1, new RailroadLine(dest1, dest3, Color.BLUE, 3));
    }
}
