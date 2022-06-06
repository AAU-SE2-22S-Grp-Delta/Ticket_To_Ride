package at.aau.se2.tickettoride.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.MessageFormat;

public class PointsDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        //TODO Edit to get real number of players
        int numberOfPlayers = 4;

        for (int i = 1; i <= numberOfPlayers; i++) {
            TextView textView = new TextView(getActivity());
            //TODO Get Points
            textView.setText(MessageFormat.format("Player {0}: {1}", i, 0));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(80, 20, 50, 0);

            layout.addView(textView, params);
        }

        builder.setView(layout)
                .setTitle("Punkte")
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> dismiss());

        return builder.create();
    }
}
