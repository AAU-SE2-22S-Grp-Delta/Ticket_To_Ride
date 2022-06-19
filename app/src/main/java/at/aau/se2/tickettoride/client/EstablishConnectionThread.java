package at.aau.se2.tickettoride.client;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.net.Socket;

public class EstablishConnectionThread extends Thread {
    private static final String TAG = "ClientConnection";
    private final Object lock = new Object();
    private Context context;
    private String serverAddress;

    @Override
    public void run() {
        try {
            Log.d(TAG, "EstablishConnectionThread: waiting for ipv4 ...");
            synchronized (lock) {
                lock.wait();
                if (!serverAddress.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
                    throw new IllegalArgumentException("illegal ipv4 format for " + serverAddress);
                } else {
                    Log.d(TAG, "EstablishConnectionThread: connecting ...");
                    Socket clientSocket = new Socket(serverAddress, 8001);
                    Log.d(TAG, "connected");
                    SendingThread sendingThread = new SendingThread(clientSocket);
                    sendingThread.start();

                    ReceivingThread receivingTread = new ReceivingThread(clientSocket);
                    receivingTread.setContext(context);
                    receivingTread.start();

                    ClientConnection client = ClientConnection.getInstance();
                    client.setSendingThread(sendingThread);
                    client.setReceivingThread(receivingTread);
                    Log.d(TAG, "launched communication threads");
                }
            }
        } catch (IOException e) {
            Log.d(TAG, e.toString());
        } catch (InterruptedException e) {
            Log.d(TAG, e.toString());
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
