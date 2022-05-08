package at.aau.se2.tickettoride.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

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
    private final ArrayList<Integer> cardsToChoose = new ArrayList<Integer>();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_destination_cards, null);

        String[] destinations = getChoices();

        CheckBox checkBox1 = view.findViewById(R.id.checkBox1);
        checkBox1.setText(destinations[0]);
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                choose(isChecked, 0);
            }
        });

        CheckBox checkBox2 = view.findViewById(R.id.checkBox2);
        checkBox2.setText(destinations[1]);
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                choose(isChecked, 1);
            }
        });

        CheckBox checkBox3 = view.findViewById(R.id.checkBox3);
        checkBox3.setText(destinations[2]);
        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                choose(isChecked, 2);
            }
        });

        //Set Dialog
        builder.setView(view)
                .setTitle("Wähle eine Karte")
                .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        addChosenCardsToHand();
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

    public String[] getChoices(){
        String[] destinations = new String[3];
        for (int i = 0; i < 3; i++) {
            int number = gameModel.getDeskDestinationCards().get(i);
            cardsToChoose.add(number);
            destinations[i] = "Ziel " + number;
        }
        return destinations;
    }

    public void choose(boolean cheeked, int i){
        if(cheeked){
            selectedItems.add(cardsToChoose.get(i));
        }else if(selectedItems.contains(cardsToChoose.get(i))){
            selectedItems.remove(cardsToChoose.get(i));
        }
    }

    public void addChosenCardsToHand(){
        ArrayList <Integer> newPlayerDestinationCards = new ArrayList<>(gameModel.getPlayerDestinationCards());
        for (int i = 0; i < selectedItems.size(); i++) {
            newPlayerDestinationCards.add(selectedItems.get(i));
            gameModel.getDeskDestinationCards().remove(selectedItems.get(i));
            Log.i("RESULT", selectedItems.get(i).toString());
        }
        gameModel.setPlayerDestinationCards(newPlayerDestinationCards);
    }
}
