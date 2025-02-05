package me.s1204.galaxycamsnd;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.provider.Settings;
import android.widget.Toast;

public class CameraActivity extends Activity {

    protected static final String CAMERA_FORCED_SHUTTERSOUND_KEY = "csc_pref_camera_forced_shuttersound_key";
    protected static final String CAMERA_PACKAGE = "com.sec.android.app.camera";
    protected static final String CAMERA_ACTIVITY = CAMERA_PACKAGE + ".Camera";
    protected static final String PACKAGE_PREFIX = "package:";

    /** @noinspection CallToPrintStackTrace*/
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finishAndRemoveTask();
        Intent intent = null;
        if (Settings.System.canWrite(this)) {
            try {
                Settings.System.putInt(getContentResolver(), CAMERA_FORCED_SHUTTERSOUND_KEY, 0);
                if (Settings.Global.getInt(getContentResolver(), Settings.Global.MODE_RINGER) == 2) {
                    Toast.makeText(this, R.string.ring_mode_warn, Toast.LENGTH_SHORT).show();
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                Toast.makeText(this, R.string.failed_write_value, Toast.LENGTH_LONG).show();
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, R.string.failed_get_value, Toast.LENGTH_LONG).show();
            }
            try {
                 intent = new Intent().setClassName(CAMERA_PACKAGE, CAMERA_ACTIVITY);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, R.string.failed_launch_activity, Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, R.string.request_write_permission, Toast.LENGTH_LONG).show();
            intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse(PACKAGE_PREFIX + getPackageName())).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);
    }
}
