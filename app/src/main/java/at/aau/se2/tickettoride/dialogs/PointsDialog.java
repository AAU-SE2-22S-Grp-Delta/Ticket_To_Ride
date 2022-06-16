package at.aau.se2.tickettoride.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.io.IOException;
import java.util.HashMap;

import at.aau.se2.tickettoride.clientConnection.ClientConnection;
import at.aau.se2.tickettoride.dataStructures.Player;
import at.aau.se2.tickettoride.models.GameModel;

import java.text.MessageFormat;
import java.util.List;

public class PointsDialog extends DialogFragment {
    GameModel gameModel = GameModel.getInstance();
    private AlertDialog.Builder builder;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(getActivity());
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);

        List<Player> players = gameModel.getPlayers();

        for (int i = 0; i < players.size(); i++) {
            TextView textView = new TextView(getActivity());

            Player player = players.get(i);
            textView.setText(MessageFormat.format("{0}: {1}", player.getName(), player.getPoints()));

            //TextColor
            int colorPlayer = getTextColor(player.getPlayerColor());
            textView.setTextColor(colorPlayer);

            textView.setTextSize(18);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(80, 20, 50, 0);

            layout.addView(textView, params);
        }

        builder.setView(layout)
                .setTitle("Punkte")
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> dismiss());

        return builder.create();
    }

    private int getTextColor(int colorPlayer){
        switch (colorPlayer){
            //RED
            case 0:
                return Color.RED;
            //BLUE
            case 1:
                return Color.BLUE;
            //GREEN
            case 2:
                return Color.GREEN;
            //YELLOW
            case 3:
                return Color.YELLOW;
            //BLACK
            case 4:
                return Color.BLACK;
            default:
                return Color.GRAY;
        }
    }
}
