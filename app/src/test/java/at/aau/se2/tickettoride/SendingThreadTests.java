package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.Socket;

import at.aau.se2.tickettoride.client.SendingThread;

public class SendingThreadTests {
    static Socket mockedSocket = Mockito.mock(Socket.class);

    static SendingThread sendingThread;

    @BeforeAll
    static void init() {
        try {
            sendingThread = new SendingThread(mockedSocket);
        } catch (IOException ignored) {
        }
    }

    @Test
    void testClass() {
        assertEquals(SendingThread.class, sendingThread.getClass());
    }

    @Test
    void testRun(){
        assertDoesNotThrow(() -> sendingThread.start());
    }

    @Test
    void testSendCommand(){
        assertDoesNotThrow(() -> sendingThread.setCommand("test"));
    }
}
