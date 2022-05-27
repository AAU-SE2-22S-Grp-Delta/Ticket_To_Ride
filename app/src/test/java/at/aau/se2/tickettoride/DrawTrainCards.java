package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import at.aau.se2.tickettoride.dialogs.TrainDialogFragment;
import at.aau.se2.tickettoride.models.GameModel;

public class DrawTrainCards {
    TrainDialogFragment trainDialogFragment;
    GameModel gameModel;

    @BeforeEach
    public void SetUp() {
        gameModel = GameModel.getInstance();
        gameModel.setDeskClosedTrainCards(new ArrayList<>(Arrays.asList(1, 2, 3)));
        trainDialogFragment = new TrainDialogFragment();
        trainDialogFragment.cardNr = gameModel.getNextClosedTrainCard();
    }

    @Test
    public void testAddCardToHand() {
        trainDialogFragment.addCardToHand();
        assertEquals(gameModel.getPlayerTrainCards().size(), 1);
        assertEquals(gameModel.getPlayerTrainCards(), new ArrayList<>(Collections.singletonList(1)));
    }
}
