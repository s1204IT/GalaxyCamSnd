package me.s1204.GalaxyCamSnd;

import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

import static android.provider.Settings.System.putInt;

public class OneTapService extends TileService {
    @Override
    public void onClick() {
        super.onClick();
        Tile tile = getQsTile();
        int state = tile.getState();
        if (state != Tile.STATE_INACTIVE) {
            if (state == Tile.STATE_ACTIVE) {
                putInt(getContentResolver(), "csc_pref_camera_forced_shuttersound_key", 1);
            }
            tile.setState(Tile.STATE_INACTIVE);
        } else {
            // TODO: BootCompletedReceiverの実装
            putInt(getContentResolver(), "csc_pref_camera_forced_shuttersound_key", 0);
            tile.setState(Tile.STATE_ACTIVE);
        }
        tile.updateTile();
    }
}
