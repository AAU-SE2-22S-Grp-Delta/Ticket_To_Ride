package at.aau.se2.tickettoride.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.fragments.DeskOpenTrainFragment;

public class DeskOpenTrainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desk_open_train);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, DeskOpenTrainFragment.newInstance())
                    .commitNow();
        }
    }
}