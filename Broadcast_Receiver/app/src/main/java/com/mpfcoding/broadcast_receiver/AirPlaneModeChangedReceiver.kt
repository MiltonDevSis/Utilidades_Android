package com.mpfcoding.broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirPlaneModeChangedReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val isAirPlaneModeEnabled = intent?.getBooleanExtra("state", false) ?: return

        if (isAirPlaneModeEnabled){
            Toast.makeText(context, "Airplane mode enabled", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context, "Airplane mode disabled", Toast.LENGTH_LONG).show()
        }
    }
}