package com.neufmer.ygfstore.binding.progressbutton;

import android.databinding.BindingAdapter;

import com.neufmer.ygfstore.view.ProgressButton;
import com.wangxing.code.mvvm.binding.command.BindingCommand;

/**
 * Created by WangXing on 2019/6/24.
 */
public class ViewAdapter {

    @BindingAdapter(value = {"onProgressClickListener"}, requireAll = false)
    public static void onProgressClickListener(ProgressButton view, final BindingCommand<String> listener) {
        view.setOnProgressClickListener(new ProgressButton.OnProgressClickListener() {
            @Override
            public void clickProgress() {
                if (listener != null) {
                    listener.execute("progress");
                }
            }

            @Override
            public void clickPause() {
                if (listener != null) {
                    listener.execute("pause");
                }
            }

            @Override
            public void clickResume() {
                if (listener != null) {
                    listener.execute("resume");
                }
            }

            @Override
            public void clickFinish() {
                if (listener != null) {
                    listener.execute("finish");
                }
            }
        });
    }


}
