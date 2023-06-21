package com.example.singleactivityexperiment.MyThreads;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

import com.example.singleactivityexperiment.DataModel.DataModel;
import com.example.singleactivityexperiment.Services.MyCounterService;

public class MyThread {
    private static MyThread instance;
    private static final String TAG = "msg";
    static Context context;
    private static Thread thread;

    private MyThread() {
        // Private constructor to prevent instantiation from outside
    }

    public static synchronized MyThread getInstance(Context app_context) {
        if (instance == null) {
            instance = new MyThread();
        }
        context = app_context;
        return instance;
    }

    public void startThread() {
        if (thread == null) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, "Thread started: "+Thread.currentThread().getName());
                    timer.start();
                }
            });
            thread.start();
        }
    }

        CountDownTimer timer = new CountDownTimer(20000, 400) {
        @Override
        public void onTick(long millisUntilFinished) {
            DataModel.counter.setValue(millisUntilFinished/1000);
        }

        @Override
        public void onFinish() {
            Toast.makeText(context, "Counter ended!", Toast.LENGTH_SHORT).show();
            Intent counterService = new Intent(context, MyCounterService.class);
            context.stopService(counterService);
            DataModel.isActive_home.setValue(true);
            timer.cancel();
        }
    };
}

