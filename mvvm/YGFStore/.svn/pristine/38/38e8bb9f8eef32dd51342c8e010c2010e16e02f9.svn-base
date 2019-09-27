package com.neufmer.ygfstore.ui.task_detail.failreasonsitem;

import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.neufmer.ygfstore.bean.InspectionsheetResultBean;
import com.neufmer.ygfstore.ui.task_detail.TaskDetailViewModel;
import com.wangxing.code.mvvm.base.ItemViewModel;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;

/**
 * Created by WangXing on 2019/7/11.
 */
public class TaskDetailFailreasonsChildsItemViewModel extends ItemViewModel<TaskDetailViewModel> {

    public InspectionsheetResultBean.FailreasonsBean.ChildsBean mData;

    public ObservableBoolean selectFlag = new ObservableBoolean(false);

    public TaskDetailFailreasonsChildsItemViewModel(@NonNull TaskDetailViewModel viewModel, InspectionsheetResultBean.FailreasonsBean.ChildsBean data) {
        super(viewModel);
        mData = data;
    }

    public BindingCommand onItemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

            int position = viewModel.failreasonsChildsItems.indexOf(TaskDetailFailreasonsChildsItemViewModel.this);
            boolean b = viewModel.failreasonsChildsItems.get(position).selectFlag.get();
            viewModel.failreasonsChildsItems.get(position).selectFlag.set(!b);
        }
    });

}
