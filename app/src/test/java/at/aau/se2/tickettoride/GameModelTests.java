package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import android.graphics.Color;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import at.aau.se2.tickettoride.datastructures.Destination;
import at.aau.se2.tickettoride.datastructures.Player;
import at.aau.se2.tickettoride.datastructures.RailroadLine;
import at.aau.se2.tickettoride.datastructures.TrainCard;
import at.aau.se2.tickettoride.models.GameModel;

class GameModelTests {
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
    static void init() {
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
    void testGetClosedTrainCards() {
        gm.setDeskClosedTrainCards(deskClosedTrainCards);
        assertEquals(deskClosedTrainCards, gm.getDeskClosedTrainCards());
    }

    @Test
    void testGetOpenTrainCards() {
        gm.setDeskOpenTrainCards(deskOpenTrainCards);
        assertEquals(deskOpenTrainCards, gm.getDeskOpenTrainCards());
    }

    @Test
    void testGetDiscardedTrainCards() {
        gm.setDeskDiscardedTrainCards(deskDiscardedTrainCards);
        assertEquals(deskDiscardedTrainCards, gm.getDeskDiscardedTrainCards());
    }


    @Test
    void testGetDeskDestCards() {
        gm.setDeskDestinationCards(deskDestinationCards);
        assertEquals(deskDestinationCards, gm.getDeskDestinationCards());
    }


    @Test
    void testGetPlayerTrainCards() {
        gm.setPlayerTrainCards(playerTrainCards);
        assertEquals(playerTrainCards, gm.getPlayerTrainCards());
    }


    @Test
    void testGetPlayerDestCards() {
        gm.setPlayerDestinationCards(playerDestinationCards);
        assertEquals(playerDestinationCards, gm.getPlayerDestinationCards());
    }


    @Test
    void testGetPlayerColorTrainCards() {
        playerColoredTrainCards = 35;
        gm.setPlayerColoredTrainCards(playerColoredTrainCards);
        assertEquals(playerColoredTrainCards, gm.getPlayerColoredTrainCards());
    }

    @Test
    void testDrawOpen() {
        List<TrainCard> tmp = new ArrayList<>();
        tmp.add(new TrainCard(TrainCard.Type.PINK));
        tmp.add(new TrainCard(TrainCard.Type.BLUE));
        gm.setDeskOpenTrainCards(deskOpenTrainCards);
        gm.setDeskClosedTrainCards(tmp);
        gm.drawOpenTrainCard(0);
        assertEquals(new TrainCard(TrainCard.Type.PINK).getType(), gm.getPlayerTrainCards().get(0).getType());
    }

    @Test
    void testAddTrainCard() {
        List<TrainCard> tmp = new ArrayList<>();
        gm.addDrawnTrainCard(new TrainCard(TrainCard.Type.BLACK));
        tmp.add(new TrainCard(TrainCard.Type.PINK));
        tmp.add(new TrainCard(TrainCard.Type.BLUE));
        assertEquals(tmp.get(0).getType(), gm.getPlayerTrainCards().get(0).getType());
    }

    @Test
    void testAddDiscardedTrainCard() {
        gm.addDiscardedTrainCard(new TrainCard(TrainCard.Type.BLACK));
        assertEquals(new TrainCard(TrainCard.Type.GREEN).getType(), gm.getDeskDiscardedTrainCards().get(0).getType());
    }

    @Test
    void testAddDiscardedMissionCards() {
        List<Integer> tmp = new ArrayList<>();
        tmp.add(7);
        tmp.add(2);
        gm.addDiscardedMissionCards(tmp);
        List<Integer> tmp2 = new ArrayList<>();
        tmp2.add(12);
        tmp2.add(7);
        tmp2.add(2);
        assertEquals(tmp2, gm.getDeskDestinationCards());
    }

    @Test
    void testActivePlayer() {
        String player = "Player1";
        gm.setActivePlayer(player);
        assertEquals(player, gm.getActivePlayer());
    }

    @Test
    void testIsPlaying() {
        String player = "Player1";
        gm.setPlayerName(player);
        gm.setActivePlayer(player);
        assertTrue(gm.isPlaying());
    }

    @Test
    void testChooseMissionCards() {
        List<Integer> chooseMissionCards = new ArrayList<>(Arrays.asList(10, 20));
        gm.setChooseMissionCards(chooseMissionCards);
        assertEquals(chooseMissionCards, gm.getChooseMissionCards());
    }

    @Test
    void testPlayers() {
        List<Player> players = new ArrayList<>(Collections.singletonList(new Player("Player1", Color.RED)));
        gm.setPlayers(players);
        assertEquals(players, gm.getPlayers());
    }

    @Test
    void testLobbyPlayer() {
        String[] players = new String[]{"Player1", "Player2"};
        gm.setLobbyPlayers(players);
        assertEquals(players, gm.getLobbyPlayers());
    }

    @Test
    void testGetPlayerByName1() {
        String playerName = "Player1";
        Player player = new Player(playerName, Color.RED);
        List<Player> players = new ArrayList<>(Collections.singletonList(player));
        gm.setPlayers(players);

        try {
            Method method = GameModel.class.getDeclaredMethod("getPlayerByName", String.class);
            method.setAccessible(true);
            assertEquals(player, method.invoke(gm, playerName));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
        }
    }

    @Test
    void testGetPlayerByName2() {
        String playerName = "Player1";
        Player player = new Player(playerName, Color.RED);
        List<Player> players = new ArrayList<>(Collections.singletonList(player));
        gm.setPlayers(players);

        try {
            Method method = GameModel.class.getDeclaredMethod("getPlayerByName", String.class);
            method.setAccessible(true);
            assertNull(method.invoke(gm, "Player2"));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
        }
    }

    @Test
    void testGetRailroadLineByName1() {
        Destination vancouver = new Destination("Vancouver");
        Destination calgary = new Destination("Calgary");
        RailroadLine railroadLine = new RailroadLine(vancouver, calgary, "gray", 3);
        assertEquals(railroadLine, gm.getRailroadLineByName("Vancouver", "Calgary"));
    }

    @Test
    void testGetRailroadLineByName2() {
        assertNull(gm.getRailroadLineByName("Dest1", "Dest2"));
    }

    @Test
    void testAllMissions() {
        List<List<Integer>> allMissions = new ArrayList<>(Arrays.asList(new ArrayList<>(Arrays.asList(1, 2)), new ArrayList<>(Arrays.asList(3, 4))));
        gm.setAllMissions(allMissions);
        assertEquals(allMissions, gm.getAllMissions());
    }

    @Test
    void testAllRival() {
        List<String> rivals = new ArrayList<>(Arrays.asList("Rival1", "Rival2"));
        gm.setAllRival(rivals);
        assertEquals(rivals, gm.getAllRival());
    }

    @Test
    void testCheatMission() {
        assertDoesNotThrow(() -> gm.cheatMission());
    }

    @Test
    void testCheatTrainCard() {
        assertDoesNotThrow(() -> gm.cheatTrainCard());
    }
}
