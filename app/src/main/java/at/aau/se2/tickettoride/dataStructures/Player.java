package at.aau.se2.tickettoride.dataStructures;

import android.graphics.Color;

import java.util.HashMap;
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
    private int playerColor;
    private int numStones;
    private boolean isInGame;

    /**
     * Creates a player-object gives it a id and marks the name as used
     * @param name a unique name
     */
    public Player(String name, int id, int color) {
        this.id = id;
        setName(name);
        this.playerColor = color;
        this.isInGame = false;
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

    public void enterGame() {
        //TODO assign Game / GameModel or something
        if (isInGame) throw new IllegalStateException("Player is already playing");
        isInGame = true;
        this.numStones = 45;
    }

    public int getPlayerColor()
    {
        return playerColor;
    }
}
