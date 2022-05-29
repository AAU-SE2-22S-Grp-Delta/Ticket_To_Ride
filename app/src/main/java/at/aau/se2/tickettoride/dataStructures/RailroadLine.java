package at.aau.se2.tickettoride.dataStructures;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.ImageView;

/**
 * RailroadLine-class represents a single connection between two Destination-Objects
 */
public class RailroadLine
{
    //TODO exception-handling
    private int id;
    private Destination destination1;
    private Destination destination2;
    private int color = 0;
    private int distance = 0;
    private Player owner;

    /**
     * Creates a single RailroadLine to connect two distinct destinations
     *
     * @param destination1
     * @param destination2
     * @param color        to build the line train cards of this color will be needed
     * @param distance     to build the line that many train cards will be needed
     */
    public RailroadLine(Destination destination1, Destination destination2, int color, int distance)
    {
        configureConnection(destination1, destination2);
        this.color = color;
        this.distance = distance;
        this.owner = null;
    }

    public RailroadLine(Destination destination1, Destination destination2)
    {
        configureConnection(destination1, destination2);
    }


    public void configureConnection(Destination destination1, Destination destination2)
    {
        if (destination1 == null) throw new IllegalArgumentException("destination1 == null");
        if (destination2 == null) throw new IllegalArgumentException("destination2 == null");
        if (destination1.getName().equals(destination2.getName()))
            throw new IllegalArgumentException("destination1 == destination2");
        this.destination1 = destination1;
        this.destination2 = destination2;
    }

    public Destination getDestination1()
    {
        return destination1;
    }

    public Destination getDestination2()
    {
        return destination2;
    }

    public int getColor()
    {
        return color;
    }

    public int getDistance()
    {
        return distance;
    }

    public Player getOwner()
    {
        return owner;
    }

    /**
     * Sets the owner if this.owner == null
     *
     * @param owner
     * @throws IllegalStateException if owner != null
     */
    public void setOwner(Player owner) throws IllegalStateException
    {
        if (this.owner != null)
            throw new IllegalStateException("Line already owned by " + owner.getName());
        this.owner = owner;
    }



    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof RailroadLine)) return false;
        RailroadLine other = (RailroadLine) obj;
        return (this.destination1.getName().equals(other.destination1.getName()) &&
                this.destination2.getName().equals(other.destination2.getName())) ||
                (this.destination1.getName().equals(other.destination2.getName()) &&
                        this.destination2.getName().equals(other.destination1.getName()));
    }

    public boolean isBuilt()
    {
        return this.owner == null;
    }

    public void buildRoad(Canvas canvas, Paint paint, Bitmap bm, ImageView imageView)
    {
        //check if there is already a road built
        if (isBuilt())
            throw new IllegalStateException("Track already owned");
        //check if player has enough cards of given color to build

        //build road
        paint.setColor(this.color);

        float xDist = (Math.abs(destination2.getX() - destination1.getX()) / this.distance - 1);
        float yDist = (Math.abs(destination2.getY() - destination1.getY()) / this.distance - 1);
        float currX = 0;
        float currY = 0;
        for (int i = 1; i <= this.distance; i++)
        {

            if (destination1.getY() < destination2.getY())
            {
                if (i == 1)
                {
                    currX = this.destination1.getX();
                    currY = this.destination1.getY();
                }
                //point1 left of point2
                if (destination1.getX() < destination2.getX())
                {
                    canvas.drawLine(currX, currY, currX + xDist - 10, currY + yDist - 10, paint);
                    currX += xDist;
                }
                //point1 right of point2
                else
                {
                    canvas.drawLine(currX, currY, currX - xDist + 10, currY + yDist - 10, paint);
                    currX -= xDist;
                }
                currY += yDist;

            } else
            {
                if (i == 1)
                {
                    currX = this.destination2.getX();
                    currY = this.destination2.getY();
                }
                if (destination2.getX() < destination1.getX())
                {
                    canvas.drawLine(currX, currY, currX + xDist - 10, currY + yDist - 10, paint);
                    currX += xDist;
                }
                //point1 right of point2
                else
                {
                    canvas.drawLine(currX, currY, currX - xDist + 10, currY + yDist - 10, paint);
                    currX -= xDist;
                }
                currY += yDist;
            }
        }

        imageView.setImageBitmap(bm);
        imageView.invalidate();

        //set owner

        //remove cards and pieces

    }

    public void buildRoad(Canvas canvas, Paint paint, Bitmap bm, ImageView imageView, Player player)
    {
        paint.setStrokeWidth(24);
        //check if there is already a road built
        if (owner != null)
            throw new IllegalStateException("Track already owned");
        //check if player has enough cards of given color to build

        //build road
        paint.setColor(player.getPlayerColor());
        canvas.drawLine(this.destination1.getX(), this.destination1.getY(), this.destination2.getX(), this.destination2.getY(), paint);
        imageView.setImageBitmap(bm);

        //set owner
        this.owner = player;

        //remove cards and pieces

    }
}