package com.wangxing.mvvm.core.binding.viewadapter.checkbox;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.wangxing.mvvm.core.binding.command.BindingCommand;

import androidx.databinding.BindingAdapter;

public class ViewAdapter {
    /**
     * @param bindingCommand //绑定监听
     */
    @BindingAdapter(value = {"onCheckBoxChangedCommand"}, requireAll = false)
    public static void onCheckBoxChangedCommand(final CheckBox checkBox, final BindingCommand<Boolean> bindingCommand) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                bindingCommand.execute(b);
            }
        });
    }
}
