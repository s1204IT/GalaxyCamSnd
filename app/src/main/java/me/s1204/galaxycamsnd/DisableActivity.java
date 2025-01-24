package me.s1204.galaxycamsnd;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import static android.content.Intent.*;
import static android.content.pm.PackageManager.*;
import static android.net.Uri.parse;
import static android.widget.Toast.*;

public class DisableActivity extends Activity {

    private void ofDisable(Class<?> cls) {
        // for でループさせると分割クラスが呼び出されるため使用不可
        getPackageManager().setComponentEnabledSetting(new ComponentName(this, cls), COMPONENT_ENABLED_STATE_DISABLED, DONT_KILL_APP);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.enable_shutter_sound)
                    .setIcon(getPackageManager().getApplicationIcon(getPackageName()))
                    .setMessage(R.string.dialog)
                    // "Yes"
                    .setPositiveButton(R.string.yes, (d, s) -> {
                        if (Settings.System.canWrite(this)) {
                            // シャッター音を強制化
                            Settings.System.putInt(getContentResolver(), CameraActivity.camera_forced_shuttersound_key, 1);
                            makeText(this, R.string.shutter_sound_enabled, LENGTH_LONG).show();

                            // クラス無効化
                            ofDisable(CameraActivity.class);
                            ofDisable(BootCompletedReceiver.class);
                            ofDisable(DisableActivity.class);

                            // アンインストールを要求
                            startActivity(new Intent(ACTION_DELETE).setData(parse(CameraActivity.package_prefix + getPackageName())));
                        } else {
                            finishAndRemoveTask();
                            makeText(this, R.string.request_write_permission, LENGTH_LONG).show();
                            startActivity(new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, parse(CameraActivity.package_prefix + getPackageName())).setFlags(FLAG_ACTIVITY_NEW_TASK));
                        }
                    })
                    // "No"
                    .setNegativeButton(R.string.no, (d, s) -> finish())
                    .show();
        } catch (NameNotFoundException ignored) {
        }

    }
}
