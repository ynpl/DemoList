package com.neufmer.ygfstore.ui.task_detail.failreasonsitem;

import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.neufmer.ygfstore.bean.InspectionsheetResultBean;
import com.neufmer.ygfstore.ui.task_detail.TaskDetailItemViewModel;
import com.neufmer.ygfstore.ui.task_detail.TaskDetailViewModel;
import com.wangxing.code.mvvm.base.ItemViewModel;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;

/**
 * Created by WangXing on 2019/7/11.
 */
public class TaskDetailFailreasonsGroupItemViewModel extends ItemViewModel<TaskDetailViewModel> {

    public InspectionsheetResultBean.FailreasonsBean mData;

    public ObservableBoolean selectFlag = new ObservableBoolean(false);

    public TaskDetailFailreasonsGroupItemViewModel(@NonNull TaskDetailViewModel viewModel, InspectionsheetResultBean.FailreasonsBean data) {
        super(viewModel);
        mData = data;
    }

    public BindingCommand onItemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (!viewModel.failreasonsChildsItems.isEmpty()) {
                viewModel.failreasonsChildsItems.clear();
                for (InspectionsheetResultBean.FailreasonsBean.ChildsBean child : mData.getChilds()) {
                    child.setGroupId(mData.getGroupId());
                    viewModel.failreasonsChildsItems.add(new TaskDetailFailreasonsChildsItemViewModel(viewModel, child));
                }
            }

            int position = viewModel.failreasonsGroupItems.indexOf(TaskDetailFailreasonsGroupItemViewModel.this);
            for (int i = 0; i < viewModel.failreasonsGroupItems.size(); i++) {
                if (position == i) {
                    viewModel.failreasonsGroupItems.get(i).selectFlag.set(true);
                } else {
                    viewModel.failreasonsGroupItems.get(i).selectFlag.set(false);
                }
            }

        }
    });

}
