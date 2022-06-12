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

import at.aau.se2.tickettoride.dataStructures.Player;
import at.aau.se2.tickettoride.dataStructures.TrainCard;
import at.aau.se2.tickettoride.models.GameModel;

public class ReceivingThread extends Thread {
    private static final String DELIMITER_VALUE = "\\.";

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
        String response = split.length > 1 ? message.substring(command.length() + 1) : "";
        if (response.endsWith(DELIMITER_VALUE)) {
            response = response.substring(0, response.length() - 1);
        }

        List<TrainCard> trainCards;
        switch (command) {
            case "listGames":
                broadcastResponse("listGames", response);
                break;
            case "drawMission":
                List<Integer> cards = Arrays.stream(response.split(":")).map(Integer::parseInt).collect(Collectors.toList());
                gameModel.setChooseMissionCards(cards);
                broadcastResponse("drawMission", "1");
                syncGame();
                break;
            case "getHandCards":
                if (!response.isEmpty()) {
                    trainCards = Arrays.stream(response.split(DELIMITER_VALUE))
                            .map(c -> new TrainCard(TrainCard.Type.getByString(c)))
                            .collect(Collectors.toList());
                    gameModel.setPlayerTrainCards(trainCards);
                }
                broadcastResponse("refresh_player_train", "1");
                break;
            case "getOpenCards":
                if (!response.isEmpty()) {
                    trainCards = Arrays.stream(response.split(DELIMITER_VALUE))
                            .map(c -> new TrainCard(TrainCard.Type.getByString(c)))
                            .collect(Collectors.toList());
                    gameModel.setDeskOpenTrainCards(trainCards);
                }
                broadcastResponse("refresh_desk_open_train", "1");
                break;
            case "sync":
                syncGame();
                break;
            case "listPlayersGame":
                if(!response.isEmpty()){
                    gameModel.playersString = response.split(DELIMITER_VALUE);
                }
                broadcastResponse("refresh_players", "1");
                break;
            case "getColors":
                if(!response.isEmpty()){
                    String[] playersToDELIMITER = gameModel.playersString;
                    String[] colors = response.split(DELIMITER_VALUE);
                    for (int i = 0; i < playersToDELIMITER.length; i++) {
                        String player = playersToDELIMITER[i];
                        //Sonst leeres Wort!!!
                        String color = colors[i].split(player)[1];
                        int colorCodex = getColorCodex(color);
                        if(colorCodex != -1) gameModel.getPlayers().add(new Player(player, colorCodex));
                    }
                }
                broadcastResponse("colors", "1");
                break;
            case "getPoints":
                if(!response.isEmpty()){
                    List<Player> players = gameModel.getPlayers();
                    String[] points = response.split(DELIMITER_VALUE);
                    for (int i = 0; i < players.size(); i++) {
                        Player player = gameModel.getPlayers().get(i);
                        String DELIMITER = player.getName();
                        int point = Integer.parseInt(points[i].split(DELIMITER)[1]);
                        player.setPoints(point);
                    }
                }
                broadcastResponse("getPoints", "1");
                break;
            default:
                break;
        }
    }

    private int getColorCodex(String color){
        switch (color) {
            case "RED": return 0;
            case "BLUE": return 1;
            case "GREEN": return 2;
            case "YELLOW": return 3;
            case "BLACK": return 4;
            default: return -1;
        }
    }

    private void syncGame() {
        client.sendCommand("getHandCards");
        client.sendCommand("getOpenCards");
        client.sendCommand("listPlayersGame");
        client.sendCommand("getColors");
        client.sendCommand("getPoints");
    }

    private void broadcastResponse(String command, String response) {
        if (context != null) {
            Intent intent = new Intent("server");
            intent.putExtra(command, response);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }
}
