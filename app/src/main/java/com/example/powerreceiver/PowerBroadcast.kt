package com.example.powerreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar

class PowerBroadcast : BroadcastReceiver() {
    companion object {
        const val CUSTOM_ACTION = BuildConfig.APPLICATION_ID + " ACTION_TEST"
    }

    override fun onReceive(p0: Context?, p1: Intent?) {
        val action = p1?.action

        if (action != null) {
            var msg = "unknown intent"

            when (action) {
                Intent.ACTION_POWER_CONNECTED -> msg = "connected"
                Intent.ACTION_POWER_DISCONNECTED -> msg = "Disconnected"
                CUSTOM_ACTION -> msg = "custom Broadcast received"
            }
            Toast.makeText(p0,msg,Toast.LENGTH_SHORT).show()
        }
    }
}