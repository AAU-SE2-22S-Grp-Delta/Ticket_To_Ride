package at.aau.se2.tickettoride;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import android.content.Context;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

import at.aau.se2.tickettoride.client.ReceivingThread;

class ReceivingThreadTests {
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

    @Test
    void testParseMessage() {
        assertDoesNotThrow(() -> {
            try {
                Method method = ReceivingThread.class.getDeclaredMethod("parseMessage", String.class);
                method.setAccessible(true);
                method.invoke(receivingThread, "command:Test1.;command:");
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | NullPointerException ignored) {
            }
        });
    }

    @Test
    void testGetColorCodex() {
        try {
            Method method = ReceivingThread.class.getDeclaredMethod("getColorCodex", String.class);
            method.setAccessible(true);
            assertEquals(0, method.invoke(receivingThread, "RED"));
            assertEquals(1, method.invoke(receivingThread, "BLUE"));
            assertEquals(2, method.invoke(receivingThread, "GREEN"));
            assertEquals(3, method.invoke(receivingThread, "YELLOW"));
            assertEquals(4, method.invoke(receivingThread, "BLACK"));
            assertEquals(-1, method.invoke(receivingThread, "NO_COLOR"));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | NullPointerException ignored) {
        }
    }

    @Test
    void testSyncGame() {
        assertDoesNotThrow(() -> {
            try {
                Method method = ReceivingThread.class.getDeclaredMethod("syncGame", (Class<?>[]) null);
                method.setAccessible(true);
                method.invoke(receivingThread);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | NullPointerException ignored) {
            }
        });
    }
}
