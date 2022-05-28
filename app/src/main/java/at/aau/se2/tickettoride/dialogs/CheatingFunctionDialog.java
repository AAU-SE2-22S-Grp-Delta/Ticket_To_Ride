package at.aau.se2.tickettoride.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckedTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.models.GameModel;

public class CheatingFunctionDialog extends DialogFragment {
    GameModel gameModel = GameModel.getInstance();


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_cheating_function,null);

        CheckedTextView player1 = view.findViewById(R.id.checkedTextViewPlayer2);
        CheckedTextView player2 = view.findViewById(R.id.checkedTextViewPlayer3);
        CheckedTextView player3 = view.findViewById(R.id.checkedTextViewPlayer4);
        CheckedTextView player4 = view.findViewById(R.id.checkedTextViewPlayer5);

        return super.onCreateDialog(savedInstanceState);
    }
}