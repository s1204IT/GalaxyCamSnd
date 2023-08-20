package me.s1204.galaxycamsnd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.provider.Settings;

public class DeleteActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finishAndRemoveTask();
        try {
            Settings.System.putInt(getContentResolver(), "csc_pref_camera_forced_shuttersound_key", 1);
        } finally {
            startActivity(new Intent(Intent.ACTION_DELETE).setData(Uri.parse("package:" + getPackageName())));
        }
    }
}
