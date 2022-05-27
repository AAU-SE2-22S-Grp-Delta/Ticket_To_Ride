package at.aau.se2.tickettoride.clientConnection;

import android.util.Log;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EstablishConnectionThread extends Thread {

    protected Socket clientSocket;
    private SendingThread sendingThread;
    private ReceiveingThread receivingTread;

    private Object lock = new Object();
    String ipv4;

    @Override
    public void run() {
        try {
            Log.d("ClientConnection", "EstablishConnectionThread: waiting for ipv4 ...");
            synchronized (lock) {
                lock.wait();
                if (!ipv4.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
                    throw new Exception("illegal ipv4 format for " + ipv4);
                } else {
                    Log.d("ClientConnection", "EstablishConnectionThread: connecting ...");
                    clientSocket = new Socket(ipv4, 8001);
                    Log.d("ClientConnection", "connected");
                    sendingThread = new SendingThread(clientSocket);
                    receivingTread = new ReceiveingThread(clientSocket);
                    sendingThread.start();
                    receivingTread.start();
                    ClientConnection cc = ClientConnection.getInstance();
                    cc.setSendingThread(sendingThread);
                    cc.setReceiveingThread(receivingTread);
                    Log.d("ClientConnection", "launched communication threads");
                }
            }
        } catch (Exception e) {
            Log.d("ClientConnection", e.toString());
        }
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}
