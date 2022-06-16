package at.aau.se2.tickettoride.client;

import android.content.Context;
import android.util.Log;

public class ClientConnection {
    private static ClientConnection clientConnection;
    private final EstablishConnectionThread establishConnectionThread;
    private SendingThread sendingThread;
    private ReceivingThread receivingThread;

    public static ClientConnection getInstance() {
        if (clientConnection == null) {
            clientConnection = new ClientConnection();
        }
        return clientConnection;
    }

    private ClientConnection() {
        establishConnectionThread = new EstablishConnectionThread();
        establishConnectionThread.start();
    }

    public void setSendingThread(SendingThread sendingThread) {
        this.sendingThread = sendingThread;
    }

    public void setReceivingThread(ReceivingThread receivingThread) {
        this.receivingThread = receivingThread;
    }

    public void sendCommand(String command) {
        if (sendingThread == null) {
            Log.d("ClientConnection", "no sending thread");
            return;
        }
        sendingThread.setCommand(command);
    }

    public void setup(Context context, String serverAddress) {
        //TODO this is temporary
        if (establishConnectionThread == null) {
            return;
        }
        establishConnectionThread.setup(context, serverAddress);
    }
}
