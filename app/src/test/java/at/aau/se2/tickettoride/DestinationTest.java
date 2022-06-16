package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import android.widget.Button;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import at.aau.se2.tickettoride.datastructures.Destination;

class DestinationTest
{
    static Destination dest1;
    static Destination dest2;
    static Button btn1;

    @BeforeAll
    static void init()
    {
        btn1 = Mockito.mock(Button.class);
        Mockito.when(btn1.getX()).thenReturn(10f);
        Mockito.when(btn1.getY()).thenReturn(10f);
        Mockito.when(btn1.getWidth()).thenReturn(10);
        Mockito.when(btn1.getHeight()).thenReturn(10);
        dest1 = new Destination("testdest1");
        dest2 = new Destination("testdest2");
        dest1.setButton(btn1);
        dest2.setButton(btn1);
    }

    @Test
    void testSetNameNull()
    {
        assertThrows(IllegalArgumentException.class, () -> dest1.setName(null));
    }

    @Test
    void testSetNameEmpty()
    {
        assertThrows(IllegalArgumentException.class, () -> dest1.setName(""));
    }

    @Test
    void testSetNameUsed()
    {
        assertThrows(IllegalArgumentException.class, () -> dest1.setName(null));
    }

    @Test
    void testGetX()
    {
        assertEquals(15f, dest1.getX());
    }

    @Test
    void testGetY()
    {
        assertEquals(15f, dest1.getY());
    }

    @Test
    void testGetters()
    {
        assertEquals("testdest1", dest1.getName());
        assertEquals(btn1, dest1.getButton());
    }
}