package com.ndta.apptracker.services

import android.content.Intent
import androidx.lifecycle.LifecycleService
import com.ndta.apptracker.utils.Constants
import timber.log.Timber

class TrackingService : LifecycleService() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                Constants.ACTION_START_OR_RESUME_SERVICE -> {
                    Timber.d("Started or Resumed Service")
                }
                Constants.ACTION_PAUSE_SERVICE -> {
                    Timber.d("Pause Service")
                }
                Constants.ACTION_STOP_SERVICE -> {
                    Timber.d("Stop Service")
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }
}