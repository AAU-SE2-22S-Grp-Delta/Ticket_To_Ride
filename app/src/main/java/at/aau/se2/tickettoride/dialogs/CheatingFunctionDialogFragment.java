package at.aau.se2.tickettoride.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.List;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.helpers.ResourceHelper;
import at.aau.se2.tickettoride.models.GameModel;

public class CheatingFunctionDialogFragment extends DialogFragment {
    GameModel gameModel = GameModel.getInstance();
    LinearLayout linearLayoutMissionCards;
    TextView rival1;
    TextView rival2;
    TextView rival3;
    TextView rival4;
    Button ok;
    int actions;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_cheating_function,null);

        rival1 = view.findViewById(R.id.textViewRival1);
        rival2 = view.findViewById(R.id.textViewRival2);
        rival3 = view.findViewById(R.id.textViewRival3);
        rival4 = view.findViewById(R.id.textViewRival4);
        linearLayoutMissionCards = view.findViewById(R.id.linearLayoutMissionCards);
        ok = view.findViewById(R.id.buttonOk);

        List<String> allRival = gameModel.getAllRival();
        List<List<Integer>> allMissions = gameModel.getAllMissions();

        switch (allRival.size()) {
            case 0:
                rival1.setText("-");
                rival2.setText("-");
                rival3.setText("-");
                rival4.setText("-");
                break;
            case 1:
                rival1.setText(allRival.get(0));
                rival2.setText("-");
                rival3.setText("-");
                rival4.setText("-");
                break;
            case 2:
                rival1.setText(allRival.get(0));
                rival2.setText(allRival.get(1));
                rival3.setText("-");
                rival4.setText("-");
                break;
            case 3:
                rival1.setText(allRival.get(0));
                rival2.setText(allRival.get(1));
                rival3.setText(allRival.get(2));
                rival4.setText("-");
                break;
            case 4:
                rival1.setText(allRival.get(0));
                rival2.setText(allRival.get(1));
                rival3.setText(allRival.get(2));
                rival4.setText(allRival.get(3));
                break;
            default:
                break;
        }

        actions = 1;

        rival1.setOnClickListener(v -> handleClick(allMissions,1));

        rival2.setOnClickListener(v -> handleClick(allMissions,2));

        rival3.setOnClickListener(v -> handleClick(allMissions,3));

        rival4.setOnClickListener(v -> handleClick(allMissions, 4));

        ok.setOnClickListener(v -> dismiss());

        builder.setView(view);
        return builder.create();
    }

    private void handleClick(List<List<Integer>> allMissions, int rival) {
        if (actions != 0) {
            if (allMissions.size() == rival) {
                List<Integer> missions = allMissions.get(rival - 1);
                displayDestinationCards(missions);
                actions--;
            } else {
                Log.d("Error", "No rival!");
            }
        } else {
            Log.d("Error", "No cheating action left!");
        }
    }

    public void displayDestinationCards(List<Integer> missionCards) {
        linearLayoutMissionCards.removeAllViews();

        for (int card : missionCards) {
            View imageView = ResourceHelper.getMissionView(requireContext(), card);
            linearLayoutMissionCards.addView(imageView);
        }
    }
}