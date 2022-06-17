package at.aau.se2.tickettoride.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.List;

import at.aau.se2.tickettoride.clientConnection.ClientConnection;
import at.aau.se2.tickettoride.dataStructures.Player;
import at.aau.se2.tickettoride.databinding.ActivityGameBinding;
import at.aau.se2.tickettoride.dialogs.CheatingFunctionDialogFragment;
import at.aau.se2.tickettoride.dialogs.PointsDialog;
import at.aau.se2.tickettoride.fragments.DrawDestinationCardsFragment;
import at.aau.se2.tickettoride.fragments.PlayerDestinationFragment;
import at.aau.se2.tickettoride.helpers.ShakeDetection;
import at.aau.se2.tickettoride.models.GameModel;

public class GameActivity extends AppCompatActivity {
    private ActivityGameBinding binding;
    private final GameModel gameModel;
    private final ClientConnection client;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private ShakeDetection shakeDetection;
    private int shakeCount;
    private Vibrator vibrator;
    private VibrationEffect vibrationEffect;

    private Dialog playerDialog;
    private Dialog cheatDialog;

    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            shakeCount = shakeDetection.checkShake(sensorEvent, shakeCount);
            if (shakeCount == 5) {
                shakeCount = 0;
                gameModel.cheatMission();
                DialogFragment cheatingDialog = new CheatingFunctionDialogFragment();
                cheatingDialog.show(getSupportFragmentManager(), "cheating");
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
            //ignore
        }
    };

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            for (String key : bundle.keySet()) {
                switch (key) {
                    case "action_call":
                        if (bundle.getString(key).equals("1")) {
                            displayPlayerDialog();
                        }
                        break;
                    case "cheat":
                        if (bundle.getString(key).equals("1")) {
                            displayCheatDialog();

                            // requires Oreo (API 26) or higher
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                vibrationEffect = VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE);
                                vibrator.cancel();
                                vibrator.vibrate(vibrationEffect);
                            }

                        }
                        break;
                    default:
                        break;
                }
            }
        }
    };

    public GameActivity() {
        this.gameModel = GameModel.getInstance();
        this.client = ClientConnection.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
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
        sensorManager.registerListener(sensorListener, accelerometer, SensorManager.SENSOR_DELAY_UI);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        initComponents();

        displayChooseMission();

        displayPlayerDialog();
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        sensorManager.unregisterListener(sensorListener);

        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("server"));
        sensorManager.registerListener(sensorListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void initComponents() {
        binding.missionsButton.setOnClickListener(view -> {
            FragmentManager fm = getSupportFragmentManager();
            PlayerDestinationFragment destinationFragment = PlayerDestinationFragment.newInstance();
            destinationFragment.show(fm, "fragment_player_destination");
        });

        binding.pointsTextView.setOnClickListener(view -> {
            try {
                client.sendCommand("getPoints");
                Thread.sleep(500);
                DialogFragment pointsDialog = new PointsDialog();
                pointsDialog.show(getSupportFragmentManager(), "points");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void displayChooseMission() {
        DrawDestinationCardsFragment dialog = DrawDestinationCardsFragment.newInstance();
        dialog.show(getSupportFragmentManager(), "mission_cards");
    }

    private void displayPlayerDialog() {
        if (playerDialog != null && playerDialog.isShowing()) {
            playerDialog.dismiss();
        }

        if (!gameModel.isPlaying()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Aktiver Spieler")
                    .setMessage(gameModel.getActivePlayer());

            playerDialog = builder.create();
            playerDialog.show();
        }
    }

    private void displayCheatDialog() {
        if (cheatDialog != null && cheatDialog.isShowing()) {
            cheatDialog.dismiss();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cheat detected!")
                .setMessage("One player cheated");

        cheatDialog = builder.create();
        cheatDialog.show();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        builder.setTitle("Exit")
                .setMessage("Willst du wirklich das Spiel verlassen?")
                .setCancelable(false)
                .setPositiveButton("Bestätigen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        List<Player> players = gameModel.getPlayers();
                        players.removeIf(player -> player.getName().equals(gameModel.getPlayerName()));
                        client.sendCommand("exitGame");
                        finish();
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(0);
                    }
                })
                .setNegativeButton("Abbruch", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}