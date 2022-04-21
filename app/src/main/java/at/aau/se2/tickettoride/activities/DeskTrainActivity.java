package at.aau.se2.tickettoride.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.dialogs.TrainDialogFragment;
import at.aau.se2.tickettoride.enums.Colors;
import at.aau.se2.tickettoride.fragments.DeckCardsFragment;

public class DeskTrainActivity extends AppCompatActivity implements TrainDialogFragment.TrainDialogListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desk_train);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, DeckCardsFragment.newInstance())
                    .commitNow();
        }
    }

    //TODO Delete Listener
    //Get Color
    @Override
    public void onDialogPositiveClick(Colors color) {
        //TODO Send Color to Hand
        Log.i("COLOR", color.name());
    }
}