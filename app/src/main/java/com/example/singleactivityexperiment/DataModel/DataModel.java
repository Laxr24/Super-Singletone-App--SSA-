package com.example.singleactivityexperiment.DataModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataModel extends ViewModel {
    public static MutableLiveData<Long> counter = new MutableLiveData<>();
    public static MutableLiveData<Boolean> isActive_home = new MutableLiveData<>(false);
}
