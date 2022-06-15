package at.aau.se2.tickettoride.models;

import java.util.ArrayList;

import at.aau.se2.tickettoride.dataStructures.Mission;

public class Missions {
    public static ArrayList<Mission> getMissions() {
        ArrayList<Mission> missions = new ArrayList<>();

        missions.add(new Mission(1, "Boston [34]", "Miami [11]", 12));
        missions.add(new Mission(2, "Calgary [26]", "Phoenix [6]", 13));
        missions.add(new Mission(3, "Calgary [26]", "Salt Lake City [24]", 7));
        missions.add(new Mission(4, "Chicago [36]", "New Orleans [10]", 7));
        missions.add(new Mission(5, "Chicago [36]", "Santa Fe [33]", 9));
        missions.add(new Mission(6, "Dallas [8]", "New York [30]", 11));
        missions.add(new Mission(7, "Denver [23]", "El Paso [7]", 4));
        missions.add(new Mission(8, "Denver [23]", "Pittsburgh [16]", 11));
        missions.add(new Mission(9, "Duluth [29]", "El Paso [7]", 10));
        missions.add(new Mission(10, "Duluth [29]", "Houston [9]", 8));
        missions.add(new Mission(11, "Helena [25]", "Los Angeles [5]", 8));
        missions.add(new Mission(12, "Kansas City [21]", "Houston [9]", 5));
        missions.add(new Mission(13, "Los Angeles [5]", "Chicago [36]", 16));
        missions.add(new Mission(14, "Los Angeles [5]", "Miami [11]", 20));
        missions.add(new Mission(15, "Los Angeles [5]", "New York [30]", 21));
        missions.add(new Mission(16, "Montreal [32]", "Atlanta [12]", 9));
        missions.add(new Mission(17, "Montreal [32]", "New Orleans [10]", 13));
        missions.add(new Mission(18, "New York [330]", "Atlanta [12]", 6));
        missions.add(new Mission(19, "Portland [3]", "Nashville [17]]", 17));
        missions.add(new Mission(20, "Portland [3]", "Phoenix [6]", 11));
        missions.add(new Mission(21, "San Francisco [4]", "Atlanta [12]", 17));
        missions.add(new Mission(22, "Sault St. Marie [28]", "Nashville [17]", 8));
        missions.add(new Mission(23, "Sault St. Marie [28]", "Oklahoma City [19]", 9));
        missions.add(new Mission(24, "Seattle [2]", "Los Angeles [5]", 9));
        missions.add(new Mission(25, "Seattle [2]", "New York [30]", 22));
        missions.add(new Mission(26, "Toronto [31]", "Miami [11]", 10));
        missions.add(new Mission(27, "Vancouver [1]", "Montreal [32]", 20));
        missions.add(new Mission(28, "Vancouver [1]", "Santa Fe [33]", 13));
        missions.add(new Mission(29, "Winnipeg [27]", "Houston [9]", 12));
        missions.add(new Mission(30, "Winnipeg [27]", "Little Rock [18]", 11));

        return missions;
    }

    public static Mission getMissionById(int id) {
        ArrayList<Mission> missions = getMissions();
        return missions.stream().filter(mission -> mission.getId() == id).findFirst().orElse(null);
    }
}
