
package com.example.android.dessertstarter

import android.os.Handler
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import timber.log.Timber


class DessertTimer(lifecycle: Lifecycle) : LifecycleObserver {

    // The number of seconds counted since the timer started
    var secondsCount = 0


    private var handler = Handler()
    private lateinit var runnable: Runnable

    init {
        // Add this as a lifecycle Observer, which allows for the class to react to changes in this
        // activity's lifecycle state.
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startTimer() {

        runnable = Runnable {
            secondsCount++
            Timber.i("Timer is at : $secondsCount")

            handler.postDelayed(runnable, 1000)
        }

        // This is what initially starts the timer
        handler.postDelayed(runnable, 1000)


    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopTimer() {
        // Removes all pending posts of runnable from the handler's queue, effectively stopping the
        // timer
        handler.removeCallbacks(runnable)
    }
}