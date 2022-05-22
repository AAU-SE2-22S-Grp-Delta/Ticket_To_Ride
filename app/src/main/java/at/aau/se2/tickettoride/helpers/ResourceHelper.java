package at.aau.se2.tickettoride.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.dataStructures.Mission;
import at.aau.se2.tickettoride.databinding.CardMissionBinding;
import at.aau.se2.tickettoride.enums.TrainCards;
import at.aau.se2.tickettoride.models.Missions;

public class ResourceHelper {
    public static View getMissionView(Context context, int card) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        CardMissionBinding binding = CardMissionBinding.inflate(inflater);
        Mission mission = Missions.getMissionById(card);
        if (mission != null) {
            binding.destination.setText(String.format("%s - %s", mission.getDestination1(), mission.getDestination2()));
            binding.points.setText(String.format("%s", mission.getPoints()));
        }
        return binding.getRoot();
    }

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
