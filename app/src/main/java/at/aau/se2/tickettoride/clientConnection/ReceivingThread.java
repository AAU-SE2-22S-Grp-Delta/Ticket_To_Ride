package at.aau.se2.tickettoride.clientConnection;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import at.aau.se2.tickettoride.dataStructures.TrainCard;
import at.aau.se2.tickettoride.models.GameModel;

public class ReceivingThread extends Thread {
    private final BufferedReader receive;
    private Context context;
    private final GameModel gameModel = GameModel.getInstance();
    private final ClientConnection client = ClientConnection.getInstance();

    public ReceivingThread(Socket clientSocket) throws IOException {
        this.receive = new BufferedReader(new InputStreamReader(new DataInputStream(clientSocket.getInputStream())));
    }

    @Override
    public void run() {
        while (true) {
            try {
                String line = receive.readLine();
                parseMessage(line);
            } catch (IOException e) {
                Log.d("ClientReceive", e.toString());
            }
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private void parseMessage(String line) {
        String[] messages = line.split(";");
        for (String message : messages) {
            Log.d("ClientReceive", message);
            if (message.isEmpty()) {
                continue;
            }
            handleResponse(message);
        }
    }

    private void handleResponse(String message) {
        String[] split = message.split(":");
        String command = split[0];
        String response = message.substring(command.length() + 1);

        switch (command) {
            case "listGames":
                broadcastResponse("listGames", response);
                break;
            case "drawMission":
                List<Integer> cards = Arrays.stream(response.split(":")).map(Integer::parseInt).collect(Collectors.toList());
                gameModel.setChooseMissionCards(cards);
                broadcastResponse("drawMission", "1");
                client.sendCommand("getOpenCards");
                break;
            case "getOpenCards":
                List<TrainCard> trainCards = Arrays.stream(response.split(":"))
                        .map(c -> new TrainCard(TrainCard.Type.getByString(c)))
                        .collect(Collectors.toList());
                gameModel.setDeskOpenTrainCards(trainCards);
                break;
            default:
                break;
        }
    }

    private void broadcastResponse(String command, String response) {
        if (context != null) {
            Intent intent = new Intent("server");
            intent.putExtra(command, response);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }
}
