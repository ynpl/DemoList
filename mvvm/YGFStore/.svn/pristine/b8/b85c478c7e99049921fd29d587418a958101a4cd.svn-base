package com.neufmer.ygfstore.ui.main.fragment.task;

import android.annotation.SuppressLint;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.neufmer.ygfstore.bean.TaskBean;
import com.neufmer.ygfstore.ui.patrol_task.PatrolTaskActivity;
import com.wangxing.code.mvvm.base.ItemViewModel;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;

/**
 * Created by WangXing on 2019/6/13.
 */
public class TaskItemViewModel extends ItemViewModel<TaskFragmentViewModel> {

    public TaskBean mData;

    public ObservableField<String> assignees = new ObservableField<>("");

    @SuppressLint("SimpleDateFormat")
    TaskItemViewModel(@NonNull TaskFragmentViewModel viewModel, TaskBean data) {
        super(viewModel);
        mData = data;
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < mData.getAssignees().size(); i++) {
            if (i == mData.getAssignees().size() - 1) {
                buffer.append(mData.getAssignees().get(i).getName());
            } else {
                buffer.append(mData.getAssignees().get(i).getName());
                buffer.append("、");
            }
        }
        assignees.set(buffer.toString());

    }

    public BindingCommand viewTask = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putInt("taskId", mData.getId());
            viewModel.startActivity(PatrolTaskActivity.class, bundle);

        }
    });


}
