package at.aau.se2.tickettoride.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.dialogs.TrainDialogFragment;
import at.aau.se2.tickettoride.enums.Colors;
import at.aau.se2.tickettoride.fragments.DeskTrainFragment;

public class DeskTrainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desk_train);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, DeskTrainFragment.newInstance())
                    .commitNow();
        }
    }
}