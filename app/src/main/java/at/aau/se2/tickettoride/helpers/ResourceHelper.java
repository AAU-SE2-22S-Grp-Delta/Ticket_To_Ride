package at.aau.se2.tickettoride.helpers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.dataStructures.Mission;
import at.aau.se2.tickettoride.dataStructures.TrainCard;
import at.aau.se2.tickettoride.databinding.CardMissionBinding;
import at.aau.se2.tickettoride.models.Missions;

public class ResourceHelper {
    @SuppressLint("SetTextI18n")
    public static View getMissionView(Context context, int card) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        CardMissionBinding binding = CardMissionBinding.inflate(inflater);
        Mission mission = Missions.getMissionById(card);
        if (mission != null) {
            binding.mission.setText(Integer.toString(mission.getId()));
            binding.destination.setText(String.format("%s\n%s", "From: "+mission.getDestination1(), "To:       "+mission.getDestination2()));
            binding.points.setText(String.format("%s", "Points: "+mission.getPoints()));
        }
        return binding.getRoot();
    }

    public static int getTrainResource(TrainCard card) {
        switch (card.getType()) {
            case PINK:
                return R.drawable.ic_train_pink;
            case BLUE:
                return R.drawable.ic_train_blue;
            case GREEN:
                return R.drawable.ic_train_green;
            case YELLOW:
                return R.drawable.ic_train_yellow;
            case RED:
                return R.drawable.ic_train_red;
            case WHITE:
                return R.drawable.ic_train_white;
            case ORANGE:
                return R.drawable.ic_train_orange;
            case BLACK:
                return R.drawable.ic_train_black;
            case LOCOMOTIVE:
                return R.drawable.ic_train;
            default:
                throw new IllegalStateException("Unexpected value: " + card);
        }
    }
}
