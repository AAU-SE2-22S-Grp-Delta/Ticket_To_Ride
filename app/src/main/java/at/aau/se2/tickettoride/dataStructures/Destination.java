package at.aau.se2.tickettoride.dataStructures;

import android.graphics.Point;
import android.widget.Button;

import java.util.HashSet;
import java.util.Set;

/**
 * Destination-Class represents a Point on the map which can be connected
 * with other destinations via railroad lines.
 */
public class Destination {
    //TODO exception-handling

    private static Set<String> names = new HashSet<>();
    private Button button;
    private String name;

    /**
     * Creates a Destination Object and marks the name as used
     * @param name a unique name
     * @param button a button on the map, representing the location and its coordinates
     */
    public Destination(String name, Button button) {
        this.button = button;
        setName(name);
    }

    public Button getButton()
    {
        return button;
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

    public float getX()
    {
        return this.button.getX() + (float) this.button.getWidth()/2;
    }

    public float getY()
    {
        return this.button.getY() + (float) this.button.getHeight()/2;
    }

}
