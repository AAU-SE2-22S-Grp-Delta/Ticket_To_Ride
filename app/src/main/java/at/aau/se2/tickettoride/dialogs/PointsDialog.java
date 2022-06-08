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

import java.util.HashMap;

import at.aau.se2.tickettoride.clientConnection.ClientConnection;
import at.aau.se2.tickettoride.models.GameModel;

public class PointsDialog extends DialogFragment {
    GameModel gameModel = GameModel.getInstance();
    //TODO Add symbol
    private static final String SPLITTER = "";
    private String[] response;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        //GET VALUES FROM RECEIVING THREAD
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            for (String key : bundle.keySet()) {
                switch (key) {
                    case "getPoints":
                        response = bundle.getString(key).split(SPLITTER);
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
            String player = players[i];
            String points = response[i].split(player)[1];
            textView.setText(player+ ": " + points);

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
