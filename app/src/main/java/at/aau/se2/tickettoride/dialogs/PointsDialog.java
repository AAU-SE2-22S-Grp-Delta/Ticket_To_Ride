package at.aau.se2.tickettoride.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import at.aau.se2.tickettoride.activities.DrawDestinationCardsActivity;
import at.aau.se2.tickettoride.clientConnection.ClientConnection;

public class PointsDialog extends DialogFragment {


    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            for (String key : bundle.keySet()) {
                switch (key) {
                    case "getPoints":
                        String response = bundle.getString(key);
                        //TODO Find a way to split
                        break;
                    default:
                        break;
                }
            }
        }
    };

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        //TODO Edit to get real number of players
        ClientConnection client = ClientConnection.getInstance();
        client.sendCommand("getPoints");
        int numberOfPlayers = 4;

        for (int i = 1; i <= numberOfPlayers; i++) {
            TextView textView = new TextView(getActivity());
            //TODO Get Points
            textView.setText("Player " + i + ": 0");

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(80, 20, 50, 0);

            layout.addView(textView, params);
        }

        builder.setView(layout)
                .setTitle("Punkte")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                });

        return builder.create();
    }
}
