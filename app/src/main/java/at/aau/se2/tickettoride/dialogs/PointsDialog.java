package at.aau.se2.tickettoride.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.HashMap;

import at.aau.se2.tickettoride.clientConnection.ClientConnection;
import at.aau.se2.tickettoride.models.GameModel;

import java.text.MessageFormat;

public class PointsDialog extends DialogFragment {
    GameModel gameModel = GameModel.getInstance();
    private String[] response;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        //GET VALUES FROM RECEIVING THREAD
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            for (String key : bundle.keySet()) {
                switch (key) {
                    case "getPoints":
                        response = bundle.getString(key).split("\\.");
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

        //SEND COMMAND TO SERVER
        ClientConnection client = ClientConnection.getInstance();
        client.sendCommand("getPoints");

        String[] players = gameModel.getPlayers();

        for (int i = 1; i < players.length; i++) {
            TextView textView = new TextView(getActivity());
            //TODO Get Points
            textView.setText(MessageFormat.format("Player {0}: {1}", i, 0));
            String player = players[i];
            String points = response[i].split(player)[1];
            textView.setText(player+ ": " + points);

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
