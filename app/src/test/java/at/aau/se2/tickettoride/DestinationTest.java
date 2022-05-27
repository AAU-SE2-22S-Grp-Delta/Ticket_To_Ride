package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import android.widget.Button;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import at.aau.se2.tickettoride.dataStructures.Destination;

public class DestinationTest {
    Destination dest1;
    Destination dest2;

    @BeforeEach
    public void init() {
        dest1 = new Destination("testdest1", new Button(null));
        dest2 = new Destination("testdest2", new Button(null));
    }

    @Test
    public void testSetNameNull() {
        assertThrows(IllegalArgumentException.class, () -> dest1.setName(null));
    }

    @Test
    public void testSetNameEmpty() {
        assertThrows(IllegalArgumentException.class, () -> dest1.setName(""));
    }

    @Test
    public void testGetters() {
        assertEquals("testdest1", dest1.getName());
    }
}