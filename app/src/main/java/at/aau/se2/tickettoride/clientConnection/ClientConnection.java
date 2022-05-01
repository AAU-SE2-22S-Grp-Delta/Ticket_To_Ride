package at.aau.se2.tickettoride.clientConnection;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientConnection {
    private static ClientConnection clientConnection;
    private SendingThread sendingThread;
    private ReceiveingThread receiveingThread;
    private EstablishConnectionThread establishConnectionThread;

    public static ClientConnection getInstance() {
        if (clientConnection == null) clientConnection = new ClientConnection();
        return clientConnection;
    }

    private ClientConnection() {
        establishConnectionThread = new EstablishConnectionThread();
        establishConnectionThread.start();
    }

    public void setSendingThread(SendingThread sendingThread) {
        this.sendingThread = sendingThread;
    }

    public void setReceiveingThread(ReceiveingThread receiveingThread) {
        this.receiveingThread = receiveingThread;
    }

    public void sendCommand(String command) {
        if (sendingThread == null) {
            Log.d("ClientConnection", "no sending thread");
            return;
        }
        sendingThread.setCommand(command);
    }

    public void setIPv4(String iPv4) {
        //TODO this is temporary
        if (establishConnectionThread == null) return;
        establishConnectionThread.setIpv4(iPv4);
    }
}
