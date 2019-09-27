package com.neufmer.ygfstore.ui.addtask;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.bean.StoresBean;
import com.wangxing.code.mvvm.base.ItemViewModel;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;
import com.wangxing.code.mvvm.utils.ContextUtils;

public class AddTaskStoreItemViewModel extends ItemViewModel<AddTaskViewModel> {

    public ObservableBoolean selectFlag = new ObservableBoolean(false);

    public ObservableField<String> distance = new ObservableField<>("");

    public StoresBean mData;

    public AddTaskStoreItemViewModel(@NonNull AddTaskViewModel viewModel, StoresBean data) {
        super(viewModel);
        mData = data;
        int distance = mData.getDistance();
        if (distance == -1) {
            this.distance.set(ContextUtils.getContext().getString(R.string.add_task_activity_no_position));
        } else {
            this.distance.set(distance + "km");
        }

    }

    public BindingCommand onItemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            boolean flag = selectFlag.get();
            selectFlag.set(!flag);
        }
    });

}
