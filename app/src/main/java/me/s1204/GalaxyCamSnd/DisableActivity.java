package me.s1204.GalaxyCamSnd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import static android.provider.Settings.System.putInt;

public class DisableActivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    putInt(getContentResolver(), "csc_pref_camera_forced_shuttersound_key", 0);
    super.onDestroy();
    finishAndRemoveTask();
    Intent camera=new Intent();
    camera.setClassName("com.sec.android.app.camera", "com.sec.android.app.camera.Camera");
    startActivity(camera);
  }
}
