package at.aau.se2.tickettoride.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.dialogs.DestinationDialogFragment;
import at.aau.se2.tickettoride.fragments.DeskDestinationFragment;

public class DeskDestinationActivity extends AppCompatActivity implements DestinationDialogFragment.DestinationDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desk_destination);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, DeskDestinationFragment.newInstance())
                    .commitNow();
        }
    }

    //Get Choices
    @Override
    public void onDialogPositiveClick(ArrayList<String> selected) {
        //TODO Send Choices to Hand
        for (int i = 0; i < selected.size(); i++) {
            Log.i("CHOICES", selected.get(i));
        }
    }
}