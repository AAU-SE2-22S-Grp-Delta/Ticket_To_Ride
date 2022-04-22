package at.aau.se2.tickettoride;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import android.widget.Button;

import at.aau.se2.tickettoride.dataStructures.Destination;

public class DestinationTest
{
    Destination dest1;
    Destination dest2;

    @Before
    public void init()
    {
        dest1 = new Destination("testdest1", new Button(null));
        dest2 = new Destination("testdest2", new Button(null));
    }

    @Test
    public void testSetNameNull()
    {
        assertThrows(IllegalArgumentException.class, () -> dest1.setName(null));
    }

    @Test
    public void testSetNameEmpty()
    {
        assertThrows(IllegalArgumentException.class, () -> dest1.setName(""));
    }
}