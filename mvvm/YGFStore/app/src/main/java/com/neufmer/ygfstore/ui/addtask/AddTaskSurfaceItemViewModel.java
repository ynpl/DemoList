package com.neufmer.ygfstore.ui.addtask;

import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.neufmer.ygfstore.bean.InspectionsheetsBean;
import com.wangxing.code.mvvm.base.ItemViewModel;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;

/**
 * Created by WangXing on 2019/6/28.
 */
public class AddTaskSurfaceItemViewModel extends ItemViewModel<AddTaskViewModel> {

    public ObservableBoolean selectFlag = new ObservableBoolean(false);

    public InspectionsheetsBean mData;

    public AddTaskSurfaceItemViewModel(@NonNull AddTaskViewModel viewModel, InspectionsheetsBean data) {
        super(viewModel);
        mData = data;
    }

    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            int position = viewModel.surfaceItems.indexOf(AddTaskSurfaceItemViewModel.this);
            for (int i = 0; i < viewModel.surfaceItems.size(); i++) {
                if (position == i) {
                    viewModel.surfaceItems.get(i).selectFlag.set(true);
                } else {
                    viewModel.surfaceItems.get(i).selectFlag.set(false);
                }
            }
        }
    });

}
