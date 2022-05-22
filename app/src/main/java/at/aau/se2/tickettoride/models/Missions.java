package at.aau.se2.tickettoride.models;

import java.util.ArrayList;

import at.aau.se2.tickettoride.dataStructures.Mission;

public class Missions {
    public static ArrayList<Mission> getMissions() {
        ArrayList<Mission> missions = new ArrayList<>();

        missions.add(new Mission(1, "Boston", "Miami", 12));
        missions.add(new Mission(2, "Calgary", "Phoenix", 13));
        missions.add(new Mission(3, "Calgary", "Salt Lake City", 7));
        missions.add(new Mission(4, "Chicago", "New Orleans", 7));
        missions.add(new Mission(5, "Chicago", "Santa Fe", 9));
        missions.add(new Mission(6, "Dallas", "New York", 11));
        missions.add(new Mission(7, "Denver", "El Paso", 4));
        missions.add(new Mission(8, "Denver", "Pittsburgh", 11));
        missions.add(new Mission(9, "Duluth", "El Paso", 10));
        missions.add(new Mission(10, "Duluth", "Houston", 8));
        missions.add(new Mission(11, "Helena", "Los Angeles", 8));
        missions.add(new Mission(12, "Kansas City", "Houston", 5));
        missions.add(new Mission(13, "Los Angeles", "Chicago", 16));
        missions.add(new Mission(14, "Los Angeles", "Miami", 20));
        missions.add(new Mission(15, "Los Angeles", "New York", 21));
        missions.add(new Mission(16, "Montreal", "Atlanta", 9));
        missions.add(new Mission(17, "Montreal", "New Orleans", 13));
        missions.add(new Mission(18, "New York", "Atlanta", 6));
        missions.add(new Mission(19, "Portland", "Nashville", 17));
        missions.add(new Mission(20, "Portland", "Phoenix", 11));
        missions.add(new Mission(21, "San Francisco", "Atlanta", 17));
        missions.add(new Mission(22, "Sault St. Marie", "Nashville", 8));
        missions.add(new Mission(23, "Sault St. Marie", "Oklahoma City", 9));
        missions.add(new Mission(24, "Seattle", "Los Angeles", 9));
        missions.add(new Mission(25, "Seattle", "New York", 22));
        missions.add(new Mission(26, "Toronto", "Miami", 10));
        missions.add(new Mission(27, "Vancouver", "Montreal", 20));
        missions.add(new Mission(28, "Vancouver", "Santa Fe", 13));
        missions.add(new Mission(29, "Winnipeg", "Houston", 12));
        missions.add(new Mission(30, "Winnipeg", "Little Rock", 11));

        return missions;
    }

    public static Mission getMissionById(int id) {
        ArrayList<Mission> missions = getMissions();
        return missions.stream().filter(mission -> mission.getId() == id).findFirst().orElse(null);
    }
}
