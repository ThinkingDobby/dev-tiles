package com.example.devtiles

import android.provider.Settings
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.widget.Toast

class UsbDebugTileService : TileService() {

    override fun onStartListening() = refresh()

    override fun onClick() {
        // 잠금 화면에서 바로 켜지지 않도록 잠금 해제를 먼저 요구한다.
        if (isLocked) unlockAndRun(::toggle) else toggle()
    }

    private fun toggle() {
        try {
            Settings.Global.putInt(contentResolver, Settings.Global.ADB_ENABLED, if (isAdbEnabled()) 0 else 1)
        } catch (e: SecurityException) {
            Toast.makeText(this, R.string.permission_missing, Toast.LENGTH_LONG).show()
        }
        refresh()
    }

    private fun isAdbEnabled() =
        Settings.Global.getInt(contentResolver, Settings.Global.ADB_ENABLED, 0) == 1

    private fun refresh() {
        val tile = qsTile ?: return
        tile.state = if (isAdbEnabled()) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
        tile.updateTile()
    }
}
