package com.neufmer.ygfstore.ui.addtask.changeaddress;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;

import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.toolbar.ToolbarViewModel;
import com.wangxing.code.mvvm.base.event.SingleLiveEvent;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;
import com.wangxing.code.mvvm.utils.ToastUtils;

public class ChangeAddressViewModel extends ToolbarViewModel {


    public SingleLiveEvent<Void> showChooseAddressDialogUC = new SingleLiveEvent<Void>();

    public ObservableField<String> pvText = new ObservableField<>("");

    public ChangeAddressViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        setTitleTextRes(R.string.change_address);
        setLeftCloseStyle();
        setRightTextVisible(View.VISIBLE);
        setRightHasSelect(false);

    }

    /**
     * toolbar右边文字点击事件
     */
    @Override
    protected void rightTextOnClick() {
        super.rightTextOnClick();
        ToastUtils.showShort("完成");
    }

    /**
     * 显示选择地区的dialog
     */
    public BindingCommand showChooseAddressDialogClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            showChooseAddressDialogUC.call();
        }
    });


}
