package com.neufmer.ygfstore.ui.addtask;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.neufmer.ygfstore.bean.GroupsUsersBean;
import com.wangxing.code.mvvm.base.ItemViewModel;
import com.wangxing.code.mvvm.binding.command.BindingCommand;
import com.wangxing.code.mvvm.binding.command.BindingConsumer;

/**
 * Created by WangXing on 2019/6/20.
 * 子层
 */
public class AddTaskPersonnelGroupItemViewModel extends ItemViewModel<AddTaskViewModel> {


    public GroupsUsersBean.UsersBean itemData ;

    public ObservableBoolean itemSelect = new ObservableBoolean(false);

    public AddTaskPersonnelItemViewModel mParentViewModel;

    public AddTaskPersonnelGroupItemViewModel(@NonNull AddTaskViewModel viewModel, GroupsUsersBean.UsersBean data, AddTaskPersonnelItemViewModel parentViewModel) {
        super(viewModel);
        itemData = data;
        mParentViewModel = parentViewModel;
    }

    public BindingCommand<Boolean> onCheckedChangedCommand = new BindingCommand<Boolean>(new BindingConsumer<Boolean>() {
        @Override
        public void call(Boolean aBoolean) {
            itemSelect.set(aBoolean);
            if (aBoolean) {
                mParentViewModel.count++;
                if (mParentViewModel.count == mParentViewModel.items.size()) {
                    mParentViewModel.parentSelectFlag.set(true);
                }
            }else {
                if (mParentViewModel.count!=0) {
                    mParentViewModel.count --;
                }
                mParentViewModel.parentSelectFlag.set(false);
            }
        }
    });

}
