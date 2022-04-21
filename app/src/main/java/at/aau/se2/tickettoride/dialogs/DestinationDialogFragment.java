package at.aau.se2.tickettoride.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Arrays;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.models.GameModel;

public class DestinationDialogFragment extends DialogFragment {
    GameModel gameModel = GameModel.getInstance();
    ArrayList<Integer> selectedItems = new ArrayList<Integer>();
    //TODO Get the cards from GameModel
    private ArrayList<Integer> cardsToChoice = new ArrayList<Integer>();
    String[] destinations = new String[3];

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        getChoices();

        //Set Dialog
        builder.setView(inflater.inflate(R.layout.dialog_destination_cards, null))
                .setTitle("Wähle eine Karte")
                .setMultiChoiceItems(destinations, null, new DialogInterface.OnMultiChoiceClickListener() {
                    //Add Selected Items to List of Choices
                    //TODO Find a way to get the values of the array
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
                        if(isChecked){
                            selectedItems.add(i);
                        }else if(selectedItems.contains(i)){
                            selectedItems.remove(i);
                        }
                    }
                })
                .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        gameModel.setPlayerDestinationCards(selectedItems);
                        for (int i = 0; i < gameModel.getPlayerDestinationCards().size(); i++) {
                            Log.i("RESULT", gameModel.getPlayerDestinationCards().get(i).toString());
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DestinationDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    private void getChoices(){
        //TODO Delete it after the function is in the game.
        //Predefine List
        gameModel.setDeskDestinationCards(new ArrayList<Integer>(Arrays.asList(4,7,9)));

        for (int i = 0; i < 3; i++) {
            int number = gameModel.getDeskDestinationCards().get(i);
            cardsToChoice.add(number);
            //Log.i("DATA", Integer.toString(number));
            destinations[i] = "Ziel " + number;
        }
    }
}
