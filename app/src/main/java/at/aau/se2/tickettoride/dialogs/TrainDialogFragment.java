package at.aau.se2.tickettoride.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import java.util.Objects;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.dataStructures.TrainCard;
import at.aau.se2.tickettoride.helpers.ResourceHelper;
import at.aau.se2.tickettoride.models.GameModel;

public class TrainDialogFragment extends DialogFragment {

    GameModel gameModel = GameModel.getInstance();
    public TrainCard card;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Create View
        LinearLayout layout = new LinearLayout(getActivity());
        ImageView imageView = new ImageView(getActivity());

        card = gameModel.getNextClosedTrainCard();
        imageView.setImageResource(ResourceHelper.getTrainResource(card));
        imageView.setMinimumHeight(500);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(imageView);

        //Set Dialog Window
        builder.setView(layout)
                .setTitle("Deine neue Waagonkarte")
                .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        addCardToHand();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        gameModel.addDiscardedTrainCard(card);
                        Objects.requireNonNull(TrainDialogFragment.this.getDialog()).cancel();
                    }
                });
        return builder.create();
    }
    public void addCardToHand(){
        gameModel.addDrawnTrainCard(card);

        FragmentActivity activity = getActivity();
        if (activity != null) {
            Bundle result = new Bundle();
            getParentFragmentManager().setFragmentResult("RefreshPlayerTrain", result);
        }
    }
}
