package at.aau.se2.tickettoride.clientConnection;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceivingThread extends Thread {
    private final BufferedReader receive;

    public ReceivingThread(Socket clientSocket) throws IOException {
        this.receive = new BufferedReader(new InputStreamReader(new DataInputStream(clientSocket.getInputStream())));
    }

    @Override
    public void run() {
        while (true) {
            try {
                String line = receive.readLine();
                parseServerMsg(line);
            } catch (IOException e) {
                Log.d("ClientReceive", e.toString());
            }
        }
    }

    private void parseServerMsg(String line) {
        String[] messages = line.split(";");
        for (String message : messages) {
            Log.d("ClientReceive", message);
            if (message.isEmpty()) {
                continue;
            }
        }
    }
}
