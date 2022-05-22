package at.aau.se2.tickettoride.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import at.aau.se2.tickettoride.clientConnection.ClientConnection;
import at.aau.se2.tickettoride.databinding.ActivityGameBinding;
import at.aau.se2.tickettoride.dialogs.PointsDialog;
import at.aau.se2.tickettoride.fragments.PlayerDestinationFragment;
import at.aau.se2.tickettoride.helpers.LocalGameHelper;
import at.aau.se2.tickettoride.models.GameModel;

public class GameActivity extends AppCompatActivity {
    private GameModel gameModel;
    private ClientConnection clientConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityGameBinding binding = ActivityGameBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Hide the status and action bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        initComponents(binding);

        startGame();
    }

    private void initComponents(ActivityGameBinding binding) {
        binding.missionsButton.setOnClickListener(view -> {
            FragmentManager fm = getSupportFragmentManager();
            PlayerDestinationFragment destinationFragment = PlayerDestinationFragment.newInstance();
            destinationFragment.show(fm, "fragment_player_destination");
        });
        binding.pointsTextView.setOnClickListener(view -> {
            DialogFragment pointsDialog = new PointsDialog();
            pointsDialog.show(getSupportFragmentManager(), "points");
        });
    }

    private void startGame() {
        gameModel = GameModel.getInstance();
        clientConnection = ClientConnection.getInstance();
        clientConnection.sendCommand("enterLobby:testPlayer;createGame:testGame:testPlayer");

        // Generate a new game (will be made on server)
        LocalGameHelper.generateTestGame(gameModel);

        // After starting a new game send refresh to all attached fragments
        Bundle result = new Bundle();
        getSupportFragmentManager().setFragmentResult("refresh", result);
    }
}