package me.s1204.galaxycamsnd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.provider.Settings;
import android.widget.Toast;

public class CameraActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent;
        finishAndRemoveTask();
        if (Settings.System.canWrite(this)) {
            Settings.System.putInt(getContentResolver(), "csc_pref_camera_forced_shuttersound_key", 0);
            intent = new Intent().setClassName("com.sec.android.app.camera", "com.sec.android.app.camera.Camera");
        } else {
            Toast.makeText(this, R.string.write, Toast.LENGTH_LONG).show();
            intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" + getPackageName())).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);
    }
}
