package at.aau.se2.tickettoride.clientConnection;

import android.util.Log;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ReceiveingThread extends Thread {
    protected DataInputStream receive;

    protected ReceiveingThread(Socket clientSocket) throws IOException {
        receive = new DataInputStream(clientSocket.getInputStream());
    }

    @Override
    public void run() {
        while (true) {
            try {
                String line = receive.readLine();
                praseServerMsg(line);
            } catch (IOException e) {
                Log.d("ClientReceive", e.toString());
            }
        }
    }

    private void praseServerMsg(String line) {
        String[] messages = line.split(";");
        for (int i = 0; i < messages.length; i++) {
            Log.d("ClientReceive", messages[i]);
            if (messages[i].equals("")) ;
        }
    }
}
