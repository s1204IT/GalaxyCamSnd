package me.s1204.galaxycamsnd;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

public class BootCompletedReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (Settings.System.canWrite(context)) {
            Settings.System.putInt(context.getContentResolver(), CameraActivity.camera_forced_shuttersound_key, 0);
        }
    }
}
