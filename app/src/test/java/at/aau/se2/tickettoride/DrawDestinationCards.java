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
import java.util.Collections;
import java.util.HashSet;

import at.aau.se2.tickettoride.dialogs.DestinationDialogFragment;
import at.aau.se2.tickettoride.models.GameModel;

@RunWith(JUnit4.class)
public class DrawDestinationCards {
    DestinationDialogFragment destinationDialogFragment;
    String[] destinations;

    @Before
    public void SetUp(){
        ArrayList<Integer> deskDestinationCards = new ArrayList<>(Arrays.asList(1, 2, 3));
        GameModel gameModel = GameModel.getInstance();
        gameModel.setDeskDestinationCards(deskDestinationCards);
        destinationDialogFragment = new DestinationDialogFragment();
        destinations = destinationDialogFragment.getChoices();
    }

    @Test
    public void testGetChoicesLength(){
        assertEquals(destinations.length, 3);
    }

    @Test
    public void testGetChoicesItems(){
        String[] array = {"Ziel 1", "Ziel 2", "Ziel 3"};
        assertEquals(destinations, array);
    }

    @Test
    public void testChooseAdd1(){
        destinationDialogFragment.choose(true, 0);
        ArrayList<Integer> selected = new ArrayList<>(Collections.singletonList(1));
        assertEquals(destinationDialogFragment.selectedItems, selected);
    }

    @Test
    public void testChooseAdd2(){
        destinationDialogFragment.choose(true, 0);
        destinationDialogFragment.choose(true, 2);
        ArrayList<Integer> selected = new ArrayList<>(Arrays.asList(1, 3));
        assertEquals(destinationDialogFragment.selectedItems, selected);
    }

    @Test
    public void testChooseDelete1(){
        destinationDialogFragment.choose(true, 0);
        destinationDialogFragment.choose(false,0);
        ArrayList<Integer> selected = new ArrayList<>();
        assertEquals(destinationDialogFragment.selectedItems, selected);
    }

    @Test
    public void testChooseDelete2(){
        destinationDialogFragment.choose(true, 0);
        destinationDialogFragment.choose(true, 2);
        destinationDialogFragment.choose(false,0);
        ArrayList<Integer> selected = new ArrayList<>(Arrays.asList(3));
        assertEquals(destinationDialogFragment.selectedItems, selected);
    }
}
