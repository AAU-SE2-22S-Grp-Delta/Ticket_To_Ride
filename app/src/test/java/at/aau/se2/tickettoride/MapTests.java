package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import android.graphics.Color;
import android.widget.Button;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import at.aau.se2.tickettoride.dataStructures.Destination;
import at.aau.se2.tickettoride.dataStructures.Map;
import at.aau.se2.tickettoride.dataStructures.RailroadLine;

public class MapTests
{
    static Destination dest1;
    static Destination dest2;
    static Destination dest3;
    static Destination dest4;
    static Destination dest5;
    static Destination dest6;
    static RailroadLine r1;
    static RailroadLine r2;
    static RailroadLine r3;
    static RailroadLine r4;
    static RailroadLine r6;
    static Map map;
    Set<Destination> testDestSet = new HashSet<>();
    Set<RailroadLine> testSet = new HashSet<>();


    @BeforeAll
    static public void init()
    {
        Button btn1 = new Button(null);
        Button btn2 = new Button(null);
        Button btn3 = new Button(null);
        map = new Map();

        dest1 = new Destination("testMdest1", btn1);
        dest2 = new Destination("testMdest2", btn2);
        dest3 = new Destination("testMdest3", btn3);
        dest4 = new Destination("testMdest4", btn2);
        dest5 = new Destination("testMdest5", btn2);
        dest6 = new Destination("testMdest6", btn2);

        r1 = new RailroadLine(dest1, dest2, Color.BLUE, 3);
        r2 = new RailroadLine(dest3, dest2, Color.BLUE, 3);
        r3 = new RailroadLine(dest3, dest5, Color.BLUE, 3);
        r4 = new RailroadLine(dest5, dest3, Color.BLUE, 3);
        r6 = new RailroadLine(dest5, dest6, Color.BLUE, 3);
        map.addDestination(dest1);
        map.addDestination(dest2);

    }

    @Test
    public void testAddDestination()
    {
        testDestSet.add(dest3);
        testDestSet.add(dest1);
        testDestSet.add(dest2);
        map.addDestination(dest3);
        assertEquals(testDestSet, map.getDestinations());
    }

    @Test
    public void testAddDestTaken()
    {
        map.addDestination(dest4);
        assertThrows(IllegalStateException.class, () -> map.addDestination(dest4));
    }

    @Test
    public void testAddRailroad()
    {
        testSet.add(r2);
        testSet.add(r6);
        map.addRailroadLine(r2);
        assertEquals(testSet, map.getRailroadLines());
    }

    @Test
    public void testAddRailroadThrows()
    {
        assertThrows(IllegalArgumentException.class, ()-> map.addRailroadLine(r3));
        assertThrows(IllegalArgumentException.class, ()-> map.addRailroadLine(r4));
        map.addDestination(dest5);
        map.addDestination(dest6);
        map.addRailroadLine(r6);
        testSet.add(r6);
        assertThrows(IllegalStateException.class, () -> map.addRailroadLine(r6));
    }
}
