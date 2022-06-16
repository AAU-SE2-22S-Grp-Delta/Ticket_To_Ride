package at.aau.se2.tickettoride.client;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class SendingThread extends Thread {
    private static final String TAG = "ClientSend";
    private final DataOutputStream send;
    private final Object lock = new Object();
    private final ArrayList<String> queue = new ArrayList<>();
    private String command = null;
    private boolean isSending = false;

    public SendingThread(Socket clientSocket) throws IOException {
        this.send = new DataOutputStream(clientSocket.getOutputStream());
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (lock) {
                    if (command == null) {
                        Log.d(TAG, "Pause sending thread");
                        lock.wait();
                        Log.d(TAG, "Continue sending thread");
                    }

                    if (command != null && sendCommand(command) == 0) {
                        if (queue.isEmpty()) {
                            command = null;
                            isSending = false;
                        } else {
                            command = queue.remove(0);
                        }
                    }
                }
            } catch (InterruptedException e) {
                Log.d(TAG, e.toString());
                Thread.currentThread().interrupt();
            }
        }
    }

    public void setCommand(String command) {
        if (isSending) {
            queue.add(command);
            return;
        }

        isSending = true;
        this.command = command;
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public int sendCommand(String command) {
        try {
            Log.d(TAG, "sent: " + command);
            send.writeBytes(command + "\n");
        } catch (IOException e) {
            Log.d(TAG, e.toString());
            return -1;
        }
        return 0;
    }
}
