package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import at.aau.se2.tickettoride.dataStructures.TrainCard;

public class TrainCardTest {
    @Test
    public void testToString() {
        TrainCard card = new TrainCard(TrainCard.Type.BLACK);
        assertEquals("black", card.getType().toString());
    }

    @Test
    public void testGetPink() {
        TrainCard.Type cardType = TrainCard.Type.getByString("pink");
        assertEquals(TrainCard.Type.PINK, cardType);
    }

    @Test
    public void testGetBlue() {
        TrainCard.Type cardType = TrainCard.Type.getByString("blue");
        assertEquals(TrainCard.Type.BLUE, cardType);
    }

    @Test
    public void testGetGreen() {
        TrainCard.Type cardType = TrainCard.Type.getByString("green");
        assertEquals(TrainCard.Type.GREEN, cardType);
    }

    @Test
    public void testGetYellow() {
        TrainCard.Type cardType = TrainCard.Type.getByString("yellow");
        assertEquals(TrainCard.Type.YELLOW, cardType);
    }

    @Test
    public void testGetRed() {
        TrainCard.Type cardType = TrainCard.Type.getByString("red");
        assertEquals(TrainCard.Type.RED, cardType);
    }

    @Test
    public void testGetWhite() {
        TrainCard.Type cardType = TrainCard.Type.getByString("white");
        assertEquals(TrainCard.Type.WHITE, cardType);
    }

    @Test
    public void testGetOrange() {
        TrainCard.Type cardType = TrainCard.Type.getByString("orange");
        assertEquals(TrainCard.Type.ORANGE, cardType);
    }

    @Test
    public void testGetBlack() {
        TrainCard.Type cardType = TrainCard.Type.getByString("black");
        assertEquals(TrainCard.Type.BLACK, cardType);
    }

    @Test
    public void testGetLocomotive() {
        TrainCard.Type cardType = TrainCard.Type.getByString("locomotive");
        assertEquals(TrainCard.Type.LOCOMOTIVE, cardType);
    }

}
