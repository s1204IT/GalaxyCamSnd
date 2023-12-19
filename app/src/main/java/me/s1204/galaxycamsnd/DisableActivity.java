package me.s1204.galaxycamsnd;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import static android.content.Intent.*;
import static android.content.pm.PackageManager.*;
import static android.net.Uri.parse;
import static android.provider.Settings.*;
import static android.provider.Settings.System.*;
import static android.widget.Toast.*;

public class DisableActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finishAndRemoveTask();
        if (canWrite(this)) {
            putInt(
              getContentResolver(),
              "csc_pref_camera_forced_shuttersound_key",
              1
            );
            makeText(this, R.string.enabled, LENGTH_LONG).show();
            getPackageManager().setComponentEnabledSetting(
              new ComponentName(this, BootCompletedReceiver.class),
              COMPONENT_ENABLED_STATE_DISABLED,
              DONT_KILL_APP
            );
            startActivity(
              new Intent(ACTION_DELETE).setData(
                parse("package:" + getPackageName())
              )
            );
        } else {
            makeText(this, R.string.write, LENGTH_LONG).show();
            startActivity(
              new Intent(
                ACTION_MANAGE_WRITE_SETTINGS,
                parse("package:" + getPackageName())
              ).setFlags(FLAG_ACTIVITY_NEW_TASK)
            );
        }
    }
}
