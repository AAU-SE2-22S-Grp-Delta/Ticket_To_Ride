package at.aau.se2.tickettoride.helpers;

import androidx.annotation.NonNull;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.aau.se2.tickettoride.dataStructures.TrainCard;
import at.aau.se2.tickettoride.models.GameModel;

public class LocalGameHelper {
    public static void generateTestGame(GameModel gameModel) {
        List<TrainCard> trainCards = getTrainCards();
        List<TrainCard> playerTrainCards = getPlayerTrainCards(trainCards, 4);
        List<TrainCard> deskOpenTrainCards = getPlayerTrainCards(trainCards, 5);
        List<Integer> destinationCards = generateDestinationCards();
        List<Integer> playerDestinationCards = getPlayerMissionCards(destinationCards);

        gameModel.setDeskOpenTrainCards(deskOpenTrainCards);
        gameModel.setDeskClosedTrainCards(trainCards);
        gameModel.setDeskDestinationCards(destinationCards);
        gameModel.setPlayerTrainCards(playerTrainCards);
        gameModel.setPlayerDestinationCards(playerDestinationCards);
    }

    @NonNull
    private static List<TrainCard> getTrainCards() {
        List<TrainCard> cards = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            cards.add(new TrainCard(TrainCard.Type.BOX));
            cards.add(new TrainCard(TrainCard.Type.PASSENGER));
            cards.add(new TrainCard(TrainCard.Type.TANKER));
            cards.add(new TrainCard(TrainCard.Type.REEFER));
            cards.add(new TrainCard(TrainCard.Type.FREIGHT));
            cards.add(new TrainCard(TrainCard.Type.HOPPER));
            cards.add(new TrainCard(TrainCard.Type.COAL));
            cards.add(new TrainCard(TrainCard.Type.CABOOSE));
        }
        for (int i = 0; i < 14; i++) {
            cards.add(new TrainCard(TrainCard.Type.LOCOMOTIVE));
        }

        Collections.shuffle(cards);

        return cards;
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
    private static List<TrainCard> getPlayerTrainCards(List<TrainCard> trainCards, int numCards) {
        List<TrainCard> cards = new ArrayList<>();
        for (int i = 0; i < numCards; i++) {
            TrainCard card = trainCards.remove(0);
            cards.add(card);
        }
        return cards;
    }

    @NonNull
    private static List<Integer> getPlayerMissionCards(List<Integer> missionCards) {
        List<Integer> cards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Integer card = missionCards.remove(0);
            cards.add(card);
        }
        return cards;
    }
}
