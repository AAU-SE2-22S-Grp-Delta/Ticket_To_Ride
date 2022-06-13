package at.aau.se2.tickettoride.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;
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
    List<TextView> allRivals;
    Button ok;
    int actions;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_cheating_function,null);
        allRivals = new ArrayList<>();
        allRivals.add(rival1);
        allRivals.add(rival2);
        allRivals.add(rival3);
        allRivals.add(rival4);

        List<String> allRival = gameModel.getAllRival();
        List<List<Integer>> allMissions = gameModel.getAllMissions();

        for(int i = 0; i<allRival.size(); i++)
        {
            if(allRival.get(i) != null)
            {
                allRivals.get(i).setText(allRival.get(i));
            }
        }

        actions = 1;

        rival1 = view.findViewById(R.id.textViewRival1);
        rival1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(actions != 0) {
                    List<Integer> missions = allMissions.get(0);
                    if(missions != null) {
                        displayDestinationCards(missions);
                        actions--;
                    }
                } else {
                    Log.d("Error", "No cheating action left!");
                }
            }
        });

        rival2 = view.findViewById(R.id.textViewRival2);
        rival2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(actions != 0) {
                    List<Integer> missions = allMissions.get(1);
                    if(missions != null) {
                        displayDestinationCards(missions);
                        actions--;
                    }
                } else {
                    Log.d("Error", "No cheating action left!");
                }
            }
        });

        rival3 = view.findViewById(R.id.textViewRival3);
        rival3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(actions != 0) {
                    List<Integer> missions = allMissions.get(2);
                    if(missions != null) {
                        displayDestinationCards(missions);
                        actions--;
                    }
                } else {
                    Log.d("Error", "No cheating action left!");
                }
            }
        });

        rival4 = view.findViewById(R.id.textViewRival4);
        rival4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(actions != 0) {
                    List<Integer> missions = allMissions.get(3);
                    if(missions != null) {
                        displayDestinationCards(missions);
                        actions--;
                    }
                } else {
                    Log.d("Error", "No cheating action left!");
                }
            }
        });

        linearLayoutMissionCards = view.findViewById(R.id.linearLayoutMissionCards);

        ok = view.findViewById(R.id.buttonOk);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        builder.setView(view);
        return builder.create();
    }

    public void displayDestinationCards(List<Integer> missionCards) {
        linearLayoutMissionCards.removeAllViews();

        for (int card : missionCards) {
            View imageView = ResourceHelper.getMissionView(requireContext(), card);
            linearLayoutMissionCards.addView(imageView);
        }
    }
}