package at.aau.se2.tickettoride.activities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import at.aau.se2.tickettoride.clientConnection.ClientConnection;
import at.aau.se2.tickettoride.databinding.ActivityGameBinding;
import at.aau.se2.tickettoride.dialogs.CheatingFunctionDialogFragment;
import at.aau.se2.tickettoride.dialogs.PointsDialog;
import at.aau.se2.tickettoride.fragments.PlayerDestinationFragment;
import at.aau.se2.tickettoride.helpers.ShakeDetection;
import at.aau.se2.tickettoride.models.GameModel;

public class GameActivity extends AppCompatActivity implements SensorEventListener  {
    private GameModel gameModel;
    private ClientConnection clientConnection;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private ShakeDetection shakeDetection;
    private int shakeCount;
    private Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityGameBinding binding = ActivityGameBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Hide the status and action bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        //Cheating function (when the smartphone is shaken, then the dialog CheatingFunctionDialogFragment will be shown
        shakeDetection = new ShakeDetection();
        shakeCount = 0;
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        sensorManager.registerListener(this, accelerometer , SensorManager.SENSOR_DELAY_NORMAL);

        initComponents(binding);

        startGame();
    }

    private void initComponents(ActivityGameBinding binding) {
        binding.missionsButton.setOnClickListener(view -> {
            FragmentManager fm = getSupportFragmentManager();
            PlayerDestinationFragment destinationFragment = PlayerDestinationFragment.newInstance();
            destinationFragment.show(fm, "fragment_player_destination");
        });
        binding.pointsTextView.setOnClickListener(view -> {
            DialogFragment pointsDialog = new PointsDialog();
            pointsDialog.show(getSupportFragmentManager(), "points");
        });
    }

    private void startGame() {
        gameModel = GameModel.getInstance();
        clientConnection = ClientConnection.getInstance();
        clientConnection.sendCommand("enterLobby:testPlayer;createGame:testGame:testPlayer");

        // After starting a new game send refresh to all attached fragments
        Bundle result = new Bundle();
        getSupportFragmentManager().setFragmentResult("refresh", result);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        shakeDetection.checkShake(sensorEvent, shakeCount);

        if(shakeCount > 5)
        {
            DialogFragment cheatingDialog = new CheatingFunctionDialogFragment();
            cheatingDialog.show(getSupportFragmentManager(),"cheating");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //ignore
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }
}