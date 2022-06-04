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

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.clientConnection.ClientConnection;
import at.aau.se2.tickettoride.databinding.ActivityMainBinding;
import at.aau.se2.tickettoride.dialogs.HelpDialogFragment;
import at.aau.se2.tickettoride.helpers.LocalGameHelper;
import at.aau.se2.tickettoride.models.GameModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
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
                    default:
                        break;
                }
            }
        }
    };

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
            // TODO: Temporary generate player with time
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss");
            String date = simpleDateFormat.format(new Date());
            client.sendCommand("enterLobby:Player" + date);

            client.sendCommand("listGames");

            // TODO: Temporary needed to test start of a game
            GameModel gameModel = GameModel.getInstance();
            LocalGameHelper.generateTestGame(gameModel);

            Intent intent = new Intent(this, DrawDestinationCardsActivity.class);
            startActivity(intent);
        });

        binding.buttonLocal.setOnClickListener(v -> {
            // Generate a new local game
            GameModel gameModel = GameModel.getInstance();
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