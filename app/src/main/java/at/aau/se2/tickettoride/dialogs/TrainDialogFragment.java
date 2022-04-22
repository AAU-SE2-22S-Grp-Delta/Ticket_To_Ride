package at.aau.se2.tickettoride.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Arrays;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.models.GameModel;
import at.aau.se2.tickettoride.ResourceHelper;

public class TrainDialogFragment extends DialogFragment {

    GameModel gameModel = GameModel.getInstance();
    int cardNr;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Colors color;

        //Create View
        LinearLayout layout = new LinearLayout(getActivity());
        ImageView imageView = new ImageView(getActivity());
        //Generate Random color
        //TODO Delete it after it is in game.
        gameModel.setDeskClosedTrainCards(new ArrayList<Integer>(Arrays.asList(3, 5, 6)));
        gameModel.setPlayerTrainCards(new ArrayList<Integer>(Arrays.asList(2, 4)));
        cardNr = gameModel.getNextClosedTrainCard();

        imageView.setImageResource(ResourceHelper.getTrainResource(cardNr));
        imageView.setMinimumHeight(500);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(imageView);

        //Set Dialog Window
        builder.setView(layout)
                .setTitle("Deine neue Waagonkarte")
                .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        gameModel.getPlayerTrainCards().add(cardNr);
                        Log.i("RESULT", Integer.toString(cardNr));
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
