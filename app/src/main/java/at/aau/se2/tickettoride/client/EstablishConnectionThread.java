package at.aau.se2.tickettoride.client;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.net.Socket;

public class EstablishConnectionThread extends Thread {
    private final Object lock = new Object();
    private Context context;
    private String serverAddress;

    @Override
    public void run() {
        try {
            Log.d("ClientConnection", "EstablishConnectionThread: waiting for ipv4 ...");
            synchronized (lock) {
                lock.wait();
                if (!serverAddress.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
                    throw new IllegalArgumentException("illegal ipv4 format for " + serverAddress);
                } else {
                    Log.d("ClientConnection", "EstablishConnectionThread: connecting ...");
                    Socket clientSocket = new Socket(serverAddress, 8001);
                    Log.d("ClientConnection", "connected");
                    SendingThread sendingThread = new SendingThread(clientSocket);
                    sendingThread.start();

                    ReceivingThread receivingTread = new ReceivingThread(clientSocket);
                    receivingTread.setContext(context);
                    receivingTread.start();

                    ClientConnection client = ClientConnection.getInstance();
                    client.setSendingThread(sendingThread);
                    client.setReceivingThread(receivingTread);
                    Log.d("ClientConnection", "launched communication threads");
                }
            }
        } catch (IOException e) {
            Log.d("ClientConnection", e.toString());
        } catch (InterruptedException e) {
            Log.d("ClientConnection", e.toString());
            Thread.currentThread().interrupt();
        }
    }

    public void setup(Context context, String serverAddress) {
        this.context = context;
        this.serverAddress = serverAddress;
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}
