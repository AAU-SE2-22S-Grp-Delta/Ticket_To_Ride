package at.aau.se2.tickettoride.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.models.GameModel;

public class DestinationDialogFragment extends DialogFragment {
    private final GameModel gameModel = GameModel.getInstance();
    private final ArrayList<Integer> selectedItems = new ArrayList<>();
    private final ArrayList<Integer> cardsToChoose = new ArrayList<>();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_destination_cards, null);

        String[] destinations = getChoices();

        CheckBox checkBox1 = view.findViewById(R.id.checkBox1);
        checkBox1.setText(destinations[0]);
        checkBox1.setOnCheckedChangeListener((compoundButton, isChecked) -> choose(isChecked, 0));

        CheckBox checkBox2 = view.findViewById(R.id.checkBox2);
        checkBox2.setText(destinations[1]);
        checkBox2.setOnCheckedChangeListener((compoundButton, isChecked) -> choose(isChecked, 1));

        CheckBox checkBox3 = view.findViewById(R.id.checkBox3);
        checkBox3.setText(destinations[2]);
        checkBox3.setOnCheckedChangeListener((compoundButton, isChecked) -> choose(isChecked, 2));

        //Set Dialog
        builder.setView(view)
                .setTitle("Wähle eine Karte")
                .setPositiveButton(R.string.accept, (dialogInterface, id) -> addChosenCardsToHand());
        return builder.create();
    }

    public String[] getChoices() {
        String[] destinations = new String[3];
        List<Integer> missions = gameModel.getChooseMissionCards();
        for (int i = 0; i < 3; i++) {
            int number = missions.get(i);
            cardsToChoose.add(number);
            destinations[i] = "Ziel " + number;
        }
        return destinations;
    }

    public void choose(boolean checked, int i) {
        if (checked) {
            selectedItems.add(cardsToChoose.get(i));
        } else {
            selectedItems.remove(cardsToChoose.get(i));
        }
    }

    public void addChosenCardsToHand() {
        gameModel.setPlayerDestinationCards(selectedItems);
    }
}
