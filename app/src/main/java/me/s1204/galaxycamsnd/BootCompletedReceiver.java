package me.s1204.galaxycamsnd;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootCompletedReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        CameraActivity.doDisable(context, true);
    }
}
