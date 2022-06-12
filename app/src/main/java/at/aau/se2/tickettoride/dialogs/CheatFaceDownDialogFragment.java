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

public class CheatFaceDownDialogFragment extends DialogFragment {
    GameModel gameModel = GameModel.getInstance();
    public Dialog dialog;
    Button action1;
    Button action2;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_cheating_face_down,null);

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);

        action1 = view.findViewById(R.id.buttonAction1);
        action1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


        action2 = view.findViewById(R.id.buttonAction2);
        action2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        dialog.setContentView(view);
        dialog.create();
        return dialog;
    }
}
