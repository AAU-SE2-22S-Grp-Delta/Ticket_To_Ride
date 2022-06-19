package at.aau.se2.tickettoride.fragments;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import at.aau.se2.tickettoride.activities.GameActivity;
import at.aau.se2.tickettoride.client.ClientConnection;
import at.aau.se2.tickettoride.databinding.FragmentLobbyBinding;
import at.aau.se2.tickettoride.models.GameModel;

public class LobbyFragment extends Fragment {
    private FragmentLobbyBinding binding;
    private final GameModel gameModel;
    private final ClientConnection client;
    private final List<String> list;
    private ArrayAdapter<String> adapter;
    private final Handler handler;
    private Runnable runnable;
    private boolean isGameOwner = false;
    private boolean isGameJoined = false;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            for (String key : bundle.keySet()) {
                switch (key) {
                    case "game_started":
                        if (bundle.getString(key).equals("1")) {
                            startGame();
                        }
                        break;
                    case "listGames":
                        String response = bundle.getString(key);
                        List<String> games = Arrays.stream(response.split("\\.")).collect(Collectors.toList());
                        updateList(games);

                        break;
                    case "refresh_players":
                        List<String> players = Arrays.asList(gameModel.playersString);
                        updateList(players);

                        if (isGameOwner && players.size() > 1) {
                            binding.start.setVisibility(View.VISIBLE);
                        }

                        break;
                    default:
                        break;
                }
            }
        }
    };

    public LobbyFragment() {
        this.gameModel = GameModel.getInstance();
        this.client = ClientConnection.getInstance();
        this.list = new ArrayList<>();
        this.handler = new Handler();
    }

    public static LobbyFragment newInstance() {
        return new LobbyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLobbyBinding.inflate(inflater, container, false);

        initComponents();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        handler.removeCallbacks(runnable);
        LocalBroadcastManager.getInstance(binding.getRoot().getContext()).unregisterReceiver(receiver);
        binding = null;
    }

    private void initComponents() {
        LocalBroadcastManager.getInstance(binding.getRoot().getContext()).registerReceiver(receiver, new IntentFilter("server"));

        this.adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list);
        binding.listView.setAdapter(adapter);

        binding.listView.setOnItemClickListener((parent, view, position, id) -> {
            if (isGameJoined) {
                return;
            }

            this.isGameJoined = true;
            binding.create.setVisibility(View.GONE);

            String game = list.get(position);
            client.sendCommand("joinGame:" + game);

            refreshPlayers(game);
        });

        binding.create.setOnClickListener(v -> setGameName());

        binding.start.setOnClickListener(v -> client.sendCommand("startGame"));

        setPlayerName();
    }

    private void setGameName() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss", Locale.getDefault());
        String date = simpleDateFormat.format(new Date());

        EditText input = new EditText(getActivity());
        input.setText(String.format("Game%s", date));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Spiel anlegen")
                .setMessage("Name des Spiels:")
                .setView(input)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    this.isGameOwner = true;
                    this.isGameJoined = true;
                    binding.create.setVisibility(View.GONE);

                    String game = input.getText().toString();

                    // Create game
                    client.sendCommand("createGame:" + game);

                    // List available games
                    refreshPlayers(game);

                    dialog.dismiss();
                });
        builder.create().show();
    }

    private void setPlayerName() {
        EditText input = new EditText(getActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Spielername")
                .setMessage("Name des Spielers:")
                .setView(input)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    String playerName = input.getText().toString();
                    gameModel.setPlayerName(playerName);

                    // Enter lobby
                    client.sendCommand("enterLobby:" + playerName);

                    // List available games
                    client.sendCommand("listGames");

                    dialog.dismiss();
                });

        builder.create().show();
    }

    private void refreshPlayers(String game) {
        handler.postDelayed(runnable = () -> {
            handler.postDelayed(runnable, 2000);
            client.sendCommand("listPlayersGame:" + game);
        }, 2000);
    }

    private void updateList(List<String> games) {
        list.clear();
        list.addAll(games);
        adapter.notifyDataSetChanged();
    }

    private void startGame() {
        handler.removeCallbacks(runnable);

        Intent intent = new Intent(getActivity(), GameActivity.class);
        startActivity(intent);
    }
}