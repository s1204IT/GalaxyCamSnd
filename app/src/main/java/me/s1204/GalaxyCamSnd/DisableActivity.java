package me.s1204.GalaxyCamSnd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import static android.net.Uri.parse;
import static android.provider.Settings.System.canWrite;
import static android.provider.Settings.System.putInt;

public class DisableActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finishAndRemoveTask();
        if (!canWrite(getApplicationContext())) {
            startActivity(new Intent("android.settings.action.MANAGE_WRITE_SETTINGS", parse("package:me.s1204.GalaxyCamSnd")).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        } else {
            // TODO: targetSdk の問題の解決 <- 本来 try する必要は無い
            try {
                // TODO: タイルサービスと同期
                putInt(getContentResolver(), "csc_pref_camera_forced_shuttersound_key", 0);
            } finally {
                startActivity((new Intent()).setClassName("com.sec.android.app.camera", "com.sec.android.app.camera.Camera"));
            }
        }
    }
}
