package at.aau.se2.tickettoride.dataStructures;

/**
 * RailroadLine-class represents a double connection between two Destination-Objects
 */
public class DoubleRailroadLine extends RailroadLine{
    Player owner2;
    int color2;

    /**
     * Creates a double RailroadLine to connect two distinct destinations
     * @param destination1
     * @param destination2
     * @param color1 to build the 1st line train cards of this color will be needed
     * @param distance
     * @param color2 to build the 2nd line train cards of this color will be needed
     */
    public DoubleRailroadLine(Destination destination1, Destination destination2, int color1, int distance, int color2) {
        super(destination1, destination2, color1, distance);
        this.color2 = color2;
        this.owner2 = null;
    }

    public Player getOwner2() {
        return owner2;
    }

    /**
     * Sets the owner2 if this.owner == null
     * @param owner2
     * @throws IllegalStateException if owner != null
     */
    public void setOwner2(Player owner2) throws IllegalStateException {
        if (this.owner2 != null) throw new IllegalStateException("Line already owned by " + owner2.getName());
        this.owner2 = owner2;
    }
}
