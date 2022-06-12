package at.aau.se2.tickettoride.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.clientConnection.ClientConnection;
import at.aau.se2.tickettoride.databinding.ActivityMainBinding;
import at.aau.se2.tickettoride.dialogs.HelpDialogFragment;
import at.aau.se2.tickettoride.helpers.LocalGameHelper;
import at.aau.se2.tickettoride.models.GameModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final GameModel gameModel;
    private ClientConnection client;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            for (String key : bundle.keySet()) {
                switch (key) {
                    case "listGames":
                        Log.v("Broadcast", "Command: " + key + " - Value: " + bundle.getString(key));
                        break;
                    case "drawMission":
                        if (bundle.getString(key).equals("1")) {
                            Intent i = new Intent(context, DrawDestinationCardsActivity.class);
                            startActivity(i);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    };

    public MainActivity() {
        gameModel = GameModel.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initComponents();
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initComponents() {
        binding.button.setOnClickListener(v -> {
            // TODO: Temporary generate player and game with time
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss", Locale.getDefault());
            String date = simpleDateFormat.format(new Date());

            String playerName = "Player" + date;
            gameModel.setPlayerName(playerName);

            // Enter lobby with Player + time
            client.sendCommand("enterLobby:" + playerName);

            // Create game with Game + time
            client.sendCommand("createGame:Game" + date);

            // List available games
            client.sendCommand("listGames");

            // Start game created
            client.sendCommand("startGame");
        });

        binding.buttonLocal.setOnClickListener(v -> {
            // Generate a new local game
            LocalGameHelper.generateTestGame(gameModel);

            Intent intent = new Intent(this, DrawDestinationCardsActivity.class);
            startActivity(intent);
        });

        binding.floatingActionButton.setOnClickListener(v -> {
            HelpDialogFragment helpDialogFragment = new HelpDialogFragment();
            helpDialogFragment.show(getSupportFragmentManager(), "helpDialog");
        });

        // Establish connection
        client = ClientConnection.getInstance();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String serverAddress = sharedPreferences.getString("server_address", "");
        if (serverAddress.isEmpty()) {
            binding.button.setEnabled(false);
        } else {
            client.setup(this, serverAddress);
        }

        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("server"));
    }
}