package me.s1204.galaxycamsnd;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

/** @noinspection NullableProblems*/
public class DeviceAdminReceiver extends android.app.admin.DeviceAdminReceiver {

    void doDisable(Context context) {
        if (Settings.System.canWrite(context)) {
            Settings.System.putInt(context.getContentResolver(), CameraActivity.CAMERA_FORCED_SHUTTERSOUND_KEY, 0);
        }
    }

    void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        doDisable(context);
    }

    @Override
    public void onEnabled(Context context, Intent intent) {
        doDisable(context);
        showToast(context, context.getString(R.string.admin_receiver_status_enabled));
    }

    @Override
    public CharSequence onDisableRequested(Context context, Intent intent) {
        context.startActivity(new Intent(context, DisableActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        return context.getString(R.string.admin_receiver_status_disable_warning);
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        context.startActivity(new Intent(context, DisableActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        showToast(context, context.getString(R.string.admin_receiver_status_disabled));
    }

}
