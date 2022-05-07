package at.aau.se2.tickettoride.helpers;

import java.util.ArrayList;

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

    //TODO Fragen, ob es irgendwo Punkte gibt Weg finden bezüglich Punkte von Ziele
    public int getPointsForDestinations(int Destination){
        return 0;
    }

    //TODO Fragen bezüglich Zwischenergebnis
    public int calculateSum(){
        GameModel gameModel = GameModel.getInstance();
        ArrayList<Integer> playerPlayedDestinations = new ArrayList<>(gameModel.getPlayerPlayedDestinations());
        ArrayList<Integer> playerDestinationCards = new ArrayList<>(gameModel.getPlayerDestinationCards());
        //TODO Zwischenergebnis zu Summe rechnen
        int sum = 0;

        //Punkte von Zielkarten dazuzählen und abziehen
        for (int i = 0; i < playerDestinationCards.size(); i++) {
            if (playerDestinationCards.contains(playerDestinationCards.get(i))) sum+=getPointsForDestinations(playerDestinationCards.get(i));
            else sum-=getPointsForDestinations(playerDestinationCards.get(i));
        }

        //Zusatzpunkte für längste Strecke
        //TODO Checken ob Spieler die längste Strecke hat.
        // Falls Spieler nicht hat, checken ob es überhaupt einen gibt mit längster Stecke.
        // Falls Gleichstand bekommen die jenigen Spieler 10 Punkte

        return sum;
    }
}
