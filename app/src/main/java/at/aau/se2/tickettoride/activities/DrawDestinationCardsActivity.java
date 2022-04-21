package at.aau.se2.tickettoride.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.fragments.DrawDestinationCardsFragment;

public class DrawDestinationCardsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_destination_cards);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, DrawDestinationCardsFragment.newInstance())
                    .commitNow();
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
}