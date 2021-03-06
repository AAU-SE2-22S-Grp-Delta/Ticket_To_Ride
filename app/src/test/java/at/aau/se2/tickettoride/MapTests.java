package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import android.graphics.Color;
import android.widget.Button;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import at.aau.se2.tickettoride.datastructures.Destination;
import at.aau.se2.tickettoride.datastructures.Map;
import at.aau.se2.tickettoride.datastructures.RailroadLine;

class MapTests {
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
    static void init() {
        Button btn1 = new Button(null);
        Button btn2 = new Button(null);
        Button btn3 = new Button(null);
        map = new Map();

        dest1 = new Destination("testMdest1");
        dest2 = new Destination("testMdest2");
        dest3 = new Destination("testMdest3");
        dest4 = new Destination("testMdest4");
        dest5 = new Destination("testMdest5");
        dest6 = new Destination("testMdest6");

        dest1.setButton(btn1);
        dest2.setButton(btn2);
        dest3.setButton(btn3);
        dest4.setButton(btn2);
        dest5.setButton(btn2);
        dest6.setButton(btn2);

        r1 = new RailroadLine(dest1, dest2, "blue", 3);
        r2 = new RailroadLine(dest3, dest2, "blue", 3);
        r3 = new RailroadLine(dest3, dest5, "blue", 3);
        r4 = new RailroadLine(dest5, dest3, "blue", 3);
        r6 = new RailroadLine(dest5, dest6, "blue", 3);
        map.addDestination(dest1);
        map.addDestination(dest2);

    }

    @Test
    void testAddDestination() {
        testDestSet.add(dest3);
        testDestSet.add(dest1);
        testDestSet.add(dest2);
        map.addDestination(dest3);
        assertEquals(testDestSet, map.getDestinations());
        assertThrows(IllegalStateException.class, () -> map.addDestination(null));
    }

    @Test
    void testAddDestTaken() {
        map.addDestination(dest4);
        assertThrows(IllegalStateException.class, () -> map.addDestination(dest4));
    }

    @Test
    void testAddRailroad() {
        testSet.add(r2);
        testSet.add(r6);
        map.addRailroadLine(r2);
        assertEquals(testSet, map.getRailroadLines());
        assertThrows(IllegalStateException.class, () -> map.addRailroadLine(null));
    }

    @Test
    void testAddRailroadThrows() {
        assertThrows(IllegalArgumentException.class, () -> map.addRailroadLine(r3));
        assertThrows(IllegalArgumentException.class, () -> map.addRailroadLine(r4));
        map.addDestination(dest5);
        map.addDestination(dest6);
        map.addRailroadLine(r6);
        testSet.add(r6);
        assertThrows(IllegalStateException.class, () -> map.addRailroadLine(r6));
    }

    @Test
    void testGetByString() {
        assertEquals(Color.BLUE, Map.MapColor.getByString("blue"));
        assertEquals(Color.GREEN, Map.MapColor.getByString("green"));
        assertEquals(Color.YELLOW, Map.MapColor.getByString("yellow"));
        assertEquals(Color.RED, Map.MapColor.getByString("red"));
        assertEquals(Color.LTGRAY, Map.MapColor.getByString("white"));
        assertEquals(Color.rgb(255, 69, 80), Map.MapColor.getByString("orange"));
        assertEquals(Color.GRAY, Map.MapColor.getByString("gray"));
        assertEquals(Color.BLACK, Map.MapColor.getByString("black"));
        assertEquals(Color.rgb(199, 21, 133), Map.MapColor.getByString(""));
    }

    @Test
    void testToString() {
        assertEquals("black", Map.MapColor.BLACK.toString());
    }
}