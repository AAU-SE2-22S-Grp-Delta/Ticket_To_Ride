package at.aau.se2.tickettoride.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Random;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.enums.Colors;

public class TrainDialogFragment extends DialogFragment {

    public interface TrainDialogListener{
        public void onDialogPositiveClick(Colors color);
    }

    TrainDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (TrainDialogListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString() + " must implement DestinationDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Colors color;

        //Create View
        LinearLayout layout = new LinearLayout(getActivity());
        ImageView imageView = new ImageView(getActivity());
        //Generate Random color
        Random random = new Random();
        int randomNumber = random.nextInt(7);
        switch (randomNumber){
            case 0: //Black
                imageView.setImageResource(R.drawable.ic_train_black);
                color = Colors.BLACK;
                break;
            case 1: //Blue
                imageView.setImageResource(R.drawable.ic_train_blue);
                color = Colors.BLUE;
                break;
            case 2: //Green
                imageView.setImageResource(R.drawable.ic_train_green);
                color = Colors.GREEN;
                break;
            case 3: //Orange
                imageView.setImageResource(R.drawable.ic_train_orange);
                color = Colors.ORANGE;
                break;
            case 4: //Purpur
                imageView.setImageResource(R.drawable.ic_train_purpur);
                color = Colors.PURPUR;
                break;
            case 5: //Red
                imageView.setImageResource(R.drawable.ic_train_red);
                color = Colors.RED;
                break;
            case 6: //White
                imageView.setImageResource(R.drawable.ic_train_white);
                color = Colors.WHITE;
                break;
            default: //Yellow
                imageView.setImageResource(R.drawable.ic_train_yellow);
                color = Colors.YELLOW;
                break;
        }
        imageView.setMinimumHeight(500);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(imageView);

        //Set Dialog Window
        builder.setView(layout)
                .setTitle("Deine neue Waagonkarte")
                .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        listener.onDialogPositiveClick(color);
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
