package me.s1204.galaxycamsnd;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import static android.provider.Settings.System.*;

public class BootCompletedReceiver extends BroadcastReceiver {
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    public void onReceive(Context c, Intent i) {
        if (canWrite(c)) {
            putInt(c.getContentResolver(), "csc_pref_camera_forced_shuttersound_key", 0);
        }
    }
}
