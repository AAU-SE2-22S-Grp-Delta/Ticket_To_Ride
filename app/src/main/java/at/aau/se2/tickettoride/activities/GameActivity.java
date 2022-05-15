package at.aau.se2.tickettoride.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.clientConnection.ClientConnection;
import at.aau.se2.tickettoride.databinding.ActivityGameBinding;
import at.aau.se2.tickettoride.dialogs.PointsDialog;
import at.aau.se2.tickettoride.fragments.PlayerDestinationFragment;
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

        /*
            225 Waggons in
            144 (lieber 255?) farbige Spielkarten    8 farben = 25 wagons jeder farbe + 25 lokomotiven
        */

        generateTestGame();

        // After starting a new game send refresh to all attached fragments
        Bundle result = new Bundle();
        getSupportFragmentManager().setFragmentResult("refresh", result);
    }

    private void generateTestGame() {
        List<Integer> trainCards = generateTrainCards();
        List<Integer> playerTrainCards = Arrays.asList(getNumCards(trainCards, 4));
        Integer[] deskOpenTrainCards = getNumCards(trainCards, 5);
        List<Integer> destinationCards = generateDestinationCards();
        List<Integer> playerDestinationCards = Arrays.asList(getNumCards(destinationCards, 3));

        gameModel.setDeskOpenTrainCards(deskOpenTrainCards);
        gameModel.setDeskClosedTrainCards(trainCards);
        gameModel.setDeskDestinationCards(destinationCards);
        gameModel.setPlayerTrainCards(playerTrainCards);
        gameModel.setPlayerDestinationCards(playerDestinationCards);
    }

    @NonNull
    private List<Integer> generateTrainCards() {
        List<Integer> trainCards = new ArrayList<>();
        int[] countTrainCards = new int[9];
        int generatedCards = 0;
        while (generatedCards < 110) {
            int card = ThreadLocalRandom.current().nextInt(1, 9 + 1);
            int count = countTrainCards[card - 1];
            if (card != 9 && count >= 12 || card == 9 && count >= 14) {
                continue;
            }

            countTrainCards[card - 1]++;
            trainCards.add(card);

            generatedCards++;
        }
        return trainCards;
    }

    @NonNull
    private List<Integer> generateDestinationCards() {
        List<Integer> destinationCards = new ArrayList<>();
        int generatedCards = 0;
        while (generatedCards < 30) {
            int card = ThreadLocalRandom.current().nextInt(1, 30 + 1);
            if (destinationCards.contains(card)) {
                continue;
            }

            destinationCards.add(card);

            generatedCards++;
        }
        return destinationCards;
    }

    @NonNull
    private Integer[] getNumCards(List<Integer> cards, int count) {
        Integer[] numCards = new Integer[count];
        for (int i = 0; i < count; i++) {
            Integer card = cards.remove(0);
            numCards[i] = card;
        }
        return numCards;
    }
}