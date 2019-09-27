package com.wangxing.code;

import android.app.Application;
import android.util.Log;

import com.wangxing.mvvm.core.base.BaseViewModel;
import com.wangxing.mvvm.core.binding.command.BindingAction;
import com.wangxing.mvvm.core.binding.command.BindingCommand;
import com.wangxing.mvvm.core.binding.command.BindingConsumer;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;

public class MainActivityViewModel extends BaseViewModel {

    private static final String TAG = "MainActivityViewModel";

    //    String s = "1";
    public ObservableField<String> stringObservableField = new ObservableField<>("乌老二");
    //    int i = 0
    public ObservableField<String> edit = new ObservableField<>();

    public ObservableArrayList<String> strings = new ObservableArrayList<>();

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

    }


    public void onClickBtn() {
        Log.e(TAG, "onClickBtn: " + edit.get());
        String s = edit.get();
        stringObservableField.set(s);

    }

    public BindingCommand<Void> command = new BindingCommand<Void>(new BindingAction() {
        @Override
        public void call() {
            Log.e(TAG, "onClickBtn: " + edit.get());
            String s = edit.get();
            stringObservableField.set(s);
        }
    });

    public BindingCommand<Boolean> check = new BindingCommand<Boolean>(new BindingConsumer<Boolean>() {
        @Override
        public void call(Boolean s) {

        }
    });


}
