package at.aau.se2.tickettoride.helpers;

import at.aau.se2.tickettoride.models.GameModel;

public class PointsHelper {
    //TODO Fragen, ob wir während des Spiels die Punkte berechnen oder erst am Ende
    //Punkte für vollständige Strecken
    public int getPointsForRoutes(int lengthOfRoute){
        switch (lengthOfRoute){
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 7;
            case 5:
                return 10;
            case 6:
                return 15;
            default:
                throw new IllegalStateException("Unexpected value: " + lengthOfRoute);
        }
    }
}
