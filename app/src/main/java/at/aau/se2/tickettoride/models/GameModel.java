package at.aau.se2.tickettoride.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import at.aau.se2.tickettoride.clientConnection.ClientConnection;
import at.aau.se2.tickettoride.dataStructures.Map;
import at.aau.se2.tickettoride.dataStructures.TrainCard;

/**
 * GameModel-class represents an active game and stores the current game situation
 */
public class GameModel {
    private static GameModel instance = null;
    private final ClientConnection client;

    private List<TrainCard> deskClosedTrainCards = new ArrayList<>();
    private List<TrainCard> deskOpenTrainCards = new ArrayList<>();
    private List<TrainCard> deskDiscardedTrainCards = new ArrayList<>();
    private List<Integer> deskDestinationCards = new ArrayList<>();
    private List<TrainCard> playerTrainCards = new ArrayList<>();
    private List<Integer> playerDestinationCards = new ArrayList<>();
    private List<Integer> chooseMissionCards = new ArrayList<>();
    private int playerColoredTrainCards = 45;
    private Map map = new Map();

    private GameModel() {
        this.client = ClientConnection.getInstance();
    }

    public static synchronized GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
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
        this.playerDestinationCards = cards;

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

    // Special methods
    public TrainCard getNextClosedTrainCard() {
        return deskClosedTrainCards.remove(0);
    }

    public Integer getNextMissionCard() {
        return deskDestinationCards.remove(0);
    }

    public void drawOpenTrainCard(int pos) {
        TrainCard current = deskOpenTrainCards.get(pos);
        playerTrainCards.add(current);
        deskOpenTrainCards.add(pos, getNextClosedTrainCard());
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