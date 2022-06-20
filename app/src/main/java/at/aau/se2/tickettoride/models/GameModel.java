package at.aau.se2.tickettoride.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import at.aau.se2.tickettoride.client.ClientConnection;
import at.aau.se2.tickettoride.datastructures.Destination;
import at.aau.se2.tickettoride.datastructures.DoubleRailroadLine;
import at.aau.se2.tickettoride.datastructures.Map;
import at.aau.se2.tickettoride.datastructures.Player;
import at.aau.se2.tickettoride.datastructures.RailroadLine;
import at.aau.se2.tickettoride.datastructures.TrainCard;

/**
 * GameModel-class represents an active game and stores the current game situation
 */

public class GameModel {
    private static final String BLACK = "black";
    private static final String BLUE = "blue";
    private static final String GRAY = "gray";
    private static final String ORANGE = "orange";
    private static final String PINK = "pink";
    private static final String RED = "red";
    private static final String WHITE = "white";
    private static final String YELLOW = "yellow";

    private static GameModel instance = null;
    private final ClientConnection client;

    private String playerName;
    private String activePlayer;

    private List<TrainCard> deskClosedTrainCards = new ArrayList<>();
    private List<TrainCard> deskOpenTrainCards = new ArrayList<>();
    private List<TrainCard> deskDiscardedTrainCards = new ArrayList<>();
    private List<Integer> deskDestinationCards = new ArrayList<>();
    private final List<TrainCard> playerTrainCards = new ArrayList<>();
    private final List<Integer> playerDestinationCards = new ArrayList<>();
    private List<Integer> chooseMissionCards = new ArrayList<>();
    private List<List<Integer>> allMissions = new ArrayList<>(4);
    private List<String> allRival = new ArrayList<>(4);
    private int playerColoredTrainCards = 45;
    private final Map map = buildMap();
    private String[] lobbyPlayers;
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
                .forEach(this.playerDestinationCards::add);

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

    public String[] getLobbyPlayers() {
        return lobbyPlayers;
    }

    public void setLobbyPlayers(String[] lobbyPlayers) {
        this.lobbyPlayers = lobbyPlayers;
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
        if (full.length < 2) return;
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
        Map m = new Map();

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
        Destination saultstmarie = new Destination("Sault St Marie");
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


        m.addDestination(raleigh);
        m.addDestination(charleston);
        m.addDestination(saintlouis);
        m.addDestination(lasvegas);
        m.addDestination(washington);
        m.addDestination(omaha);
        m.addDestination(atlanta);
        m.addDestination(boston);
        m.addDestination(calgary);
        m.addDestination(chicago);
        m.addDestination(dallas);
        m.addDestination(denver);
        m.addDestination(duluth);
        m.addDestination(elpaso);
        m.addDestination(helena);
        m.addDestination(houston);
        m.addDestination(kansascity);
        m.addDestination(littlerock);
        m.addDestination(losangeles);
        m.addDestination(miami);
        m.addDestination(montreal);
        m.addDestination(nashville);
        m.addDestination(neworleans);
        m.addDestination(newyork);
        m.addDestination(oklahomacity);
        m.addDestination(phoenix);
        m.addDestination(pittsburgh);
        m.addDestination(portland);
        m.addDestination(saltlakecity);
        m.addDestination(sanfrancisco);
        m.addDestination(santafe);
        m.addDestination(saultstmarie);
        m.addDestination(seattle);
        m.addDestination(toronto);
        m.addDestination(vancouver);
        m.addDestination(winnipeg);

        m.addRailroadLine(new RailroadLine(vancouver, calgary, GRAY, 3));
        m.addRailroadLine(new RailroadLine(calgary, winnipeg, WHITE, 6));
        m.addRailroadLine(new RailroadLine(winnipeg, saultstmarie, GRAY, 6));
        m.addRailroadLine(new RailroadLine(saultstmarie, montreal, BLACK, 5));
        m.addRailroadLine(new DoubleRailroadLine(montreal, boston, GRAY, 2, GRAY));
        m.addRailroadLine(new RailroadLine(montreal, newyork, BLUE, 3));
        m.addRailroadLine(new RailroadLine(montreal, toronto, GRAY, 3));
        m.addRailroadLine(new DoubleRailroadLine(newyork, boston, YELLOW, 2, RED));
        m.addRailroadLine(new DoubleRailroadLine(newyork, pittsburgh, YELLOW, 2, YELLOW));
        m.addRailroadLine(new RailroadLine(toronto, pittsburgh, GRAY, 2));
        m.addRailroadLine(new RailroadLine(toronto, saultstmarie, GRAY, 2));
        m.addRailroadLine(new RailroadLine(toronto, duluth, PINK, 6));
        m.addRailroadLine(new RailroadLine(saultstmarie, duluth, GRAY, 3));
        m.addRailroadLine(new RailroadLine(duluth, winnipeg, BLACK, 4));
        m.addRailroadLine(new RailroadLine(winnipeg, helena, BLUE, 4));
        m.addRailroadLine(new RailroadLine(helena, calgary, GRAY, 4));
        m.addRailroadLine(new RailroadLine(helena, duluth, ORANGE, 6));
        m.addRailroadLine(new RailroadLine(helena, seattle, YELLOW, 6));
        m.addRailroadLine(new DoubleRailroadLine(seattle, vancouver, GRAY, 1, GRAY));
        m.addRailroadLine(new RailroadLine(seattle, calgary, GRAY, 4));
        m.addRailroadLine(new DoubleRailroadLine(seattle, portland, GRAY, 1, GRAY));
        m.addRailroadLine(new DoubleRailroadLine(portland, sanfrancisco, YELLOW, 5, PINK));
        m.addRailroadLine(new DoubleRailroadLine(sanfrancisco, saltlakecity, ORANGE, 5, WHITE));
        m.addRailroadLine(new RailroadLine(saltlakecity, portland, BLUE, 6));
        m.addRailroadLine(new RailroadLine(saltlakecity, helena, PINK, 3));
        m.addRailroadLine(new RailroadLine(helena, omaha, RED, 5));
        m.addRailroadLine(new DoubleRailroadLine(omaha, duluth, GRAY, 2, GRAY));
        m.addRailroadLine(new RailroadLine(duluth, chicago, RED, 3));
        m.addRailroadLine(new RailroadLine(chicago, toronto, WHITE, 4));
        m.addRailroadLine(new DoubleRailroadLine(newyork, washington, ORANGE, 2, BLACK));
        m.addRailroadLine(new RailroadLine(washington, pittsburgh, GRAY, 2));
        m.addRailroadLine(new RailroadLine(pittsburgh, chicago, BLACK, 3));
        m.addRailroadLine(new RailroadLine(chicago, omaha, BLUE, 4));
        m.addRailroadLine(new RailroadLine(omaha, denver, PINK, 4));
        m.addRailroadLine(new RailroadLine(denver, helena, YELLOW, 4));
        m.addRailroadLine(new RailroadLine(denver, saltlakecity, YELLOW, 3));
        m.addRailroadLine(new RailroadLine(saltlakecity, lasvegas, ORANGE, 3));
        m.addRailroadLine(new RailroadLine(lasvegas, losangeles, GRAY, 2));
        m.addRailroadLine(new RailroadLine(losangeles, phoenix, GRAY, 3));
        m.addRailroadLine(new RailroadLine(phoenix, elpaso, GRAY, 3));
        m.addRailroadLine(new RailroadLine(elpaso, losangeles, BLACK, 6));
        m.addRailroadLine(new DoubleRailroadLine(losangeles, sanfrancisco, YELLOW, 3, PINK));
        m.addRailroadLine(new RailroadLine(santafe, denver, GRAY, 2));
        m.addRailroadLine(new RailroadLine(santafe, oklahomacity, BLUE, 3));
        m.addRailroadLine(new RailroadLine(santafe, elpaso, GRAY, 2));
        m.addRailroadLine(new RailroadLine(santafe, phoenix, GRAY, 3));
        m.addRailroadLine(new RailroadLine(elpaso, oklahomacity, YELLOW, 5));
        m.addRailroadLine(new RailroadLine(elpaso, dallas, RED, 4));
        m.addRailroadLine(new RailroadLine(elpaso, houston, YELLOW, 6));
        m.addRailroadLine(new RailroadLine(houston, neworleans, RED, 2));
        m.addRailroadLine(new DoubleRailroadLine(houston, dallas, GRAY, 1, GRAY));
        m.addRailroadLine(new RailroadLine(dallas, littlerock, GRAY, 2));
        m.addRailroadLine(new DoubleRailroadLine(dallas, oklahomacity, GRAY, 2, GRAY));
        m.addRailroadLine(new RailroadLine(oklahomacity, littlerock, GRAY, 2));
        m.addRailroadLine(new DoubleRailroadLine(oklahomacity, kansascity, GRAY, 2, GRAY));
        m.addRailroadLine(new RailroadLine(oklahomacity, denver, RED, 4));
        m.addRailroadLine(new RailroadLine(littlerock, neworleans, YELLOW, 3));
        m.addRailroadLine(new RailroadLine(littlerock, nashville, WHITE, 3));
        m.addRailroadLine(new RailroadLine(littlerock, saintlouis, GRAY, 2));
        m.addRailroadLine(new RailroadLine(neworleans, miami, RED, 6));
        m.addRailroadLine(new RailroadLine(neworleans, atlanta, ORANGE, 4));
        m.addRailroadLine(new RailroadLine(atlanta, miami, BLUE, 5));
        m.addRailroadLine(new RailroadLine(atlanta, charleston, GRAY, 2));
        m.addRailroadLine(new DoubleRailroadLine(atlanta, raleigh, GRAY, 2, GRAY));
        m.addRailroadLine(new RailroadLine(atlanta, nashville, GRAY, 1));
        m.addRailroadLine(new RailroadLine(miami, charleston, PINK, 4));
        m.addRailroadLine(new RailroadLine(charleston, raleigh, GRAY, 2));
        m.addRailroadLine(new DoubleRailroadLine(raleigh, washington, GRAY, 2, GRAY));
        m.addRailroadLine(new RailroadLine(raleigh, pittsburgh, GRAY, 2));
        m.addRailroadLine(new RailroadLine(raleigh, nashville, BLACK, 3));
        m.addRailroadLine(new RailroadLine(nashville, pittsburgh, YELLOW, 4));
        m.addRailroadLine(new RailroadLine(nashville, saintlouis, GRAY, 2));
        m.addRailroadLine(new RailroadLine(saintlouis, pittsburgh, YELLOW, 5));
        m.addRailroadLine(new DoubleRailroadLine(saintlouis, chicago, YELLOW, 2, WHITE));
        m.addRailroadLine(new DoubleRailroadLine(saintlouis, kansascity, BLUE, 2, PINK));
        m.addRailroadLine(new DoubleRailroadLine(kansascity, omaha, GRAY, 1, GRAY));
        m.addRailroadLine(new DoubleRailroadLine(kansascity, denver, BLACK, 4, ORANGE));
        m.addRailroadLine(new RailroadLine(denver, phoenix, WHITE, 5));

        return m;
    }

    public boolean isPlaying()
    {
        if (activePlayer == null) return false;
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

    public void cheatTrainCard() {client.sendCommand("cheatTrainCard");}
}