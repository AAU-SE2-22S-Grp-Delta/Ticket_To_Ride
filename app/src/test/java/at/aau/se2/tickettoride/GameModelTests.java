package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.aau.se2.tickettoride.dataStructures.Map;
import at.aau.se2.tickettoride.dataStructures.TrainCard;
import at.aau.se2.tickettoride.models.GameModel;

public class GameModelTests
{

    static List<TrainCard> deskClosedTrainCards = new ArrayList<>();
    static List<Integer> nextMission = new ArrayList<>();
    static List<TrainCard> deskNextClosedTrainCards = new ArrayList<>();
    static List<TrainCard> deskOpenTrainCards = new ArrayList<>();
    static List<TrainCard> deskDiscardedTrainCards = new ArrayList<>();
    static List<Integer> deskDestinationCards = new ArrayList<>();
    static List<TrainCard> playerTrainCards = new ArrayList<>();
    static List<Integer> playerDestinationCards = new ArrayList<>();
    static int playerColoredTrainCards = 45;
    static Map map = new Map();

    GameModel gm = GameModel.getInstance();

    @BeforeAll
    public static void init()
    {
        deskClosedTrainCards.add(new TrainCard(TrainCard.Type.BLACK));
        deskOpenTrainCards = new ArrayList<>(Arrays.asList(new TrainCard(TrainCard.Type.BLUE), new TrainCard(TrainCard.Type.PINK), new TrainCard(TrainCard.Type.ORANGE), new TrainCard(TrainCard.Type.YELLOW), new TrainCard(TrainCard.Type.WHITE)));
        deskDiscardedTrainCards.add(new TrainCard(TrainCard.Type.GREEN));
        deskDestinationCards.add(12);
        playerTrainCards.add(new TrainCard(TrainCard.Type.PINK));
        playerTrainCards.add(new TrainCard(TrainCard.Type.BLUE));
        playerDestinationCards.add(19);
        deskNextClosedTrainCards.add(new TrainCard(TrainCard.Type.RED));
        nextMission.add(12);
    }

    @Test
    public void testGetClosedTrainCards()
    {
        gm.setDeskClosedTrainCards(deskClosedTrainCards);
        assertEquals(deskClosedTrainCards, gm.getDeskClosedTrainCards());
    }

    @Test
    public void testGetOpenTrainCards()
    {
        gm.setDeskOpenTrainCards(deskOpenTrainCards);
        assertEquals(deskOpenTrainCards, gm.getDeskOpenTrainCards());
    }

    @Test
    public void testGetDiscardedTrainCards()
    {
        gm.setDeskDiscardedTrainCards(deskDiscardedTrainCards);
        assertEquals(deskDiscardedTrainCards, gm.getDeskDiscardedTrainCards());
    }


    @Test
    public void testGetDeskDestCards()
    {
        gm.setDeskDestinationCards(deskDestinationCards);
        assertEquals(deskDestinationCards, gm.getDeskDestinationCards());
    }


    @Test
    public void testGetPlayerTrainCards()
    {
        gm.setPlayerTrainCards(playerTrainCards);
        assertEquals(playerTrainCards, gm.getPlayerTrainCards());
    }


    @Test
    public void testGetPlayerDestCards()
    {
        gm.setPlayerDestinationCards(playerDestinationCards);
        assertEquals(playerDestinationCards, gm.getPlayerDestinationCards());
    }


    @Test
    public void testGetPlayerColorTrainCards()
    {
        playerColoredTrainCards = 35;
        gm.setPlayerColoredTrainCards(playerColoredTrainCards);
        assertEquals(playerColoredTrainCards, gm.getPlayerColoredTrainCards());
    }

    @Test
    public void testGetMap()
    {

    }

    @Test
    public void testSetMap()
    {

    }

    @Test
    public void testGetNextClosed()
    {
        gm.setDeskClosedTrainCards(deskNextClosedTrainCards);
        assertEquals(new TrainCard(TrainCard.Type.RED).getType(), gm.getNextClosedTrainCard().getType());
    }
    @Test
    public void testGetNextMission()
    {
        gm.setDeskDestinationCards(nextMission);
        assertEquals(12, gm.getNextMissionCard());
    }

    @Test
    public void testDrawOpen()
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
    public void testAddTrainCard()
    {
        List<TrainCard> tmp = new ArrayList<>();
        gm.addDrawnTrainCard(new TrainCard(TrainCard.Type.BLACK));
        tmp.add(new TrainCard(TrainCard.Type.PINK));
        tmp.add(new TrainCard(TrainCard.Type.BLUE));
        assertEquals(tmp.get(0).getType(), gm.getPlayerTrainCards().get(0).getType());
    }

    @Test
    public void testAddDiscardedTrainCard()
    {
        gm.addDiscardedTrainCard(new TrainCard(TrainCard.Type.BLACK));
        assertEquals(new TrainCard(TrainCard.Type.BLACK).getType(), gm.getDeskDiscardedTrainCards().get(0).getType());
    }

    @Test
    public void testAddDiscardedMissionCards()
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
