package com.example.singleactivityexperiment.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.singleactivityexperiment.MyThreads.MyThread;

public class MyCounterService extends Service {
    private static final String TAG = "msg";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MyThread thread = MyThread.getInstance(this);
        thread.startThread();
        return START_NOT_STICKY;
    }

//    CountDownTimer timer = new CountDownTimer(20000, 400) {
//        @Override
//        public void onTick(long millisUntilFinished) {
//            DataModel.counter.setValue(millisUntilFinished/1000);
//        }
//
//        @Override
//        public void onFinish() {
//            Toast.makeText(MyCounterService.this, "Counter ended!", Toast.LENGTH_SHORT).show();
//            stopSelf();
//            timer.cancel();
//        }
//    };

    @Override
    public void onDestroy() {
        stopSelf();
        Log.i(TAG, "onDestroy: Service destroyed");
        super.onDestroy();
    }

    public static void startService(Context context){
        Intent counterService = new Intent(context, MyCounterService.class);
        context.startService(counterService);
    }
}
