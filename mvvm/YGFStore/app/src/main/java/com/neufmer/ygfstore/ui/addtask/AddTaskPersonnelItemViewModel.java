package com.neufmer.ygfstore.ui.addtask;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.neufmer.ygfstore.BR;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.bean.GroupsUsersBean;
import com.wangxing.code.mvvm.base.ItemViewModel;
import com.wangxing.code.mvvm.binding.command.BindingCommand;
import com.wangxing.code.mvvm.binding.command.BindingConsumer;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * recyclerview嵌套父层
 */
public class AddTaskPersonnelItemViewModel extends ItemViewModel<AddTaskViewModel> {

    public GroupsUsersBean mData;

    public int count = 0;

    public ObservableBoolean parentSelectFlag = new ObservableBoolean(false);

    public ObservableList<AddTaskPersonnelGroupItemViewModel> items = new ObservableArrayList<>();

    public ItemBinding<AddTaskPersonnelGroupItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_choose_personnel_group_item);


    public AddTaskPersonnelItemViewModel(@NonNull AddTaskViewModel viewModel, GroupsUsersBean data) {
        super(viewModel);
        mData = data;

        for (GroupsUsersBean.UsersBean user : mData.getUsers()) {
            items.add(new AddTaskPersonnelGroupItemViewModel(viewModel, user, this));
        }
    }

    public BindingCommand<Boolean> checkedChangedCommand = new BindingCommand<Boolean>(new BindingConsumer<Boolean>() {
        @Override
        public void call(Boolean aBoolean) {
            for (AddTaskPersonnelGroupItemViewModel item : items) {
                if (aBoolean) {//全选
                    if (!item.itemSelect.get()) {
                        item.itemSelect.set(aBoolean);
                    }
                }else {//全不选
                    if (count == items.size()){
                        if (item.itemSelect.get()) {
                            item.itemSelect.set(aBoolean);
                        }
                    }
                }
            }
        }
    });


}
