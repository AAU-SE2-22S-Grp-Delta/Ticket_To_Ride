package at.aau.se2.tickettoride.dataStructures;

import java.util.HashSet;
import java.util.Set;

public class Map {
    private Set<Destination> destinations;
    private Set<RailroadLine> railroadLines;

    public Map() {
        destinations = new HashSet<>();
        railroadLines = new HashSet<>();
    }

    public void addDestination(Destination destination) {
        if (destination == null) throw new IllegalStateException("destination is null");
        destinations.add(destination);
    }

    public void addRailroadLine(RailroadLine railroadLine) {
        if (railroadLine == null) throw new IllegalStateException("railroadLine is null");
        Destination destination1 = railroadLine.getDestination1();
        Destination destination2 = railroadLine.getDestination2();
        if (!destinations.contains(destination1))
            throw new IllegalArgumentException("destination1 " + destination1.getName() + " don't exist on the map");
        if (!destinations.contains(destination2))
            throw new IllegalArgumentException("destination2 " + destination2.getName() + " don't exist on the map");
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