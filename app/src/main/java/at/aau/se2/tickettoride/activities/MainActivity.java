package at.aau.se2.tickettoride.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import at.aau.se2.tickettoride.clientConnection.ClientConnection;
import at.aau.se2.tickettoride.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity {
    ClientConnection cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //establish connection;
        cc = ClientConnection.getInstance();

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Enter IP of server");
        TextInputEditText textInput = new TextInputEditText(builder.getContext());
        builder.setView(textInput);
        builder.setPositiveButton("set", (dialogInterface, i) -> {
           cc.setIPv4(textInput.getText().toString());
        });
        builder.create().show();

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