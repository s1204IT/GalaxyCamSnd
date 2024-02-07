package me.s1204.galaxycamsnd;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

public class BootCompletedReceiver extends BroadcastReceiver {
    public void onReceive(Context c, Intent i) {
        if (Settings.System.canWrite(c)) {
            Settings.System.putInt(c.getContentResolver(), CameraActivity.shutter_sound_volume, 0);
        }
    }
}
