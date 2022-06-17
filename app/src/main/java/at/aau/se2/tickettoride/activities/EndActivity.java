package at.aau.se2.tickettoride.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import at.aau.se2.tickettoride.adapter.ScoreAdapter;
import at.aau.se2.tickettoride.dataStructures.Player;
import at.aau.se2.tickettoride.databinding.ActivityEndBinding;
import at.aau.se2.tickettoride.models.GameModel;


public class EndActivity extends AppCompatActivity {
    private ActivityEndBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEndBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initComponents();
    }

    private void initComponents(){
        List<Player> players = GameModel.getInstance().getPlayers();
        players.sort(Comparator.comparing(Player::getPoints));
        Collections.reverse(players);

        ScoreAdapter adapter = new ScoreAdapter(this, players);
        ListView list = binding.listViewScore;
        list.setAdapter(adapter);

        binding.buttonEnd.setOnClickListener(view -> {
            finish();
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        });
    }
}
