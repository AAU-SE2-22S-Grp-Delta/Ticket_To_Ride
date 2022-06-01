package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import at.aau.se2.tickettoride.dataStructures.Mission;

public class MissionTest
{
    Mission m1 = new Mission(0, "dest1", "dest2", 4);;


    @Test
    public void testGetDest1()
    {
        assertEquals("dest1", m1.getDestination1());
    }
    @Test
    public void testGetDest2()
    {
        assertEquals("dest2", m1.getDestination2());
    }
    @Test
    public void testGetPoints()
    {
        assertEquals(4, m1.getPoints());
    }
    @Test
    public void testGetId()
    {
        assertEquals(0, m1.getId());
    }


}
