package at.aau.se2.tickettoride.models;

import java.util.ArrayList;
import java.util.List;

import at.aau.se2.tickettoride.dataStructures.Map;

/**
 * GameModel-class represents an active game and stores the current game situation
 */
public class GameModel {
    private static volatile GameModel instance = null;

    private List<Integer> deskClosedTrainCards = new ArrayList<>();
    private Integer[] deskOpenTrainCards = new Integer[5];
    private List<Integer> deskDiscardedTrainCards = new ArrayList<>();
    private List<Integer> deskDestinationCards = new ArrayList<>();
    private List<Integer> playerTrainCards = new ArrayList<>();
    private List<Integer> playerDestinationCards = new ArrayList<>();
    private int playerColoredTrainCards = 45;
    private Map map = new Map();

    private GameModel() {
    }

    public static synchronized GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
    }

    public List<Integer> getDeskClosedTrainCards() {
        return deskClosedTrainCards;
    }

    public void setDeskClosedTrainCards(List<Integer> deskClosedTrainCards) {
        this.deskClosedTrainCards = deskClosedTrainCards;
    }

    public Integer[] getDeskOpenTrainCards() {
        return deskOpenTrainCards;
    }

    public void setDeskOpenTrainCards(Integer[] deskOpenTrainCards) {
        this.deskOpenTrainCards = deskOpenTrainCards;
    }

    public List<Integer> getDeskDiscardedTrainCards() {
        return deskDiscardedTrainCards;
    }

    public void setDeskDiscardedTrainCards(List<Integer> deskDiscardedTrainCards) {
        this.deskDiscardedTrainCards = deskDiscardedTrainCards;
    }

    public List<Integer> getDeskDestinationCards() {
        return deskDestinationCards;
    }

    public void setDeskDestinationCards(List<Integer> deskDestinationCards) {
        this.deskDestinationCards = deskDestinationCards;
    }

    public List<Integer> getPlayerTrainCards() {
        return playerTrainCards;
    }

    public void setPlayerTrainCards(List<Integer> playerTrainCards) {
        this.playerTrainCards = playerTrainCards;
    }

    public List<Integer> getPlayerDestinationCards() {
        return playerDestinationCards;
    }

    public void setPlayerDestinationCards(List<Integer> playerDestinationCards) {
        this.playerDestinationCards = playerDestinationCards;
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
    public Integer getNextClosedTrainCard() {
        return deskClosedTrainCards.remove(0);
    }

    public void addDiscardedTrainCards(Integer trainCard) {
        deskDiscardedTrainCards.add(trainCard);
    }
}