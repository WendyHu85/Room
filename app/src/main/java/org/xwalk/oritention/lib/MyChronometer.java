package org.xwalk.oritention.lib;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.Chronometer;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

public class MyChronometer extends Chronometer implements LifecycleEventObserver {
    private long elapsedTime;
    public MyChronometer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_CREATE) {

        }
        if (event == Lifecycle.Event.ON_START) {

        }
        if (event == Lifecycle.Event.ON_PAUSE) {
            elapsedTime = SystemClock.elapsedRealtime()-getBase();
            stop();
        }
        if (event == Lifecycle.Event.ON_RESUME) {
            setBase(SystemClock.elapsedRealtime()-elapsedTime);
            start();
        }
    }
}
