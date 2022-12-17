package me.s1204.GalaxyCamSnd;

import android.annotation.TargetApi;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

import static android.os.Build.VERSION_CODES.N;
import static android.provider.Settings.System.putInt;

@TargetApi(N)
public class OneTapService extends TileService {
    public void onClick() {
        Tile tile = getQsTile();
        switch (tile.getState()){
            case Tile.STATE_ACTIVE:
                putInt(getContentResolver(), "csc_pref_camera_forced_shuttersound_key", 1);
                tile.setState(Tile.STATE_INACTIVE);
                break;
            case Tile.STATE_INACTIVE:
                putInt(getContentResolver(), "csc_pref_camera_forced_shuttersound_key", 0);
                tile.setState(Tile.STATE_ACTIVE);
                break;
            default:
                break;
        }
        tile.updateTile();
    }
}
