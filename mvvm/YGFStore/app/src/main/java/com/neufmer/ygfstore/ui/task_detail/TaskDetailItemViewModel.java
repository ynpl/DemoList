package com.neufmer.ygfstore.ui.task_detail;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.neufmer.ygfstore.bean.InspectionsheetResultBean;
import com.wangxing.code.mvvm.base.ItemViewModel;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;

/**
 * Created by WangXing on 2019/6/26.
 */
public class TaskDetailItemViewModel extends ItemViewModel<TaskDetailViewModel> {

    public ObservableBoolean selectFlag = new ObservableBoolean(false);

    public InspectionsheetResultBean.InspectionQuestionsBean mData;

    public TaskDetailItemViewModel(@NonNull TaskDetailViewModel viewModel, InspectionsheetResultBean.InspectionQuestionsBean data) {
        super(viewModel);
        mData = data;
    }

    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            int position = viewModel.items.indexOf(TaskDetailItemViewModel.this);
            for (int i = 0; i < viewModel.items.size(); i++) {
                if (position == i) {
                    viewModel.items.get(i).selectFlag.set(true);
                } else {
                    viewModel.items.get(i).selectFlag.set(false);
                }
            }
            viewModel.switchPagerUC.setValue(position);
        }
    });


}
