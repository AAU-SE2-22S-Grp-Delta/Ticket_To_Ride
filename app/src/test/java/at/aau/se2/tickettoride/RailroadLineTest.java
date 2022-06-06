package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.Button;
import android.widget.ImageView;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import at.aau.se2.tickettoride.dataStructures.Destination;
import at.aau.se2.tickettoride.dataStructures.Player;
import at.aau.se2.tickettoride.dataStructures.RailroadLine;

class RailroadLineTest {
    static Destination dest1;
    static Destination dest2;
    static Destination dest3;
    static Destination dest4;
    static RailroadLine r1;
    static RailroadLine r2;
    static RailroadLine r3;
    static RailroadLine r4;
    static RailroadLine r5;
    static Player player1;
    static Player player2;

    static Button btn1;
    static Button btn2;
    static Button btn3;

    static ImageView drawView;
    static Bitmap bm;
    static Canvas canvas;
    static Paint paint;

    @BeforeAll
    static void init() {
        btn1 = Mockito.mock(Button.class);
        Mockito.when(btn1.getX()).thenReturn(10f);
        Mockito.when(btn1.getY()).thenReturn(10f);
        Mockito.when(btn1.getWidth()).thenReturn(10);
        Mockito.when(btn1.getHeight()).thenReturn(10);

        btn2 = Mockito.mock(Button.class);
        Mockito.when(btn1.getX()).thenReturn(20f);
        Mockito.when(btn1.getY()).thenReturn(20f);
        Mockito.when(btn1.getWidth()).thenReturn(10);
        Mockito.when(btn1.getHeight()).thenReturn(10);

        btn3 = Mockito.mock(Button.class);
        Mockito.when(btn1.getX()).thenReturn(5f);
        Mockito.when(btn1.getY()).thenReturn(40f);
        Mockito.when(btn1.getWidth()).thenReturn(10);
        Mockito.when(btn1.getHeight()).thenReturn(10);

        drawView = Mockito.mock(ImageView.class);
        bm = Bitmap.createBitmap(3840, 2160, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bm);
        paint = new Paint();


        dest1 = new Destination("testRLdest1", btn1);
        dest2 = new Destination("testRLdest2", btn2);
        dest3 = new Destination("testRLdest3", btn3);
        dest4 = new Destination("testRLdest4", btn2);

        r1 = new RailroadLine(dest1, dest2, Color.BLUE, 3);
        r2 = new RailroadLine(dest3, dest4, Color.BLUE, 3);
        r4 = new RailroadLine(dest1, dest3, Color.RED, 2);
        r5 = new RailroadLine(dest1, dest3, Color.RED, 2);
        r3 = new RailroadLine(dest2, dest4);

        player1 = new Player("testplayer1", Color.GREEN);
        player2 = new Player("testplayer2", Color.RED);

        r2.setOwner(player1);
    }

    @Test
    void testGetOwnerNone()
    {
        assertNull(r1.getOwner());
    }

    @Test
    void testGetOwner()
    {
        assertEquals(player1, r2.getOwner());
    }

    @Test
    void testIsNotBuilt()
    {
        assertFalse(r1.isBuilt());
    }

    @Test
    void testIsBuilt()
    {
        assertTrue(r2.isBuilt());
    }

    @Test
    void testConnectionFirstNull() {
        assertThrows(IllegalArgumentException.class, () -> r2 = new RailroadLine(null, dest2, Color.BLUE, 3));
    }

    @Test
    void testConnectionSecondNull() {
        assertThrows(IllegalArgumentException.class, () -> r2 = new RailroadLine(dest1, null, Color.BLUE, 3));
    }

    @Test
    void testConnectionSameDest() {
        assertThrows(IllegalArgumentException.class, () -> r2 = new RailroadLine(dest1, dest1, Color.BLUE, 3));
    }

    @Test
    void testDestEquals() {
        assertEquals(r1, new RailroadLine(dest1, dest2));
        assertEquals(r1, new RailroadLine(dest2, dest1));
        assertNotEquals(r1, r2);
    }


    @Test
    void testGetDest() {
        assertEquals(dest1, r1.getDestination1());
        assertEquals(dest2, r1.getDestination2());
    }

    @Test
    void testGetColor(){
        assertEquals(Color.BLUE, r1.getColor());
    }

    @Test
    void testGetDist()
    {
        assertEquals(3, r1.getDistance());
    }

    @Test
    void testSetOwnerNew()
    {
        r4.setOwner(player2);
        assertEquals(player2, r4.getOwner());
    }

    @Test
    void testSetOwnerBuilt()
    {
        assertThrows(IllegalStateException.class, () -> r2.setOwner(player1));
    }

    @Test
    void testEquals() {
        assertEquals(r1, new RailroadLine(dest1, dest2, Color.BLUE, 3));
        assertEquals(r1, new RailroadLine(dest2, dest1, Color.BLUE, 3));
        assertNotEquals(r1, new RailroadLine(dest1, dest3, Color.BLUE, 3));
    }

    @Test
    void testBuildRoadOwned()
    {
        assertThrows(IllegalStateException.class, () -> r2.buildRoad(canvas, paint, bm, drawView));
        //dest1 10 10, dest2 20 20, dest3 5 40

        //Y1 < Y2
            //X1 < X2
        r1.buildRoad(canvas, paint, bm, drawView);
            //X1 > X2
        new RailroadLine(dest3, dest1, Color.GREEN, 2).buildRoad(canvas, paint, bm, drawView);
        //Y1 > Y2
            //X1 < X2
        new RailroadLine(dest3, dest2, Color.GREEN, 2).buildRoad(canvas, paint, bm, drawView);
            //X1 > X2
        new RailroadLine(dest2, dest1, Color.GREEN, 2).buildRoad(canvas, paint, bm, drawView);
    }

    @Test
    void testBuildRoad()
    {
        r5.buildRoad(canvas, paint, bm, drawView, player1);
    }
}
