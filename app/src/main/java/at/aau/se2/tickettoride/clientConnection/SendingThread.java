package at.aau.se2.tickettoride.clientConnection;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SendingThread extends Thread {
    protected Socket clientSocket;
    protected DataOutputStream send;

    private final Object lock;
    private String command = null;

    public SendingThread(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        send = new DataOutputStream(clientSocket.getOutputStream());
        lock = new Object();
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (lock) {
                    if (command == null) {
                        Log.d("ClientSend", "Pause sending thread");
                        lock.wait();
                        Log.d("ClientSend", "Continue sending thread");
                    }
                    if (command != null && sendCommand(command) == 0) command = null;
                }
            } catch (InterruptedException e) {
                Log.d("ClientSend", e.toString());
                Thread.currentThread().interrupt();
            }
        }
    }

    public void setCommand(String command) {
        this.command = command;
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public int sendCommand(String command) {
        try {
            Log.d("ClientSend", "sent: " + command);
            send.writeBytes(command + "\n");
        } catch (IOException e) {
            Log.d("ClientSend", e.toString());
            return -1;
        }
//        String in = response.readLine();
//        System.out.println(in);
        return 0;
    }
}
