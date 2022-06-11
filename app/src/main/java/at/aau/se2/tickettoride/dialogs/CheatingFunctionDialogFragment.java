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

import java.util.List;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.helpers.ResourceHelper;
import at.aau.se2.tickettoride.models.GameModel;

public class CheatingFunctionDialogFragment extends DialogFragment {
    GameModel gameModel = GameModel.getInstance();
    LinearLayout linearLayoutMissionCards;
    TextView player1;
    TextView player2;
    TextView player3;
    TextView player4;
    Button ok;
    int actions;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_cheating_function,null);

        // TODO: Karten von den richtigen Spielern bekommen (vom Server)

        actions = 1;
        // für Testzweck
        player1 = view.findViewById(R.id.textViewPlayer1);
        player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(actions != 0) {
                    displayDestinationCards(gameModel.getPlayerDestinationCards());
                    actions--;
                } else {
                    Log.d("Error", "No cheating action left!");
                }
            }
        });

        player2 = view.findViewById(R.id.textViewPlayer2);
        player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(actions != 0) {
                    displayDestinationCards(gameModel.getPlayerDestinationCards());
                } else {
                    Log.d("Error", "No cheating action left!");
                }
            }
        });

        player3 = view.findViewById(R.id.textViewPlayer3);
        player3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(actions != 0) {
                    displayDestinationCards(gameModel.getPlayerDestinationCards());
                } else {
                    Log.d("Error", "No cheating action left!");
                }
            }
        });

        player4 = view.findViewById(R.id.textViewPlayer4);
        player4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(actions != 0) {
                    displayDestinationCards(gameModel.getPlayerDestinationCards());
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