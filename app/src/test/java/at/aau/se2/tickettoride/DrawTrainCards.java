package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import at.aau.se2.tickettoride.dataStructures.TrainCard;
import at.aau.se2.tickettoride.dialogs.TrainDialogFragment;
import at.aau.se2.tickettoride.models.GameModel;

public class DrawTrainCards {
    TrainDialogFragment trainDialogFragment;
    GameModel gameModel;

    @BeforeEach
    public void SetUp() {
        gameModel = GameModel.getInstance();
        gameModel.setDeskClosedTrainCards(new ArrayList<>(Arrays.asList(new TrainCard(TrainCard.Type.PINK), new TrainCard(TrainCard.Type.BLACK), new TrainCard(TrainCard.Type.ORANGE))));
        trainDialogFragment = new TrainDialogFragment();
        trainDialogFragment.card = gameModel.getNextClosedTrainCard();
    }

    @Test
    public void testAddCardToHand() {
        trainDialogFragment.addCardToHand();
        assertEquals(gameModel.getPlayerTrainCards().size(), 1);
        assertEquals(gameModel.getPlayerTrainCards().get(0).getType(), new TrainCard(TrainCard.Type.PINK).getType());
    }
}
