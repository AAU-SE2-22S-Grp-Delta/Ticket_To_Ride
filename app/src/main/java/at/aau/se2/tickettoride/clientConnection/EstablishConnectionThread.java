package at.aau.se2.tickettoride.clientConnection;

import android.util.Log;

import java.io.IOException;
import java.net.Socket;

public class EstablishConnectionThread extends Thread {
    protected Socket clientSocket;
    private SendingThread sendingThread;
    private ReceivingThread receivingTread;

    private final Object lock = new Object();
    private String ipv4;

    @Override
    public void run() {
        try {
            Log.d("ClientConnection", "EstablishConnectionThread: waiting for ipv4 ...");
            synchronized (lock) {
                lock.wait();
                if (!ipv4.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
                    throw new IllegalArgumentException("illegal ipv4 format for " + ipv4);
                } else {
                    Log.d("ClientConnection", "EstablishConnectionThread: connecting ...");
                    clientSocket = new Socket(ipv4, 8001);
                    Log.d("ClientConnection", "connected");
                    sendingThread = new SendingThread(clientSocket);
                    receivingTread = new ReceivingThread(clientSocket);
                    sendingThread.start();
                    receivingTread.start();
                    ClientConnection cc = ClientConnection.getInstance();
                    cc.setSendingThread(sendingThread);
                    cc.setReceivingThread(receivingTread);
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

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}
