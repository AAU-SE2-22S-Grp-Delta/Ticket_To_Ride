package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import at.aau.se2.tickettoride.dataStructures.TrainCard;
import at.aau.se2.tickettoride.dialogs.DestinationDialogFragment;
import at.aau.se2.tickettoride.models.GameModel;

class DrawDestinationCards {
    DestinationDialogFragment destinationDialogFragment;
    String[] destinations;
    static List<TrainCard> deskClosedTrainCards;
    static List<TrainCard> deskOpenTrainCards;
    static List<Integer> deskDestinationCards;
    static List<TrainCard> playerTrainCards;
    static List<Integer> playerDestinationCards;
    static GameModel gameModel = GameModel.getInstance();

    @BeforeAll
    static void init()
    {
        deskClosedTrainCards = new ArrayList<>(Collections.singletonList(new TrainCard(TrainCard.Type.BLACK)));
        deskOpenTrainCards = new ArrayList<>(Arrays.asList(new TrainCard(TrainCard.Type.BLUE), new TrainCard(TrainCard.Type.PINK), new TrainCard(TrainCard.Type.ORANGE), new TrainCard(TrainCard.Type.YELLOW), new TrainCard(TrainCard.Type.WHITE)));
        deskDestinationCards = new ArrayList<>(Collections.singletonList(12));
        playerTrainCards = new ArrayList<>(Arrays.asList(new TrainCard(TrainCard.Type.PINK), new TrainCard(TrainCard.Type.BLUE)));
        playerDestinationCards = new ArrayList<>(Collections.singletonList(19));

        gameModel.setDeskClosedTrainCards(deskClosedTrainCards);
        gameModel.setDeskOpenTrainCards(deskOpenTrainCards);
        gameModel.setDeskDestinationCards(deskDestinationCards);
        gameModel.setPlayerTrainCards(playerTrainCards);
        gameModel.setPlayerDestinationCards(playerDestinationCards);
    }



    @BeforeEach
    void SetUp() {
        deskClosedTrainCards = new ArrayList<>(Collections.singletonList(new TrainCard(TrainCard.Type.BLACK)));
        deskOpenTrainCards = new ArrayList<>(Arrays.asList(new TrainCard(TrainCard.Type.BLUE), new TrainCard(TrainCard.Type.PINK), new TrainCard(TrainCard.Type.ORANGE), new TrainCard(TrainCard.Type.YELLOW), new TrainCard(TrainCard.Type.WHITE)));
        playerTrainCards = new ArrayList<>(Arrays.asList(new TrainCard(TrainCard.Type.PINK), new TrainCard(TrainCard.Type.BLUE)));
        playerDestinationCards = new ArrayList<>(Collections.singletonList(19));

        gameModel.setDeskClosedTrainCards(deskClosedTrainCards);
        gameModel.setDeskOpenTrainCards(deskOpenTrainCards);
        gameModel.setPlayerTrainCards(playerTrainCards);
        gameModel.setPlayerDestinationCards(playerDestinationCards);

        gameModel.setDeskDestinationCards(new ArrayList<>(Arrays.asList(1, 2, 3)));
        destinationDialogFragment = new DestinationDialogFragment();
        destinations = destinationDialogFragment.getChoices();
    }

    @Test
    void testGetChoicesLength() {
        assertEquals(destinations.length, 3);
    }

    @Test
    void testGetChoicesItems() {
        String[] array = {"Ziel 1", "Ziel 2", "Ziel 3"};
        assertArrayEquals(destinations, array);
    }

    @Test
    void testChooseAdd1() {
        destinationDialogFragment.choose(true, 0);
        ArrayList<Integer> selected = new ArrayList<>(Collections.singletonList(1));
        assertEquals(destinationDialogFragment.selectedItems, selected);
    }

    @Test
    void testChooseAdd2() {
        destinationDialogFragment.choose(true, 0);
        destinationDialogFragment.choose(true, 2);
        ArrayList<Integer> selected = new ArrayList<>(Arrays.asList(1, 3));
        assertEquals(destinationDialogFragment.selectedItems, selected);
    }

    @Test
    void testChooseDelete1() {
        destinationDialogFragment.choose(true, 0);
        destinationDialogFragment.choose(false, 0);
        ArrayList<Integer> selected = new ArrayList<>();
        assertEquals(destinationDialogFragment.selectedItems, selected);
    }

    @Test
    void testChooseDelete2() {
        destinationDialogFragment.choose(true, 0);
        destinationDialogFragment.choose(true, 2);
        destinationDialogFragment.choose(false, 0);
        ArrayList<Integer> selected = new ArrayList<>(Collections.singletonList(3));
        assertEquals(destinationDialogFragment.selectedItems, selected);
    }

    @Test
    void testAddChosenCardsToHand() {
        destinationDialogFragment.choose(true, 0);
        destinationDialogFragment.choose(true, 2);
        ArrayList<Integer> playerCards = new ArrayList<>(Arrays.asList(19, 1, 3));
        destinationDialogFragment.addChosenCardsToHand();
        assertEquals(playerCards, gameModel.getPlayerDestinationCards());
    }
}
