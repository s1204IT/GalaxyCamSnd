package me.s1204.galaxycamsnd;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import static android.content.Intent.*;
import static android.content.pm.PackageManager.*;
import static android.net.Uri.parse;

import static me.s1204.galaxycamsnd.CameraActivity.*;

import java.util.concurrent.atomic.AtomicReference;

public class DisableActivity extends Activity {

    private void ofDisable(Class<?> clazz) {
        // for でループさせると分割クラスが呼び出されるため使用不可
        getPackageManager().setComponentEnabledSetting(new ComponentName(this, clazz), COMPONENT_ENABLED_STATE_DISABLED, DONT_KILL_APP);
    }

    private void makeToast(int resId) {
        runOnUiThread(() -> Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show());
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
                        AtomicReference<Intent> intent = new AtomicReference<>();
                        if (Settings.System.canWrite(this)) {
                            // シャッター音を強制化
                            doDisable(this, false);
                            makeToast(R.string.shutter_sound_enabled);

                            // クラス無効化
                            ofDisable(CameraActivity.class);
                            ofDisable(BootCompletedReceiver.class);
                            ofDisable(DisableActivity.class);

                            // アンインストールを要求
                            intent.set(new Intent(ACTION_DELETE).setData(parse(PACKAGE_PREFIX + getPackageName())));
                        } else {
                            finishAndRemoveTask();
                            makeToast(R.string.request_write_permission);
                            intent.set(new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, parse(PACKAGE_PREFIX + getPackageName())).setFlags(FLAG_ACTIVITY_NEW_TASK));
                        }
                        startActivity(intent.get());
                    })
                    // "No"
                    .setNegativeButton(R.string.no, (d, s) -> finish())
                    .show();
        } catch (NameNotFoundException ignored) {
        }

    }
}
