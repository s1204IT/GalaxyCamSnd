package me.s1204.galaxycamsnd;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.provider.Settings;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicReference;

public class CameraActivity extends Activity {

    protected static final String CAMERA_FORCED_SHUTTERSOUND_KEY = "csc_pref_camera_forced_shuttersound_key";
    protected static final String CAMERA_PACKAGE = "com.sec.android.app.camera";
    protected static final String CAMERA_ACTIVITY = CAMERA_PACKAGE + ".Camera";
    protected static final String PACKAGE_PREFIX = "package:";

    protected static void doDisable(Context context, boolean status) {
        if (Settings.System.canWrite(context)) {
            Settings.System.putInt(context.getContentResolver(), CAMERA_FORCED_SHUTTERSOUND_KEY, status ? 0 : 1);
        }
    }

    protected static void makeToast(Context context, int resId) {
        Toast.makeText(context, context.getString(resId), Toast.LENGTH_SHORT).show();
    }

    /** @noinspection CallToPrintStackTrace*/
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finishAndRemoveTask();
        AtomicReference<Intent> intent = new AtomicReference<>();
        if (Settings.System.canWrite(this)) {
            try {
                doDisable(this, true);
                //noinspection DanglingJavadoc
                if (Settings.Global.getInt(getContentResolver(), Settings.Global.MODE_RINGER) == 2 // サウンドモード
                        && Settings.System.getInt(getContentResolver(), "volume_system_speaker") > 0 // システム音量が有効
                        && Settings.Global.getInt(getContentResolver(), "zen_mode") == 0 // 通知をミュートが無効
                        /**
                         * この式だと、
                         * サウンドモード：サウンド
                         * システム音量：１以上
                         * 通知をミュート：有効
                         * >>>[通知をミュート]中に許可 の アラームとサウンド：タッチ操作音 がオン
                         * この状態でもシャッター音が鳴ってしまうが、API が @hide なので仕方無い。
                         */
                ) {
                    makeToast(this, R.string.ring_mode_warn);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                makeToast(this, R.string.failed_write_value);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                makeToast(this, R.string.failed_get_value);
            }
            try {
                 intent.set(new Intent().setClassName(CAMERA_PACKAGE, CAMERA_ACTIVITY));
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                makeToast(this, R.string.failed_launch_activity);
            }
        } else {
            makeToast(this, R.string.request_write_permission);
            intent.set(new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse(PACKAGE_PREFIX + getPackageName())).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
        startActivity(intent.get());
    }
}
