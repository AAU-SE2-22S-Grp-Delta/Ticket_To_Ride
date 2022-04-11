package at.aau.se2.tickettoride.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
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

public class TrainDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LinearLayout layout = new LinearLayout(getActivity());
        Random random = new Random();
        int randomNumber = random.nextInt(8);
        ImageView imageView = new ImageView(getActivity());
        switch (randomNumber){
            case 0: //Black
                imageView.setImageResource(R.drawable.ic_train_black);
                break;
            case 1: //Blue
                imageView.setImageResource(R.drawable.ic_train_blue);
                break;
            case 2: //Green
                imageView.setImageResource(R.drawable.ic_train_green);
                break;
            case 3: //Orange
                imageView.setImageResource(R.drawable.ic_train_orange);
                break;
            case 4: //Purpur
                imageView.setImageResource(R.drawable.ic_train_purpur);
                break;
            case 5: //Red
                imageView.setImageResource(R.drawable.ic_train_red);
                break;
            case 6: //White
                imageView.setImageResource(R.drawable.ic_train_white);
                break;
            case 7: //Yellow
                imageView.setImageResource(R.drawable.ic_train_yellow);
                break;
            default:
                imageView.setImageResource(R.drawable.ic_train_black);
                break;
        }

        imageView.setMinimumHeight(500);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(imageView);

        builder.setView(layout)
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
