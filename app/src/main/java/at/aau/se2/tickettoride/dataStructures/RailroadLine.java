package at.aau.se2.tickettoride.dataStructures;

import android.graphics.Color;

/**
 * RailroadLine-class represents a single connection between two Destination-Objects
 */
public class RailroadLine {
    //TODO exception-handling
    private Destination destination1;
    private Destination destination2;
    private final Color color;
    private final int distance;
    private Player owner;

    /**
     * Creates a single RailroadLine to connect two distinct destinations
     * @param destination1
     * @param destination2
     * @param color to build the line train cards of this color will be needed
     * @param distance to build the line that many train cards will be needed
     */
    public RailroadLine(Destination destination1, Destination destination2, Color color, int distance) {
        configureConnection(destination1, destination2);
        this.color = color;
        this.distance = distance;
        this.owner = null;
    }

    public void configureConnection (Destination destination1, Destination destination2) {
        if (destination1 == null) throw new IllegalArgumentException("destination1 == null");
        if (destination2 == null) throw new IllegalArgumentException("destination2 == null");
        if (destination1.getName().equals(destination2.getName())) throw new IllegalArgumentException ("destination1 == destination2");
        this.destination1 = destination1;
        this.destination2 = destination2;
    }

    public Destination getDestination1() {
        return destination1;
    }

    public Destination getDestination2() {
        return destination2;
    }

    public Color getColor() {
        return color;
    }

    public int getDistance() {
        return distance;
    }

    public Player getOwner() {
        return owner;
    }

    /**
     * Sets the owner if this.owner == null
     * @param owner
     * @throws IllegalStateException if owner != null
     */
    public void setOwner(Player owner) throws IllegalStateException {
        if (this.owner != null) throw new IllegalStateException("Line already owned by " + owner.getName());
        this.owner = owner;
    }
}
