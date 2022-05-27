package at.aau.se2.tickettoride.clientConnection;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SendingThread extends Thread {
    protected Socket clientSocket;
    protected DataOutputStream send;
    private Object lock;

    private String command = null;

    public SendingThread(Socket clientSocket) throws Exception {
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
                        Log.d("ClientConnection", "Pause sending thread");
                        lock.wait();
                        Log.d("ClientConnection", "Continue sending thread");
                    }
                    if (command != null && sendCommand(command) == 0) command = null;
                }

            } catch (Exception e) {
                Log.d("ClientSend", e.toString());
                e.printStackTrace();
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
            e.printStackTrace();
            return -1;
        }
//        String in = response.readLine();
//        System.out.println(in);
        return 0;
    }
}
