package at.aau.se2.tickettoride.enums;

import java.util.HashMap;
import java.util.Map;

public enum TrainCards {
    BOX(1),
    PASSENGER(2),
    TANKER(3),
    REEFER(4),
    FREIGHT(5),
    HOPPER(6),
    COAL(7),
    CABOOSE(8),
    LOCOMOTIVE(9);

    private final int value;
    private static final Map<Integer, TrainCards> map = new HashMap<>();

    private TrainCards(int value) {
        this.value = value;
    }

    static {
        for (TrainCards trainCards : TrainCards.values()) {
            map.put(trainCards.value, trainCards);
        }
    }

    public static TrainCards valueOf(int card) {
        return (TrainCards) map.get(card);
    }

    public int getValue() {
        return value;
    }
}
