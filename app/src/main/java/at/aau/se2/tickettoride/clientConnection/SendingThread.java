package at.aau.se2.tickettoride.clientConnection;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SendingThread extends Thread {
    private final DataOutputStream send;
    private final Object lock = new Object();
    private String command = null;

    public SendingThread(Socket clientSocket) throws IOException {
        this.send = new DataOutputStream(clientSocket.getOutputStream());
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

                    if (command != null && sendCommand(command) == 0) {
                        command = null;
                    }
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
        return 0;
    }
}
