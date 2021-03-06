package at.aau.se2.tickettoride.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.client.ClientConnection;
import at.aau.se2.tickettoride.databinding.ActivityMainBinding;
import at.aau.se2.tickettoride.dialogs.HelpDialogFragment;
import at.aau.se2.tickettoride.helpers.LocalGameHelper;
import at.aau.se2.tickettoride.models.GameModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final GameModel gameModel;
    private final ClientConnection client;

    public MainActivity() {
        this.gameModel = GameModel.getInstance();
        this.client = ClientConnection.getInstance();
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
            Intent intent = new Intent(this, LobbyActivity.class);
            startActivity(intent);
        });

        binding.buttonLocal.setOnClickListener(v -> {
            // Generate a new local game
            LocalGameHelper.generateTestGame(gameModel);

            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        });

        binding.floatingActionButton.setOnClickListener(v -> {
            HelpDialogFragment helpDialogFragment = new HelpDialogFragment();
            helpDialogFragment.show(getSupportFragmentManager(), "helpDialog");
        });

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String serverAddress = sharedPreferences.getString("server_address", "");
        if (serverAddress.isEmpty()) {
            binding.button.setEnabled(false);
        } else {
            client.setup(this, serverAddress);
        }
    }
}