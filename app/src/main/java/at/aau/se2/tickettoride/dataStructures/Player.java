package at.aau.se2.tickettoride.dataStructures;

/**
 * Player-Class represents a person who is playing the Game
 */
public class Player
{
    private String name;
    private int id = 0;
    private int playerColor;
    private int numStones;
    private int points;
    private boolean isInGame;

    public Player(String name, int color)
    {
        setName(name);
        this.playerColor = color;
        this.isInGame = false;
        this.numStones = 0;
        this.points = 0;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getStones() {return this.numStones;}

    public void removeStones(int numToRemove)
    {
        if (numStones >= numToRemove)
            this.numStones -= numToRemove;
        else throw new IllegalArgumentException("Not enough pieces");
    }


    //TODO change unique name check
    public void setName(String name)
    {
        if (name == null) throw new IllegalArgumentException("name is null");
        if (name.length() == 0) throw new IllegalArgumentException("name.length is 0");
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
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
