package at.aau.se2.tickettoride.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.models.GameModel;

public class DestinationDialogFragment extends DialogFragment {
    GameModel gameModel = GameModel.getInstance();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        ArrayList<Integer> selectedItems = new ArrayList();

        //TODO Get the cards from GameModel
        //Set Dialog
        builder.setView(inflater.inflate(R.layout.dialog_destination_cards, null))
                .setTitle("Wähle eine Karte")
                .setMultiChoiceItems(R.array.destinations, null, new DialogInterface.OnMultiChoiceClickListener() {
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
}
