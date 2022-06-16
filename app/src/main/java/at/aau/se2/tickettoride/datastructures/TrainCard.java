package at.aau.se2.tickettoride.datastructures;

import androidx.annotation.NonNull;

public class TrainCard {
    public enum Type {
        PINK("pink"), BLUE("blue"), GREEN("green"), YELLOW("yellow"), RED("red"), WHITE("white"), ORANGE("orange"), BLACK("black"), LOCOMOTIVE("locomotive");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        @NonNull
        @Override
        public String toString() {
            return value;
        }

        public static Type getByString(String color) {
            switch (color) {
                case "pink":
                    return PINK;
                case "blue":
                    return BLUE;
                case "green":
                    return GREEN;
                case "yellow":
                    return YELLOW;
                case "red":
                    return RED;
                case "white":
                    return WHITE;
                case "orange":
                    return ORANGE;
                case "black":
                    return BLACK;
                case "locomotive":
                    return LOCOMOTIVE;
                default:
                    return null;
            }
        }
    }

    private final Type type;

    public TrainCard(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
