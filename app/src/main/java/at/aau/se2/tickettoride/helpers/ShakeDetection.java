package at.aau.se2.tickettoride.helpers;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class ShakeDetection implements SensorEventListener {

    private static final double SHAKE_THRESHOLD_GRAVITY = 2.7d;
    private static final int SHAKE_SLOP_TIME_MS = 500;
    private static final int SHAKE_COUNT_RESET_TIME_MS = 3000;

    private OnShakeListener shakeListener;
    private long shakeTimestamp;
    private int shakeCount;

    public void setOnShakeListener(OnShakeListener shakeListener) {
        this.shakeListener = shakeListener;
    }

    public interface OnShakeListener {
        public void onShake(int count);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(shakeListener != null)
        {
            double x = sensorEvent.values[0];
            double y = sensorEvent.values[1];
            double z = sensorEvent.values[2];

            // calculate the proportion to earth gravity (9.81)
            double gX = x / SensorManager.GRAVITY_EARTH;
            double gY = y / SensorManager.GRAVITY_EARTH;
            double gZ = z / SensorManager.GRAVITY_EARTH;

            // calculate gForce (if close by 1 -> no movement)
            double gForce = Math.sqrt(gX*gX + gY*gY + gZ*gZ);

            if(gForce > SHAKE_THRESHOLD_GRAVITY)
            {
                long now = System.currentTimeMillis();

                // ignore the shake event, when it is too close to each other (<500ms)
                if(shakeTimestamp + SHAKE_SLOP_TIME_MS > now)
                {
                    return;
                }

                //reset the shakeCount after 3 seconds of now shake
                if(shakeTimestamp + SHAKE_COUNT_RESET_TIME_MS < now)
                {
                    shakeCount = 0;
                }

                shakeTimestamp = now;
                shakeCount++;

                shakeListener.onShake(shakeCount);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //ignore
    }
}
