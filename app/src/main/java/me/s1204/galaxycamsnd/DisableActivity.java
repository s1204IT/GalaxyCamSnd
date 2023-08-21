package me.s1204.galaxycamsnd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.provider.Settings;
import android.widget.Toast;

public class DisableActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finishAndRemoveTask();
        if (Settings.System.canWrite(this)) {
            Settings.System.putInt(getContentResolver(), "csc_pref_camera_forced_shuttersound_key", 1);
            Toast.makeText(this, R.string.enabled, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, R.string.write, Toast.LENGTH_LONG).show();
            startActivity(new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" + getPackageName())).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }
}
