package at.aau.se2.tickettoride.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import at.aau.se2.tickettoride.clientConnection.ClientConnection;
import at.aau.se2.tickettoride.dataStructures.Map;
import at.aau.se2.tickettoride.dataStructures.Player;
import at.aau.se2.tickettoride.dataStructures.TrainCard;

/**
 * GameModel-class represents an active game and stores the current game situation
 */
public class GameModel {
    private static GameModel instance = null;
    private final ClientConnection client;

    private String playerName;

    private List<TrainCard> deskClosedTrainCards = new ArrayList<>();
    private List<TrainCard> deskOpenTrainCards = new ArrayList<>();
    private List<TrainCard> deskDiscardedTrainCards = new ArrayList<>();
    private List<Integer> deskDestinationCards = new ArrayList<>();
    private List<TrainCard> playerTrainCards = new ArrayList<>();
    private List<Integer> playerDestinationCards = new ArrayList<>();
    private List<Integer> chooseMissionCards = new ArrayList<>();
    private int playerColoredTrainCards = 45;
    private Map map = new Map();
    public String[] playersString;
    private List<Player> players = new ArrayList<>();

    private GameModel() {
        this.client = ClientConnection.getInstance();
    }

    public static synchronized GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<TrainCard> getDeskClosedTrainCards() {
        return deskClosedTrainCards;
    }

    public void setDeskClosedTrainCards(List<TrainCard> deskClosedTrainCards) {
        this.deskClosedTrainCards = deskClosedTrainCards;
    }

    public List<TrainCard> getDeskOpenTrainCards() {
        return deskOpenTrainCards;
    }

    public void setDeskOpenTrainCards(List<TrainCard> deskOpenTrainCards) {
        this.deskOpenTrainCards = deskOpenTrainCards;
    }

    public List<TrainCard> getDeskDiscardedTrainCards() {
        return deskDiscardedTrainCards;
    }

    public void setDeskDiscardedTrainCards(List<TrainCard> deskDiscardedTrainCards) {
        this.deskDiscardedTrainCards = deskDiscardedTrainCards;
    }

    public List<Integer> getDeskDestinationCards() {
        return deskDestinationCards;
    }

    public void setDeskDestinationCards(List<Integer> deskDestinationCards) {
        this.deskDestinationCards = deskDestinationCards;
    }

    public List<TrainCard> getPlayerTrainCards() {
        return playerTrainCards;
    }

    public void setPlayerTrainCards(List<TrainCard> playerTrainCards) {
        this.playerTrainCards.clear();
        this.playerTrainCards.addAll(playerTrainCards);
    }

    public List<Integer> getPlayerDestinationCards() {
        return playerDestinationCards;
    }

    public void setPlayerDestinationCards(List<Integer> cards) {
        cards.stream()
                .filter(c -> !this.playerDestinationCards.contains(c))
                .forEach(c -> this.playerDestinationCards.add(c));

        String result = cards.stream().map(Object::toString).collect(Collectors.joining(":"));
        client.sendCommand("chooseMission:" + result);
    }

    public List<Integer> getChooseMissionCards() {
        return chooseMissionCards;
    }

    public void setChooseMissionCards(List<Integer> chooseMissionCards) {
        this.chooseMissionCards = chooseMissionCards;
    }

    public int getPlayerColoredTrainCards() {
        return playerColoredTrainCards;
    }

    public void setPlayerColoredTrainCards(int playerColoredTrainCards) {
        this.playerColoredTrainCards = playerColoredTrainCards;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    // Special methods
    public void drawOpenTrainCard(int pos) {
        client.sendCommand("cardOpen:" + pos);
    }

    public void addDrawnTrainCard(TrainCard trainCard) {
        playerTrainCards.add(trainCard);
    }

    public void addDiscardedTrainCard(TrainCard trainCard) {
        deskDiscardedTrainCards.add(trainCard);
    }

    public void addDiscardedMissionCards(List<Integer> missionCards) {
        deskDestinationCards.addAll(missionCards);
    }
}