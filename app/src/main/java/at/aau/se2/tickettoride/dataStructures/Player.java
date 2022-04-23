package at.aau.se2.tickettoride.dataStructures;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Player-Class represents a person who is playing the Game
 */
public class Player
{
    private String name;
    private int id = 0;
    private int playerColor;
    private int numStones;
    private boolean isInGame;

    public Player(String name, int color)
    {
        this.id = id++;
        setName(name);
        this.playerColor = color;
        this.isInGame = false;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    //TODO change unique name check
    public void setName(String name)
    {
        if (name == null) throw new IllegalArgumentException("name is null");
        if (name.length() == 0) throw new IllegalArgumentException("name.length is 0");
        this.name = name;
    }

    public void enterGame()
    {
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
