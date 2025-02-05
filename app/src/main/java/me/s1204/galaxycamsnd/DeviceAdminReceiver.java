package me.s1204.galaxycamsnd;

import android.content.Context;
import android.content.Intent;

import static me.s1204.galaxycamsnd.CameraActivity.*;

/** @noinspection NullableProblems*/
public class DeviceAdminReceiver extends android.app.admin.DeviceAdminReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        doDisable(context, true);
    }

    @Override
    public void onEnabled(Context context, Intent intent) {
        doDisable(context, true);
        makeToast(context, R.string.admin_receiver_status_enabled);
    }

}
