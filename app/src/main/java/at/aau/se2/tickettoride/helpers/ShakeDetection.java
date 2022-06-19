package at.aau.se2.tickettoride.helpers;

import android.hardware.SensorEvent;
import android.hardware.SensorManager;

public class ShakeDetection {

    private static final float SHAKE_THRESHOLD_GRAVITY = 2.5F;
    private static final int SHAKE_SLOP_TIME_MS = 500;

    public int checkShake(SensorEvent sensorEvent, int shakeCount) {
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];
        long shakeTimestamp = sensorEvent.timestamp;

        // calculate the proportion to earth gravity (9.81)
        float gX = x / SensorManager.GRAVITY_EARTH;
        float gY = y / SensorManager.GRAVITY_EARTH;
        float gZ = z / SensorManager.GRAVITY_EARTH;

        // calculate gForce (if close by 1 -> no movement)
        float gForce = (float)Math.sqrt(gX*gX + gY*gY + gZ*gZ);

        if (gForce > SHAKE_THRESHOLD_GRAVITY) {
            long now = System.currentTimeMillis();

            // ignore the shake event, when it is too close to each other (<500ms)
            if(shakeTimestamp + SHAKE_SLOP_TIME_MS > now)
            {
                shakeCount++;
            }

            return shakeCount;

        } else {
            return shakeCount;
        }
    }
}
