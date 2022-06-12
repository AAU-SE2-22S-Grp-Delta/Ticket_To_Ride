package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import at.aau.se2.tickettoride.dataStructures.TrainCard;
import at.aau.se2.tickettoride.models.GameModel;

class GameModelTests
{
    static List<TrainCard> deskClosedTrainCards;
    static List<Integer> nextMission;
    static List<TrainCard> deskNextClosedTrainCards;
    static List<TrainCard> deskOpenTrainCards;
    static List<TrainCard> deskDiscardedTrainCards;
    static List<Integer> deskDestinationCards;
    static List<TrainCard> playerTrainCards;
    static List<Integer> playerDestinationCards;
    static int playerColoredTrainCards;
    static GameModel gm = GameModel.getInstance();

    @BeforeAll
    static void init()
    {
        deskClosedTrainCards = new ArrayList<>(Collections.singletonList(new TrainCard(TrainCard.Type.BLACK)));
        nextMission = new ArrayList<>(Collections.singletonList(12));
        deskNextClosedTrainCards = new ArrayList<>(Collections.singletonList(new TrainCard(TrainCard.Type.RED)));
        deskOpenTrainCards = new ArrayList<>(Arrays.asList(new TrainCard(TrainCard.Type.BLUE), new TrainCard(TrainCard.Type.PINK), new TrainCard(TrainCard.Type.ORANGE), new TrainCard(TrainCard.Type.YELLOW), new TrainCard(TrainCard.Type.WHITE)));
        deskDiscardedTrainCards = new ArrayList<>(Collections.singletonList(new TrainCard(TrainCard.Type.GREEN)));
        deskDestinationCards = new ArrayList<>(Collections.singletonList(12));
        playerTrainCards = new ArrayList<>(Arrays.asList(new TrainCard(TrainCard.Type.PINK), new TrainCard(TrainCard.Type.BLUE)));
        playerDestinationCards = new ArrayList<>(Collections.singletonList(19));
        playerColoredTrainCards = 45;

        gm.setDeskClosedTrainCards(deskClosedTrainCards);
        gm.setDeskOpenTrainCards(deskOpenTrainCards);
        gm.setDeskDiscardedTrainCards(deskDiscardedTrainCards);
        gm.setDeskDestinationCards(deskDestinationCards);
        gm.setPlayerTrainCards(playerTrainCards);
        gm.setPlayerDestinationCards(playerDestinationCards);
    }

    @Test
    void testGetPlayerName() {
        String playerName = "Player1";
        gm.setPlayerName(playerName);
        assertEquals(playerName, gm.getPlayerName());
    }

    @Test
    void testGetClosedTrainCards()
    {
        gm.setDeskClosedTrainCards(deskClosedTrainCards);
        assertEquals(deskClosedTrainCards, gm.getDeskClosedTrainCards());
    }

    @Test
    void testGetOpenTrainCards()
    {
        gm.setDeskOpenTrainCards(deskOpenTrainCards);
        assertEquals(deskOpenTrainCards, gm.getDeskOpenTrainCards());
    }

    @Test
    void testGetDiscardedTrainCards()
    {
        gm.setDeskDiscardedTrainCards(deskDiscardedTrainCards);
        assertEquals(deskDiscardedTrainCards, gm.getDeskDiscardedTrainCards());
    }


    @Test
    void testGetDeskDestCards()
    {
        gm.setDeskDestinationCards(deskDestinationCards);
        assertEquals(deskDestinationCards, gm.getDeskDestinationCards());
    }


    @Test
    void testGetPlayerTrainCards()
    {
        gm.setPlayerTrainCards(playerTrainCards);
        assertEquals(playerTrainCards, gm.getPlayerTrainCards());
    }


    @Test
    void testGetPlayerDestCards()
    {
        gm.setPlayerDestinationCards(playerDestinationCards);
        assertEquals(playerDestinationCards, gm.getPlayerDestinationCards());
    }


    @Test
    void testGetPlayerColorTrainCards()
    {
        playerColoredTrainCards = 35;
        gm.setPlayerColoredTrainCards(playerColoredTrainCards);
        assertEquals(playerColoredTrainCards, gm.getPlayerColoredTrainCards());
    }

    @Test
    void testGetMap()
    {

    }

    @Test
    void testSetMap()
    {

    }

    @Test
    void testGetNextClosed()
    {
        gm.setDeskClosedTrainCards(deskNextClosedTrainCards);
        assertEquals(new TrainCard(TrainCard.Type.RED).getType(), gm.getNextClosedTrainCard().getType());
    }
    @Test
    void testGetNextMission()
    {
        gm.setDeskDestinationCards(nextMission);
        assertEquals(12, gm.getNextMissionCard());
    }

    @Test
    void testDrawOpen()
    {
        List<TrainCard> tmp = new ArrayList<>();
        tmp.add(new TrainCard(TrainCard.Type.PINK));
        tmp.add(new TrainCard(TrainCard.Type.BLUE));
        gm.setDeskOpenTrainCards(deskOpenTrainCards);
        gm.setDeskClosedTrainCards(tmp);
        gm.drawOpenTrainCard(0);
        assertEquals(new TrainCard(TrainCard.Type.PINK).getType(), gm.getPlayerTrainCards().get(0).getType());
    }

    @Test
    void testAddTrainCard()
    {
        List<TrainCard> tmp = new ArrayList<>();
        gm.addDrawnTrainCard(new TrainCard(TrainCard.Type.BLACK));
        tmp.add(new TrainCard(TrainCard.Type.PINK));
        tmp.add(new TrainCard(TrainCard.Type.BLUE));
        assertEquals(tmp.get(0).getType(), gm.getPlayerTrainCards().get(0).getType());
    }

    @Test
    void testAddDiscardedTrainCard()
    {
        gm.addDiscardedTrainCard(new TrainCard(TrainCard.Type.BLACK));
        assertEquals(new TrainCard(TrainCard.Type.GREEN).getType(), gm.getDeskDiscardedTrainCards().get(0).getType());
    }

    @Test
    void testAddDiscardedMissionCards()
    {
        List<Integer> tmp = new ArrayList<>();
        tmp.add(7);
        tmp.add(2);
        gm.addDiscardedMissionCards(tmp);
        List<Integer> tmp2 = new ArrayList<>();
        tmp2.add(7);
        tmp2.add(2);
        assertEquals(tmp2, gm.getDeskDestinationCards());
    }
}
