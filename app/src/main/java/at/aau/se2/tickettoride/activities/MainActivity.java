package at.aau.se2.tickettoride.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import at.aau.se2.tickettoride.clientConnection.ClientConnection;
import at.aau.se2.tickettoride.databinding.ActivityMainBinding;
import at.aau.se2.tickettoride.dialogs.HelpDialogFragment;

public class MainActivity extends AppCompatActivity {
    private ClientConnection cc;

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
            Intent intent = new Intent(this, DrawDestinationCardsActivity.class);
            startActivity(intent);
        });

        binding.floatingActionButton.setOnClickListener(v -> {
            HelpDialogFragment helpDialogFragment = new HelpDialogFragment();
            helpDialogFragment.show(getSupportFragmentManager(), "helpDialog");
        });
    }
}