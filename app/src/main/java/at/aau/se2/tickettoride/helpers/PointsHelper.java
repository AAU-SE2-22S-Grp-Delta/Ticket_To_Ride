package at.aau.se2.tickettoride.helpers;

import java.util.ArrayList;

import at.aau.se2.tickettoride.dataStructures.RailroadLine;
import at.aau.se2.tickettoride.models.GameModel;

public class PointsHelper {
    GameModel gameModel = GameModel.getInstance();
    ArrayList<RailroadLine> railroadLines = new ArrayList<>(gameModel.getMap().getRailroadLines());
    RailroadLine railroad;

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

    public boolean checkDestination(int destination, String owner){
        /*for (int j = 0; j < railroadLines.size(); j++) {
            //TODO Change function
            if (railroadLines.get(j).getOwner().getName().equals(owner) && railroadLines.get(j).getId() == destination){
                railroad = railroadLines.get(j);
                return true;
            }
        }*/
        return false;
    }

    //TODO Fragen bezüglich Zwischenergebnis
    public int calculateSum(String player){
        ArrayList<Integer> playerDestinationCards = new ArrayList<>(gameModel.getPlayerDestinationCards());
        //TODO Zwischenergebnis zu Summe rechnen
        int sum = 0;

        //Punkte von Zielkarten dazuzählen und abziehen
        for (int i = 0; i < playerDestinationCards.size(); i++) {
            if (checkDestination(i, player)) sum+=railroad.getDistance();
            else sum-=railroad.getDistance();
        }

        //Zusatzpunkte für längste Strecke
        //TODO Checken ob Spieler die längste Strecke hat.
        // Falls Spieler es nicht hat, checken ob es überhaupt einen gibt mit längster Stecke.
        // Falls Gleichstand bekommen die jenigen Spieler 10 Punkte

        return sum;
    }
}
