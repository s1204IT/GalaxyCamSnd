package me.s1204.GalaxyCamSnd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import static android.net.Uri.parse;
import static android.provider.Settings.System.canWrite;
import static android.provider.Settings.System.putInt;

public class DisableActivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    if (!canWrite(this)) {
      startActivity(new Intent("android.settings.action.MANAGE_WRITE_SETTINGS", parse("package:me.s1204.GalaxyCamSnd")));
      finishAndRemoveTask();
    }
    super.onCreate(savedInstanceState);
    putInt(getContentResolver(), "csc_pref_camera_forced_shuttersound_key", 0);
    finishAndRemoveTask();
    startActivity((new Intent()).setClassName("com.sec.android.app.camera", "com.sec.android.app.camera.Camera"));
  }
}
