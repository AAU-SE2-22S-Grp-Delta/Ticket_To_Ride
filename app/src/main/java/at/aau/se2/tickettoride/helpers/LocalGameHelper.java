package at.aau.se2.tickettoride.helpers;

import androidx.annotation.NonNull;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.aau.se2.tickettoride.models.GameModel;

public class LocalGameHelper {
    public static void generateTestGame(GameModel gameModel) {
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
        gameModel.setDeskDiscardedTrainCards(new ArrayList<>());
        gameModel.setPlayerColoredTrainCards(45);
    }

    @NonNull
    private static List<Integer> generateTrainCards() {
        List<Integer> trainCards = new ArrayList<>();
        SecureRandom random = new SecureRandom();
        int[] countTrainCards = new int[9];
        int generatedCards = 0;
        while (generatedCards < 110) {
            int card = random.nextInt(9) + 1;
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
    private static List<Integer> generateDestinationCards() {
        List<Integer> destinationCards = new ArrayList<>();
        SecureRandom random = new SecureRandom();
        int generatedCards = 0;
        while (generatedCards < 30) {
            int card = random.nextInt(30) + 1;
            if (destinationCards.contains(card)) {
                continue;
            }

            destinationCards.add(card);

            generatedCards++;
        }
        return destinationCards;
    }

    @NonNull
    private static Integer[] getNumCards(List<Integer> cards, int count) {
        Integer[] numCards = new Integer[count];
        for (int i = 0; i < count; i++) {
            Integer card = cards.remove(0);
            numCards[i] = card;
        }
        return numCards;
    }
}
