package at.aau.se2.tickettoride.dataStructures;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Destination-Class represents a Point on the map which can be connected
 * with other destinations via railroad lines.
 */
public class Destination {
    //TODO exception-handling

    private static Set<String> names = new HashSet<>();

    private String name;
    private Point coordinate;

    /**
     * Creates a Destination Object and marks the name as used
     * @param name a unique name
     * @param coordinate a point on the map
     */
    public Destination(String name, Point coordinate) {
        setName(name);
        setCoordinate(coordinate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) throw new IllegalArgumentException("name is null");
        if (name.length() == 0) throw new IllegalArgumentException("name.length is 0");
        if (names.contains(name)) throw new IllegalArgumentException("name " + name + "is already used!");
        names.remove(this.name);
        names.add(name);
        this.name = name;
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point coordinate) {
        //TODO what are the max coordinates?
        if (coordinate == null) throw new IllegalArgumentException("coordinate is null");
        if (coordinate.x < 0) throw new IllegalArgumentException("coordinate.x < 0");
        if (coordinate.y < 0) throw new IllegalArgumentException("coordinate.y < 0");
        this.coordinate = coordinate;
    }
}
