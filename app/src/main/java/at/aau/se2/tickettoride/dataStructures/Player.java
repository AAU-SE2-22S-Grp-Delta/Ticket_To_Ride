package at.aau.se2.tickettoride.dataStructures;

import java.util.HashSet;
import java.util.Set;

/**
 * Player-Class represents a person who is playing the Game
 */
public class Player {
    //TODO exception-handling
    private static int playerId;
    private static Set<String> names = new HashSet<>();

    private int id;
    private String name;

    /**
     * Creates a player-object gives it a id and marks the name as used
     * @param name a unique name
     */
    public Player(String name) {
        this.id = playerId++;
        setName(name);
    }

    public static int getPlayerId() {
        return playerId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) throw new IllegalArgumentException("name is null");
        if (name.length() == 0) throw new IllegalArgumentException("name.length is 0");
        if (names.contains(name)) throw new IllegalArgumentException("name " + name + "is already used!");
        names.remove(this.name);
        names.add(name);
        this.name = name;
    }
}
