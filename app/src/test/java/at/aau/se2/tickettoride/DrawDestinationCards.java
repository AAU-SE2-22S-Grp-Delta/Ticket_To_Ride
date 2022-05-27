package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import at.aau.se2.tickettoride.dialogs.DestinationDialogFragment;
import at.aau.se2.tickettoride.models.GameModel;

public class DrawDestinationCards {
    DestinationDialogFragment destinationDialogFragment;
    String[] destinations;
    GameModel gameModel;

    @BeforeEach
    public void SetUp() {
        gameModel = GameModel.getInstance();
        gameModel.setDeskDestinationCards(new ArrayList<>(Arrays.asList(1, 2, 3)));
        destinationDialogFragment = new DestinationDialogFragment();
        destinations = destinationDialogFragment.getChoices();
    }

    @Test
    public void testGetChoicesLength() {
        assertEquals(destinations.length, 3);
    }

    @Test
    public void testGetChoicesItems() {
        String[] array = {"Ziel 1", "Ziel 2", "Ziel 3"};
        assertArrayEquals(destinations, array);
    }

    @Test
    public void testChooseAdd1() {
        destinationDialogFragment.choose(true, 0);
        ArrayList<Integer> selected = new ArrayList<>(Collections.singletonList(1));
        assertEquals(destinationDialogFragment.selectedItems, selected);
    }

    @Test
    public void testChooseAdd2() {
        destinationDialogFragment.choose(true, 0);
        destinationDialogFragment.choose(true, 2);
        ArrayList<Integer> selected = new ArrayList<>(Arrays.asList(1, 3));
        assertEquals(destinationDialogFragment.selectedItems, selected);
    }

    @Test
    public void testChooseDelete1() {
        destinationDialogFragment.choose(true, 0);
        destinationDialogFragment.choose(false, 0);
        ArrayList<Integer> selected = new ArrayList<>();
        assertEquals(destinationDialogFragment.selectedItems, selected);
    }

    @Test
    public void testChooseDelete2() {
        destinationDialogFragment.choose(true, 0);
        destinationDialogFragment.choose(true, 2);
        destinationDialogFragment.choose(false, 0);
        ArrayList<Integer> selected = new ArrayList<>(Collections.singletonList(3));
        assertEquals(destinationDialogFragment.selectedItems, selected);
    }

    @Test
    public void testAddChosenCardsToHand() {
        destinationDialogFragment.choose(true, 0);
        destinationDialogFragment.choose(true, 2);
        ArrayList<Integer> playerCards = new ArrayList<>(Arrays.asList(1, 3));
        destinationDialogFragment.addChosenCardsToHand();
        assertEquals(gameModel.getPlayerDestinationCards(), playerCards);
    }
}
