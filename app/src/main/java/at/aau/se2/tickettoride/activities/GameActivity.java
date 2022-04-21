package at.aau.se2.tickettoride.activities;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.databinding.ActivityGameBinding;
import at.aau.se2.tickettoride.models.GameModel;

public class GameActivity extends AppCompatActivity {
    private GameModel gameModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityGameBinding binding = ActivityGameBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setView(R.layout.fragment_player_mission);

        androidx.appcompat.app.AlertDialog dialog = builder.create();

        binding.missionsBtn.setOnClickListener(view1 -> {
            dialog.show();
        });

        startGame();
    }

    private void startGame() {
        gameModel = GameModel.getInstance();

        // Generate a new game (will be made on server)
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
        Integer[] numCards = new Integer[5];
        for (int i = 0; i < count; i++) {
            Integer card = cards.remove(0);
            numCards[i] = card;
        }
        return numCards;
    }
}