package eu.ugopiemontese.beats;

import android.Manifest;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import eu.ugopiemontese.beats.utils.PermissionHelper;

import static android.hardware.SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM;

public class HRJobService extends JobService implements SensorEventListener {

    private static final String TAG = HRJobService.class.getSimpleName();
    public static final int PERM_REQUEST_BODY_SENSORS = 1;

    // Stop sensor after 30 seconds
    private final static int DELAY = 30;

    @Override
    public boolean onStartJob(JobParameters params) {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.BODY_SENSORS) != PackageManager.PERMISSION_GRANTED) {

            PermissionHelper.requestPermissions((BeatsApplication) getApplicationContext(),
                    new String[]{
                            Manifest.permission.BODY_SENSORS
                    },
                    PERM_REQUEST_BODY_SENSORS,
                    getString(R.string.notify_perm_title),
                    getString(R.string.notify_perm_body),
                    R.drawable.ic_security);

            jobFinished(params, true);

            return true;
        }

        final SensorManager mSensorManager = ((SensorManager) getSystemService(SENSOR_SERVICE));
        final Sensor mHeartRateSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        mSensorManager.registerListener(this, mHeartRateSensor, SensorManager.SENSOR_DELAY_NORMAL);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSensorManager.unregisterListener(HRJobService.this, mHeartRateSensor);
            }
        }, DELAY * 1000);

        jobFinished(params, false);

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        jobFinished(params, true);
        return false;
    }

    private String currentTimeStr() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        return df.format(c.getTime());
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if ((event.sensor.getType() == Sensor.TYPE_HEART_RATE) && (event.values.length > 0)) {

            for (float value: event.values) {

                String msg = currentTimeStr() + " " + (int) value;
                Log.d(TAG, msg);

                if (event.accuracy >= SENSOR_STATUS_ACCURACY_MEDIUM) {
                   // TODO : record value into DB
                }

            }

        } else {
            Log.d(TAG, "Unknown sensor type!");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        return;
    }

}
