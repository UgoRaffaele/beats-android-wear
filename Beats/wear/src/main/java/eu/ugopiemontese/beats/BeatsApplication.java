package eu.ugopiemontese.beats;

import android.Manifest;
import android.util.Log;
import com.orm.SugarApp;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;

public class BeatsApplication extends SugarApp implements OnRequestPermissionsResultCallback {

    private static final String TAG = BeatsApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        boolean isGranted = false;
        for (int i = 0; i < grantResults.length; i++)
            if (permissions[i].equals(Manifest.permission.BODY_SENSORS) && (grantResults[i] == PackageManager.PERMISSION_GRANTED))
                isGranted = true;

        if (!isGranted)
            Log.d(TAG, "BODY_SENSORS permission not granted. App cannot work properly.");

    }

}