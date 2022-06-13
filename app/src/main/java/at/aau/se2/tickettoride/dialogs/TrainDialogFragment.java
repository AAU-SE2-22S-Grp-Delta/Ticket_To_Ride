package at.aau.se2.tickettoride.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.dataStructures.TrainCard;
import at.aau.se2.tickettoride.helpers.ResourceHelper;
import at.aau.se2.tickettoride.models.GameModel;

public class TrainDialogFragment extends DialogFragment {
    private TrainCard card;

    public static TrainDialogFragment newInstance(String card) {
        TrainDialogFragment fragment = new TrainDialogFragment();
        Bundle args = new Bundle();
        args.putString("card_drawn", card);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            String c = getArguments().getString("card_drawn");
            card = new TrainCard(TrainCard.Type.getByString(c));
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Create View
        LinearLayout layout = new LinearLayout(getActivity());
        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(ResourceHelper.getTrainResource(card));
        imageView.setMinimumHeight(500);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(imageView);

        //Set Dialog Window
        builder.setView(layout)
                .setTitle("Deine neue Waagonkarte")
                .setPositiveButton(R.string.accept, (dialogInterface, id) -> dismiss());
        return builder.create();
    }
}
