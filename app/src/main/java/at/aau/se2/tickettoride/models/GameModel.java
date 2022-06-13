package at.aau.se2.tickettoride.models;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import at.aau.se2.tickettoride.clientConnection.ClientConnection;
import at.aau.se2.tickettoride.dataStructures.Destination;
import at.aau.se2.tickettoride.dataStructures.DoubleRailroadLine;
import at.aau.se2.tickettoride.dataStructures.Map;
import at.aau.se2.tickettoride.dataStructures.Player;
import at.aau.se2.tickettoride.dataStructures.RailroadLine;
import at.aau.se2.tickettoride.dataStructures.TrainCard;
import at.aau.se2.tickettoride.dialogs.TrainDialogFragment;

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
    private Map map = buildMap();
    public String[] playersString;
    private List<Player> players = new ArrayList<>();

    private GameModel() {
        this.client = ClientConnection.getInstance();
        Player test = new Player("test", Color.BLUE);
        getRailroadLineByName("Raleigh", "Atlanta").setOwner(test);
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


    /**
     *
     * @param serverMap this is a command of the format "getMap:[DestName],[DestName],[ownerName],[ownerName2]:
     */
    public void updateMap(String serverMap) {
        String sentences[] = serverMap.split(":");
        if (!sentences[1].equals("getMap")) return;
        for (int i = 1; i < sentences.length; i++) {
            String words[] = sentences[i].split(",");
            RailroadLine line = getRailroadLineByName(words[0], words[1]);
            Player owner = getPlayerByName(words[2]);
            if (owner == null) throw new IllegalArgumentException("Player of Name " + words[2] + " notFound");
            line.setOwner(owner);
        }
    }

    private Player getPlayerByName(String name) {
        for (Player p : this.players) if (p.getName().equals(name)) return p;
        return null;
    }


    /**
     * searches for a railroadLine which names are equal to dest1 and dest2
     *
     * @param destination1 the name of one destination of the line
     * @param destination2 the name of one destination of the line
     * @return the RailroadLine on success, null on fail
     */
    public RailroadLine getRailroadLineByName(String destination1, String destination2) {
        for (RailroadLine r : map.getRailroadLines()) {
            String d1 = r.getDestination1().getName();
            String d2 = r.getDestination2().getName();
            if (destination1.equals(d1) && destination2.equals(d2) || destination1.equals(d2) && destination2.equals(d1)) {
                return r;
            }
        }
        return null;
    }


    private Map buildMap() {
        Map map = new Map();

        Destination atlanta = new Destination("Atlanta");
        Destination boston = new Destination("Boston");
        Destination calgary = new Destination("Calgary");
        Destination chicago = new Destination("Chicago");
        Destination dallas = new Destination("Dallas");
        Destination denver = new Destination("Denver");
        Destination duluth = new Destination("Duluth");
        Destination elpaso = new Destination("El Paso");
        Destination helena = new Destination("Helena");
        Destination houston = new Destination("Houston");
        Destination kansascity = new Destination("Kansas City");
        Destination littlerock = new Destination("Little Rock");
        Destination losangeles = new Destination("Los Angeles");
        Destination miami = new Destination("Miami");
        Destination montreal = new Destination("Montreal");
        Destination nashville = new Destination("Nashville");
        Destination neworleans = new Destination("New Orleans");
        Destination newyork = new Destination("New York");
        Destination oklahomacity = new Destination("Oklahoma City");
        Destination phoenix = new Destination("Phoenix");
        Destination pittsburgh = new Destination("Pittsburgh");
        Destination portland = new Destination("Portland");
        Destination saltlakecity = new Destination("Salt Lake City");
        Destination sanfrancisco = new Destination("San Francisco");
        Destination santafe = new Destination("Santa Fe");
        Destination saultstmarie = new Destination("Sault St. Marie");
        Destination seattle = new Destination("Seattle");
        Destination toronto = new Destination("Toronto");
        Destination vancouver = new Destination("Vancouver");
        Destination winnipeg = new Destination("Winnipeg");
        Destination omaha = new Destination("Omaha");
        Destination washington = new Destination("Washington");
        Destination lasvegas = new Destination("Las Vegas");
        Destination charleston = new Destination("Charleston");
        Destination saintlouis = new Destination("Saint Louis");
        Destination raleigh = new Destination("Raleigh");


        map.addDestination(raleigh);
        map.addDestination(charleston);
        map.addDestination(saintlouis);
        map.addDestination(lasvegas);
        map.addDestination(washington);
        map.addDestination(omaha);
        map.addDestination(atlanta);
        map.addDestination(boston);
        map.addDestination(calgary);
        map.addDestination(chicago);
        map.addDestination(dallas);
        map.addDestination(denver);
        map.addDestination(duluth);
        map.addDestination(elpaso);
        map.addDestination(helena);
        map.addDestination(houston);
        map.addDestination(kansascity);
        map.addDestination(littlerock);
        map.addDestination(losangeles);
        map.addDestination(miami);
        map.addDestination(montreal);
        map.addDestination(nashville);
        map.addDestination(neworleans);
        map.addDestination(newyork);
        map.addDestination(oklahomacity);
        map.addDestination(phoenix);
        map.addDestination(pittsburgh);
        map.addDestination(portland);
        map.addDestination(saltlakecity);
        map.addDestination(sanfrancisco);
        map.addDestination(santafe);
        map.addDestination(saultstmarie);
        map.addDestination(seattle);
        map.addDestination(toronto);
        map.addDestination(vancouver);
        map.addDestination(winnipeg);

        map.addRailroadLine(new RailroadLine(vancouver, calgary, Map.MapColor.getByString("gray"), 3));
        map.addRailroadLine(new RailroadLine(calgary, winnipeg, Map.MapColor.getByString("white"), 6));
        map.addRailroadLine(new RailroadLine(winnipeg, saultstmarie, Map.MapColor.getByString("gray"), 6));
        map.addRailroadLine(new RailroadLine(saultstmarie, montreal, Map.MapColor.getByString("black"), 5));
        map.addRailroadLine(new DoubleRailroadLine(montreal, boston, Map.MapColor.getByString("gray"), 2, Map.MapColor.getByString("gray")));
        map.addRailroadLine(new RailroadLine(montreal, newyork, Map.MapColor.getByString("blue"), 3));
        map.addRailroadLine(new RailroadLine(montreal, toronto, Map.MapColor.getByString("gray"), 3));
        map.addRailroadLine(new DoubleRailroadLine(newyork, boston, Map.MapColor.getByString("yellow"), 2, Map.MapColor.getByString("red")));
        map.addRailroadLine(new DoubleRailroadLine(newyork, pittsburgh, Map.MapColor.getByString("yellow"), 2, Map.MapColor.getByString("yellow")));
        map.addRailroadLine(new RailroadLine(toronto, pittsburgh, Map.MapColor.getByString("gray"), 2));
        map.addRailroadLine(new RailroadLine(toronto, saultstmarie, Map.MapColor.getByString("gray"), 2));
        map.addRailroadLine(new RailroadLine(toronto, duluth, Map.MapColor.getByString("pink"), 6));
        map.addRailroadLine(new RailroadLine(saultstmarie, duluth, Map.MapColor.getByString("gray"), 3));
        map.addRailroadLine(new RailroadLine(duluth, winnipeg, Map.MapColor.getByString("black"), 4));
        map.addRailroadLine(new RailroadLine(winnipeg, helena, Map.MapColor.getByString("blue"), 4));
        map.addRailroadLine(new RailroadLine(helena, calgary, Map.MapColor.getByString("gray"), 4));
        map.addRailroadLine(new RailroadLine(helena, duluth, Map.MapColor.getByString("orange"), 6));
        map.addRailroadLine(new RailroadLine(helena, seattle, Map.MapColor.getByString("yellow"), 6));
        map.addRailroadLine(new DoubleRailroadLine(seattle, vancouver, Map.MapColor.getByString("gray"), 1, Map.MapColor.getByString("gray")));
        map.addRailroadLine(new RailroadLine(seattle, calgary, Map.MapColor.getByString("gray"), 4));
        map.addRailroadLine(new DoubleRailroadLine(seattle, portland, Map.MapColor.getByString("gray"), 1, Map.MapColor.getByString("gray")));
        map.addRailroadLine(new DoubleRailroadLine(portland, sanfrancisco, Map.MapColor.getByString("yellow"), 5, Map.MapColor.getByString("pink")));
        map.addRailroadLine(new DoubleRailroadLine(sanfrancisco, saltlakecity, Map.MapColor.getByString("orange"), 5, Map.MapColor.getByString("white")));
        map.addRailroadLine(new RailroadLine(saltlakecity, portland, Map.MapColor.getByString("blue"), 6));
        map.addRailroadLine(new RailroadLine(saltlakecity, helena, Map.MapColor.getByString("pink"), 3));
        map.addRailroadLine(new RailroadLine(helena, omaha, Map.MapColor.getByString("red"), 5));
        map.addRailroadLine(new DoubleRailroadLine(omaha, duluth, Map.MapColor.getByString("gray"), 2, Map.MapColor.getByString("gray")));
        map.addRailroadLine(new RailroadLine(duluth, chicago, Map.MapColor.getByString("red"), 3));
        map.addRailroadLine(new RailroadLine(chicago, toronto, Map.MapColor.getByString("white"), 4));
        map.addRailroadLine(new DoubleRailroadLine(newyork, washington, Map.MapColor.getByString("orange"), 2, Map.MapColor.getByString("black")));
        map.addRailroadLine(new RailroadLine(washington, pittsburgh, Map.MapColor.getByString("gray"), 2));
        map.addRailroadLine(new RailroadLine(pittsburgh, chicago, Map.MapColor.getByString("black"), 3));
        map.addRailroadLine(new RailroadLine(chicago, omaha, Map.MapColor.getByString("blue"), 4));
        map.addRailroadLine(new RailroadLine(omaha, denver, Map.MapColor.getByString("pink"), 4));
        map.addRailroadLine(new RailroadLine(denver, helena, Map.MapColor.getByString("yellow"), 4));
        map.addRailroadLine(new RailroadLine(denver, saltlakecity, Map.MapColor.getByString("yellow"), 3));
        map.addRailroadLine(new RailroadLine(saltlakecity, lasvegas, Map.MapColor.getByString("orange"), 3));
        map.addRailroadLine(new RailroadLine(lasvegas, losangeles, Map.MapColor.getByString("gray"), 2));
        map.addRailroadLine(new RailroadLine(losangeles, phoenix, Map.MapColor.getByString("gray"), 3));
        map.addRailroadLine(new RailroadLine(phoenix, elpaso, Map.MapColor.getByString("gray"), 3));
        map.addRailroadLine(new RailroadLine(elpaso, losangeles, Map.MapColor.getByString("black"), 6));
        map.addRailroadLine(new DoubleRailroadLine(losangeles, sanfrancisco, Map.MapColor.getByString("yellow"), 3, Map.MapColor.getByString("pink")));
        map.addRailroadLine(new RailroadLine(santafe, denver, Map.MapColor.getByString("gray"), 2));
        map.addRailroadLine(new RailroadLine(santafe, oklahomacity, Map.MapColor.getByString("blue"), 3));
        map.addRailroadLine(new RailroadLine(santafe, elpaso, Map.MapColor.getByString("gray"), 2));
        map.addRailroadLine(new RailroadLine(santafe, phoenix, Map.MapColor.getByString("gray"), 3));
        map.addRailroadLine(new RailroadLine(elpaso, oklahomacity, Map.MapColor.getByString("yellow"), 5));
        map.addRailroadLine(new RailroadLine(elpaso, dallas, Map.MapColor.getByString("red"), 4));
        map.addRailroadLine(new RailroadLine(elpaso, houston, Map.MapColor.getByString("yellow"), 6));
        map.addRailroadLine(new RailroadLine(houston, neworleans, Map.MapColor.getByString("red"), 2));
        map.addRailroadLine(new DoubleRailroadLine(houston, dallas, Map.MapColor.getByString("gray"), 1, Map.MapColor.getByString("gray")));
        map.addRailroadLine(new RailroadLine(dallas, littlerock, Map.MapColor.getByString("gray"), 2));
        map.addRailroadLine(new DoubleRailroadLine(dallas, oklahomacity, Map.MapColor.getByString("gray"), 2, Map.MapColor.getByString("gray")));
        map.addRailroadLine(new RailroadLine(oklahomacity, littlerock, Map.MapColor.getByString("gray"), 2));
        map.addRailroadLine(new DoubleRailroadLine(oklahomacity, kansascity, Map.MapColor.getByString("gray"), 2, Map.MapColor.getByString("gray")));
        map.addRailroadLine(new RailroadLine(oklahomacity, denver, Map.MapColor.getByString("red"), 4));
        map.addRailroadLine(new RailroadLine(littlerock, neworleans, Map.MapColor.getByString("yellow"), 3));
        map.addRailroadLine(new RailroadLine(littlerock, nashville, Map.MapColor.getByString("white"), 3));
        map.addRailroadLine(new RailroadLine(littlerock, saintlouis, Map.MapColor.getByString("gray"), 2));
        map.addRailroadLine(new RailroadLine(neworleans, miami, Map.MapColor.getByString("red"), 6));
        map.addRailroadLine(new RailroadLine(neworleans, atlanta, Map.MapColor.getByString("orange"), 4));
        map.addRailroadLine(new RailroadLine(atlanta, miami, Map.MapColor.getByString("blue"), 5));
        map.addRailroadLine(new RailroadLine(atlanta, charleston, Map.MapColor.getByString("gray"), 2));
        map.addRailroadLine(new DoubleRailroadLine(atlanta, raleigh, Map.MapColor.getByString("gray"), 2, Map.MapColor.getByString("gray")));
        map.addRailroadLine(new RailroadLine(atlanta, nashville, Map.MapColor.getByString("gray"), 1));
        map.addRailroadLine(new RailroadLine(miami, charleston, Map.MapColor.getByString("pink"), 4));
        map.addRailroadLine(new RailroadLine(charleston, raleigh, Map.MapColor.getByString("gray"), 2));
        map.addRailroadLine(new DoubleRailroadLine(raleigh, washington, Map.MapColor.getByString("gray"), 2, Map.MapColor.getByString("gray")));
        map.addRailroadLine(new RailroadLine(raleigh, pittsburgh, Map.MapColor.getByString("gray"), 2));
        map.addRailroadLine(new RailroadLine(raleigh, nashville, Map.MapColor.getByString("black"), 3));
        map.addRailroadLine(new RailroadLine(nashville, pittsburgh, Map.MapColor.getByString("yellow"), 4));
        map.addRailroadLine(new RailroadLine(nashville, saintlouis, Map.MapColor.getByString("gray"), 2));
        map.addRailroadLine(new RailroadLine(saintlouis, pittsburgh, Map.MapColor.getByString("yellow"), 5));
        map.addRailroadLine(new DoubleRailroadLine(saintlouis, chicago, Map.MapColor.getByString("yellow"), 2, Map.MapColor.getByString("white")));
        map.addRailroadLine(new DoubleRailroadLine(saintlouis, kansascity, Map.MapColor.getByString("blue"), 2, Map.MapColor.getByString("pink")));
        map.addRailroadLine(new DoubleRailroadLine(kansascity, omaha, Map.MapColor.getByString("gray"), 1, Map.MapColor.getByString("gray")));
        map.addRailroadLine(new DoubleRailroadLine(kansascity, denver, Map.MapColor.getByString("black"), 4, Map.MapColor.getByString("orange")));
        map.addRailroadLine(new RailroadLine(denver, phoenix, Map.MapColor.getByString("white"), 5));

        return map;
    }
}