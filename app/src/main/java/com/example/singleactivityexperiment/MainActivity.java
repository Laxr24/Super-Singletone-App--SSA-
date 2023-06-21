package com.example.singleactivityexperiment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import com.example.singleactivityexperiment.DataModel.DataModel;
import com.example.singleactivityexperiment.Services.MyCounterService;


public class MainActivity extends AppCompatActivity {

    Button nextBtn, backBtn;
    TextView counterTxt;
    private static final String TAG = "msg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Instantiate service
        super.onCreate(savedInstanceState);
        new Handler(Looper.getMainLooper()).post(()->{
            setContentView(R.layout.activity_main);
            nextBtnEvent();
            new ViewModelProvider(this).get(DataModel.class);
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onCreate: On Start (Home View)");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onCreate: On Destroy");
    }

    private void nextBtnEvent(){
        nextBtn = findViewById(R.id.nextBtn);
        Log.i(TAG, "onCreate: On Create");
        nextBtn.setOnClickListener(v->{
            MyCounterService.startService(this);
            Log.i(TAG, "onCreate: NextBtn clicked");
            setContentView(R.layout.second_screen);
            backBtnEvent();
            // Test code
        });
    }

    private void backBtnEvent(){
        backBtn = findViewById(R.id.backBtn);
        counterTxt = findViewById(R.id.counterTxt);
        DataModel.isActive_home.setValue(false);

        backBtn.setOnClickListener(vi->{
            DataModel.isActive_home.setValue(false);
            setContentView(R.layout.activity_main);
            nextBtnEvent();
        });

        DataModel.counter.observe(this, aLong -> counterTxt.setText(String.valueOf(aLong)));
        DataModel.isActive_home.observe(this, aBoolean -> Log.i(TAG, "Active Livedata: "+aBoolean));

    }


}