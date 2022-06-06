package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import at.aau.se2.tickettoride.dataStructures.TrainCard;

class TrainCardTest {
    @Test
    void testToString() {
        TrainCard card = new TrainCard(TrainCard.Type.BLACK);
        assertEquals("black", card.getType().toString());
    }

    @Test
    void testGetPink() {
        TrainCard.Type cardType = TrainCard.Type.getByString("pink");
        assertEquals(TrainCard.Type.PINK, cardType);
    }

    @Test
    void testGetBlue() {
        TrainCard.Type cardType = TrainCard.Type.getByString("blue");
        assertEquals(TrainCard.Type.BLUE, cardType);
    }

    @Test
    void testGetGreen() {
        TrainCard.Type cardType = TrainCard.Type.getByString("green");
        assertEquals(TrainCard.Type.GREEN, cardType);
    }

    @Test
    void testGetYellow() {
        TrainCard.Type cardType = TrainCard.Type.getByString("yellow");
        assertEquals(TrainCard.Type.YELLOW, cardType);
    }

    @Test
    void testGetRed() {
        TrainCard.Type cardType = TrainCard.Type.getByString("red");
        assertEquals(TrainCard.Type.RED, cardType);
    }

    @Test
    void testGetWhite() {
        TrainCard.Type cardType = TrainCard.Type.getByString("white");
        assertEquals(TrainCard.Type.WHITE, cardType);
    }

    @Test
    void testGetOrange() {
        TrainCard.Type cardType = TrainCard.Type.getByString("orange");
        assertEquals(TrainCard.Type.ORANGE, cardType);
    }

    @Test
    void testGetBlack() {
        TrainCard.Type cardType = TrainCard.Type.getByString("black");
        assertEquals(TrainCard.Type.BLACK, cardType);
    }

    @Test
    void testGetLocomotive() {
        TrainCard.Type cardType = TrainCard.Type.getByString("locomotive");
        assertEquals(TrainCard.Type.LOCOMOTIVE, cardType);
    }

}
