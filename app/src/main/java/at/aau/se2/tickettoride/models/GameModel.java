package at.aau.se2.tickettoride.models;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import at.aau.se2.tickettoride.client.ClientConnection;
import at.aau.se2.tickettoride.dataStructures.Destination;
import at.aau.se2.tickettoride.dataStructures.DoubleRailroadLine;
import at.aau.se2.tickettoride.dataStructures.Map;
import at.aau.se2.tickettoride.dataStructures.Player;
import at.aau.se2.tickettoride.dataStructures.RailroadLine;
import at.aau.se2.tickettoride.dataStructures.TrainCard;

/**
 * GameModel-class represents an active game and stores the current game situation
 */

public class GameModel
{

    private static GameModel instance = null;
    private final ClientConnection client;

    private String playerName;
    private String activePlayer;

    private List<TrainCard> deskClosedTrainCards = new ArrayList<>();
    private List<TrainCard> deskOpenTrainCards = new ArrayList<>();
    private List<TrainCard> deskDiscardedTrainCards = new ArrayList<>();
    private List<Integer> deskDestinationCards = new ArrayList<>();
    private List<TrainCard> playerTrainCards = new ArrayList<>();
    private List<Integer> playerDestinationCards = new ArrayList<>();
    private List<Integer> chooseMissionCards = new ArrayList<>();
    private List<List<Integer>> allMissions = new ArrayList<>(4);
    private List<String> allRival = new ArrayList<>(4);
    private int playerColoredTrainCards = 45;
    private Map map = buildMap();
    public String[] playersString;
    private List<Player> players = new ArrayList<>();

    private GameModel()
    {
        this.client = ClientConnection.getInstance();
        Player test = new Player("test", Color.BLUE);
        getRailroadLineByName("Raleigh", "Atlanta").setOwner(test);
    }

    public static synchronized GameModel getInstance()
    {
        if (instance == null)
        {
            instance = new GameModel();
        }
        return instance;
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    public String getActivePlayer()
    {
        return activePlayer;
    }

    public void setActivePlayer(String activePlayer)
    {
        this.activePlayer = activePlayer;
    }

    public List<TrainCard> getDeskClosedTrainCards()
    {
        return deskClosedTrainCards;
    }

    public void setDeskClosedTrainCards(List<TrainCard> deskClosedTrainCards)
    {
        this.deskClosedTrainCards = deskClosedTrainCards;
    }

    public List<TrainCard> getDeskOpenTrainCards()
    {
        return deskOpenTrainCards;
    }

    public void setDeskOpenTrainCards(List<TrainCard> deskOpenTrainCards)
    {
        this.deskOpenTrainCards = deskOpenTrainCards;
    }

    public List<TrainCard> getDeskDiscardedTrainCards()
    {
        return deskDiscardedTrainCards;
    }

    public void setDeskDiscardedTrainCards(List<TrainCard> deskDiscardedTrainCards)
    {
        this.deskDiscardedTrainCards = deskDiscardedTrainCards;
    }

    public List<Integer> getDeskDestinationCards()
    {
        return deskDestinationCards;
    }

    public void setDeskDestinationCards(List<Integer> deskDestinationCards)
    {
        this.deskDestinationCards = deskDestinationCards;
    }

    public List<TrainCard> getPlayerTrainCards()
    {
        return playerTrainCards;
    }

    public void setPlayerTrainCards(List<TrainCard> playerTrainCards)
    {
        this.playerTrainCards.clear();
        this.playerTrainCards.addAll(playerTrainCards);
    }

    public List<Integer> getPlayerDestinationCards()
    {
        return playerDestinationCards;
    }

    public void setPlayerDestinationCards(List<Integer> cards)
    {
        cards.stream()
                .filter(c -> !this.playerDestinationCards.contains(c))
                .forEach(c -> this.playerDestinationCards.add(c));

        String result = cards.stream().map(Object::toString).collect(Collectors.joining(":"));
        client.sendCommand("chooseMission:" + result);
    }

    public List<Integer> getChooseMissionCards()
    {
        return chooseMissionCards;
    }

    public void setChooseMissionCards(List<Integer> chooseMissionCards)
    {
        this.chooseMissionCards = chooseMissionCards;
    }

    public int getPlayerColoredTrainCards()
    {
        return playerColoredTrainCards;
    }

    public void setPlayerColoredTrainCards(int playerColoredTrainCards)
    {
        this.playerColoredTrainCards = playerColoredTrainCards;
    }

    public Map getMap()
    {
        return map;
    }

    public void setPlayers(List<Player> players)
    {
        this.players = players;
    }

    public List<Player> getPlayers()
    {
        return players;
    }

    // Special methods
    public void drawOpenTrainCard(int pos)
    {
        client.sendCommand("cardOpen:" + pos);
    }

    public void addDrawnTrainCard(TrainCard trainCard)
    {
        playerTrainCards.add(trainCard);
    }

    public void addDiscardedTrainCard(TrainCard trainCard)
    {
        deskDiscardedTrainCards.add(trainCard);
    }

    public void addDiscardedMissionCards(List<Integer> missionCards)
    {
        deskDestinationCards.addAll(missionCards);
    }

    public void buildRoad(String dest1, String dest2, String color)
    {
        client.sendCommand("buildRailroad:"+dest1+","+dest2+","+color+".");
    }


    /**
     * @param serverMap this is a command of the format "getMap:[DestName],[DestName],[ownerName],[ownerName2]:
     */
    public void updateMap(String serverMap)
    {
        String[] full = serverMap.split(":");
        String[] roads = full[1].split("\\.");
        for (String road : roads)
        {
            String[] values = road.split(",");
            if (!values[2].equals("null"))
                getRailroadLineByName(values[0], values[1]).setOwner(getPlayerByName(values[2]));
        }
    }

    private Player getPlayerByName(String name)
    {
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
    public RailroadLine getRailroadLineByName(String destination1, String destination2)
    {
        for (RailroadLine r : map.getRailroadLines())
        {
            String d1 = r.getDestination1().getName();
            String d2 = r.getDestination2().getName();
            if (destination1.equals(d1) && destination2.equals(d2) || destination1.equals(d2) && destination2.equals(d1))
            {
                return r;
            }
        }
        return null;
    }


    private Map buildMap()
    {
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

        map.addRailroadLine(new RailroadLine(vancouver, calgary, "gray", 3));
        map.addRailroadLine(new RailroadLine(calgary, winnipeg, "white", 6));
        map.addRailroadLine(new RailroadLine(winnipeg, saultstmarie, "gray", 6));
        map.addRailroadLine(new RailroadLine(saultstmarie, montreal, "black", 5));
        map.addRailroadLine(new DoubleRailroadLine(montreal, boston, "gray", 2, "gray"));
        map.addRailroadLine(new RailroadLine(montreal, newyork, "blue", 3));
        map.addRailroadLine(new RailroadLine(montreal, toronto, "gray", 3));
        map.addRailroadLine(new DoubleRailroadLine(newyork, boston, "yellow", 2, "red"));
        map.addRailroadLine(new DoubleRailroadLine(newyork, pittsburgh, "yellow", 2, "yellow"));
        map.addRailroadLine(new RailroadLine(toronto, pittsburgh, "gray", 2));
        map.addRailroadLine(new RailroadLine(toronto, saultstmarie, "gray", 2));
        map.addRailroadLine(new RailroadLine(toronto, duluth, "pink", 6));
        map.addRailroadLine(new RailroadLine(saultstmarie, duluth, "gray", 3));
        map.addRailroadLine(new RailroadLine(duluth, winnipeg, "black", 4));
        map.addRailroadLine(new RailroadLine(winnipeg, helena, ("blue"), 4));
        map.addRailroadLine(new RailroadLine(helena, calgary, ("gray"), 4));
        map.addRailroadLine(new RailroadLine(helena, duluth, "orange", 6));
        map.addRailroadLine(new RailroadLine(helena, seattle, ("yellow"), 6));
        map.addRailroadLine(new DoubleRailroadLine(seattle, vancouver, "gray", 1, "gray"));
        map.addRailroadLine(new RailroadLine(seattle, calgary, "gray", 4));
        map.addRailroadLine(new DoubleRailroadLine(seattle, portland, "gray", 1, "gray"));
        map.addRailroadLine(new DoubleRailroadLine(portland, sanfrancisco, "yellow", 5,"pink"));
        map.addRailroadLine(new DoubleRailroadLine(sanfrancisco, saltlakecity, "orange", 5, "white"));
        map.addRailroadLine(new RailroadLine(saltlakecity, portland, "blue", 6));
        map.addRailroadLine(new RailroadLine(saltlakecity, helena, "pink", 3));
        map.addRailroadLine(new RailroadLine(helena, omaha, "red", 5));
        map.addRailroadLine(new DoubleRailroadLine(omaha, duluth, "gray", 2, "gray"));
        map.addRailroadLine(new RailroadLine(duluth, chicago, "red", 3));
        map.addRailroadLine(new RailroadLine(chicago, toronto, "white", 4));
        map.addRailroadLine(new DoubleRailroadLine(newyork, washington, "orange", 2,"black"));
        map.addRailroadLine(new RailroadLine(washington, pittsburgh, "gray", 2));
        map.addRailroadLine(new RailroadLine(pittsburgh, chicago, ("black"), 3));
        map.addRailroadLine(new RailroadLine(chicago, omaha, ("blue"), 4));
        map.addRailroadLine(new RailroadLine(omaha, denver, ("pink"), 4));
        map.addRailroadLine(new RailroadLine(denver, helena, ("yellow"), 4));
        map.addRailroadLine(new RailroadLine(denver, saltlakecity, ("yellow"), 3));
        map.addRailroadLine(new RailroadLine(saltlakecity, lasvegas, ("orange"), 3));
        map.addRailroadLine(new RailroadLine(lasvegas, losangeles, ("gray"), 2));
        map.addRailroadLine(new RailroadLine(losangeles, phoenix, ("gray"), 3));
        map.addRailroadLine(new RailroadLine(phoenix, elpaso, ("gray"), 3));
        map.addRailroadLine(new RailroadLine(elpaso, losangeles, ("black"), 6));
        map.addRailroadLine(new DoubleRailroadLine(losangeles, sanfrancisco, "yellow", 3, "pink"));
        map.addRailroadLine(new RailroadLine(santafe, denver, ("gray"), 2));
        map.addRailroadLine(new RailroadLine(santafe, oklahomacity, ("blue"), 3));
        map.addRailroadLine(new RailroadLine(santafe, elpaso, ("gray"), 2));
        map.addRailroadLine(new RailroadLine(santafe, phoenix, ("gray"), 3));
        map.addRailroadLine(new RailroadLine(elpaso, oklahomacity, ("yellow"), 5));
        map.addRailroadLine(new RailroadLine(elpaso, dallas, ("red"), 4));
        map.addRailroadLine(new RailroadLine(elpaso, houston, ("yellow"), 6));
        map.addRailroadLine(new RailroadLine(houston, neworleans, ("red"), 2));
        map.addRailroadLine(new DoubleRailroadLine(houston, dallas, ("gray"), 1, "gray"));
        map.addRailroadLine(new RailroadLine(dallas, littlerock, ("gray"), 2));
        map.addRailroadLine(new DoubleRailroadLine(dallas, oklahomacity, ("gray"), 2, ("gray")));
        map.addRailroadLine(new RailroadLine(oklahomacity, littlerock, ("gray"), 2));
        map.addRailroadLine(new DoubleRailroadLine(oklahomacity, kansascity, ("gray"), 2, ("gray")));
        map.addRailroadLine(new RailroadLine(oklahomacity, denver, ("red"), 4));
        map.addRailroadLine(new RailroadLine(littlerock, neworleans, ("yellow"), 3));
        map.addRailroadLine(new RailroadLine(littlerock, nashville, ("white"), 3));
        map.addRailroadLine(new RailroadLine(littlerock, saintlouis, ("gray"), 2));
        map.addRailroadLine(new RailroadLine(neworleans, miami, ("red"), 6));
        map.addRailroadLine(new RailroadLine(neworleans, atlanta, ("orange"), 4));
        map.addRailroadLine(new RailroadLine(atlanta, miami, "blue", 5));
        map.addRailroadLine(new RailroadLine(atlanta, charleston, ("gray"), 2));
        map.addRailroadLine(new DoubleRailroadLine(atlanta, raleigh, ("gray"), 2, ("gray")));
        map.addRailroadLine(new RailroadLine(atlanta, nashville, ("gray"), 1));
        map.addRailroadLine(new RailroadLine(miami, charleston, ("pink"), 4));
        map.addRailroadLine(new RailroadLine(charleston, raleigh, ("gray"), 2));
        map.addRailroadLine(new DoubleRailroadLine(raleigh, washington, ("gray"), 2, ("gray")));
        map.addRailroadLine(new RailroadLine(raleigh, pittsburgh, ("gray"), 2));
        map.addRailroadLine(new RailroadLine(raleigh, nashville, ("black"), 3));
        map.addRailroadLine(new RailroadLine(nashville, pittsburgh, ("yellow"), 4));
        map.addRailroadLine(new RailroadLine(nashville, saintlouis, ("gray"), 2));
        map.addRailroadLine(new RailroadLine(saintlouis, pittsburgh, ("yellow"), 5));
        map.addRailroadLine(new DoubleRailroadLine(saintlouis, chicago, ("yellow"), 2, ("white")));
        map.addRailroadLine(new DoubleRailroadLine(saintlouis, kansascity, ("blue"), 2, ("pink")));
        map.addRailroadLine(new DoubleRailroadLine(kansascity, omaha, ("gray"), 1, ("gray")));
        map.addRailroadLine(new DoubleRailroadLine(kansascity, denver, ("black"), 4, ("orange")));
        map.addRailroadLine(new RailroadLine(denver, phoenix, ("white"), 5));

        return map;
    }

    public boolean isPlaying()
    {
        return playerName.equals(activePlayer);
    }

    public void setAllMissions(List<List<Integer>> allMissions)
    {
        this.allMissions = allMissions;
    }

    public List<List<Integer>> getAllMissions()
    {
        return allMissions;
    }

    public void setAllRival(List<String> allRival)
    {
        this.allRival = allRival;
    }

    public List<String> getAllRival()
    {
        return allRival;
    }

    public void cheatMission()
    {
        client.sendCommand("cheatMission");
    }
}