package at.aau.se2.tickettoride.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.fragments.MapFragment;

public class MapActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MapFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}