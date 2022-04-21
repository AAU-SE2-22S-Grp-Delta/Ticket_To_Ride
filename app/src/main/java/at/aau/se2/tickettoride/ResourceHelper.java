package at.aau.se2.tickettoride;

import at.aau.se2.tickettoride.enums.TrainCards;

public class ResourceHelper {
    public static int getTrainResource(int card) {
        switch (TrainCards.valueOf(card)) {
            case BOX:
                return R.drawable.ic_train_purpur;
            case PASSENGER:
                return R.drawable.ic_train_white;
            case TANKER:
                return R.drawable.ic_train_blue;
            case REEFER:
                return R.drawable.ic_train_yellow;
            case FREIGHT:
                return R.drawable.ic_train_orange;
            case HOPPER:
                return R.drawable.ic_train_black;
            case COAL:
                return R.drawable.ic_train_red;
            case CABOOSE:
                return R.drawable.ic_train_green;
            case LOCOMOTIVE:
                return R.drawable.ic_train;
            default:
                throw new IllegalStateException("Unexpected value: " + card);
        }
    }
}
