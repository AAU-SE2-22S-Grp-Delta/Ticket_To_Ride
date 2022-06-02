package at.aau.se2.tickettoride.dataStructures;

public class TrainCard {
    public enum Type {
        BOX, PASSENGER, TANKER, REEFER, FREIGHT, HOPPER, COAL, CABOOSE, LOCOMOTIVE
    }

    private final Type type;

    public TrainCard(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
