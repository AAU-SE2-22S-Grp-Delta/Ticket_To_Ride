package at.aau.se2.tickettoride.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.models.GameModel;

public class CheatingFaceDownDialogFragment extends DialogFragment {
    GameModel gameModel = GameModel.getInstance();
    private Dialog dialog;
    Button action1;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_cheating_face_down,null);

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);

        action1 = view.findViewById(R.id.buttonAction1);
        action1.setOnClickListener(v -> {
            gameModel.cheatTrainCard();
            dialog.dismiss();
        });
        dialog.setContentView(view);
        dialog.create();
        return dialog;
    }
}

