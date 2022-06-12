package at.aau.se2.tickettoride.activities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import at.aau.se2.tickettoride.clientConnection.ClientConnection;
import at.aau.se2.tickettoride.databinding.ActivityGameBinding;
import at.aau.se2.tickettoride.dialogs.CheatFaceDownDialogFragment;
import at.aau.se2.tickettoride.dialogs.CheatingFunctionDialogFragment;
import at.aau.se2.tickettoride.dialogs.PointsDialog;
import at.aau.se2.tickettoride.fragments.PlayerDestinationFragment;
import at.aau.se2.tickettoride.helpers.ShakeDetection;
import at.aau.se2.tickettoride.models.GameModel;

public class GameActivity extends AppCompatActivity {
    private GameModel gameModel;
    private ClientConnection clientConnection;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private ShakeDetection shakeDetection;
    private int shakeCount;
    DialogFragment dialogFragment = new CheatFaceDownDialogFragment();
    boolean condition = true;


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
        sensorManager.registerListener(sensorListener, accelerometer , SensorManager.SENSOR_DELAY_UI);

        initComponents(binding);

        startGame();
    }

    private SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float z = sensorEvent.values[2];

            int count = shakeDetection.checkShake(sensorEvent, shakeCount);
            shakeCount = count;
            String msg = String.valueOf(count);
            Log.d("Sensor Count", msg);

            if(shakeCount == 5)
            {
                shakeCount = 0;
                DialogFragment cheatingDialog = new CheatingFunctionDialogFragment();
                cheatingDialog.show(getSupportFragmentManager(),"cheating");
            }

            if ((z > -10 && z < -9) && condition) {
                condition = false;
                dialogFragment.show(getSupportFragmentManager(), "cheating2");
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
            //ignore
        }
    };

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
    protected void onPause() {
        sensorManager.unregisterListener(sensorListener);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorListener,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }
}