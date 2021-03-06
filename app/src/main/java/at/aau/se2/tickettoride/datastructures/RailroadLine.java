package at.aau.se2.tickettoride.datastructures;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.widget.ImageView;

import at.aau.se2.tickettoride.models.GameModel;

/**
 * RailroadLine-class represents a single connection between two Destination-Objects
 */
public class RailroadLine
{
    private Destination destination1;
    private Destination destination2;
    private String color;
    private int distance;
    private Player owner;





    /**
     * Creates a single RailroadLine to connect two distinct destinations
     *
     * @param destination1
     * @param destination2
     * @param color        to build the line train cards of this color will be needed
     * @param distance     to build the line that many train cards will be needed
     */
    public RailroadLine(Destination destination1, Destination destination2, String color, int distance)
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

    public String getColor()
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
        this.owner = owner;
    }

    @Override
    public int hashCode()
    {
        return super.hashCode();
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
        if(this.owner != null)
        {
            Log.d("ISBUILT", this.getOwner().getName());
            return true;
        }
        return false;
    }

    public void updateRoads(Canvas canvas, Paint paint, Bitmap bm, ImageView imageView)
    {
        if (this.isBuilt())
        {
            Log.d("debug", "drawing player toad");
            drawPlayerRoad(canvas, paint, bm, imageView, this.owner);
            return;
        }
        paint.setColor(Map.MapColor.getByString(this.color));
        paint.setStrokeWidth(12);

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
    }

    private void drawPlayerRoad(Canvas canvas, Paint paint, Bitmap bm, ImageView imageView, Player owner)
    {
        paint.setStrokeWidth(24);
        paint.setColor(getPlayerColorCodes(owner.getPlayerColor()));
        canvas.drawLine(this.destination1.getX(), this.destination1.getY(), this.destination2.getX(), this.destination2.getY(), paint);
        imageView.setImageBitmap(bm);
    }

    public void checkBuild(Canvas canvas, Paint paint, Bitmap bm, ImageView imageView)
    {
            GameModel.getInstance().buildRoad(destination1.getName(), destination2.getName(), this.color);

            updateRoads(canvas, paint, bm, imageView);
            //somehow delegate method
            //no green tracks
            //white tracks not visible
            //orange/red not different enough

    }

    private int getPlayerColorCodes(int color)
    {
        switch (color){
            //RED
            case 0:
                return Color.RED;
            //BLUE
            case 1:
                return Color.BLUE;
            //GREEN
            case 2:
                return Color.GREEN;
            //YELLOW
            case 3:
                return Color.YELLOW;
            //BLACK
            case 4:
                return Color.BLACK;
            default:
                return Color.GRAY;
        }
    }


}