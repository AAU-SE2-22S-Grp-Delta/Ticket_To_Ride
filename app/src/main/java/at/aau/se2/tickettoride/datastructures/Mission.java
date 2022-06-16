package at.aau.se2.tickettoride.datastructures;

import androidx.annotation.Nullable;

public class Mission {
    private final int id;
    private final String destination1;
    private final String destination2;
    private final int points;

    public Mission(int id, String destination1, String destination2, int points) {
        this.id = id;
        this.destination1 = destination1;
        this.destination2 = destination2;
        this.points = points;
    }

    public String getDestination1() {
        return destination1;
    }

    public String getDestination2() {
        return destination2;
    }

    public int getPoints() {
        return points;
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode()
    {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj)
    {
        if (!(obj instanceof Mission)) return false;
        Mission m2 = (Mission) obj;
        return this.id == m2.getId();
    }
}
