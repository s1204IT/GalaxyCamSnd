package me.s1204.galaxycamsnd;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.provider.Settings;
import android.widget.Toast;

public class CameraActivity extends Activity {

    protected static final String shutter_sound_volume = "csc_pref_camera_forced_shuttersound_key";
    protected static final String camera_package = "com.sec.android.app.camera";
    protected static final String camera_activity = camera_package + ".Camera";
    protected static final String package_prefix = "package:";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finishAndRemoveTask();
        Intent intent = null;
        if (Settings.System.canWrite(this)) {
            try {
                Settings.System.putInt(getContentResolver(), shutter_sound_volume, 0);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                Toast.makeText(this, R.string.failed_write_value, Toast.LENGTH_LONG).show();
            }
            try {
                 intent = new Intent().setClassName(camera_package, camera_activity);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, R.string.failed_launch_activity, Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, R.string.request_write_permission, Toast.LENGTH_LONG).show();
            intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse(package_prefix + getPackageName())).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);
    }
}
