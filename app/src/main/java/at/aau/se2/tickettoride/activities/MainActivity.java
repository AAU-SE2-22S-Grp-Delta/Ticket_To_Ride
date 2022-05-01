package at.aau.se2.tickettoride.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import at.aau.se2.tickettoride.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.button.setOnClickListener(v -> {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        });

        binding.button1.setOnClickListener(v -> {
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        });

        binding.button2.setOnClickListener(v -> {
            Intent intent = new Intent(this, PlayerDestinationActivity.class);
            startActivity(intent);
        });

        binding.button3.setOnClickListener(v -> {
            Intent intent = new Intent(this, PlayerTrainActivity.class);
            startActivity(intent);
        });

        binding.button4.setOnClickListener(v -> {
            Intent intent = new Intent(this, DeskDestinationActivity.class);
            startActivity(intent);
        });

        binding.button5.setOnClickListener(v -> {
            Intent intent = new Intent(this, DeskTrainActivity.class);
            startActivity(intent);
        });

        binding.button6.setOnClickListener(v -> {
            Intent intent = new Intent(this, DeskOpenTrainActivity.class);
            startActivity(intent);
        });

        binding.button7.setOnClickListener(v -> {
            Intent intent = new Intent(this, DrawDestinationCardsActivity.class);
            startActivity(intent);
        });
    }
}