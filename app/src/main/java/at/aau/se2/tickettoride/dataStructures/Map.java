package at.aau.se2.tickettoride.dataStructures;

import android.graphics.Color;

import java.util.HashSet;
import java.util.Set;

public class Map {

    public enum MapColor {
        BLUE("blue"), GREEN("green"), YELLOW("yellow"), RED("red"), WHITE("white"), ORANGE("orange"), GRAY("gray"), BLACK("black"), PINK("pink");

        private String value;

        MapColor(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }

        public static int getByString(String color) {
            switch (color)
            {
                case "blue":
                    return Color.BLUE;
                case "green":
                    return Color.GREEN;
                case "yellow":
                    return Color.YELLOW;
                case "red":
                    return Color.RED;
                case "white":
                    return Color.WHITE;
                case "orange":
                    return Color.rgb(255, 69, 80);
                case "gray":
                    return Color.GRAY;
                case "black":
                    return Color.BLACK;
                default:
                    return Color.rgb(199, 21, 133);
            }
        }
    }

    private Set<Destination> destinations;
    private Set<RailroadLine> railroadLines;

    public Map() {
        destinations = new HashSet<>();
        railroadLines = new HashSet<>();
    }

    public void addDestination(Destination destination) {
        if (destination == null) throw new IllegalStateException("destination is null");
        if (destinations.contains(destination)) throw new IllegalStateException("destination is already added");
        destinations.add(destination);
    }

    public void addRailroadLine(RailroadLine railroadLine) {
        if (railroadLine == null) throw new IllegalStateException("railroadLine is null");
        Destination destination1 = railroadLine.getDestination1();
        Destination destination2 = railroadLine.getDestination2();
        if (!destinations.contains(destination1))
            throw new IllegalArgumentException("destination1 " + destination1.getName() + " doesn't exist on the map");
        if (!destinations.contains(destination2))
            throw new IllegalArgumentException("destination2 " + destination2.getName() + " doesn't exist on the map");
        for (RailroadLine line : railroadLines)
            if (line.equals(railroadLine))
                throw new IllegalStateException("connection between " + railroadLine.getDestination1().getName() + " and " +
                        railroadLine.getDestination2().getName() + " already exist");

        railroadLines.add(railroadLine);
    }


    public Set<Destination> getDestinations() {
        return destinations;
    }

    public Set<RailroadLine> getRailroadLines() {
        return railroadLines;
    }
}