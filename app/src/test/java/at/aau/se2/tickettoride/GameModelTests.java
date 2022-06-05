package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.aau.se2.tickettoride.dataStructures.Map;
import at.aau.se2.tickettoride.models.GameModel;

public class GameModelTests
{

    static List<Integer> deskClosedTrainCards = new ArrayList<>();
    static List<Integer> nextMission = new ArrayList<>();
    static List<Integer> deskNextClosedTrainCards = new ArrayList<>();
    static Integer[] deskOpenTrainCards = new Integer[5];
    static List<Integer> deskDiscardedTrainCards = new ArrayList<>();
    static List<Integer> deskDestinationCards = new ArrayList<>();
    static List<Integer> playerTrainCards = new ArrayList<>();
    static List<Integer> playerDestinationCards = new ArrayList<>();
    static int playerColoredTrainCards = 45;
    static Map map = new Map();

    static GameModel gm = GameModel.getInstance();

    @BeforeAll
    public static void init()
    {
        gm.setPlayerTrainCards(playerTrainCards);
        gm.setPlayerDestinationCards(playerDestinationCards);
        gm.setDeskOpenTrainCards(deskOpenTrainCards);
        gm.setDeskDiscardedTrainCards(deskDiscardedTrainCards);
        gm.setDeskOpenTrainCards(deskOpenTrainCards);
        gm.setDeskClosedTrainCards(deskClosedTrainCards);
        gm.setPlayerColoredTrainCards(playerColoredTrainCards);
        deskClosedTrainCards.add(3);
        deskOpenTrainCards = new Integer[]{2,3,1,5,4};
        deskDiscardedTrainCards.add(7);
        deskDestinationCards.add(12);
        playerTrainCards.add(4);
        playerTrainCards.add(3);
        playerDestinationCards.add(19);
        deskNextClosedTrainCards.add(3);
        nextMission.add(12);
    }
    @AfterAll
    public static void exitTests(){
        deskClosedTrainCards = new ArrayList<>();
        nextMission = new ArrayList<>();
        deskNextClosedTrainCards = new ArrayList<>();
        deskOpenTrainCards = new Integer[5];
        deskDiscardedTrainCards = new ArrayList<>();
        deskDestinationCards = new ArrayList<>();
        playerTrainCards = new ArrayList<>();
        playerDestinationCards = new ArrayList<>();
        gm.setPlayerTrainCards(playerTrainCards);
        gm.setPlayerDestinationCards(playerDestinationCards);
        gm.setDeskOpenTrainCards(deskOpenTrainCards);
        gm.setDeskDiscardedTrainCards(deskDiscardedTrainCards);
        gm.setDeskOpenTrainCards(deskOpenTrainCards);
        gm.setDeskClosedTrainCards(deskClosedTrainCards);
        gm.setPlayerColoredTrainCards(playerColoredTrainCards);
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
    public void testGetNextClosed()
    {
        gm.setDeskClosedTrainCards(deskNextClosedTrainCards);
        assertEquals(3, gm.getNextClosedTrainCard());
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
        List<Integer> tmp = new ArrayList<>();
        tmp.add(2);
        tmp.add(2);
        gm.setDeskOpenTrainCards(deskOpenTrainCards);
        gm.setDeskClosedTrainCards(tmp);
        gm.drawOpenTrainCard(0);
        assertEquals(tmp, gm.getPlayerTrainCards());
    }

    @Test
    public void testAddTrainCard()
    {
        List<Integer> tmp = new ArrayList<>();
        gm.addDrawnTrainCard(2);
        tmp.add(2);
        tmp.add(2);
        assertEquals(tmp, gm.getPlayerTrainCards());
    }

    @Test
    public void testAddDiscardedTrainCard()
    {
        gm.setDeskDiscardedTrainCards(new ArrayList<>());
        List<Integer> tmp = new ArrayList<>();
        gm.addDiscardedTrainCard(2);
        tmp.add(2);
        assertEquals(tmp, gm.getDeskDiscardedTrainCards());
    }

    @Test
    public void testAddDiscardedMissionCards()
    {
        gm.setDeskDiscardedTrainCards(new ArrayList<>());
        List<Integer> tmp = new ArrayList<>();
        tmp.add(7);
        tmp.add(2);
        gm.addDiscardedMissionCards(tmp);
        List<Integer> tmp2 = new ArrayList<>();
        tmp2.add(7);
        tmp2.add(2);
        assertEquals(new ArrayList<>(Arrays.asList(7, 2)), gm.getDeskDestinationCards());
    }
}
