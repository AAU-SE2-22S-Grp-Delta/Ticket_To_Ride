package at.aau.se2.tickettoride.fragments;

public class MissionCardFragment {

    private String destination1;
    private String destination2;
    private int points;
    private int image;

    public MissionCardFragment(String destination1, String destination2, int points, int image) {
        this.destination1 = destination1;
        this.destination2 = destination2;
        this.points = points;
        this.image = image;
    }

    public String getDestination1() {
        return destination1;
    }

    public void setDestination1(String destination1) {
        this.destination1 = destination1;
    }

    public String getDestination2() {
        return destination2;
    }

    public void setDestination2(String destination2) {
        this.destination2 = destination2;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
