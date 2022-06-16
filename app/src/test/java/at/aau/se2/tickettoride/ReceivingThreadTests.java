package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import android.content.Context;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.Socket;

import at.aau.se2.tickettoride.client.ReceivingThread;

public class ReceivingThreadTests {
    static Socket mockedSocket = Mockito.mock(Socket.class);
    static Context mockedContext = Mockito.mock(Context.class);

    static ReceivingThread receivingThread;

    @BeforeAll
    static void init() {
        try {
            receivingThread = new ReceivingThread(mockedSocket);
        } catch (IOException ignored) {
        }
    }

    @Test
    void testClass() {
        assertEquals(ReceivingThread.class, receivingThread.getClass());
    }

    @Test
    void testRun(){
        assertDoesNotThrow(() -> receivingThread.start());
    }

    @Test
    void testContext(){
        assertDoesNotThrow(() -> receivingThread.setContext(mockedContext));
    }
}
