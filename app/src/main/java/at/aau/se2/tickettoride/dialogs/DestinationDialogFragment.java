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

public class DestinationDialogFragment extends DialogFragment {
    public interface DestinationDialogListener {
        void onDialogPositiveClick(ArrayList<String> selected);
    }

    DestinationDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (DestinationDialogListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString() + " must implement DestinationDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        ArrayList<String> selectedItems = new ArrayList();

        //Set Dialog
        builder.setView(inflater.inflate(R.layout.dialog_destination_cards, null))
                .setTitle("Wähle eine Karte")
                .setMultiChoiceItems(R.array.destinations, null, new DialogInterface.OnMultiChoiceClickListener() {
                    //Add Selected Items to List of Choices
                    //TODO Find a way to get the values of the array
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
                        if(isChecked){
                            selectedItems.add(Integer.toString(i+1));
                        }else if(selectedItems.contains(Integer.toString(i+1))){
                            selectedItems.remove(Integer.toString(i+1));
                        }
                    }
                })
                .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        listener.onDialogPositiveClick(selectedItems);
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
