package com.example.powerreceiver

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val CUSTOM_ACTION = BuildConfig.APPLICATION_ID + " ACTION_TEST"
    }

    private val powerBroadcast = PowerBroadcast()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)

       
            /**Register the receiver using the activity context
             * This is a System Broadcast
             */
            this.registerReceiver(powerBroadcast, filter)

        /**
         * LocalBroadcastManager is used to register and send custom broadcast within the same app
         */
        LocalBroadcastManager.getInstance(this).registerReceiver(powerBroadcast, IntentFilter(
            CUSTOM_ACTION)
        )

        /**
         * send a custom broadcast when a button is clicked
         */
        button.setOnClickListener {
            val intent = Intent(CUSTOM_ACTION)
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        }
    }

    override fun onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(powerBroadcast)
        super.onDestroy()
    }
}