package at.aau.se2.tickettoride;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import at.aau.se2.tickettoride.dialogs.DestinationDialogFragment;
import at.aau.se2.tickettoride.models.GameModel;

@RunWith(JUnit4.class)
public class DrawDestinationCards {
    DestinationDialogFragment destinationDialogFragment;

    @Before
    public void SetUp(){
        ArrayList<Integer> deskDestinationCards = new ArrayList<>(Arrays.asList(1, 2, 3));
        GameModel gameModel = GameModel.getInstance();
        gameModel.setDeskDestinationCards(deskDestinationCards);
        destinationDialogFragment = new DestinationDialogFragment();
    }

    @Test
    public void testGetChoicesLength(){
        String[] destinations = destinationDialogFragment.getChoices();
        assertEquals(destinations.length, 3);
    }

    @Test
    public void testGetChoicesItems(){
        String[] destinations = destinationDialogFragment.getChoices();
        String[] array = {"Ziel 1", "Ziel 2", "Ziel 3"};
        assertEquals(destinations, array);
    }
}
