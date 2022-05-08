package at.aau.se2.tickettoride;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import at.aau.se2.tickettoride.dialogs.TrainDialogFragment;
import at.aau.se2.tickettoride.models.GameModel;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(JUnit4.class)
public class DrawTrainCards {
    TrainDialogFragment trainDialogFragment;
    GameModel gameModel;

    @Before
    public void SetUp(){
        gameModel = GameModel.getInstance();
        gameModel.setDeskClosedTrainCards(new ArrayList<>(Arrays.asList(1, 2, 3)));
        trainDialogFragment = new TrainDialogFragment();
        trainDialogFragment.cardNr = gameModel.getNextClosedTrainCard();
    }

    @Test
    public void testAddCardToHand(){
        trainDialogFragment.addCardToHand();
        assertEquals(gameModel.getPlayerTrainCards().size(), 1);
        assertEquals(gameModel.getPlayerTrainCards(), new ArrayList<>(Arrays.asList(1)));
    }
}
