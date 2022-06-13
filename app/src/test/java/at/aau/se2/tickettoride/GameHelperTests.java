package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import at.aau.se2.tickettoride.dataStructures.TrainCard;
import at.aau.se2.tickettoride.helpers.LocalGameHelper;
import at.aau.se2.tickettoride.models.GameModel;

class GameHelperTests
{
    static List<TrainCard> deskClosedTrainCards;
    static List<TrainCard> deskOpenTrainCards;
    static List<Integer> deskDestinationCards;
    static List<TrainCard> playerTrainCards;
    static List<Integer> playerDestinationCards;
    static GameModel gm = GameModel.getInstance();

    @BeforeAll
    static void init()
    {
        deskClosedTrainCards = new ArrayList<>(Collections.singletonList(new TrainCard(TrainCard.Type.BLACK)));
        deskOpenTrainCards = new ArrayList<>(Arrays.asList(new TrainCard(TrainCard.Type.BLUE), new TrainCard(TrainCard.Type.PINK), new TrainCard(TrainCard.Type.ORANGE), new TrainCard(TrainCard.Type.YELLOW), new TrainCard(TrainCard.Type.WHITE)));
        deskDestinationCards = new ArrayList<>(Collections.singletonList(12));
        playerTrainCards = new ArrayList<>(Arrays.asList(new TrainCard(TrainCard.Type.PINK), new TrainCard(TrainCard.Type.BLUE)));
        playerDestinationCards = new ArrayList<>(Collections.singletonList(19));

        gm.setDeskClosedTrainCards(deskClosedTrainCards);
        gm.setDeskOpenTrainCards(deskOpenTrainCards);
        gm.setDeskDestinationCards(deskDestinationCards);
        gm.setPlayerTrainCards(playerTrainCards);
        gm.setPlayerDestinationCards(playerDestinationCards);
    }

    @Test
    void testGenerate()
    {
        LocalGameHelper.generateTestGame(gm);
        assertEquals(101, gm.getDeskClosedTrainCards().size());
        assertEquals(27, gm.getDeskDestinationCards().size());
        assertEquals(4, gm.getPlayerTrainCards().size());
        assertEquals(5, gm.getDeskOpenTrainCards().size());
        assertEquals(1, gm.getPlayerDestinationCards().size());
    }
}
