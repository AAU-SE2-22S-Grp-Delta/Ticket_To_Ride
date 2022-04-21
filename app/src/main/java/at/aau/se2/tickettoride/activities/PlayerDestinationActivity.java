package at.aau.se2.tickettoride.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.fragments.PlayerMissionFragment;

public class PlayerDestinationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_destination);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, PlayerMissionFragment.newInstance())
                    .commitNow();
        }
    }
}