package me.s1204.GalaxyCamSnd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import static android.content.pm.PackageManager.PERMISSION_DENIED;
import static android.net.Uri.parse;
import static android.provider.Settings.System.putInt;

public class DisableActivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    if (getApplication().checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") == PERMISSION_DENIED) {
      Intent grant = new Intent();
      grant.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      grant.setAction("android.settings.action.MANAGE_WRITE_SETTINGS");
      grant.setData(parse("package:me.s1204.GalaxyCamSnd"));
      startActivity(grant);
      super.onDestroy();
      finishAndRemoveTask();
    }
    super.onCreate(savedInstanceState);
    putInt(getContentResolver(), "csc_pref_camera_forced_shuttersound_key", 0);
    super.onDestroy();
    finishAndRemoveTask();
    startActivity((new Intent()).setClassName("com.sec.android.app.camera", "com.sec.android.app.camera.Camera"));
  }
}
