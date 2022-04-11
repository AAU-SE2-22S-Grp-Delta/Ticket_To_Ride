package at.aau.se2.tickettoride.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

import at.aau.se2.tickettoride.R;

public class TrainDialogFragment extends DialogFragment {
    @NonNull
    @Override
    //TODO Change
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        ArrayList<String> selectedItems = new ArrayList();

        builder.setView(inflater.inflate(R.layout.dialog_train_cards, null))
                .setTitle("Deine neue Waagonkarte")
                .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TrainDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
